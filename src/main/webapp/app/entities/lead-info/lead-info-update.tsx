import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities as getLeadInfos } from 'app/entities/lead-info/lead-info.reducer';
import { IUser } from 'app/shared/model/user.model';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { ILeadInfo } from 'app/shared/model/lead-info.model';
import { getEntity, updateEntity, createEntity, reset } from './lead-info.reducer';

export const LeadInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const leadInfos = useAppSelector(state => state.leadInfo.entities);
  const users = useAppSelector(state => state.userManagement.users);
  const leadInfoEntity = useAppSelector(state => state.leadInfo.entity);
  const loading = useAppSelector(state => state.leadInfo.loading);
  const updating = useAppSelector(state => state.leadInfo.updating);
  const updateSuccess = useAppSelector(state => state.leadInfo.updateSuccess);

  const handleClose = () => {
    navigate('/lead-info' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getLeadInfos({}));
    dispatch(getUsers({}));
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
      ...leadInfoEntity,
      ...values,
      reportsTo: leadInfos.find(it => it.id.toString() === values.reportsTo?.toString()),
      assignedUser: users.find(it => it.id.toString() === values.assignedUser?.toString()),
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
          ...leadInfoEntity,
          reportsTo: leadInfoEntity?.reportsTo?.id,
          assignedUser: leadInfoEntity?.assignedUser?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="lxmcrmApp.leadInfo.home.createOrEditLabel" data-cy="LeadInfoCreateUpdateHeading">
            <Translate contentKey="lxmcrmApp.leadInfo.home.createOrEditLabel">Create or edit a LeadInfo</Translate>
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
                  id="lead-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.description')}
                id="lead-info-description"
                name="description"
                data-cy="description"
                type="textarea"
              />
              <UncontrolledTooltip target="descriptionLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.description" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.salutation')}
                id="lead-info-salutation"
                name="salutation"
                data-cy="salutation"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <UncontrolledTooltip target="salutationLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.salutation" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.firstName')}
                id="lead-info-firstName"
                name="firstName"
                data-cy="firstName"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="firstNameLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.firstName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.lastName')}
                id="lead-info-lastName"
                name="lastName"
                data-cy="lastName"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="lastNameLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.lastName" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.title')}
                id="lead-info-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="titleLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.title" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.department')}
                id="lead-info-department"
                name="department"
                data-cy="department"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="departmentLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.department" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.doNotCall')}
                id="lead-info-doNotCall"
                name="doNotCall"
                data-cy="doNotCall"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="doNotCallLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.doNotCall" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.phoneHome')}
                id="lead-info-phoneHome"
                name="phoneHome"
                data-cy="phoneHome"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="phoneHomeLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.phoneHome" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.phoneMobile')}
                id="lead-info-phoneMobile"
                name="phoneMobile"
                data-cy="phoneMobile"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="phoneMobileLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.phoneMobile" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.phoneWork')}
                id="lead-info-phoneWork"
                name="phoneWork"
                data-cy="phoneWork"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="phoneWorkLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.phoneWork" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.phoneOther')}
                id="lead-info-phoneOther"
                name="phoneOther"
                data-cy="phoneOther"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="phoneOtherLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.phoneOther" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.phoneFax')}
                id="lead-info-phoneFax"
                name="phoneFax"
                data-cy="phoneFax"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="phoneFaxLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.phoneFax" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.dateReviewed')}
                id="lead-info-dateReviewed"
                name="dateReviewed"
                data-cy="dateReviewed"
                type="date"
              />
              <UncontrolledTooltip target="dateReviewedLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.dateReviewed" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.lawfulBasis')}
                id="lead-info-lawfulBasis"
                name="lawfulBasis"
                data-cy="lawfulBasis"
                type="text"
                validate={{
                  maxLength: { value: 512, message: translate('entity.validation.maxlength', { max: 512 }) },
                }}
              />
              <UncontrolledTooltip target="lawfulBasisLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.lawfulBasis" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.lawfulBasisSource')}
                id="lead-info-lawfulBasisSource"
                name="lawfulBasisSource"
                data-cy="lawfulBasisSource"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="lawfulBasisSourceLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.lawfulBasisSource" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.primaryAddressStreet')}
                id="lead-info-primaryAddressStreet"
                name="primaryAddressStreet"
                data-cy="primaryAddressStreet"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <UncontrolledTooltip target="primaryAddressStreetLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressStreet" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.primaryAddressCity')}
                id="lead-info-primaryAddressCity"
                name="primaryAddressCity"
                data-cy="primaryAddressCity"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="primaryAddressCityLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressCity" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.primaryAddressState')}
                id="lead-info-primaryAddressState"
                name="primaryAddressState"
                data-cy="primaryAddressState"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="primaryAddressStateLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressState" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.primaryAddressPostalcode')}
                id="lead-info-primaryAddressPostalcode"
                name="primaryAddressPostalcode"
                data-cy="primaryAddressPostalcode"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <UncontrolledTooltip target="primaryAddressPostalcodeLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressPostalcode" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.primaryAddressCountry')}
                id="lead-info-primaryAddressCountry"
                name="primaryAddressCountry"
                data-cy="primaryAddressCountry"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <UncontrolledTooltip target="primaryAddressCountryLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressCountry" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.altAddressStreet')}
                id="lead-info-altAddressStreet"
                name="altAddressStreet"
                data-cy="altAddressStreet"
                type="text"
                validate={{
                  maxLength: { value: 150, message: translate('entity.validation.maxlength', { max: 150 }) },
                }}
              />
              <UncontrolledTooltip target="altAddressStreetLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressStreet" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.altAddressCity')}
                id="lead-info-altAddressCity"
                name="altAddressCity"
                data-cy="altAddressCity"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="altAddressCityLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressCity" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.altAddressState')}
                id="lead-info-altAddressState"
                name="altAddressState"
                data-cy="altAddressState"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="altAddressStateLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressState" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.altAddressPostalcode')}
                id="lead-info-altAddressPostalcode"
                name="altAddressPostalcode"
                data-cy="altAddressPostalcode"
                type="text"
                validate={{
                  maxLength: { value: 20, message: translate('entity.validation.maxlength', { max: 20 }) },
                }}
              />
              <UncontrolledTooltip target="altAddressPostalcodeLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressPostalcode" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.altAddressCountry')}
                id="lead-info-altAddressCountry"
                name="altAddressCountry"
                data-cy="altAddressCountry"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <UncontrolledTooltip target="altAddressCountryLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressCountry" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.assistant')}
                id="lead-info-assistant"
                name="assistant"
                data-cy="assistant"
                type="text"
                validate={{
                  maxLength: { value: 75, message: translate('entity.validation.maxlength', { max: 75 }) },
                }}
              />
              <UncontrolledTooltip target="assistantLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.assistant" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.assistantPhone')}
                id="lead-info-assistantPhone"
                name="assistantPhone"
                data-cy="assistantPhone"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="assistantPhoneLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.assistantPhone" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.converted')}
                id="lead-info-converted"
                name="converted"
                data-cy="converted"
                check
                type="checkbox"
              />
              <UncontrolledTooltip target="convertedLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.converted" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.referedBy')}
                id="lead-info-referedBy"
                name="referedBy"
                data-cy="referedBy"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="referedByLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.referedBy" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.leadSource')}
                id="lead-info-leadSource"
                name="leadSource"
                data-cy="leadSource"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="leadSourceLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.leadSource" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.leadSourceDescription')}
                id="lead-info-leadSourceDescription"
                name="leadSourceDescription"
                data-cy="leadSourceDescription"
                type="text"
                validate={{
                  maxLength: { value: 512, message: translate('entity.validation.maxlength', { max: 512 }) },
                }}
              />
              <UncontrolledTooltip target="leadSourceDescriptionLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.leadSourceDescription" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.status')}
                id="lead-info-status"
                name="status"
                data-cy="status"
                type="text"
                validate={{
                  maxLength: { value: 100, message: translate('entity.validation.maxlength', { max: 100 }) },
                }}
              />
              <UncontrolledTooltip target="statusLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.status" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.statusDescription')}
                id="lead-info-statusDescription"
                name="statusDescription"
                data-cy="statusDescription"
                type="text"
                validate={{
                  maxLength: { value: 512, message: translate('entity.validation.maxlength', { max: 512 }) },
                }}
              />
              <UncontrolledTooltip target="statusDescriptionLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.statusDescription" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('lxmcrmApp.leadInfo.birthdate')}
                id="lead-info-birthdate"
                name="birthdate"
                data-cy="birthdate"
                type="date"
              />
              <UncontrolledTooltip target="birthdateLabel">
                <Translate contentKey="lxmcrmApp.leadInfo.help.birthdate" />
              </UncontrolledTooltip>
              <ValidatedField
                id="lead-info-reportsTo"
                name="reportsTo"
                data-cy="reportsTo"
                label={translate('lxmcrmApp.leadInfo.reportsTo')}
                type="select"
              >
                <option value="" key="0" />
                {leadInfos
                  ? leadInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.lastName}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="lead-info-assignedUser"
                name="assignedUser"
                data-cy="assignedUser"
                label={translate('lxmcrmApp.leadInfo.assignedUser')}
                type="select"
              >
                <option value="" key="0" />
                {users
                  ? users.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.login}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/lead-info" replace color="info">
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

export default LeadInfoUpdate;
