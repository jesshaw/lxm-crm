import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './lead-info.reducer';

export const LeadInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const leadInfoEntity = useAppSelector(state => state.leadInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="leadInfoDetailsHeading">
          <Translate contentKey="lxmcrmApp.leadInfo.detail.title">LeadInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{leadInfoEntity.id}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="lxmcrmApp.leadInfo.description">Description</Translate>
            </span>
            <UncontrolledTooltip target="description">
              <Translate contentKey="lxmcrmApp.leadInfo.help.description" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.description}</dd>
          <dt>
            <span id="salutation">
              <Translate contentKey="lxmcrmApp.leadInfo.salutation">Salutation</Translate>
            </span>
            <UncontrolledTooltip target="salutation">
              <Translate contentKey="lxmcrmApp.leadInfo.help.salutation" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.salutation}</dd>
          <dt>
            <span id="firstName">
              <Translate contentKey="lxmcrmApp.leadInfo.firstName">First Name</Translate>
            </span>
            <UncontrolledTooltip target="firstName">
              <Translate contentKey="lxmcrmApp.leadInfo.help.firstName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.firstName}</dd>
          <dt>
            <span id="lastName">
              <Translate contentKey="lxmcrmApp.leadInfo.lastName">Last Name</Translate>
            </span>
            <UncontrolledTooltip target="lastName">
              <Translate contentKey="lxmcrmApp.leadInfo.help.lastName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.lastName}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="lxmcrmApp.leadInfo.title">Title</Translate>
            </span>
            <UncontrolledTooltip target="title">
              <Translate contentKey="lxmcrmApp.leadInfo.help.title" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.title}</dd>
          <dt>
            <span id="department">
              <Translate contentKey="lxmcrmApp.leadInfo.department">Department</Translate>
            </span>
            <UncontrolledTooltip target="department">
              <Translate contentKey="lxmcrmApp.leadInfo.help.department" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.department}</dd>
          <dt>
            <span id="doNotCall">
              <Translate contentKey="lxmcrmApp.leadInfo.doNotCall">Do Not Call</Translate>
            </span>
            <UncontrolledTooltip target="doNotCall">
              <Translate contentKey="lxmcrmApp.leadInfo.help.doNotCall" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.doNotCall ? 'true' : 'false'}</dd>
          <dt>
            <span id="phoneHome">
              <Translate contentKey="lxmcrmApp.leadInfo.phoneHome">Phone Home</Translate>
            </span>
            <UncontrolledTooltip target="phoneHome">
              <Translate contentKey="lxmcrmApp.leadInfo.help.phoneHome" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.phoneHome}</dd>
          <dt>
            <span id="phoneMobile">
              <Translate contentKey="lxmcrmApp.leadInfo.phoneMobile">Phone Mobile</Translate>
            </span>
            <UncontrolledTooltip target="phoneMobile">
              <Translate contentKey="lxmcrmApp.leadInfo.help.phoneMobile" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.phoneMobile}</dd>
          <dt>
            <span id="phoneWork">
              <Translate contentKey="lxmcrmApp.leadInfo.phoneWork">Phone Work</Translate>
            </span>
            <UncontrolledTooltip target="phoneWork">
              <Translate contentKey="lxmcrmApp.leadInfo.help.phoneWork" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.phoneWork}</dd>
          <dt>
            <span id="phoneOther">
              <Translate contentKey="lxmcrmApp.leadInfo.phoneOther">Phone Other</Translate>
            </span>
            <UncontrolledTooltip target="phoneOther">
              <Translate contentKey="lxmcrmApp.leadInfo.help.phoneOther" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.phoneOther}</dd>
          <dt>
            <span id="phoneFax">
              <Translate contentKey="lxmcrmApp.leadInfo.phoneFax">Phone Fax</Translate>
            </span>
            <UncontrolledTooltip target="phoneFax">
              <Translate contentKey="lxmcrmApp.leadInfo.help.phoneFax" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.phoneFax}</dd>
          <dt>
            <span id="dateReviewed">
              <Translate contentKey="lxmcrmApp.leadInfo.dateReviewed">Date Reviewed</Translate>
            </span>
            <UncontrolledTooltip target="dateReviewed">
              <Translate contentKey="lxmcrmApp.leadInfo.help.dateReviewed" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {leadInfoEntity.dateReviewed ? (
              <TextFormat value={leadInfoEntity.dateReviewed} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="lawfulBasis">
              <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasis">Lawful Basis</Translate>
            </span>
            <UncontrolledTooltip target="lawfulBasis">
              <Translate contentKey="lxmcrmApp.leadInfo.help.lawfulBasis" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.lawfulBasis}</dd>
          <dt>
            <span id="lawfulBasisSource">
              <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasisSource">Lawful Basis Source</Translate>
            </span>
            <UncontrolledTooltip target="lawfulBasisSource">
              <Translate contentKey="lxmcrmApp.leadInfo.help.lawfulBasisSource" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.lawfulBasisSource}</dd>
          <dt>
            <span id="primaryAddressStreet">
              <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressStreet">Primary Address Street</Translate>
            </span>
            <UncontrolledTooltip target="primaryAddressStreet">
              <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressStreet" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.primaryAddressStreet}</dd>
          <dt>
            <span id="primaryAddressCity">
              <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCity">Primary Address City</Translate>
            </span>
            <UncontrolledTooltip target="primaryAddressCity">
              <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressCity" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.primaryAddressCity}</dd>
          <dt>
            <span id="primaryAddressState">
              <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressState">Primary Address State</Translate>
            </span>
            <UncontrolledTooltip target="primaryAddressState">
              <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressState" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.primaryAddressState}</dd>
          <dt>
            <span id="primaryAddressPostalcode">
              <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressPostalcode">Primary Address Postalcode</Translate>
            </span>
            <UncontrolledTooltip target="primaryAddressPostalcode">
              <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressPostalcode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.primaryAddressPostalcode}</dd>
          <dt>
            <span id="primaryAddressCountry">
              <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCountry">Primary Address Country</Translate>
            </span>
            <UncontrolledTooltip target="primaryAddressCountry">
              <Translate contentKey="lxmcrmApp.leadInfo.help.primaryAddressCountry" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.primaryAddressCountry}</dd>
          <dt>
            <span id="altAddressStreet">
              <Translate contentKey="lxmcrmApp.leadInfo.altAddressStreet">Alt Address Street</Translate>
            </span>
            <UncontrolledTooltip target="altAddressStreet">
              <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressStreet" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.altAddressStreet}</dd>
          <dt>
            <span id="altAddressCity">
              <Translate contentKey="lxmcrmApp.leadInfo.altAddressCity">Alt Address City</Translate>
            </span>
            <UncontrolledTooltip target="altAddressCity">
              <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressCity" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.altAddressCity}</dd>
          <dt>
            <span id="altAddressState">
              <Translate contentKey="lxmcrmApp.leadInfo.altAddressState">Alt Address State</Translate>
            </span>
            <UncontrolledTooltip target="altAddressState">
              <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressState" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.altAddressState}</dd>
          <dt>
            <span id="altAddressPostalcode">
              <Translate contentKey="lxmcrmApp.leadInfo.altAddressPostalcode">Alt Address Postalcode</Translate>
            </span>
            <UncontrolledTooltip target="altAddressPostalcode">
              <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressPostalcode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.altAddressPostalcode}</dd>
          <dt>
            <span id="altAddressCountry">
              <Translate contentKey="lxmcrmApp.leadInfo.altAddressCountry">Alt Address Country</Translate>
            </span>
            <UncontrolledTooltip target="altAddressCountry">
              <Translate contentKey="lxmcrmApp.leadInfo.help.altAddressCountry" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.altAddressCountry}</dd>
          <dt>
            <span id="assistant">
              <Translate contentKey="lxmcrmApp.leadInfo.assistant">Assistant</Translate>
            </span>
            <UncontrolledTooltip target="assistant">
              <Translate contentKey="lxmcrmApp.leadInfo.help.assistant" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.assistant}</dd>
          <dt>
            <span id="assistantPhone">
              <Translate contentKey="lxmcrmApp.leadInfo.assistantPhone">Assistant Phone</Translate>
            </span>
            <UncontrolledTooltip target="assistantPhone">
              <Translate contentKey="lxmcrmApp.leadInfo.help.assistantPhone" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.assistantPhone}</dd>
          <dt>
            <span id="converted">
              <Translate contentKey="lxmcrmApp.leadInfo.converted">Converted</Translate>
            </span>
            <UncontrolledTooltip target="converted">
              <Translate contentKey="lxmcrmApp.leadInfo.help.converted" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.converted ? 'true' : 'false'}</dd>
          <dt>
            <span id="referedBy">
              <Translate contentKey="lxmcrmApp.leadInfo.referedBy">Refered By</Translate>
            </span>
            <UncontrolledTooltip target="referedBy">
              <Translate contentKey="lxmcrmApp.leadInfo.help.referedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.referedBy}</dd>
          <dt>
            <span id="leadSource">
              <Translate contentKey="lxmcrmApp.leadInfo.leadSource">Lead Source</Translate>
            </span>
            <UncontrolledTooltip target="leadSource">
              <Translate contentKey="lxmcrmApp.leadInfo.help.leadSource" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.leadSource}</dd>
          <dt>
            <span id="leadSourceDescription">
              <Translate contentKey="lxmcrmApp.leadInfo.leadSourceDescription">Lead Source Description</Translate>
            </span>
            <UncontrolledTooltip target="leadSourceDescription">
              <Translate contentKey="lxmcrmApp.leadInfo.help.leadSourceDescription" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.leadSourceDescription}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="lxmcrmApp.leadInfo.status">Status</Translate>
            </span>
            <UncontrolledTooltip target="status">
              <Translate contentKey="lxmcrmApp.leadInfo.help.status" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.status}</dd>
          <dt>
            <span id="statusDescription">
              <Translate contentKey="lxmcrmApp.leadInfo.statusDescription">Status Description</Translate>
            </span>
            <UncontrolledTooltip target="statusDescription">
              <Translate contentKey="lxmcrmApp.leadInfo.help.statusDescription" />
            </UncontrolledTooltip>
          </dt>
          <dd>{leadInfoEntity.statusDescription}</dd>
          <dt>
            <span id="birthdate">
              <Translate contentKey="lxmcrmApp.leadInfo.birthdate">Birthdate</Translate>
            </span>
            <UncontrolledTooltip target="birthdate">
              <Translate contentKey="lxmcrmApp.leadInfo.help.birthdate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {leadInfoEntity.birthdate ? <TextFormat value={leadInfoEntity.birthdate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="lxmcrmApp.leadInfo.reportsTo">Reports To</Translate>
          </dt>
          <dd>{leadInfoEntity.reportsTo ? leadInfoEntity.reportsTo.lastName : ''}</dd>
          <dt>
            <Translate contentKey="lxmcrmApp.leadInfo.assignedUser">Assigned User</Translate>
          </dt>
          <dd>{leadInfoEntity.assignedUser ? leadInfoEntity.assignedUser.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/lead-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/lead-info/${leadInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LeadInfoDetail;
