import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { Translate, translate, ValidatedField, ValidatedForm, isEmail } from 'react-jhipster';

import { toast } from 'app/shared/layout/theme/toast-manager';

import { handlePasswordResetInit, reset } from '../password-reset.reducer';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import Bg from 'app/shared/layout/bg';
import FullPageLayout from 'app/shared/layout/full-page-layout';

import { useForm, Controller } from 'react-hook-form';
import { IconField } from 'primereact/iconfield';
import { InputIcon } from 'primereact/inputicon';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';

import './password-reset-init.css';

export const PasswordResetInit = () => {
  const dispatch = useAppDispatch();

  useEffect(
    () => () => {
      dispatch(reset());
    },
    [],
  );

  const navigate = useNavigate();
  const goBack = () => {
    navigate(-1);
  };

  const passwordResetInit = ({ email }) => {
    dispatch(handlePasswordResetInit(email));
  };

  const {
    handleSubmit,
    control,
    formState: { errors },
  } = useForm({ mode: 'onTouched' });

  const handleValidSubmit = e => {
    handleSubmit(passwordResetInit)(e);
  };

  const successMessage = useAppSelector(state => state.passwordReset.successMessage);

  useEffect(() => {
    if (successMessage) {
      toast.success(translate(successMessage));
    }
  }, [successMessage]);

  return (
    <FullPageLayout>
      <Bg className="fixed left-0 top-0 min-h-screen min-w-full" />
      <div className="full-page">
        <div className="content">
          <form onSubmit={handleValidSubmit}>
            <div className="title">
              <Translate contentKey="reset.request.title">Reset your password</Translate>
            </div>
            <div>
              <Translate contentKey="reset.request.messages.info">Enter the email address you used to register</Translate>
            </div>
            <IconField iconPosition="left">
              <InputIcon className="pi pi-envelope"> </InputIcon>
              <Controller
                name="email"
                control={control}
                rules={{
                  required: translate('global.messages.validate.email.required'),
                  pattern: {
                    value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/, // 邮箱格式正则表达式
                    message: translate('global.messages.validate.email.invalid'), // 邮箱格式错误时的提示信息
                  },
                }}
                render={({ field }) => (
                  <>
                    <InputText
                      {...field}
                      keyfilter="email"
                      placeholder={translate('global.form.email.placeholder')}
                      invalid={!!errors.email}
                    />
                    {errors.email && <span>{errors.email.message.toString()}</span>}
                  </>
                )}
              />
            </IconField>
            <div className="footer">
              <Button label={translate('reset.request.form.cancel')} outlined onClick={goBack} />
              <Button label={translate('reset.request.form.button')} type="submit" />
            </div>
          </form>
        </div>
      </div>
    </FullPageLayout>
  );
};

export default PasswordResetInit;
