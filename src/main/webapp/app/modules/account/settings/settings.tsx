import React, { useEffect } from 'react';
import { InputText } from 'primereact/inputtext';
import { classNames } from 'primereact/utils';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { Translate, translate, isEmail } from 'react-jhipster';
import { toast } from 'app/shared/layout/theme/toast-manager';

import { locales, languages, languageArray } from 'app/config/translation';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getSession } from 'app/shared/reducers/authentication';
import { saveAccountSettings, reset as resetAccountSettings } from './settings.reducer';
import { defaultValue } from 'app/shared/model/user.model';
import { Controller, useForm } from 'react-hook-form';

export const SettingsPage = () => {
  const dispatch = useAppDispatch();
  const account = useAppSelector(state => state.authentication.account);
  const successMessage = useAppSelector(state => state.settings.successMessage);
  const updateSuccess = useAppSelector(state => state.settings.updateSuccess);

  useEffect(() => {
    dispatch(getSession());
    return () => {
      dispatch(resetAccountSettings());
    };
  }, []);

  useEffect(() => {
    if (successMessage) {
      toast.success(translate(successMessage));
    }
  }, [successMessage]);

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
    if (account) {
      // aync data update the form
      reset(account);
    }
  }, [account, reset]);

  const onSubmit = values => {
    dispatch(
      saveAccountSettings({
        ...account,
        ...values,
      }),
    );
  };

  const getFormErrorMessage = name => {
    return errors[name] && <small className="p-error">{errors[name].message}</small>;
  };

  return (
    <div className="l-card">
      <h5>
        <Translate contentKey="settings.title" interpolate={{ username: account.login }}>
          User settings for {account.login}
        </Translate>
      </h5>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="l-form">
          <div>
            <label htmlFor="firstName">
              <Translate contentKey="settings.form.firstname" />
            </label>
            <div>
              <Controller
                control={control}
                name="firstName"
                rules={{
                  required: { value: true, message: translate('settings.messages.validate.firstname.required') },
                  minLength: { value: 1, message: translate('settings.messages.validate.firstname.minlength') },
                  maxLength: { value: 50, message: translate('settings.messages.validate.firstname.maxlength') },
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
                    placeholder={translate('settings.form.firstname.placeholder')}
                  />
                )}
              />
              {getFormErrorMessage('firstName')}
            </div>
          </div>
          <div>
            <label htmlFor="lastName">
              <Translate contentKey="settings.form.lastname" />
            </label>
            <div>
              <Controller
                control={control}
                name="lastName"
                rules={{
                  required: { value: true, message: translate('settings.messages.validate.lastname.required') },
                  minLength: { value: 1, message: translate('settings.messages.validate.lastname.minlength') },
                  maxLength: { value: 50, message: translate('settings.messages.validate.lastname.maxlength') },
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
                    placeholder={translate('settings.form.lastname.placeholder')}
                  />
                )}
              />
              {getFormErrorMessage('lastName')}
            </div>
          </div>
          <div>
            <label htmlFor="email">
              <Translate contentKey="global.form.email.label" />
            </label>
            <div>
              <Controller
                control={control}
                name="email"
                rules={{
                  required: { value: true, message: translate('global.messages.validate.email.required') },
                  minLength: { value: 5, message: translate('global.messages.validate.email.minlength') },
                  maxLength: { value: 254, message: translate('global.messages.validate.email.maxlength') },
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
                    placeholder={translate('global.form.email.placeholder')}
                  />
                )}
              />
              {getFormErrorMessage('email')}
            </div>
          </div>

          <div>
            <label htmlFor="langKey">
              <Translate contentKey="settings.form.language" />
            </label>
            <div>
              <Controller
                control={control}
                name="langKey"
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
                  />
                )}
              />
            </div>
          </div>
        </div>

        <div className="l-form-footer">
          <Button type="submit" label={translate('settings.form.button')} icon="pi pi-save" disabled={updateSuccess} />
        </div>
      </form>
    </div>
  );
};

export default SettingsPage;
