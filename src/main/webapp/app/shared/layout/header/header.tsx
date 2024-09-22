import './header.css';

import React, { useState } from 'react';
import { Translate, Storage } from 'react-jhipster';
import { Navbar, Nav, NavbarToggler, Collapse } from 'reactstrap';
import LoadingBar from 'react-redux-loading-bar';

import { Home, Brand } from './header-components';
import { AdminMenu, EntitiesMenu, AccountMenu, LocaleMenu } from '../menus';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { setLocale } from 'app/shared/reducers/locale';

import MyProfile from './my-profile';
import ThemeSelector from '../theme/theme-selector';
import { BreadCrumb } from 'primereact/breadcrumb';
import { InputText } from 'primereact/inputtext';
import { IHeaderProps, setMobileLayoutStatus, setStaticLayoutStatus } from 'app/shared/reducers/ui';
import { toBreadItems } from '../menus/bread-item';

const Header = (props: IHeaderProps) => {
  const [menuOpen, setMenuOpen] = useState(false);

  const dispatch = useAppDispatch();

  const handleLocaleChange = event => {
    const langKey = event.target.value;
    Storage.session.set('locale', langKey);
    dispatch(setLocale(langKey));
  };

  const renderDevRibbon = () =>
    props.isInProduction === false ? (
      <div className="ribbon dev">
        <Translate contentKey={`global.ribbon.${props.ribbonEnv}`} />
      </div>
    ) : null;

  const breadItems = useAppSelector(state => state.ui.breadItems);
  const mobileLayoutActivated = useAppSelector(state => state.ui.mobileLayoutActivated);
  const staticLayoutActivated = useAppSelector(state => state.ui.staticLayoutActivated);
  const handleMenu = () => {
    dispatch(setStaticLayoutStatus(!staticLayoutActivated));
    dispatch(setMobileLayoutStatus(!mobileLayoutActivated));
  };

  const toggleMenu = () => setMenuOpen(!menuOpen);

  /* jhipster-needle-add-element-to-menu - JHipster will add new menu items here */

  return (
    <div id="app-header" className="layout-topbar">
      <div className="topbar-start">
        <button type="button" className="topbar-menubutton p-link p-trigger" onClick={() => handleMenu()}>
          <i className="pi pi-bars" />
        </button>
        <div className="topbar-breadcrumb">
          <BreadCrumb model={toBreadItems(breadItems)} />
        </div>
      </div>

      {/* old nav start */}
      {renderDevRibbon()}
      <LoadingBar className="loading-bar" />
      <Navbar data-cy="navbar" dark expand="md" fixed="top" className="jh-navbar">
        <NavbarToggler aria-label="Menu" onClick={toggleMenu} />
        <Brand />
        <Collapse isOpen={menuOpen} navbar>
          <Nav id="header-tabs" className="ms-auto" navbar>
            {props.isAuthenticated && <Home />}
            {props.isAuthenticated && <EntitiesMenu />}
            {props.isAuthenticated && props.isAdmin && (
              <AdminMenu showOpenAPI={props.isOpenAPIEnabled} showDatabase={!props.isInProduction} />
            )}
            <LocaleMenu currentLocale={props.currentLocale} onClick={handleLocaleChange} />
            <AccountMenu isAuthenticated={props.isAuthenticated} />
          </Nav>
        </Collapse>
      </Navbar>
      {/* old nav end */}

      <div className="topbar-end">
        <div className="topbar-search">
          <span className="p-input-icon-left">
            <i className="pi pi-search"></i>
            <InputText placeholder="Search" />
          </span>
        </div>
        <ThemeSelector currentLocale={props.currentLocale} />
        <MyProfile />
      </div>
    </div>
  );
};

export default Header;
