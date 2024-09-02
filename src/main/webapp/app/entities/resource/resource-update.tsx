import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
// import { Button, Row, Col, FormText, UncontrolledTooltip, } from 'reactstrap';
import { isNumber, Translate, translate, TranslatorContext, ValidatedField, ValidatedForm } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { useForm, Controller } from 'react-hook-form';
import { InputNumber } from 'primereact/inputnumber';
import { InputText } from 'primereact/inputtext';
import { Dropdown } from 'primereact/dropdown';
import { Button } from 'primereact/button';
import { Calendar } from 'primereact/calendar';
import { InputSwitch } from 'primereact/inputswitch';
import { InputTextarea } from 'primereact/inputtextarea';
import { classNames } from 'primereact/utils';
import dayjs from 'dayjs';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAuthority } from 'app/shared/model/admin/authority.model';
import { getEntities as getAuthorities } from 'app/entities/admin/authority/authority.reducer';
import { IResource, defaultValue } from 'app/shared/model/resource.model';
import { getEntity, updateEntity, createEntity, reset as resetEntity } from './resource.reducer';

export const ResourceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const authorities = useAppSelector(state => state.authority.entities);
  const resourceEntity = useAppSelector(state => state.resource.entity);
  const loading = useAppSelector(state => state.resource.loading);
  const updating = useAppSelector(state => state.resource.updating);
  const updateSuccess = useAppSelector(state => state.resource.updateSuccess);

  const handleClose = () => {
    navigate('/resource');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(resetEntity());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getAuthorities({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

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
    if (resourceEntity) {
      // aync data update the form
      reset(resourceEntity);
    }
  }, [resourceEntity, reset, TranslatorContext.context.locale]);

  const onSubmit = data => {
    // console.log('submit data:', data);
    if (isNew) {
      dispatch(createEntity(data));
    } else {
      dispatch(updateEntity(data));
    }

    reset();
  };

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
            <Translate contentKey="lxmcrmApp.resource.home.createOrEditLabel">Create or edit a Resource</Translate>
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
                <label htmlFor="name">
                  <Translate contentKey="lxmcrmApp.resource.name" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="name"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('name');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.resource.help.name')}
                      />
                    )}
                  />
                  {getFormErrorMessage('name')}
                </div>
              </div>
              <div>
                <label htmlFor="permission">
                  <Translate contentKey="lxmcrmApp.resource.permission" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="permission"
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('permission');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.resource.help.permission')}
                      />
                    )}
                  />
                  {getFormErrorMessage('permission')}
                </div>
              </div>

              <div>
                <label htmlFor="authority">
                  <Translate contentKey="lxmcrmApp.resource.authority" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="authority"
                    render={({ field, fieldState }) => (
                      <Dropdown
                        id={field.name}
                        value={field?.value?.name}
                        onChange={e => field.onChange(authorities.find(it => it.name === e.value))}
                        options={authorities}
                        optionValue="name"
                        optionLabel="name"
                        showClear
                        onBlur={() => {
                          trigger('authority');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.resource.help.authority')}
                      />
                    )}
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

export default ResourceUpdate;
