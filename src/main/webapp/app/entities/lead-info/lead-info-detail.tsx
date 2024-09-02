import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
// import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Button } from 'primereact/button';
import { Tooltip } from 'primereact/tooltip';
import { Translate, translate, byteSize, TextFormat } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './lead-info.reducer';

export const LeadInfoDetail = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const leadInfoEntity = useAppSelector(state => state.leadInfo.entity);

  return (
    <div className="l-card">
      <h5>
        <Translate contentKey="lxmcrmApp.leadInfo.detail.title">LeadInfo</Translate>
      </h5>
      <div className="l-form">
        <div>
          <label id="id" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip="">
            <Translate contentKey="global.field.id">ID</Translate>
          </label>
          <Tooltip target="#id" />
          <div>{leadInfoEntity.id}</div>
        </div>
        <div>
          <label
            id="salutation"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.salutation')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.salutation">Salutation</Translate>
          </label>
          <Tooltip target="#salutation" />
          <div>{leadInfoEntity.salutation}</div>
        </div>
        <div>
          <label
            id="firstName"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.firstName')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.firstName">First Name</Translate>
          </label>
          <Tooltip target="#firstName" />
          <div>{leadInfoEntity.firstName}</div>
        </div>
        <div>
          <label
            id="lastName"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.lastName')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.lastName">Last Name</Translate>
          </label>
          <Tooltip target="#lastName" />
          <div>{leadInfoEntity.lastName}</div>
        </div>
        <div>
          <label id="title" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.title')}>
            <Translate contentKey="lxmcrmApp.leadInfo.title">Title</Translate>
          </label>
          <Tooltip target="#title" />
          <div>{leadInfoEntity.title}</div>
        </div>
        <div>
          <label
            id="department"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.department')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.department">Department</Translate>
          </label>
          <Tooltip target="#department" />
          <div>{leadInfoEntity.department}</div>
        </div>
        <div>
          <label
            id="doNotCall"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.doNotCall')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.doNotCall">Do Not Call</Translate>
          </label>
          <Tooltip target="#doNotCall" />
          <div>{leadInfoEntity.doNotCall ? 'true' : 'false'}</div>
        </div>
        <div>
          <label
            id="phoneHome"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.phoneHome')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.phoneHome">Phone Home</Translate>
          </label>
          <Tooltip target="#phoneHome" />
          <div>{leadInfoEntity.phoneHome}</div>
        </div>
        <div>
          <label
            id="phoneMobile"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.phoneMobile')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.phoneMobile">Phone Mobile</Translate>
          </label>
          <Tooltip target="#phoneMobile" />
          <div>{leadInfoEntity.phoneMobile}</div>
        </div>
        <div>
          <label
            id="phoneWork"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.phoneWork')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.phoneWork">Phone Work</Translate>
          </label>
          <Tooltip target="#phoneWork" />
          <div>{leadInfoEntity.phoneWork}</div>
        </div>
        <div>
          <label
            id="phoneOther"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.phoneOther')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.phoneOther">Phone Other</Translate>
          </label>
          <Tooltip target="#phoneOther" />
          <div>{leadInfoEntity.phoneOther}</div>
        </div>
        <div>
          <label
            id="phoneFax"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.phoneFax')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.phoneFax">Phone Fax</Translate>
          </label>
          <Tooltip target="#phoneFax" />
          <div>{leadInfoEntity.phoneFax}</div>
        </div>
        <div>
          <label
            id="dateReviewed"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.dateReviewed')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.dateReviewed">Date Reviewed</Translate>
          </label>
          <Tooltip target="#dateReviewed" />
          <div>
            {leadInfoEntity.dateReviewed ? (
              <TextFormat value={leadInfoEntity.dateReviewed} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </div>
        </div>
        <div>
          <label
            id="lawfulBasis"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.lawfulBasis')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasis">Lawful Basis</Translate>
          </label>
          <Tooltip target="#lawfulBasis" />
          <div>{leadInfoEntity.lawfulBasis}</div>
        </div>
        <div>
          <label
            id="lawfulBasisSource"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.lawfulBasisSource')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasisSource">Lawful Basis Source</Translate>
          </label>
          <Tooltip target="#lawfulBasisSource" />
          <div>{leadInfoEntity.lawfulBasisSource}</div>
        </div>
        <div>
          <label
            id="primaryAddressStreet"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressStreet')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressStreet">Primary Address Street</Translate>
          </label>
          <Tooltip target="#primaryAddressStreet" />
          <div>{leadInfoEntity.primaryAddressStreet}</div>
        </div>
        <div>
          <label
            id="primaryAddressCity"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressCity')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCity">Primary Address City</Translate>
          </label>
          <Tooltip target="#primaryAddressCity" />
          <div>{leadInfoEntity.primaryAddressCity}</div>
        </div>
        <div>
          <label
            id="primaryAddressState"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressState')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressState">Primary Address State</Translate>
          </label>
          <Tooltip target="#primaryAddressState" />
          <div>{leadInfoEntity.primaryAddressState}</div>
        </div>
        <div>
          <label
            id="primaryAddressPostalcode"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressPostalcode')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressPostalcode">Primary Address Postalcode</Translate>
          </label>
          <Tooltip target="#primaryAddressPostalcode" />
          <div>{leadInfoEntity.primaryAddressPostalcode}</div>
        </div>
        <div>
          <label
            id="primaryAddressCountry"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.primaryAddressCountry')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCountry">Primary Address Country</Translate>
          </label>
          <Tooltip target="#primaryAddressCountry" />
          <div>{leadInfoEntity.primaryAddressCountry}</div>
        </div>
        <div>
          <label
            id="altAddressStreet"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.altAddressStreet')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.altAddressStreet">Alt Address Street</Translate>
          </label>
          <Tooltip target="#altAddressStreet" />
          <div>{leadInfoEntity.altAddressStreet}</div>
        </div>
        <div>
          <label
            id="altAddressCity"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.altAddressCity')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.altAddressCity">Alt Address City</Translate>
          </label>
          <Tooltip target="#altAddressCity" />
          <div>{leadInfoEntity.altAddressCity}</div>
        </div>
        <div>
          <label
            id="altAddressState"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.altAddressState')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.altAddressState">Alt Address State</Translate>
          </label>
          <Tooltip target="#altAddressState" />
          <div>{leadInfoEntity.altAddressState}</div>
        </div>
        <div>
          <label
            id="altAddressPostalcode"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.altAddressPostalcode')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.altAddressPostalcode">Alt Address Postalcode</Translate>
          </label>
          <Tooltip target="#altAddressPostalcode" />
          <div>{leadInfoEntity.altAddressPostalcode}</div>
        </div>
        <div>
          <label
            id="altAddressCountry"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.altAddressCountry')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.altAddressCountry">Alt Address Country</Translate>
          </label>
          <Tooltip target="#altAddressCountry" />
          <div>{leadInfoEntity.altAddressCountry}</div>
        </div>
        <div>
          <label
            id="assistant"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.assistant')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.assistant">Assistant</Translate>
          </label>
          <Tooltip target="#assistant" />
          <div>{leadInfoEntity.assistant}</div>
        </div>
        <div>
          <label
            id="assistantPhone"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.assistantPhone')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.assistantPhone">Assistant Phone</Translate>
          </label>
          <Tooltip target="#assistantPhone" />
          <div>{leadInfoEntity.assistantPhone}</div>
        </div>
        <div>
          <label
            id="converted"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.converted')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.converted">Converted</Translate>
          </label>
          <Tooltip target="#converted" />
          <div>{leadInfoEntity.converted ? 'true' : 'false'}</div>
        </div>
        <div>
          <label
            id="referedBy"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.referedBy')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.referedBy">Refered By</Translate>
          </label>
          <Tooltip target="#referedBy" />
          <div>{leadInfoEntity.referedBy}</div>
        </div>
        <div>
          <label
            id="leadSource"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.leadSource')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.leadSource">Lead Source</Translate>
          </label>
          <Tooltip target="#leadSource" />
          <div>{leadInfoEntity.leadSource}</div>
        </div>
        <div>
          <label
            id="leadSourceDescription"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.leadSourceDescription')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.leadSourceDescription">Lead Source Description</Translate>
          </label>
          <Tooltip target="#leadSourceDescription" />
          <div>{leadInfoEntity.leadSourceDescription}</div>
        </div>
        <div>
          <label id="status" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.status')}>
            <Translate contentKey="lxmcrmApp.leadInfo.status">Status</Translate>
          </label>
          <Tooltip target="#status" />
          <div>{leadInfoEntity.status}</div>
        </div>
        <div>
          <label
            id="statusDescription"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.statusDescription')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.statusDescription">Status Description</Translate>
          </label>
          <Tooltip target="#statusDescription" />
          <div>{leadInfoEntity.statusDescription}</div>
        </div>
        <div>
          <label
            id="birthdate"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.birthdate')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.birthdate">Birthdate</Translate>
          </label>
          <Tooltip target="#birthdate" />
          <div>
            {leadInfoEntity.birthdate ? <TextFormat value={leadInfoEntity.birthdate} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </div>
        </div>
        <div>
          <label
            id="description"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('lxmcrmApp.leadInfo.help.description')}
          >
            <Translate contentKey="lxmcrmApp.leadInfo.description">Description</Translate>
          </label>
          <Tooltip target="#description" />
          <div>{leadInfoEntity.description}</div>
        </div>
        <div>
          <label>
            <Translate contentKey="lxmcrmApp.leadInfo.reportsTo">Reports To</Translate>
          </label>
          <div>{leadInfoEntity.reportsTo ? leadInfoEntity.reportsTo.lastName : ''}</div>
        </div>
        <div>
          <label>
            <Translate contentKey="lxmcrmApp.leadInfo.assignedUser">Assigned User</Translate>
          </label>
          <div>{leadInfoEntity.assignedUser ? leadInfoEntity.assignedUser.login : ''}</div>
        </div>
      </div>

      <div className="l-form-footer">
        <Button label={translate('entity.action.back')} icon="pi pi-arrow-left" outlined onClick={() => navigate(-1)} />
        <Button
          label={translate('entity.action.edit')}
          icon="pi pi-save"
          onClick={() => navigate(`/lead-info/${leadInfoEntity.id}/edit`)}
        />
      </div>
    </div>
  );
};

export default LeadInfoDetail;
