import './header.css';

import React, { useState, Dispatch, SetStateAction, MutableRefObject } from 'react';
import { Translate, Storage } from 'react-jhipster';
import { Navbar, Nav, NavbarToggler, Collapse } from 'reactstrap';
import LoadingBar from 'react-redux-loading-bar';

import { Home, Brand } from './header-components';
import { AdminMenu, EntitiesMenu, AccountMenu, LocaleMenu } from '../menus';
import { useAppDispatch } from 'app/config/store';
import { setLocale } from 'app/shared/reducers/locale';

import MyProfile from './my-profile';
import ThemeSelector from '../theme/theme-selector';
import { BreadCrumb } from 'primereact/breadcrumb';
import { InputText } from 'primereact/inputtext';

export interface IHeaderProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
  currentLocale: string;
  themeSelectorVisible: boolean;
  setThemeSelectorVisible: Dispatch<SetStateAction<boolean>>;
  layoutContainer: MutableRefObject<HTMLDivElement>;
}

const Header = (props: IHeaderProps) => {
  const [menuOpen, setMenuOpen] = useState(false);
  const breadItems = [{ label: 'Electronics' }];
  const home = { icon: 'pi pi-home', url: '#' };

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

  const divLayoutConatiner = props.layoutContainer;
  const layoutStaticInactive = 'layout-static-inactive'; //静态未激活
  const layoutMobileActive = 'layout-mobile-active'; //手机屏幕激活
  const blockedScroll = 'blocked-scroll';
  const handleMenu = () => {
    if (divLayoutConatiner.current?.classList.contains(layoutStaticInactive)) {
      divLayoutConatiner.current?.classList.remove(layoutStaticInactive);
    } else {
      divLayoutConatiner.current?.classList.add(layoutStaticInactive);
    }

    if (window.innerWidth < 992) {
      divLayoutConatiner.current?.classList.add(layoutMobileActive);
      document.body.classList.add(blockedScroll);
    } else {
      divLayoutConatiner.current?.classList.remove(layoutMobileActive);
    }
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
          <BreadCrumb model={breadItems} home={home} />
        </div>
      </div>

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

      <div className="topbar-end">
        <div className="topbar-search">
          <i className="pi pi-search"></i>
          <InputText placeholder="Search" className="h-8 pl-6" />
        </div>
        <ThemeSelector
          currentLocale={props.currentLocale}
          visible={props.themeSelectorVisible}
          setVisible={props.setThemeSelectorVisible}
        />
        <MyProfile />
      </div>
    </div>
  );
};

export default Header;
