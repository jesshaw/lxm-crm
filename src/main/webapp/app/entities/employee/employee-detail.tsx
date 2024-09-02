import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
// import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Button } from 'primereact/button';
import { Tooltip } from 'primereact/tooltip';
import { Translate, translate } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './employee.reducer';

export const EmployeeDetail = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const employeeEntity = useAppSelector(state => state.employee.entity);

  return (
    <div className="l-card">
      <h5>
        <Translate contentKey="lxmcrmApp.employee.detail.title">Employee</Translate>
      </h5>
      <div className="l-form">
        <div>
          <label id="id" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip="">
            <Translate contentKey="global.field.id">ID</Translate>
          </label>
          <Tooltip target="#id" />
          <div>{employeeEntity.id}</div>
        </div>
        <div>
          <label id="title" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('lxmcrmApp.employee.help.title')}>
            <Translate contentKey="lxmcrmApp.employee.title">Title</Translate>
          </label>
          <Tooltip target="#title" />
          <div>{employeeEntity.title}</div>
        </div>
        <div>
          <label
            id="nickName"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.employee.help.nickName')}
          >
            <Translate contentKey="lxmcrmApp.employee.nickName">Nick Name</Translate>
          </label>
          <Tooltip target="#nickName" />
          <div>{employeeEntity.nickName}</div>
        </div>
        <div>
          <label>
            <Translate contentKey="lxmcrmApp.employee.user">User</Translate>
          </label>
          <div>{employeeEntity.user ? employeeEntity.user.login : ''}</div>
        </div>
        <div>
          <label>
            <Translate contentKey="lxmcrmApp.employee.reportsTo">Reports To</Translate>
          </label>
          <div>{employeeEntity.reportsTo ? employeeEntity.reportsTo.nickName : ''}</div>
        </div>
      </div>

      <div className="l-form-footer">
        <Button label={translate('entity.action.back')} icon="pi pi-arrow-left" outlined onClick={() => navigate(-1)} />
        <Button label={translate('entity.action.edit')} icon="pi pi-save" onClick={() => navigate(`/employee/${employeeEntity.id}/edit`)} />
      </div>
    </div>
  );
};

export default EmployeeDetail;
