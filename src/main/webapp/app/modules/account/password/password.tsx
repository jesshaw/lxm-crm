import React, { useState, useEffect } from 'react';
import { Translate, translate, TranslatorContext } from 'react-jhipster';
import { Controller, useForm } from 'react-hook-form';
import { classNames } from 'primereact/utils';
import { Password } from 'primereact/password';
import { Button } from 'primereact/button';
import { toast } from 'app/shared/layout/theme/toast-manager';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getSession } from 'app/shared/reducers/authentication';
import { savePassword, reset as resetPassword } from './password.reducer';

export const PasswordPage = () => {
  const [password, setPassword] = useState('');
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(resetPassword());
    dispatch(getSession());
    return () => {
      dispatch(resetPassword());
    };
  }, []);

  const updatePassword = event => setPassword(event.target.value);

  const account = useAppSelector(state => state.authentication.account);
  const successMessage = useAppSelector(state => state.password.successMessage);
  const errorMessage = useAppSelector(state => state.password.errorMessage);
  const updateSuccess = useAppSelector(state => state.password.updateSuccess);

  useEffect(() => {
    if (successMessage) {
      toast.success(translate(successMessage));
    } else if (errorMessage) {
      toast.error(translate(errorMessage));
    }
    dispatch(resetPassword());
  }, [successMessage, errorMessage]);

  const defaultValues = { currentPassword: null, newPassword: null, confirmPassword: null };

  const {
    control,
    formState: { errors },
    handleSubmit,
    reset,
    watch,
    trigger,
  } = useForm({ defaultValues: defaultValues });

  const newPassword = watch('newPassword', '');

  useEffect(() => {
    reset(defaultValues);
  }, [reset, TranslatorContext.context.locale, updateSuccess]);

  const onSubmit = ({ currentPassword, newPassword }) => {
    dispatch(savePassword({ currentPassword, newPassword }));
  };

  const getFormErrorMessage = name => {
    return errors[name] && <small className="p-error">{errors[name].message}</small>;
  };

  return (
    <div className="l-card">
      <h5>
        <Translate contentKey="password.title" interpolate={{ username: account.login }}>
          Password for {account.login}
        </Translate>
      </h5>
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="l-form">
          <div>
            <label htmlFor="currentPassword">
              <Translate contentKey="global.form.currentpassword.label" />
            </label>
            <div>
              <Controller
                control={control}
                name="currentPassword"
                rules={{
                  required: { value: true, message: translate('global.messages.validate.newpassword.required') },
                }}
                render={({ field, fieldState }) => (
                  <Password
                    id={field.name}
                    {...field}
                    value={field.value ? field.value : ''}
                    feedback={false}
                    toggleMask
                    onBlur={() => {
                      trigger('currentPassword');
                    }}
                    className={classNames({
                      'p-invalid': fieldState.invalid,
                    })}
                    placeholder={translate('global.form.currentpassword.placeholder')}
                  />
                )}
              />
              {getFormErrorMessage('currentPassword')}
            </div>
          </div>
          <div>
            <label htmlFor="newPassword">
              <Translate contentKey="global.form.newpassword.label" />
            </label>
            <div>
              <Controller
                control={control}
                name="newPassword"
                rules={{
                  required: { value: true, message: translate('global.messages.validate.newpassword.required') },
                  minLength: { value: 4, message: translate('global.messages.validate.newpassword.minlength') },
                  maxLength: { value: 50, message: translate('global.messages.validate.newpassword.maxlength') },
                }}
                render={({ field, fieldState }) => (
                  <Password
                    id={field.name}
                    {...field}
                    value={field.value ? field.value : ''}
                    toggleMask
                    onBlur={() => {
                      trigger('newPassword');
                    }}
                    className={classNames({
                      'p-invalid': fieldState.invalid,
                    })}
                    placeholder={translate('global.form.newpassword.placeholder')}
                  />
                )}
              />
              {getFormErrorMessage('newPassword')}
            </div>
          </div>
          <div>
            <label htmlFor="confirmPassword">
              <Translate contentKey="global.form.confirmpassword.label" />
            </label>
            <div>
              <Controller
                control={control}
                name="confirmPassword"
                rules={{
                  required: { value: true, message: translate('global.messages.validate.confirmpassword.required') },
                  minLength: { value: 4, message: translate('global.messages.validate.confirmpassword.minlength') },
                  maxLength: { value: 50, message: translate('global.messages.validate.confirmpassword.maxlength') },
                  validate: v => v === newPassword || translate('global.messages.error.dontmatch'),
                }}
                render={({ field, fieldState }) => (
                  <Password
                    id={field.name}
                    {...field}
                    value={field.value ? field.value : ''}
                    feedback={false}
                    toggleMask
                    onBlur={() => {
                      trigger('confirmPassword');
                    }}
                    className={classNames({
                      'p-invalid': fieldState.invalid,
                    })}
                    placeholder={translate('global.form.confirmpassword.placeholder')}
                  />
                )}
              />
              {getFormErrorMessage('confirmPassword')}
            </div>
          </div>
        </div>

        <div className="l-form-footer">
          <Button type="submit" label={translate('password.form.button')} icon="pi pi-save" disabled={updateSuccess} />
        </div>
      </form>
    </div>
  );
};

export default PasswordPage;
