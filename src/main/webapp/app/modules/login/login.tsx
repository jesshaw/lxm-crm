import React, { useState, useEffect } from 'react';
import { Navigate, useLocation, useNavigate } from 'react-router-dom';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { login } from 'app/shared/reducers/authentication';
import LoginModal from './login-modal';
import './login.css';
import FullPageLayout from 'app/shared/layout/full-page-layout';

export const Login = () => {
  const dispatch = useAppDispatch();
  const isAuthenticated = useAppSelector(state => state.authentication.isAuthenticated);
  const loginError = useAppSelector(state => state.authentication.loginError);
  const showModalLogin = useAppSelector(state => state.authentication.showModalLogin);
  const [showModal, setShowModal] = useState(showModalLogin);
  const navigate = useNavigate();
  const pageLocation = useLocation();

  useEffect(() => {
    setShowModal(true);
  }, []);

  const handleLogin = (username, password, rememberMe = false) => dispatch(login(username, password, rememberMe));

  const handleClose = () => {
    setShowModal(false);
    navigate('/');
  };

  let searchParams = new URLSearchParams(location.search);
  const redirectUrl = searchParams.get('r');
  searchParams.delete('r');

  if (isAuthenticated) {
    return <Navigate to={{ pathname: redirectUrl ?? '/', search: `?${searchParams.toString()}` }} replace />;
  }
  return (
    <FullPageLayout>
      <LoginModal showModal={showModal} handleLogin={handleLogin} handleClose={handleClose} loginError={loginError} />
    </FullPageLayout>
  );
};

export default Login;
