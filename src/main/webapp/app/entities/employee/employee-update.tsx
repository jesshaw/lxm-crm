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

import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { getEntities as getEmployees } from 'app/entities/employee/employee.reducer';
import { IEmployee, defaultValue } from 'app/shared/model/employee.model';
import { getEntity, updateEntity, createEntity, reset as resetEntity } from './employee.reducer';

export const EmployeeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const users = useAppSelector(state => state.userManagement.users);
  const employees = useAppSelector(state => state.employee.entities);
  const employeeEntity = useAppSelector(state => state.employee.entity);
  const loading = useAppSelector(state => state.employee.loading);
  const updating = useAppSelector(state => state.employee.updating);
  const updateSuccess = useAppSelector(state => state.employee.updateSuccess);

  const handleClose = () => {
    navigate('/employee');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(resetEntity());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getUsers({}));
    dispatch(getEmployees({}));
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
    if (employeeEntity) {
      // aync data update the form
      reset(employeeEntity);
    }
  }, [employeeEntity, reset, TranslatorContext.context.locale]);

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
            <Translate contentKey="lxmcrmApp.employee.home.createOrEditLabel">Create or edit a Employee</Translate>
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
                <label htmlFor="title">
                  <Translate contentKey="lxmcrmApp.employee.title" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="title"
                    rules={{
                      maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
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
                        tooltip={translate('lxmcrmApp.employee.help.title')}
                      />
                    )}
                  />
                  {getFormErrorMessage('title')}
                </div>
              </div>
              <div>
                <label htmlFor="nickName">
                  <Translate contentKey="lxmcrmApp.employee.nickName" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="nickName"
                    render={({ field, fieldState }) => (
                      <InputText
                        id={field.name}
                        {...field}
                        value={field.value ? field.value : ''}
                        onBlur={() => {
                          trigger('nickName');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.employee.help.nickName')}
                      />
                    )}
                  />
                  {getFormErrorMessage('nickName')}
                </div>
              </div>

              <div>
                <label htmlFor="user">
                  <Translate contentKey="lxmcrmApp.employee.user" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="user"
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
                          trigger('user');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.employee.help.user')}
                      />
                    )}
                  />
                </div>
              </div>
              <div>
                <label htmlFor="reportsTo">
                  <Translate contentKey="lxmcrmApp.employee.reportsTo" />
                </label>
                <div>
                  <Controller
                    control={control}
                    name="reportsTo"
                    render={({ field, fieldState }) => (
                      <Dropdown
                        id={field.name}
                        value={field?.value?.id}
                        onChange={e => field.onChange(employees.find(it => it.id === e.value))}
                        options={employees}
                        optionValue="id"
                        optionLabel="nickName"
                        showClear
                        onBlur={() => {
                          trigger('reportsTo');
                        }}
                        className={classNames({
                          'p-invalid': fieldState.invalid,
                        })}
                        tooltipOptions={{ position: 'top' }}
                        tooltip={translate('lxmcrmApp.employee.help.reportsTo')}
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

export default EmployeeUpdate;
