import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { DataTable, DataTableStateEvent } from 'primereact/datatable';
import { MultiSelect, MultiSelectChangeEvent } from 'primereact/multiselect';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { Tag } from 'primereact/tag';
import { Toolbar } from 'primereact/toolbar';
import { Translate, TextFormat, JhiPagination, JhiItemCount, getPaginationState, translate, TranslatorContext } from 'react-jhipster';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { getUsersAsAdmin, updateUser, deleteUser } from './user-management.reducer';
import { IUser } from 'app/shared/model/user.model';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { MenuItemsData, setBreadItems } from 'app/shared/reducers/ui';

export const UserManagement = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [pagination, setPagination] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  useEffect(() => {
    dispatch(setBreadItems([MenuItemsData.homeMenuItem, MenuItemsData.administrationMenuItem, MenuItemsData.userManagementMenuItem]));
  }, []);

  const getUsersFromProps = () => {
    dispatch(
      getUsersAsAdmin({
        page: pagination.activePage - 1,
        size: pagination.itemsPerPage,
        sort: `${pagination.sort},${pagination.order}`,
      }),
    );
    const endURL = `?page=${pagination.activePage}&size=${pagination.itemsPerPage}&sort=${pagination.sort},${pagination.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    getUsersFromProps();
  }, [pagination.activePage, pagination.itemsPerPage, pagination.order, pagination.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const size = params.get('size');
    const sortParam = params.get(SORT);
    if (page && sortParam) {
      const sortSplit = sortParam.split(',');
      setPagination({
        ...pagination,
        activePage: +page,
        itemsPerPage: parseInt(size),
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const onSort = (e: DataTableStateEvent) => {
    setPagination({
      ...pagination,
      order: pagination.order === ASC ? DESC : ASC,
      sort: e.sortField,
    });
  };

  const onPage = (e: DataTableStateEvent) => {
    setPagination({
      ...pagination,
      activePage: e.page + 1,
      itemsPerPage: e.rows,
    });
  };

  const handleSyncList = () => {
    getUsersFromProps();
  };

  const toggleActive = user => () => {
    dispatch(
      updateUser({
        ...user,
        activated: !user.activated,
      }),
    );
  };

  const users = useAppSelector(state => state.userManagement.users);
  const totalItems = useAppSelector(state => state.userManagement.totalItems);
  const loading = useAppSelector(state => state.userManagement.loading);

  let emptyUser: IUser = {};

  const [deleteUserDialog, setDeleteUserDialog] = useState<boolean>(false);
  const [user, setUser] = useState<IUser>(emptyUser);
  const updateSuccess = useAppSelector(state => state.userManagement.updateSuccess);

  const hideDeleteUserDialog = () => {
    setDeleteUserDialog(false);
  };

  const startToolbarTemplate = () => {
    return (
      <div className="flex flex-wrap gap-2">
        <Button
          label={translate('entity.action.refresh')}
          icon={`pi ${loading ? 'pi-spin' : ''} pi-refresh`}
          onClick={handleSyncList}
          disabled={loading}
        />
        <Button
          label={translate('entity.action.new')}
          icon="pi pi-plus"
          severity="success"
          onClick={() => navigate('/admin/user-management/new')}
        />
      </div>
    );
  };

  const endToolbarTemplate = () => {
    return <></>;
  };

  const confirmDeleteLeadInfo = user => {
    setUser(user);
    setDeleteUserDialog(true);
  };
  const confirmDeleateUser = () => {
    dispatch(deleteUser(user.login));
  };

  useEffect(() => {
    if (updateSuccess && deleteUserDialog) {
      setDeleteUserDialog(false);
      getUsersFromProps();
      setUser(emptyUser);
    }
  }, [updateSuccess]);

  const deleteLeadInfoDialogFooter = (
    <>
      <Button label={translate('entity.action.cancel')} icon="pi pi-times" outlined onClick={hideDeleteUserDialog} />
      <Button label={translate('entity.action.delete')} icon="pi pi-check" severity="danger" onClick={confirmDeleateUser} />
    </>
  );

  const allColumns = [
    {
      field: 'id',
      headerKey: 'global.field.id',
      sortable: true,
      body: rowData => {
        return <Link to={`/admin/user-management/${rowData.login}`}>{rowData.id}</Link>;
      },
    },
    {
      field: 'login',
      headerKey: 'userManagement.login',
      sortable: true,
    },
    {
      field: 'email',
      headerKey: 'userManagement.email',
      sortable: true,
    },
    {
      field: 'activated',
      headerKey: 'userManagement.activated',
      sortable: true,
      body: rowData => {
        return rowData.activated ? (
          <Button size="small" severity="success" label={translate('userManagement.activated')} onClick={toggleActive(rowData)} />
        ) : (
          <Button size="small" severity="danger" label={translate('userManagement.deactivated')} onClick={toggleActive(rowData)} />
        );
      },
    },
    {
      field: 'langKey',
      headerKey: 'userManagement.langKey',
      sortable: true,
    },
    {
      field: 'profiles',
      headerKey: 'userManagement.profiles',
      body: rowData => {
        return rowData.authorities
          ? rowData.authorities.map((authority, j) => <Tag severity="info" className="mb-1 ml-1" value={authority} />)
          : null;
      },
    },
    {
      field: 'createdDate',
      headerKey: 'userManagement.createdDate',
      sortable: true,
      body: rowData => {
        return rowData.createdDate && <TextFormat type="date" value={rowData.createdDate} format={APP_DATE_FORMAT} />;
      },
    },
    {
      field: 'lastModifiedBy',
      headerKey: 'userManagement.lastModifiedBy',
      sortable: true,
    },
    {
      field: 'lastModifiedDate',
      headerKey: 'userManagement.lastModifiedDate',
      sortable: true,
      body: rowData => {
        return rowData.lastModifiedDate && <TextFormat type="date" value={rowData.lastModifiedDate} format={APP_DATE_FORMAT} />;
      },
    },
    {
      field: 'lOperate',
      headerKey: 'entity.cloumn.operation',
      exportable: false,
      style: { minWidth: '12rem' },
      body: rowData => {
        return (
          <>
            <Button
              icon="pi pi-eye"
              tooltip={translate('entity.action.view')}
              tooltipOptions={{ position: 'top' }}
              rounded
              outlined
              className="mr-2"
              onClick={() => navigate(`/admin/user-management/${rowData.login}`)}
            />
            <Button
              icon="pi pi-pencil"
              tooltip={translate('entity.action.edit')}
              tooltipOptions={{ position: 'top' }}
              rounded
              outlined
              className="mr-2"
              onClick={() =>
                navigate(
                  `/admin/user-management/${rowData.login}/edit?page=${pagination.activePage}&size=${pagination.itemsPerPage}&sort=${pagination.sort},${pagination.order}`,
                )
              }
            />
            <Button
              icon="pi pi-trash"
              tooltip={translate('entity.action.delete')}
              tooltipOptions={{ position: 'top' }}
              rounded
              outlined
              severity="danger"
              onClick={() => confirmDeleteLeadInfo(rowData)}
            />
          </>
        );
      },
    },
  ];

  //localStorage key used to store display columns
  const entityColumnsKey = 'lxmcrmApp.userManagement.cloumns';

  const getDefaultSelectedColumns = () => {
    let storageColumns = localStorage.getItem(entityColumnsKey);
    console.log(storageColumns);
    let defaultSelectedColumns;
    if (storageColumns !== null) {
      defaultSelectedColumns = JSON.parse(storageColumns);
    } else {
      let willBeDisplayedColumns = allColumns.length <= 6 ? allColumns.slice(0, allColumns.length - 2) : allColumns.slice(0, 5);
      willBeDisplayedColumns.push(allColumns[allColumns.length - 1]); // push last operation comlumn
      defaultSelectedColumns = willBeDisplayedColumns.map(item => ({
        header: translate(item.headerKey),
        headerKey: item.headerKey,
        field: item.field,
      }));
    }
    return defaultSelectedColumns;
  };

  const [selectedColumns, setSelectedColumns] = useState(getDefaultSelectedColumns());

  const columnOptions = allColumns.map(item => ({
    header: translate(item.headerKey),
    headerKey: item.headerKey,
    field: item.field,
  }));

  useEffect(() => {
    let _selectedColumns = [...selectedColumns];
    _selectedColumns.forEach(item => (item.header = translate(item.headerKey)));
    // console.log(_selectedColumns);
    setSelectedColumns(_selectedColumns);
  }, [TranslatorContext.context.locale]);

  const onColumnToggle = (event: MultiSelectChangeEvent) => {
    let _selectedColumns = [...selectedColumns];
    const selectedOption = event.selectedOption;
    if (Array.isArray(selectedOption)) {
      if (_selectedColumns.length < columnOptions.length) {
        _selectedColumns = columnOptions;
      } else {
        _selectedColumns = [];
      }
    } else {
      const existingIndex = _selectedColumns.findIndex(existingCol => existingCol.field === selectedOption.field);
      if (existingIndex !== -1) {
        _selectedColumns.splice(existingIndex, 1);
      } else {
        _selectedColumns.push(columnOptions.find(item => item.field === selectedOption.field));
      }
    }

    setSelectedColumns(_selectedColumns);
    localStorage.setItem(entityColumnsKey, JSON.stringify(_selectedColumns));
  };

  const header = (
    <div className="l-datatable-header">
      <h5>
        <Translate contentKey="userManagement.home.title">Users</Translate>
      </h5>
      <MultiSelect
        value={selectedColumns}
        options={columnOptions}
        optionLabel="header"
        filter
        onChange={onColumnToggle}
        className="l-select-columns"
        placeholder={translate('entity.cloumn.placeholder')}
        display="chip"
      />
    </div>
  );

  const dynamicColumns = selectedColumns
    .filter(selectedCol => allColumns.some(col => col.field === selectedCol.field))
    .map(selectedCol => {
      const column = allColumns.find(col => col.field === selectedCol.field);
      return (
        <Column
          key={column?.field}
          field={column?.field}
          header={translate(column?.headerKey)}
          body={column?.body ? column.body : undefined}
          sortable={column?.sortable}
          exportable={column?.exportable}
          style={column?.style}
        />
      );
    });

  return (
    <>
      <div className="l-card">
        <Toolbar className="l-toolbar" start={startToolbarTemplate} end={endToolbarTemplate}></Toolbar>
        <DataTable
          value={users}
          dataKey="id"
          stripedRows
          header={header}
          emptyMessage={translate('lxmcrmApp.leadInfo.home.notFound')}
          onSort={onSort} //sort by backend
          sortField={pagination.sort}
          sortOrder={pagination.order === ASC ? -1 : 1}
          onPage={onPage} //sort by backend
          lazy
          loading={loading}
          paginator
          first={(pagination.activePage - 1) * pagination.itemsPerPage} //current page start index
          rows={pagination.itemsPerPage}
          totalRecords={totalItems}
          rowsPerPageOptions={[5, 10, 20, 50]}
          paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
          currentPageReportTemplate={translate('global.item-count')}
        >
          {dynamicColumns}
        </DataTable>
      </div>

      <Dialog
        visible={deleteUserDialog}
        style={{ width: '32rem' }}
        breakpoints={{ '960px': '75vw', '641px': '90vw' }}
        header={translate('entity.delete.title')}
        modal
        footer={deleteLeadInfoDialogFooter}
        onHide={hideDeleteUserDialog}
      >
        <div className="confirmation-content">
          <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
          {user && (
            <Translate contentKey="userManagement.delete.question" interpolate={{ login: user.login }}>
              Are you sure you want to delete this User?
            </Translate>
          )}
        </div>
      </Dialog>
    </>
  );
};

export default UserManagement;
