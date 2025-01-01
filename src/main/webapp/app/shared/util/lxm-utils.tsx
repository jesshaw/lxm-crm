import * as React from 'react';
import dayjs from 'dayjs';
import { FilterMatchMode } from 'primereact/api';
import { Calendar } from 'primereact/calendar';
import { DataTableFilterMeta, DataTableFilterMetaData, DataTableOperatorFilterMetaData } from 'primereact/datatable';
import { InputNumber } from 'primereact/inputnumber';
import { TriStateCheckbox } from 'primereact/tristatecheckbox';
import { Slider } from 'primereact/slider';
import { Button } from 'reactstrap';
import { ColumnProps } from 'primereact/column';

export interface LxmColumnProps extends ColumnProps {
  headerKey?: string; // for translate
}

export const convertFiltersToQueryString = (filters: DataTableFilterMeta): string => {
  const params = new URLSearchParams();

  Object.entries(filters).forEach(([field, filter]) => {
    // 判断字段是否为 DataTableOperatorFilterMetaData 类型
    if ((filter as DataTableOperatorFilterMetaData).constraints) {
      const operatorFilter = filter as DataTableOperatorFilterMetaData;
      operatorFilter.constraints.forEach((constraint, index) => {
        if (constraint.value != null) {
          const filterOptions = convertField(field, constraint as DataTableFilterMetaData);
          // console.log(filterOption);
          filterOptions.forEach(item => {
            params.append(item?.field, item?.value);
          });
        }
      });
    } else if ((filter as DataTableFilterMetaData).value != null) {
      const filterOptions = convertField(field, filter as DataTableFilterMetaData);
      // console.log(filterOption);
      filterOptions.forEach(item => {
        params.append(item?.field, item?.value);
      });
    }
  });

  return params.toString();
};

const convertField = (field: string, filter: DataTableFilterMetaData) => {
  switch (filter.matchMode) {
    case FilterMatchMode.CONTAINS:
      return [{ field: `${field}.contains`, value: `%${filter.value}%` }];
    case FilterMatchMode.STARTS_WITH:
      return [{ field: `${field}.contains`, value: `${filter.value}%` }];
    case FilterMatchMode.ENDS_WITH:
      return [{ field: `${field}.contains`, value: `%${filter.value}` }];
    case FilterMatchMode.NOT_CONTAINS:
      return [{ field: `${field}.doesNotContain`, value: `%${filter.value}%` }];
    case FilterMatchMode.EQUALS:
      return [{ field: `${field}.equals`, value: filter.value }];
    case FilterMatchMode.NOT_EQUALS:
      return [{ field: `${field}.notEquals`, value: filter.value }];
    case FilterMatchMode.LESS_THAN:
      return [{ field: `${field}.lessThan`, value: filter.value }];
    case FilterMatchMode.LESS_THAN_OR_EQUAL_TO:
      return [{ field: `${field}.lessThanOrEqual`, value: filter.value }];
    case FilterMatchMode.GREATER_THAN:
      return [{ field: `${field}.greaterThan`, value: filter.value }];
    case FilterMatchMode.GREATER_THAN_OR_EQUAL_TO:
      return [{ field: `${field}.greaterThanOrEqual`, value: filter.value }];
    case FilterMatchMode.DATE_IS:
      return [{ field: `${field}.equals`, value: dayjs(filter.value).format('YYYY-MM-DD') }];
    case FilterMatchMode.DATE_IS_NOT:
      return [{ field: `${field}.notEquals`, value: dayjs(filter.value).format('YYYY-MM-DD') }];
    case FilterMatchMode.DATE_BEFORE:
      return [{ field: `${field}.lessThan`, value: dayjs(filter.value).format('YYYY-MM-DD') }];
    case FilterMatchMode.DATE_AFTER:
      return [{ field: `${field}.greaterThan`, value: dayjs(filter.value).format('YYYY-MM-DD') }];
    case FilterMatchMode.IN:
      return [{ field: `${field}.in`, value: filter.value }];
    case FilterMatchMode.NOT_IN:
      return [{ field: `${field}.notIn`, value: filter.value }];
    case FilterMatchMode.BETWEEN:
      return [
        { field: `${field}.greaterThanOrEqual`, value: filter.value[0] },
        { field: `${field}.lessThanOrEqual`, value: filter.value[1] },
      ];
    default:
      return null;
  }
};

const filterClearTemplate = options => {
  return <Button type="button" icon="pi pi-times" onClick={options.filterClearCallback} className="p-button-secondary"></Button>;
};

export const dateFilterTemplate = options => {
  return (
    <Calendar
      value={options.value}
      onChange={e => options.filterCallback(e.value, options.index)}
      dateFormat="yy-mm-dd"
      placeholder="yyyy-mm-dd"
      mask="9999-99-99"
    />
  );
};

export const numericFilterTemplate = options => {
  return (
    <InputNumber
      useGrouping={false}
      min={1}
      minFractionDigits={0} // 最小小数位数为 0
      maxFractionDigits={0} // 最大小数位数为 0
      value={options.value}
      onChange={e => options.filterCallback(e.value, options.index)}
    />
  );
};

export const booleanFilterTemplate = options => {
  return <TriStateCheckbox value={options.value} onChange={e => options.filterCallback(e.value)} />;
};

// MultiSelect sample by users: [{id:1,login:'admin'},{id:1,login:'user'}]
// const assignNameFilterTemplate = options => {
//   return (
//     <MultiSelect
//       value={options.value ?? ''}
//       options={users}
//       onChange={e => options.filterCallback(e.value)}
//       optionLabel="login"
//       optionValue="id"
//       placeholder="Any"
//       className="p-column-filter"
//     />
//   );
// };

// percent filter
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
