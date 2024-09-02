import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
// import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Button } from 'primereact/button';
import { Tooltip } from 'primereact/tooltip';
import { Translate, translate } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './resource.reducer';

export const ResourceDetail = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const resourceEntity = useAppSelector(state => state.resource.entity);

  return (
    <div className="l-card">
      <h5>
        <Translate contentKey="lxmcrmApp.resource.detail.title">Resource</Translate>
      </h5>
      <div className="l-form">
        <div>
          <label id="id" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip="">
            <Translate contentKey="global.field.id">ID</Translate>
          </label>
          <Tooltip target="#id" />
          <div>{resourceEntity.id}</div>
        </div>
        <div>
          <label id="name" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('lxmcrmApp.resource.help.name')}>
            <Translate contentKey="lxmcrmApp.resource.name">Name</Translate>
          </label>
          <Tooltip target="#name" />
          <div>{resourceEntity.name}</div>
        </div>
        <div>
          <label
            id="permission"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.resource.help.permission')}
          >
            <Translate contentKey="lxmcrmApp.resource.permission">Permission</Translate>
          </label>
          <Tooltip target="#permission" />
          <div>{resourceEntity.permission}</div>
        </div>
        <div>
          <label>
            <Translate contentKey="lxmcrmApp.resource.authority">Authority</Translate>
          </label>
          <div>{resourceEntity.authority ? resourceEntity.authority.name : ''}</div>
        </div>
      </div>

      <div className="l-form-footer">
        <Button label={translate('entity.action.back')} icon="pi pi-arrow-left" outlined onClick={() => navigate(-1)} />
        <Button label={translate('entity.action.edit')} icon="pi pi-save" onClick={() => navigate(`/resource/${resourceEntity.id}/edit`)} />
      </div>
    </div>
  );
};

export default ResourceDetail;
