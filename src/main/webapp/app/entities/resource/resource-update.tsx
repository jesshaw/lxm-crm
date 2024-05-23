import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAuthority } from 'app/shared/model/admin/authority.model';
import { getEntities as getAuthorities } from 'app/entities/admin/authority/authority.reducer';
import { fetchDict } from 'app/entities/dict/dict.reducer';
import { IResource } from 'app/shared/model/resource.model';
import { getEntity, updateEntity, createEntity, reset } from './resource.reducer';

export const ResourceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dict = useAppSelector(state => state.dict.entity);
  const authorities = useAppSelector(state => state.authority.entities);
  const resourceEntity = useAppSelector(state => state.resource.entity);
  const loading = useAppSelector(state => state.resource.loading);
  const updating = useAppSelector(state => state.resource.updating);
  const updateSuccess = useAppSelector(state => state.resource.updateSuccess);

  const handleClose = () => {
    navigate('/resource');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(fetchDict(['resource', 'permission']));
    dispatch(getAuthorities({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...resourceEntity,
      ...values,
      authority: authorities.find(it => it.name.toString() === values.authority?.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...resourceEntity,
          authority: resourceEntity?.authority?.name,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="lxmcrmApp.resource.home.createOrEditLabel" data-cy="ResourceCreateUpdateHeading">
            <Translate contentKey="lxmcrmApp.resource.home.createOrEditLabel">Create or edit a Resource</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="resource-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              {/* <ValidatedField
                label={translate('lxmcrmApp.resource.name')}
                id="resource-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              /> */}
              <ValidatedField label={translate('lxmcrmApp.resource.name')} id="resource-name" name="name" data-cy="name" type="select">
                <option value="" key="0" />
                {dict.resource
                  ? dict.resource.map(otherEntity => (
                      <option value={otherEntity.key} key={otherEntity.key}>
                        {otherEntity.value}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <UncontrolledTooltip target="nameLabel">
                <Translate contentKey="lxmcrmApp.resource.help.name" />
              </UncontrolledTooltip>
              {/* <ValidatedField
                label={translate('lxmcrmApp.resource.permission')}
                id="resource-permission"
                name="permission"
                data-cy="permission"
                type="text"
              /> */}
              <ValidatedField
                label={translate('lxmcrmApp.resource.permission')}
                id="resource-permission"
                name="permission"
                data-cy="permission"
                type="select"
              >
                <option value="" key="0" />
                {dict.permission
                  ? dict.permission.map(otherEntity => (
                      <option value={otherEntity.key} key={otherEntity.key}>
                        {otherEntity.value}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="resource-authority"
                name="authority"
                data-cy="authority"
                label={translate('lxmcrmApp.resource.authority')}
                type="select"
              >
                <option value="" key="0" />
                {authorities
                  ? authorities.map(otherEntity => (
                      <option value={otherEntity.name} key={otherEntity.name}>
                        {otherEntity.name}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/resource" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ResourceUpdate;
