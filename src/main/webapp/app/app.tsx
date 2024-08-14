import 'react-toastify/dist/ReactToastify.css';
import './app.css';
import 'app/config/dayjs';

import React, { useEffect, useState, useRef } from 'react';
import { Card } from 'reactstrap';
import { BrowserRouter } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';

import { useAppDispatch, useAppSelector } from 'app/config/store';
import { getSession } from 'app/shared/reducers/authentication';
import { getProfile } from 'app/shared/reducers/application-profile';
import Header from 'app/shared/layout/header/header';
import Footer from 'app/shared/layout/footer/footer';
import { hasAnyAuthority } from 'app/shared/auth/private-route';
import ErrorBoundary from 'app/shared/error/error-boundary';
import { AUTHORITIES } from 'app/config/constants';
import AppRoutes from 'app/routes';
import Sidebar from 'app/shared/layout/menus/sidebar';
import { ThemeProvider } from 'app/shared/layout/theme/theme-context';
import { BreadCrumb } from 'primereact/breadcrumb';
import { setMobileLayoutStatus, setUiSettingsStatus } from './shared/reducers/ui';

const baseHref = document.querySelector('base').getAttribute('href').replace(/\/$/, '');

export const App = () => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getSession());
    dispatch(getProfile());
  }, []);

  const currentLocale = useAppSelector(state => state.locale.currentLocale);
  const isAuthenticated = useAppSelector(state => state.authentication.isAuthenticated);
  const isAdmin = useAppSelector(state => hasAnyAuthority(state.authentication.account.authorities, [AUTHORITIES.ADMIN]));
  const ribbonEnv = useAppSelector(state => state.applicationProfile.ribbonEnv);
  const isInProduction = useAppSelector(state => state.applicationProfile.inProduction);
  const isOpenAPIEnabled = useAppSelector(state => state.applicationProfile.isOpenAPIEnabled);

  const mobileLayoutActivated = useAppSelector(state => state.ui.mobileLayoutActivated);
  const staticLayoutActivated = useAppSelector(state => state.ui.staticLayoutActivated);
  const layoutStaticInactive = 'layout-static-inactive'; //静态未激活
  const layoutMobileActive = 'layout-mobile-active'; //手机屏幕激活
  const blockedScroll = 'blocked-scroll';
  const layoutContainer = useRef<HTMLDivElement>(null);
  const showStaticLayout = () => {
    console.log('showStaticLayout');
    if (window.innerWidth >= 768) {
      console.log('>=992 showStaticLayout');
      layoutContainer.current?.classList.remove(layoutStaticInactive);
    }
  };

  const hideStaticLayout = () => {
    if (window.innerWidth >= 768) {
      layoutContainer.current?.classList.add(layoutStaticInactive);
    }
  };

  const showMobileLayout = () => {
    if (window.innerWidth < 768) {
      layoutContainer.current?.classList.add(layoutMobileActive);
      document.body.classList.add(blockedScroll);
    }
  };

  const hideMobileLayout = () => {
    layoutContainer.current?.classList.remove(layoutMobileActive);
  };

  useEffect(() => {
    if (staticLayoutActivated) {
      showStaticLayout();
    } else {
      hideStaticLayout();
    }
  }, [staticLayoutActivated]);

  useEffect(() => {
    if (mobileLayoutActivated) {
      showMobileLayout();
    } else {
      hideMobileLayout();
    }
  }, [mobileLayoutActivated]);

  const breadItems = [{ label: 'Electronics' }];
  const home = { icon: 'pi pi-home', url: '#' };

  const paddingTop = '60px';
  return (
    <BrowserRouter basename={baseHref}>
      <ThemeProvider>
        <div className="layout-container layout-static" ref={layoutContainer}>
          <Sidebar
            isAuthenticated={isAuthenticated}
            isAdmin={isAdmin}
            currentLocale={currentLocale}
            ribbonEnv={ribbonEnv}
            isInProduction={isInProduction}
            isOpenAPIEnabled={isOpenAPIEnabled}
          />
          <div className="layout-content-wrapper app-container">
            <ToastContainer position="top-left" className="toastify-container" toastClassName="toastify-toast" />
            <ErrorBoundary>
              <Header
                isAuthenticated={isAuthenticated}
                isAdmin={isAdmin}
                currentLocale={currentLocale}
                ribbonEnv={ribbonEnv}
                isInProduction={isInProduction}
                isOpenAPIEnabled={isOpenAPIEnabled}
              />
            </ErrorBoundary>
            <div className="content-breadcrumb">
              <BreadCrumb model={breadItems} home={home} />
            </div>
            <div className="container-fluid view-container" id="app-view-container">
              <Card className="jh-card">
                <ErrorBoundary>
                  <AppRoutes />
                </ErrorBoundary>
              </Card>
              <Footer />
            </div>
          </div>
          <button className="layout-config-button" type="button" onClick={() => dispatch(setUiSettingsStatus(true))}>
            <i className="pi pi-palette"></i>
          </button>
          <div className="layout-mask" onClick={() => dispatch(setMobileLayoutStatus(false))}></div>
        </div>
      </ThemeProvider>
    </BrowserRouter>
  );
};

export default App;
