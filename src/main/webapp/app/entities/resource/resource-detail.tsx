import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './resource.reducer';

export const ResourceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const resourceEntity = useAppSelector(state => state.resource.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="resourceDetailsHeading">
          <Translate contentKey="lxmcrmApp.resource.detail.title">Resource</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{resourceEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="lxmcrmApp.resource.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="lxmcrmApp.resource.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{resourceEntity.name}</dd>
          <dt>
            <span id="permission">
              <Translate contentKey="lxmcrmApp.resource.permission">Permission</Translate>
            </span>
          </dt>
          <dd>{resourceEntity.permission}</dd>
          <dt>
            <Translate contentKey="lxmcrmApp.resource.authority">Authority</Translate>
          </dt>
          <dd>{resourceEntity.authority ? resourceEntity.authority.name : ''}</dd>
        </dl>
        <Button tag={Link} to="/resource" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/resource/${resourceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ResourceDetail;
