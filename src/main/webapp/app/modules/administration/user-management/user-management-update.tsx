import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { InputText } from 'primereact/inputtext';
import { InputNumber } from 'primereact/inputnumber';
import { classNames } from 'primereact/utils';
import { Dropdown } from 'primereact/dropdown';
import { InputSwitch } from 'primereact/inputswitch';
import { Button } from 'primereact/button';
import { MultiSelect } from 'primereact/multiselect';
import { useForm, Controller } from 'react-hook-form';
import { Translate, translate, isEmail, TranslatorContext } from 'react-jhipster';

import { languageArray } from 'app/config/translation';
import { getUser, getRoles, updateUser, createUser, reset as resetUser } from './user-management.reducer';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { defaultValue } from 'app/shared/model/user.model';

export const UserManagementUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { login } = useParams<'login'>();
  const isNew = login === undefined;

  useEffect(() => {
    if (isNew) {
      dispatch(resetUser());
    } else {
      dispatch(getUser(login));
    }
    dispatch(getRoles());
    return () => {
      dispatch(resetUser());
    };
  }, [login]);

  const handleClose = () => {
    navigate('/admin/user-management' + location.search);
  };

  const user = useAppSelector(state => state.userManagement.user);
  const loading = useAppSelector(state => state.userManagement.loading);
  const updating = useAppSelector(state => state.userManagement.updating);
  const authorities = useAppSelector(state => state.userManagement.authorities);
  const updateSuccess = useAppSelector(state => state.userManagement.updateSuccess);

  const {
    control,
    formState: { errors },
    handleSubmit,
    reset,
    trigger,
  } = useForm({
    defaultValues: defaultValue,
  });

  useEffect(() => {
    if (user) {
      reset(user);
    }
  }, [user, reset, TranslatorContext.context.locale]);

  const onSubmit = data => {
    // console.log('submit data:', data);
    if (isNew) {
      dispatch(createUser(data));
    } else {
      dispatch(updateUser(data));
    }
    reset();
  };

  useEffect(() => {
    // Only naviage on successful operation, otherwis it may cause incrorrect pagination.
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const getFormErrorMessage = name => {
    return errors[name] && <small className="p-error">{errors[name].message}</small>;
  };

  return (
    <div className="l-card">
      {loading ? (
        <p>Loading...</p>
      ) : (
        <>
          <h5>
            <Translate contentKey="userManagement.home.createOrEditLabel">Create or edit a User</Translate>
          </h5>

          <form onSubmit={handleSubmit(onSubmit)}>
            <div className="l-form">
              {!isNew && (
                <div>
                  <label htmlFor="id">
                    <Translate contentKey="global.field.id" />
                  </label>
                  <div>
                    <Controller
                      control={control}
                      name="id"
                      render={({ field }) => (
                        <InputNumber id={field.name} onChange={e => field.onChange(e.value)} value={field.value} disabled />
                      )}
                    />
                  </div>
                </div>
              )}
              <div>
                <label htmlFor="login">
                  <Translate contentKey="userManagement.login" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="login"
                    rules={{
                      required: {
                        value: true,
                        message: translate('register.messages.validate.login.required'),
                      },
                      pattern: {
                        value: /^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$/,
                        message: translate('register.messages.validate.login.pattern'),
                      },
                      minLength: {
                        value: 1,
                        message: translate('register.messages.validate.login.minlength'),
                      },
                      maxLength: {
                        value: 50,
                        message: translate('register.messages.validate.login.maxlength'),
                      },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('login');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('userManagement.help.login')}
                      />
                    )}
                  />
                  {getFormErrorMessage('login')}
                </div>
              </div>
              <div>
                <label htmlFor="firstName">
                  <Translate contentKey="userManagement.firstName" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="firstName"
                    rules={{
                      maxLength: {
                        value: 50,
                        message: translate('entity.validation.maxlength', { max: 50 }),
                      },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('firstName');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('userManagement.help.firstName')}
                      />
                    )}
                  />
                  {getFormErrorMessage('firstName')}
                </div>
              </div>
              <div>
                <label htmlFor="lastName">
                  <Translate contentKey="userManagement.lastName" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="lastName"
                    rules={{
                      maxLength: {
                        value: 50,
                        message: translate('entity.validation.maxlength', { max: 50 }),
                      },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('lastName');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('userManagement.help.lastName')}
                      />
                    )}
                  />
                  {getFormErrorMessage('lastName')}
                </div>
              </div>
              <div>
                <label htmlFor="email">
                  <Translate contentKey="userManagement.email" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="email"
                    rules={{
                      required: {
                        value: true,
                        message: translate('global.messages.validate.email.required'),
                      },
                      minLength: {
                        value: 5,
                        message: translate('global.messages.validate.email.minlength'),
                      },
                      maxLength: {
                        value: 254,
                        message: translate('global.messages.validate.email.maxlength'),
                      },
                      validate: v => isEmail(v) || translate('global.messages.validate.email.invalid'),
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('email');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('userManagement.help.email')}
                      />
                    )}
                  />
                  {getFormErrorMessage('email')}
                </div>
              </div>

              <div>
                <label htmlFor="activated">
                  <Translate contentKey="userManagement.activated" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="activated"
                    render={({ field, fieldState }) => (
                      <InputSwitch
                        id={field.name}
                        onChange={e => field.onChange(e.value)}
                        checked={field.value}
                        onBlur={() => {
                          trigger('activated');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('userManagement.help.activated')}
                      />
                    )}
                  />
                  {getFormErrorMessage('activated')}
                </div>
              </div>
              <div>
                <label htmlFor="langKey">
                  <Translate contentKey="userManagement.langKey" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="langKey"
                    rules={{
                      required: {
                        value: true,
                        message: translate('global.entity.validate.required'),
                      },
                    }}
                    render={({ field, fieldState }) => (
                      <Dropdown
                        id={field.name}
                        value={field?.value}
                        onChange={e => field.onChange(e.value)}
                        options={languageArray}
                        optionValue="key"
                        optionLabel="value"
                        showClear
                        onBlur={() => {
                          trigger('langKey');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('userManagement.help.langKey')}
                      />
                    )}
                  />
                </div>
              </div>
              <div>
                <label htmlFor="profiles">
                  <Translate contentKey="userManagement.profiles" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="authorities"
                    render={({ field, fieldState }) => {
                      console.log(authorities);
                      return (
                        <MultiSelect
                          id={field.name}
                          value={field?.value}
                          onChange={e => field.onChange(e.value)}
                          options={authorities}
                          // optionValue="role"
                          // optionLabel="role"
                          display="chip"
                          showClear
                          onBlur={() => {
                            trigger('authorities');
                          }}
                          className={classNames({
                            'p-invalid': fieldState.invalid,
                          })}
                          tooltipOptions={{ position: 'top' }}
                          tooltip={translate('userManagement.help.profiles')}
                        />
                      );
                    }}
                  />
                </div>
              </div>
            </div>

            <div className="l-form-footer">
              <Button type="button" label={translate('entity.action.back')} icon="pi pi-arrow-left" outlined onClick={() => navigate(-1)} />
              <Button type="submit" label={translate('entity.action.save')} icon="pi pi-save" disabled={updating} />
            </div>
          </form>
        </>
      )}
    </div>
  );
};

export default UserManagementUpdate;
