import React, { useLayoutEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { logout } from 'app/shared/reducers/authentication';

export const Logout = () => {
  const logoutUrl = useAppSelector(state => state.authentication.logoutUrl);
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  useLayoutEffect(() => {
    dispatch(logout());
    if (logoutUrl) {
      window.location.href = logoutUrl;
    } else {
      navigate('/login');
    }
  });

  return (
    <div className="p-5">
      <h4>Logged out successfully!</h4>
    </div>
  );
};

export default Logout;
