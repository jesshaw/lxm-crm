import './sidebar.css';

import React from 'react';
import Logo from '../logo';
import SideMenu from './side-menu';
import { Translate } from 'react-jhipster';
import { getSidebarMenusData, IHeaderProps } from 'app/shared/reducers/ui';

const Sidebar = (props: IHeaderProps) => {
  const sidebar = getSidebarMenusData(props);

  return (
    <div className="layout-sidebar">
      <div className="sidebar-header">
        <a className="brand-logo" href="/">
          <div className="brand-icon">
            <Logo />
          </div>
          <span className="brand-title">
            <Translate contentKey="global.title">Lxmcrm</Translate>
          </span>
          <span className="navbar-version">{VERSION.toLowerCase().startsWith('v') ? VERSION : `v${VERSION}`}</span>
        </a>
      </div>
      <div className="layout-menu-container">
        <SideMenu model={sidebar} />
      </div>
    </div>
  );
};

export default Sidebar;
