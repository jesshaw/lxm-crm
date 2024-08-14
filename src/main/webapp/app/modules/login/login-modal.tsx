import React from 'react';
import { Translate, translate, ValidatedField } from 'react-jhipster';
import { Link } from 'react-router-dom';
import { type FieldError, useForm, Controller } from 'react-hook-form';

import { InputText } from 'primereact/inputtext';
import { IconField } from 'primereact/iconfield';
import { InputIcon } from 'primereact/inputicon';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';
import { Message } from 'primereact/message';
import Bg from 'app/shared/layout/bg';

export interface ILoginModalProps {
  showModal: boolean;
  loginError: boolean;
  handleLogin: (username: string, password: string, rememberMe: boolean) => void;
  handleClose: () => void;
}

const LoginModal = (props: ILoginModalProps) => {
  const login = ({ username, password, rememberMe }) => {
    props.handleLogin(username, password, rememberMe);
  };

  const {
    handleSubmit,
    control,
    register,
    formState: { errors, touchedFields },
  } = useForm({ mode: 'onTouched' });

  const { loginError, handleClose } = props;

  const handleLoginSubmit = e => {
    handleSubmit(login)(e);
  };

  return (
    <>
      <Bg className="fixed left-0 top-0 min-h-screen min-w-full" />
      <div id="login-page">
        <div className="login-content">
          <form onSubmit={handleLoginSubmit}>
            <div className="login-title" id="login-title" data-cy="loginTitle">
              <Translate contentKey="login.title">Sign in</Translate>
            </div>
            <div className="login-body">
              {loginError ? <Message severity="error" text={translate('login.messages.error.authentication')} /> : null}
              <IconField iconPosition="left">
                <InputIcon className="pi pi-envelope"> </InputIcon>
                <Controller
                  name="username"
                  control={control}
                  rules={{ required: 'Username cannot be empty!' }}
                  render={({ field }) => (
                    <InputText {...field} placeholder={translate('global.form.username.placeholder')} invalid={!!errors.username} />
                  )}
                />
              </IconField>
              <IconField iconPosition="left">
                <InputIcon className="pi pi-lock"> </InputIcon>
                <Controller
                  name="password"
                  control={control}
                  rules={{ required: 'Password cannot be empty!' }}
                  render={({ field }) => (
                    <InputText
                      {...field}
                      placeholder={translate('login.form.password.placeholder')}
                      invalid={!!errors.password}
                      type="password"
                    />
                  )}
                />
              </IconField>
              <div className="remember-me">
                <div>
                  <Controller
                    name="rememberMe"
                    control={control}
                    render={({ field }) => (
                      <>
                        <Checkbox inputId={field.name} {...field} checked={field.value} onChange={e => field.onChange(e.checked)} />
                        <label htmlFor={field.name}>{translate('login.form.rememberme')}</label>
                      </>
                    )}
                  />
                </div>
                <Link to="/account/reset/request" data-cy="forgetYourPasswordSelector">
                  <Translate contentKey="login.password.forgot">Did you forget your password?</Translate>
                </Link>
              </div>
            </div>
            <div className="login-footer">
              <Button label={translate('login.form.button')} />
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default LoginModal;
