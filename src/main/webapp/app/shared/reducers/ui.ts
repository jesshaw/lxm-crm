import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { EntitiesMenuData } from 'app/entities/entities-menu-data';

export interface IMenuItem {
  label?: string;
  labelKey?: string;
  icon?: string;
  url?: string;
  items?: IMenuItem[];
  visable?: boolean;
}

const initialState = {
  theme: '',
  mobileLayoutActivated: false,
  staticLayoutActivated: true,
  uiSettingsActivated: false,
  myProfileActivated: false,
  breadItems: null as IMenuItem[] | null,
};

export type UIState = Readonly<typeof initialState>;

export const MenuItemsData = {
  ...EntitiesMenuData,
  homeMenuItem: { icon: 'pi pi-home', labelKey: 'global.menu.home', url: '/' }, // label为空不显示文本显示图片
  dashboardMenuItem: { label: 'DASHBOARDS', labelKey: 'global.menu.dashboards.title' },
  ecommerceMenuItem: { label: 'E-Commerce', labelKey: 'global.menu.dashboards.ecommerce', url: '/e-commerce' },
  bankingMenuItem: { label: 'Banking', labelKey: 'global.menu.dashboards.banking', url: '/' },

  accountMenuItem: { label: 'Account', labelKey: 'global.menu.account.main' },
  settingsMenuItem: { label: 'Settings', labelKey: 'global.menu.account.settings', url: '/account/settings' },
  passwordManagementMenuItem: { label: 'Password', labelKey: 'global.menu.account.password', url: '/account/password' },
  logoutMenuItem: { label: 'Logout', labelKey: 'global.menu.account.logout', url: '/account/logout' },
  loginMenuItem: { label: 'Login', labelKey: 'global.menu.account.login', url: '/account/login' },
  registerMenuItem: { label: 'Register', labelKey: 'global.menu.account.register', url: '/account/register' },

  administrationMenuItem: { label: 'Administration', labelKey: 'global.menu.admin.main' },
  userManagementMenuItem: { label: 'User management', labelKey: 'global.menu.admin.userManagement', url: '/admin/user-management' },
  metricsMenuItem: { label: 'Metrics', labelKey: 'global.menu.admin.metrics', url: '/admin/metrics' },
  healthMenuItem: { label: 'Health', labelKey: 'global.menu.admin.health', url: '/admin/health' },
  configurationMenuItem: { label: 'Configuration', labelKey: 'global.menu.admin.configuration', url: '/admin/configuration' },
  logsMenuItem: { label: 'Logs', labelKey: 'global.menu.admin.logs', url: '/admin/logs' },
  apidocsMenuItem: { label: 'API', labelKey: 'global.menu.admin.apidocs', url: '/admin/docs' },
  databaseMenuItem: { label: 'Database', labelKey: 'global.menu.admin.database', url: './h2-console/' }, //new blank open
};

export const getSidebarMenusData = (props: IHeaderProps) => {
  const dashboardMenus = {
    ...MenuItemsData.dashboardMenuItem,
    expanded: true,
    visable: props.isAuthenticated,
    items: [
      {
        ...MenuItemsData.bankingMenuItem,
        icon: 'pi pi-fw pi-image',
        visable: props.isAuthenticated,
      },
      {
        ...MenuItemsData.ecommerceMenuItem,
        icon: 'pi pi-fw pi-home',
        visable: props.isAuthenticated,
      },
    ],
  };

  const entitiesMenus = {
    ...MenuItemsData.entitesMenuItem,
    expanded: true,
    visable: props.isAuthenticated,
    items: [],
  };
  for (const [key, value] of Object.entries(EntitiesMenuData)) {
    // console.log(`Menu item key: ${key}`);
    if (key != 'entitesMenuItem') {
      const visable =
        key == 'resourceMenuItem'
          ? props.isAdmin || !!props.resources[MenuItemsData.resourceMenuItem.label]?.includes('ACCESS')
          : !!props.resources[value.label]?.includes('ACCESS');
      entitiesMenus.items.push({ ...value, icon: 'pi pi-fw pi-verified', visable });
    }
  }

  const adminMenus = {
    ...MenuItemsData.administrationMenuItem,
    visable: props.isAuthenticated && props.isAdmin,
    expanded: true,
    items: [
      {
        ...MenuItemsData.userManagementMenuItem,
        icon: 'pi pi-fw pi-users',
      },
      {
        ...MenuItemsData.metricsMenuItem,
        icon: 'pi pi-fw pi-gauge',
      },
      {
        ...MenuItemsData.healthMenuItem,
        icon: 'pi pi-fw pi-heart',
      },
      {
        ...MenuItemsData.configurationMenuItem,
        icon: 'pi pi-fw pi-cog',
      },
      {
        ...MenuItemsData.logsMenuItem,
        icon: 'pi pi-fw pi-list',
      },
      {
        ...MenuItemsData.apidocsMenuItem,
        icon: 'pi pi-fw pi-book',
        visable: props.isOpenAPIEnabled,
      },
      {
        ...MenuItemsData.databaseMenuItem,
        icon: 'pi pi-fw pi-database',
        visable: !props.isInProduction,
      },
    ],
  };

  return [dashboardMenus, entitiesMenus, adminMenus];
};
export interface IHeaderProps {
  isAuthenticated: boolean;
  isAdmin: boolean;
  ribbonEnv: string;
  isInProduction: boolean;
  isOpenAPIEnabled: boolean;
  currentLocale: string;
  resources: any;
}

export const UISlice = createSlice({
  name: 'ui',
  initialState: initialState as UIState,
  reducers: {
    setMobileLayoutStatus(state, action: PayloadAction<boolean>) {
      state.mobileLayoutActivated = action.payload;
    },
    setStaticLayoutStatus(state, action: PayloadAction<boolean>) {
      state.staticLayoutActivated = action.payload;
    },
    setUiSettingsStatus(state, action: PayloadAction<boolean>) {
      state.uiSettingsActivated = action.payload;
    },
    setMyProfileStatus(state, action: PayloadAction<boolean>) {
      state.myProfileActivated = action.payload;
    },
    setBreadItems(state, action: PayloadAction<IMenuItem[]>) {
      state.breadItems = action.payload;
    },
  },
});

export const { setMobileLayoutStatus, setStaticLayoutStatus, setUiSettingsStatus, setMyProfileStatus, setBreadItems } = UISlice.actions;

export default UISlice.reducer;
