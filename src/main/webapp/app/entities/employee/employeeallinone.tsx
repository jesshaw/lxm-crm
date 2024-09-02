import React, { useState, useEffect, useRef } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Translate, getSortState, translate } from 'react-jhipster';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, deleteEntity, createEntity, updateEntity } from './employee.reducer';
import { IEmployee } from 'app/shared/model/employee.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';

import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { DataTable, DataTableStateEvent } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputNumber } from 'primereact/inputnumber';
import { Dropdown, DropdownChangeEvent } from 'primereact/dropdown';

export const Employee = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const employeeList = useAppSelector(state => state.employee.entities);
  const loading = useAppSelector(state => state.employee.loading);
  const users = useAppSelector(state => state.userManagement.users);
  const updating = useAppSelector(state => state.employee.updating);
  const updateSuccess = useAppSelector(state => state.employee.updateSuccess);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const onSort = (e: DataTableStateEvent) => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: e.sortField,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  let emptyEmployee: IEmployee = {};

  const [deleteEmployeeDialog, setDeleteEmployeeDialog] = useState<boolean>(false);
  const [employee, setEmployee] = useState<IEmployee>(emptyEmployee);
  const [employeeDialog, setEmployeeDialog] = useState<boolean>(false);
  const [submitted, setSubmitted] = useState<boolean>(false);

  useEffect(() => {
    if (employeeDialog && users.length <= 0) {
      dispatch(getUsers({}));
    }
  }, [employeeDialog]);

  const hideDeleteEmployeeDialog = () => {
    setDeleteEmployeeDialog(false);
  };

  const toast = useRef<Toast>(null);
  const dt = useRef<DataTable<IEmployee[]>>(null);

  const startToolbarTemplate = () => {
    return (
      <div className="flex flex-wrap gap-2">
        <Button
          label={translate('lxmcrmApp.employee.home.refreshListLabel')}
          icon={`pi ${loading ? 'pi-spin' : ''} pi-refresh`}
          onClick={handleSyncList}
          disabled={loading}
        />
        <Button label={translate('lxmcrmApp.employee.home.createLabel')} icon="pi pi-plus" severity="success" onClick={openNew} />
      </div>
    );
  };
  const openNew = () => {
    setEmployee(emptyEmployee);
    setSubmitted(false);
    setEmployeeDialog(true);
  };

  const endToolbarTemplate = () => {
    return <Button label="Export" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} />;
  };
  const exportCSV = () => {
    dt.current?.exportCSV();
  };

  const actionBodyTemplate = (rowData: IEmployee) => {
    return (
      <React.Fragment>
        <Button icon="pi pi-pencil" rounded outlined className="mr-2" onClick={() => editEmployee(rowData)} />
        <Button icon="pi pi-trash" rounded outlined severity="danger" onClick={() => confirmDeleteEmployee(rowData)} />
      </React.Fragment>
    );
  };
  const editEmployee = (employee: IEmployee) => {
    setEmployee({ ...employee });
    setEmployeeDialog(true);
  };

  const confirmDeleteEmployee = (employee: IEmployee) => {
    setEmployee(employee);
    setDeleteEmployeeDialog(true);
  };

  const deleteEmployee = () => {
    dispatch(deleteEntity(employee.id));

    setDeleteEmployeeDialog(false);
    setEmployee(emptyEmployee);
    toast.current?.show({ severity: 'success', summary: 'Successful', detail: 'Employee Deleted', life: 3000 });
  };

  const hideDialog = () => {
    setSubmitted(false);
    setEmployeeDialog(false);
  };

  const onInputChange = (e: React.ChangeEvent<HTMLInputElement>, name: string) => {
    const val = (e.target && e.target.value) || '';
    let _employee = { ...employee };

    // @ts-ignore
    _employee[name] = val;

    setEmployee(_employee);
  };

  const onDropdwonChange = (e: DropdownChangeEvent, name: string, list) => {
    const val = e.value || '';
    let _employee = { ...employee };

    // @ts-ignore
    _employee[name] = list.find(it => it.id === val);

    setEmployee(_employee);
  };

  useEffect(() => {
    if (updateSuccess) {
      setEmployeeDialog(false);
      setEmployee(emptyEmployee);
      sortEntities();
    }
  }, [updateSuccess]);

  const saveEmpoyee = () => {
    setSubmitted(true);
    if (employee.id) {
      dispatch(updateEntity(employee));
    } else {
      dispatch(createEntity(employee));
    }
  };

  const header = (
    <div className="align-items-center justify-content-between flex flex-wrap gap-2">
      <h4 className="m-0">
        <Translate contentKey="lxmcrmApp.employee.home.title">Employees</Translate>
      </h4>
    </div>
  );

  const employeeDialogFooter = (
    <React.Fragment>
      <Button label={translate('entity.action.cancel')} icon="pi pi-times" outlined onClick={hideDialog} />
      <Button label={translate('entity.action.save')} icon="pi pi-check" onClick={saveEmpoyee} disabled={updating} />
    </React.Fragment>
  );

  const deleteEmployeeDialogFooter = (
    <React.Fragment>
      <Button label={translate('entity.action.cancel')} icon="pi pi-times" outlined onClick={hideDeleteEmployeeDialog} />
      <Button label={translate('entity.action.delete')} icon="pi pi-check" severity="danger" onClick={deleteEmployee} />
    </React.Fragment>
  );

  return (
    <div>
      <Toast ref={toast} />
      <div>
        <Toolbar className="mb-4" start={startToolbarTemplate} end={endToolbarTemplate}></Toolbar>

        <DataTable
          ref={dt}
          value={employeeList}
          dataKey="id"
          header={header}
          emptyMessage={translate('lxmcrmApp.employee.home.notFound')}
          sortField={sortState.sort}
          sortOrder={sortState.order === ASC ? -1 : 1}
          onSort={onSort}
          paginator
          rows={10}
          rowsPerPageOptions={[5, 10, 25]}
        >
          <Column field="id" header={translate('lxmcrmApp.employee.id')} sortable></Column>
          <Column field="title" header={translate('lxmcrmApp.employee.title')} sortable></Column>
          <Column
            body={(employee: IEmployee) => {
              return <>{employee.user ? employee.user.login : ''}</>;
            }}
            header={translate('lxmcrmApp.employee.user')}
          ></Column>
          <Column body={actionBodyTemplate} exportable={false} style={{ minWidth: '12rem' }}></Column>
        </DataTable>
      </div>

      <Dialog
        visible={employeeDialog}
        style={{ width: '32rem' }}
        breakpoints={{ '960px': '75vw', '641px': '90vw' }}
        header={translate('lxmcrmApp.employee.home.createOrEditLabel')}
        modal
        className="p-fluid"
        footer={employeeDialogFooter}
        onHide={hideDialog}
      >
        {!!employee.id && (
          <div className="field">
            <label htmlFor="employee-id" className="font-bold">
              <Translate contentKey="global.field.id" />
            </label>
            <InputNumber id="employee-id" name="id" value={employee.id} disabled />
          </div>
        )}
        <div className="field">
          <label htmlFor="employee-title" className="font-bold">
            <Translate contentKey="lxmcrmApp.employee.title" />
          </label>
          <InputText
            id="employee-title"
            name="title"
            value={employee.title}
            onChange={e => onInputChange(e, 'title')}
            required
            autoFocus
            className={submitted && !employee.title ? 'p-invalid' : ''}
          />
        </div>
        {!employee.id && (
          <div className="field">
            <label htmlFor="employee-user" className="font-bold">
              <Translate contentKey="lxmcrmApp.employee.user" />
            </label>
            <Dropdown
              id="employee-user"
              name="user"
              value={employee.user ? employee.user.id : ''}
              onChange={e => onDropdwonChange(e, 'user', users)}
              options={users}
              optionLabel="login"
              optionValue="id"
              showClear
            />
          </div>
        )}
      </Dialog>

      <Dialog
        visible={deleteEmployeeDialog}
        style={{ width: '32rem' }}
        breakpoints={{ '960px': '75vw', '641px': '90vw' }}
        header={translate('entity.delete.title')}
        modal
        footer={deleteEmployeeDialogFooter}
        onHide={hideDeleteEmployeeDialog}
      >
        <div className="confirmation-content">
          <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
          {employee && (
            <Translate contentKey="lxmcrmApp.employee.delete.question" interpolate={{ id: employee.id }}>
              Are you sure you want to delete this Employee?
            </Translate>
          )}
        </div>
      </Dialog>
    </div>
  );
};

export default Employee;
