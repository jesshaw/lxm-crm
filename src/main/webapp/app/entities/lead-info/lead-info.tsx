import React, { useState, useEffect, useRef } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
// import { Button, Table } from 'reactstrap';
import { Toolbar } from 'primereact/toolbar';
import { DataTable, DataTableFilterMeta, DataTableStateEvent } from 'primereact/datatable';
import { MultiSelect, MultiSelectChangeEvent } from 'primereact/multiselect';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import {
  byteSize,
  Translate,
  translate,
  TranslatorContext,
  TextFormat,
  getPaginationState,
  JhiPagination,
  JhiItemCount,
} from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities, deleteEntity } from './lead-info.reducer';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { ILeadInfo } from 'app/shared/model/lead-info.model';
import { MenuItemsData, setBreadItems } from 'app/shared/reducers/ui';
import { FilterMatchMode, FilterOperator } from 'primereact/api';
import { classNames } from 'primereact/utils';
import { Slider } from 'primereact/slider';
import { booleanFilterTemplate, convertFiltersToQueryString, dateFilterTemplate, numericFilterTemplate } from 'app/shared/util/lxm-utils';

export const LeadInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const [filters, setFilters] = useState<DataTableFilterMeta>({
    // global: { value: null, matchMode: FilterMatchMode.CONTAINS },
    id: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.EQUALS }],
    },
    firstName: { value: null, matchMode: FilterMatchMode.STARTS_WITH },
    // dateReviewed: { value: null, matchMode: FilterMatchMode.DATE_IS },
    dateReviewed: {
      operator: FilterOperator.AND,
      constraints: [{ value: null, matchMode: FilterMatchMode.DATE_IS }],
    },
    doNotCall: { value: null, matchMode: FilterMatchMode.EQUALS },
    assignedUserId: { value: null, matchMode: FilterMatchMode.IN },
  });

  useEffect(() => {
    dispatch(setBreadItems([MenuItemsData.homeMenuItem, MenuItemsData.entitesMenuItem, MenuItemsData.leadInfoMenuItem]));
    dispatch(getUsers({}));
  }, []);

  const leadInfoList = useAppSelector(state => state.leadInfo.entities);
  const loading = useAppSelector(state => state.leadInfo.loading);
  const totalItems = useAppSelector(state => state.leadInfo.totalItems);
  const users = useAppSelector(state => state.userManagement.users);

  const getAllEntities = query => {
    dispatch(
      getEntities({
        query,
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    const queryString = convertFiltersToQueryString(filters);
    console.log(queryString);

    getAllEntities(queryString);

    const endURL = `?${queryString}&page=${paginationState.activePage}&size=${paginationState.itemsPerPage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.itemsPerPage, paginationState.order, paginationState.sort, filters]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const size = params.get('size');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        itemsPerPage: parseInt(size),
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const onSort = (e: DataTableStateEvent) => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: e.sortField,
    });
  };

  const onPage = (e: DataTableStateEvent) => {
    setPaginationState({
      ...paginationState,
      activePage: e.page + 1,
      itemsPerPage: e.rows,
    });
  };

  const onFilter = (e: DataTableStateEvent) => {
    console.log(e.filters);
    console.log(filters);
    // const value = e..target.value;
    // let _filters = { ...filters };

    // // @ts-ignore
    // _filters['global'].value = value;

    setFilters(e.filters);

    // setPaginationState({
    //   ...paginationState,
    //   activePage: e.page + 1,
    //   itemsPerPage: e.rows,
    // });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  let emptyLeadInfo: ILeadInfo = {};

  const [deleteLeadInfoDialog, setDeleteLeadInfoDialog] = useState<boolean>(false);
  const [leadInfo, setLeadInfo] = useState<ILeadInfo>(emptyLeadInfo);
  const updateSuccess = useAppSelector(state => state.leadInfo.updateSuccess);

  const hideDeleteLeadInfoDialog = () => {
    setDeleteLeadInfoDialog(false);
  };

  const dt = useRef<DataTable<ILeadInfo[]>>(null);

  const startToolbarTemplate = () => {
    return (
      <div className="flex flex-wrap gap-2">
        <Button
          label={translate('entity.action.refresh')}
          icon={`pi ${loading ? 'pi-spin' : ''} pi-refresh`}
          onClick={handleSyncList}
          disabled={loading}
        />
        <Button label={translate('entity.action.new')} icon="pi pi-plus" severity="success" onClick={() => navigate('/lead-info/new')} />
      </div>
    );
  };

  const endToolbarTemplate = () => {
    return (
      <>
        <Button label={translate('entity.action.export')} icon="pi pi-upload" className="p-button-help" onClick={exportCSV} />
      </>
    );
  };

  const exportCSV = () => {
    dt.current?.exportCSV();
  };

  const confirmDeleteLeadInfo = (leadInfo: ILeadInfo) => {
    setLeadInfo(leadInfo);
    setDeleteLeadInfoDialog(true);
  };

  const deleteLeadInfo = () => {
    dispatch(deleteEntity(leadInfo.id));
  };

  useEffect(() => {
    if (updateSuccess && deleteLeadInfoDialog) {
      setDeleteLeadInfoDialog(false);
      sortEntities();
      setLeadInfo(emptyLeadInfo);
    }
  }, [updateSuccess]);

  const deleteLeadInfoDialogFooter = (
    <>
      <Button label={translate('entity.action.cancel')} icon="pi pi-times" outlined onClick={hideDeleteLeadInfoDialog} />
      <Button label={translate('entity.action.delete')} icon="pi pi-check" severity="danger" onClick={deleteLeadInfo} />
    </>
  );

  // const dateFilterTemplate = options => {
  //   return (
  //     <Calendar
  //       value={options.value}
  //       onChange={e => options.filterCallback(e.value, options.index)}
  //       dateFormat="yy-mm-dd"
  //       placeholder="yyyy-mm-dd"
  //       mask="9999-99-99"
  //     />
  //   );
  // };

  // const numericFilterTemplate = options => {
  //   return (
  //     <InputNumber
  //       useGrouping={false}
  //       min={1}
  //       minFractionDigits={0} // 最小小数位数为 0
  //       maxFractionDigits={0} // 最大小数位数为 0
  //       value={options.value}
  //       onChange={e => options.filterCallback(e.value, options.index)}
  //     />
  //   );
  // };

  // const booleanFilterTemplate = options => {
  //   return <TriStateCheckbox value={options.value} onChange={e => options.filterCallback(e.value)} />;
  // };

  // const assignNameFilterTemplate1 = (options: ColumnFilterElementTemplateOptions) => {
  //   return (
  //     <>
  //       <MultiSelect
  //         options={users}
  //         onChange={(e: MultiSelectChangeEvent) => options.filterApplyCallback(e.value)}
  //         optionLabel="login"
  //         optionValue="id"
  //         className="p-column-filter"
  //         maxSelectedLabels={1}
  //       />
  //     </>
  //   );
  // };

  // 多选
  const assignNameFilterTemplate = options => {
    return (
      <MultiSelect
        value={options.value ?? ''}
        options={users}
        onChange={e => options.filterCallback(e.value)}
        optionLabel="login"
        optionValue="id"
        placeholder="Any"
        className="p-column-filter"
      />
    );
  };

  // 进度百分比
  const percentFilterTemplate = options => {
    return (
      <React.Fragment>
        <Slider value={options.value} onChange={e => options.filterCallback(e.value)} range className="m-3"></Slider>
        <div className="align-items-center justify-content-between flex px-2">
          <span>{options.value ? options.value[0] : 0}</span>
          <span>{options.value ? options.value[1] : 100}</span>
        </div>
      </React.Fragment>
    );
  };

  const allColumns = [
    {
      field: 'id',
      headerKey: 'lxmcrmApp.leadInfo.id',
      sortable: true,
      filter: true,
      filterElement: numericFilterTemplate,
      dataType: 'numeric',
      showFilterOperator: false,
    },
    {
      field: 'salutation',
      headerKey: 'lxmcrmApp.leadInfo.salutation',
      sortable: true,
    },
    {
      field: 'firstName',
      headerKey: 'lxmcrmApp.leadInfo.firstName',
      sortable: true,
      filter: true,
    },
    {
      field: 'lastName',
      headerKey: 'lxmcrmApp.leadInfo.lastName',
      sortable: true,
    },
    {
      field: 'title',
      headerKey: 'lxmcrmApp.leadInfo.title',
      sortable: true,
    },
    {
      field: 'department',
      headerKey: 'lxmcrmApp.leadInfo.department',
      sortable: true,
    },
    {
      field: 'doNotCall',
      headerKey: 'lxmcrmApp.leadInfo.doNotCall',
      sortable: true,
      filter: true,
      filterElement: booleanFilterTemplate,
      dataType: 'boolean',
      body: rowData => {
        return (
          <i
            className={classNames('pi', {
              'true-icon pi-check-circle text-green-500': rowData.doNotCall,
              'false-icon pi-times-circle text-red-500': !rowData.doNotCall,
            })}
          ></i>
        );
      },
    },
    {
      field: 'phoneHome',
      headerKey: 'lxmcrmApp.leadInfo.phoneHome',
      sortable: true,
    },
    {
      field: 'phoneMobile',
      headerKey: 'lxmcrmApp.leadInfo.phoneMobile',
      sortable: true,
    },
    {
      field: 'phoneWork',
      headerKey: 'lxmcrmApp.leadInfo.phoneWork',
      sortable: true,
    },
    {
      field: 'phoneOther',
      headerKey: 'lxmcrmApp.leadInfo.phoneOther',
      sortable: true,
    },
    {
      field: 'phoneFax',
      headerKey: 'lxmcrmApp.leadInfo.phoneFax',
      sortable: true,
    },
    {
      field: 'dateReviewed',
      headerKey: 'lxmcrmApp.leadInfo.dateReviewed',
      sortable: true,
      filter: true,
      dataType: 'date',
      showFilterOperator: false,
      filterElement: dateFilterTemplate,
      body: rowData => {
        return rowData.dateReviewed && <TextFormat type="date" value={rowData.dateReviewed} format={APP_LOCAL_DATE_FORMAT} />;
      },
    },
    {
      field: 'lawfulBasis',
      headerKey: 'lxmcrmApp.leadInfo.lawfulBasis',
      sortable: true,
    },
    {
      field: 'lawfulBasisSource',
      headerKey: 'lxmcrmApp.leadInfo.lawfulBasisSource',
      sortable: true,
    },
    {
      field: 'primaryAddressStreet',
      headerKey: 'lxmcrmApp.leadInfo.primaryAddressStreet',
      sortable: true,
    },
    {
      field: 'primaryAddressCity',
      headerKey: 'lxmcrmApp.leadInfo.primaryAddressCity',
      sortable: true,
    },
    {
      field: 'primaryAddressState',
      headerKey: 'lxmcrmApp.leadInfo.primaryAddressState',
      sortable: true,
    },
    {
      field: 'primaryAddressPostalcode',
      headerKey: 'lxmcrmApp.leadInfo.primaryAddressPostalcode',
      sortable: true,
    },
    {
      field: 'primaryAddressCountry',
      headerKey: 'lxmcrmApp.leadInfo.primaryAddressCountry',
      sortable: true,
    },
    {
      field: 'altAddressStreet',
      headerKey: 'lxmcrmApp.leadInfo.altAddressStreet',
      sortable: true,
    },
    {
      field: 'altAddressCity',
      headerKey: 'lxmcrmApp.leadInfo.altAddressCity',
      sortable: true,
    },
    {
      field: 'altAddressState',
      headerKey: 'lxmcrmApp.leadInfo.altAddressState',
      sortable: true,
    },
    {
      field: 'altAddressPostalcode',
      headerKey: 'lxmcrmApp.leadInfo.altAddressPostalcode',
      sortable: true,
    },
    {
      field: 'altAddressCountry',
      headerKey: 'lxmcrmApp.leadInfo.altAddressCountry',
      sortable: true,
    },
    {
      field: 'assistant',
      headerKey: 'lxmcrmApp.leadInfo.assistant',
      sortable: true,
    },
    {
      field: 'assistantPhone',
      headerKey: 'lxmcrmApp.leadInfo.assistantPhone',
      sortable: true,
    },
    {
      field: 'converted',
      headerKey: 'lxmcrmApp.leadInfo.converted',
      sortable: true,
      body: rowData => {
        return <>{rowData.converted ? 'true' : 'false'}</>;
      },
    },
    {
      field: 'referedBy',
      headerKey: 'lxmcrmApp.leadInfo.referedBy',
      sortable: true,
    },
    {
      field: 'leadSource',
      headerKey: 'lxmcrmApp.leadInfo.leadSource',
      sortable: true,
    },
    {
      field: 'leadSourceDescription',
      headerKey: 'lxmcrmApp.leadInfo.leadSourceDescription',
      sortable: true,
    },
    {
      field: 'status',
      headerKey: 'lxmcrmApp.leadInfo.status',
      sortable: true,
    },
    {
      field: 'statusDescription',
      headerKey: 'lxmcrmApp.leadInfo.statusDescription',
      sortable: true,
    },
    {
      field: 'birthdate',
      headerKey: 'lxmcrmApp.leadInfo.birthdate',
      sortable: true,
      body: rowData => {
        return rowData.birthdate && <TextFormat type="date" value={rowData.birthdate} format={APP_LOCAL_DATE_FORMAT} />;
      },
    },
    {
      field: 'description',
      headerKey: 'lxmcrmApp.leadInfo.description',
      sortable: true,
    },
    {
      field: 'reportsTo.lastName',
      headerKey: 'lxmcrmApp.leadInfo.reportsTo',
      sortable: true,
      body: rowData => {
        return (
          rowData.reportsTo && (
            <Button text label={rowData.reportsTo.lastName} onClick={() => navigate(`/lead-info/${rowData.reportsTo.id}`)} />
          )
        );
      },
    },
    {
      field: 'assignedUserId',
      headerKey: 'lxmcrmApp.leadInfo.assignedUser',
      sortable: true,
      filter: true,
      showFilterMatchModes: false,
      filterElement: assignNameFilterTemplate,
      body: rowData => {
        return <>{rowData.assignedUser ? rowData.assignedUser.login : ''}</>;
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
              onClick={() => navigate(`/lead-info/${rowData.id}`)}
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
                  `/lead-info/${rowData.id}/edit?page=${paginationState.activePage}&size=${paginationState.itemsPerPage}&sort=${paginationState.sort},${paginationState.order}`,
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
  const entityColumnsKey = 'lxmcrmApp.leadInfo.cloumns';

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
        <Translate contentKey="lxmcrmApp.leadInfo.home.title">Lead Infos</Translate>
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
          filter={column?.filter}
          dataType={column?.dataType}
          showFilterOperator={column?.showFilterOperator}
          showFilterMatchModes={column?.showFilterMatchModes}
          filterElement={column?.filterElement}
          exportable={column?.exportable}
          style={column?.style}
          filterMenuStyle={{ width: '14rem' }}
        />
      );
    });

  return (
    <>
      <div className="l-card">
        <Toolbar className="l-toolbar" start={startToolbarTemplate} end={endToolbarTemplate}></Toolbar>
        <DataTable
          ref={dt}
          value={leadInfoList}
          dataKey="id"
          selectionMode="single"
          header={header}
          emptyMessage={translate('lxmcrmApp.leadInfo.home.notFound')}
          onSort={onSort} //sort by backend
          sortField={paginationState.sort}
          sortOrder={paginationState.order === ASC ? -1 : 1}
          onPage={onPage} //sort by backend
          filters={filters}
          onFilter={onFilter}
          lazy
          loading={loading}
          paginator
          first={(paginationState.activePage - 1) * paginationState.itemsPerPage} //current page start index
          rows={paginationState.itemsPerPage}
          totalRecords={totalItems}
          rowsPerPageOptions={[5, 10, 20, 50]}
          paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
          currentPageReportTemplate={translate('global.item-count')}
        >
          {dynamicColumns}
        </DataTable>
      </div>

      <Dialog
        visible={deleteLeadInfoDialog}
        style={{ width: '32rem' }}
        breakpoints={{ '960px': '75vw', '641px': '90vw' }}
        header={translate('entity.delete.title')}
        modal
        footer={deleteLeadInfoDialogFooter}
        onHide={hideDeleteLeadInfoDialog}
      >
        <div className="confirmation-content">
          <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
          {leadInfo && (
            <Translate contentKey="lxmcrmApp.leadInfo.delete.question" interpolate={{ id: leadInfo.id }}>
              Are you sure you want to delete this LeadInfo?
            </Translate>
          )}
        </div>
      </Dialog>
    </>
  );
};

export default LeadInfo;
