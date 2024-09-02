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

import { getEntities as getLeadInfos } from 'app/entities/lead-info/lead-info.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { ILeadInfo, defaultValue } from 'app/shared/model/lead-info.model';
import { getEntity, updateEntity, createEntity, reset as resetEntity } from './lead-info.reducer';

export const LeadInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const leadInfos = useAppSelector(state => state.leadInfo.entities);
  const users = useAppSelector(state => state.userManagement.users);
  const leadInfoEntity = useAppSelector(state => state.leadInfo.entity);
  const loading = useAppSelector(state => state.leadInfo.loading);
  const updating = useAppSelector(state => state.leadInfo.updating);
  const updateSuccess = useAppSelector(state => state.leadInfo.updateSuccess);

  const handleClose = () => {
    navigate('/lead-info' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(resetEntity());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getLeadInfos({}));
    dispatch(getUsers({}));
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
    if (leadInfoEntity) {
      // aync data update the form
      reset(leadInfoEntity);
    }
  }, [leadInfoEntity, reset, TranslatorContext.context.locale]);

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
            <Translate contentKey="lxmcrmApp.leadInfo.home.createOrEditLabel">Create or edit a LeadInfo</Translate>
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
                <label htmlFor="salutation">
                  <Translate contentKey="lxmcrmApp.leadInfo.salutation" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="salutation"
                    rules={{
                      maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('salutation');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.salutation')}
                      />
                    )}
                  />
                  {getFormErrorMessage('salutation')}
                </div>
              </div>
              <div>
                <label htmlFor="firstName">
                  <Translate contentKey="lxmcrmApp.leadInfo.firstName" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="firstName"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
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
                        tooltip={translate('lxmcrmApp.leadInfo.help.firstName')}
                      />
                    )}
                  />
                  {getFormErrorMessage('firstName')}
                </div>
              </div>
              <div>
                <label htmlFor="lastName">
                  <Translate contentKey="lxmcrmApp.leadInfo.lastName" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="lastName"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
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
                        tooltip={translate('lxmcrmApp.leadInfo.help.lastName')}
                      />
                    )}
                  />
                  {getFormErrorMessage('lastName')}
                </div>
              </div>
              <div>
                <label htmlFor="title">
                  <Translate contentKey="lxmcrmApp.leadInfo.title" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="title"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('title');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.title')}
                      />
                    )}
                  />
                  {getFormErrorMessage('title')}
                </div>
              </div>
              <div>
                <label htmlFor="department">
                  <Translate contentKey="lxmcrmApp.leadInfo.department" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="department"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('department');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.department')}
                      />
                    )}
                  />
                  {getFormErrorMessage('department')}
                </div>
              </div>
              <div>
                <label htmlFor="doNotCall">
                  <Translate contentKey="lxmcrmApp.leadInfo.doNotCall" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="doNotCall"
                    render={({ field, fieldState }) => (
                      <InputSwitch
                        id={field.name}
                        onChange={e => field.onChange(e.value)}
                        checked={field.value}
                        onBlur={() => {
                          trigger('doNotCall');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.doNotCall')}
                      />
                    )}
                  />
                  {getFormErrorMessage('doNotCall')}
                </div>
              </div>
              <div>
                <label htmlFor="phoneHome">
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneHome" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="phoneHome"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('phoneHome');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.phoneHome')}
                      />
                    )}
                  />
                  {getFormErrorMessage('phoneHome')}
                </div>
              </div>
              <div>
                <label htmlFor="phoneMobile">
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneMobile" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="phoneMobile"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('phoneMobile');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.phoneMobile')}
                      />
                    )}
                  />
                  {getFormErrorMessage('phoneMobile')}
                </div>
              </div>
              <div>
                <label htmlFor="phoneWork">
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneWork" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="phoneWork"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('phoneWork');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.phoneWork')}
                      />
                    )}
                  />
                  {getFormErrorMessage('phoneWork')}
                </div>
              </div>
              <div>
                <label htmlFor="phoneOther">
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneOther" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="phoneOther"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('phoneOther');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.phoneOther')}
                      />
                    )}
                  />
                  {getFormErrorMessage('phoneOther')}
                </div>
              </div>
              <div>
                <label htmlFor="phoneFax">
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneFax" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="phoneFax"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('phoneFax');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.phoneFax')}
                      />
                    )}
                  />
                  {getFormErrorMessage('phoneFax')}
                </div>
              </div>
              <div>
                <label htmlFor="dateReviewed">
                  <Translate contentKey="lxmcrmApp.leadInfo.dateReviewed" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="dateReviewed"
                    render={({ field, fieldState }) => (
                      <Calendar
                        id={field.name}
                        onChange={e => field.onChange(e.value)}
                        value={field.value ? dayjs(convertDateTimeFromServer(field.value)).toDate() : null}
                        dateFormat="yy-mm-dd"
                        mask="9999-99-99"
                        showIcon
                        onBlur={() => {
                          trigger('dateReviewed');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.dateReviewed')}
                      />
                    )}
                  />
                  {getFormErrorMessage('dateReviewed')}
                </div>
              </div>
              <div>
                <label htmlFor="lawfulBasis">
                  <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasis" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="lawfulBasis"
                    rules={{
                      maxLength: { value: 512, message: translate('entity.validation.maxlength', { max: 512 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('lawfulBasis');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.lawfulBasis')}
                      />
                    )}
                  />
                  {getFormErrorMessage('lawfulBasis')}
                </div>
              </div>
              <div>
                <label htmlFor="lawfulBasisSource">
                  <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasisSource" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="lawfulBasisSource"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('lawfulBasisSource');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.lawfulBasisSource')}
                      />
                    )}
                  />
                  {getFormErrorMessage('lawfulBasisSource')}
                </div>
              </div>
              <div>
                <label htmlFor="primaryAddressStreet">
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressStreet" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="primaryAddressStreet"
                    rules={{
                      maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('primaryAddressStreet');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressStreet')}
                      />
                    )}
                  />
                  {getFormErrorMessage('primaryAddressStreet')}
                </div>
              </div>
              <div>
                <label htmlFor="primaryAddressCity">
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCity" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="primaryAddressCity"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('primaryAddressCity');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressCity')}
                      />
                    )}
                  />
                  {getFormErrorMessage('primaryAddressCity')}
                </div>
              </div>
              <div>
                <label htmlFor="primaryAddressState">
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressState" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="primaryAddressState"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('primaryAddressState');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressState')}
                      />
                    )}
                  />
                  {getFormErrorMessage('primaryAddressState')}
                </div>
              </div>
              <div>
                <label htmlFor="primaryAddressPostalcode">
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressPostalcode" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="primaryAddressPostalcode"
                    rules={{
                      maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('primaryAddressPostalcode');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressPostalcode')}
                      />
                    )}
                  />
                  {getFormErrorMessage('primaryAddressPostalcode')}
                </div>
              </div>
              <div>
                <label htmlFor="primaryAddressCountry">
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCountry" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="primaryAddressCountry"
                    rules={{
                      maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('primaryAddressCountry');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressCountry')}
                      />
                    )}
                  />
                  {getFormErrorMessage('primaryAddressCountry')}
                </div>
              </div>
              <div>
                <label htmlFor="altAddressStreet">
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressStreet" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="altAddressStreet"
                    rules={{
                      maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('altAddressStreet');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.altAddressStreet')}
                      />
                    )}
                  />
                  {getFormErrorMessage('altAddressStreet')}
                </div>
              </div>
              <div>
                <label htmlFor="altAddressCity">
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressCity" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="altAddressCity"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('altAddressCity');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.altAddressCity')}
                      />
                    )}
                  />
                  {getFormErrorMessage('altAddressCity')}
                </div>
              </div>
              <div>
                <label htmlFor="altAddressState">
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressState" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="altAddressState"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('altAddressState');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.altAddressState')}
                      />
                    )}
                  />
                  {getFormErrorMessage('altAddressState')}
                </div>
              </div>
              <div>
                <label htmlFor="altAddressPostalcode">
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressPostalcode" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="altAddressPostalcode"
                    rules={{
                      maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('altAddressPostalcode');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.altAddressPostalcode')}
                      />
                    )}
                  />
                  {getFormErrorMessage('altAddressPostalcode')}
                </div>
              </div>
              <div>
                <label htmlFor="altAddressCountry">
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressCountry" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="altAddressCountry"
                    rules={{
                      maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('altAddressCountry');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.altAddressCountry')}
                      />
                    )}
                  />
                  {getFormErrorMessage('altAddressCountry')}
                </div>
              </div>
              <div>
                <label htmlFor="assistant">
                  <Translate contentKey="lxmcrmApp.leadInfo.assistant" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="assistant"
                    rules={{
                      maxLength: { value: 75, message: translate('entity.validation.maxlength', { max: 75 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('assistant');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.assistant')}
                      />
                    )}
                  />
                  {getFormErrorMessage('assistant')}
                </div>
              </div>
              <div>
                <label htmlFor="assistantPhone">
                  <Translate contentKey="lxmcrmApp.leadInfo.assistantPhone" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="assistantPhone"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('assistantPhone');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.assistantPhone')}
                      />
                    )}
                  />
                  {getFormErrorMessage('assistantPhone')}
                </div>
              </div>
              <div>
                <label htmlFor="converted">
                  <Translate contentKey="lxmcrmApp.leadInfo.converted" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="converted"
                    render={({ field, fieldState }) => (
                      <InputSwitch
                        id={field.name}
                        onChange={e => field.onChange(e.value)}
                        checked={field.value}
                        onBlur={() => {
                          trigger('converted');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.converted')}
                      />
                    )}
                  />
                  {getFormErrorMessage('converted')}
                </div>
              </div>
              <div>
                <label htmlFor="referedBy">
                  <Translate contentKey="lxmcrmApp.leadInfo.referedBy" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="referedBy"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('referedBy');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.referedBy')}
                      />
                    )}
                  />
                  {getFormErrorMessage('referedBy')}
                </div>
              </div>
              <div>
                <label htmlFor="leadSource">
                  <Translate contentKey="lxmcrmApp.leadInfo.leadSource" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="leadSource"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('leadSource');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.leadSource')}
                      />
                    )}
                  />
                  {getFormErrorMessage('leadSource')}
                </div>
              </div>
              <div>
                <label htmlFor="leadSourceDescription">
                  <Translate contentKey="lxmcrmApp.leadInfo.leadSourceDescription" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="leadSourceDescription"
                    rules={{
                      maxLength: { value: 512, message: translate('entity.validation.maxlength', { max: 512 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('leadSourceDescription');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.leadSourceDescription')}
                      />
                    )}
                  />
                  {getFormErrorMessage('leadSourceDescription')}
                </div>
              </div>
              <div>
                <label htmlFor="status">
                  <Translate contentKey="lxmcrmApp.leadInfo.status" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="status"
                    rules={{
                      maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('status');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.status')}
                      />
                    )}
                  />
                  {getFormErrorMessage('status')}
                </div>
              </div>
              <div>
                <label htmlFor="statusDescription">
                  <Translate contentKey="lxmcrmApp.leadInfo.statusDescription" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="statusDescription"
                    rules={{
                      maxLength: { value: 512, message: translate('entity.validation.maxlength', { max: 512 }) },
                    }}
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('statusDescription');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.statusDescription')}
                      />
                    )}
                  />
                  {getFormErrorMessage('statusDescription')}
                </div>
              </div>
              <div>
                <label htmlFor="birthdate">
                  <Translate contentKey="lxmcrmApp.leadInfo.birthdate" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="birthdate"
                    render={({ field, fieldState }) => (
                      <Calendar
                        id={field.name}
                        onChange={e => field.onChange(e.value)}
                        value={field.value ? dayjs(convertDateTimeFromServer(field.value)).toDate() : null}
                        dateFormat="yy-mm-dd"
                        mask="9999-99-99"
                        showIcon
                        onBlur={() => {
                          trigger('birthdate');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.birthdate')}
                      />
                    )}
                  />
                  {getFormErrorMessage('birthdate')}
                </div>
              </div>
              <div>
                <label htmlFor="description">
                  <Translate contentKey="lxmcrmApp.leadInfo.description" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="description"
                    render={({ field, fieldState }) => (
                      <InputTextarea
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        rows={5}
                        onBlur={() => {
                          trigger('description');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.description')}
                      />
                    )}
                  />
                  {getFormErrorMessage('description')}
                </div>
              </div>

              <div>
                <label htmlFor="reportsTo">
                  <Translate contentKey="lxmcrmApp.leadInfo.reportsTo" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="reportsTo"
                    render={({ field, fieldState }) => (
                      <Dropdown
                        id={field.name}
                        value={field?.value?.id}
                        onChange={e => field.onChange(leadInfos.find(it => it.id === e.value))}
                        options={leadInfos}
                        optionValue="id"
                        optionLabel="lastName"
                        showClear
                        onBlur={() => {
                          trigger('reportsTo');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.reportsTo')}
                      />
                    )}
                  />
                </div>
              </div>
              <div>
                <label htmlFor="assignedUser">
                  <Translate contentKey="lxmcrmApp.leadInfo.assignedUser" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="assignedUser"
                    render={({ field, fieldState }) => (
                      <Dropdown
                        id={field.name}
                        value={field?.value?.id}
                        onChange={e => field.onChange(users.find(it => it.id === e.value))}
                        options={users}
                        optionValue="id"
                        optionLabel="login"
                        showClear
                        onBlur={() => {
                          trigger('assignedUser');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.leadInfo.help.assignedUser')}
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

export default LeadInfoUpdate;
