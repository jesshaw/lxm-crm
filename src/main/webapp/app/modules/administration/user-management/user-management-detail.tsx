import React, { useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Tag } from 'primereact/tag';
import { Tooltip } from 'primereact/tooltip';
import { Button } from 'primereact/button';
import { Translate, translate, TextFormat } from 'react-jhipster';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { languages } from 'app/config/translation';
import { getUser } from './user-management.reducer';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const UserManagementDetail = () => {
  const dispatch = useAppDispatch();
  const navigate = useNavigate();

  const { login } = useParams<'login'>();

  useEffect(() => {
    dispatch(getUser(login));
  }, []);

  const user = useAppSelector(state => state.userManagement.user);

  return (
    <div className="l-card">
      <h5>
        <Translate contentKey="userManagement.detail.title">Users</Translate>
      </h5>
      <div className="l-form">
        <div>
          <label id="login" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('userManagement.help.login', null)}>
            <Translate contentKey="userManagement.login">Login</Translate>
          </label>
          <Tooltip target="#login" />
          <div>{user.login}</div>
        </div>
        <div>
          <label
            id="activated"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('userManagement.help.activated')}
          >
            <Translate contentKey="userManagement.activated">Activated</Translate>
          </label>
          <Tooltip target="#activated" />
          <div>
            {user.activated ? (
              <Tag severity="success" value={translate('userManagement.activated')} />
            ) : (
              <Tag severity="danger" value={translate('userManagement.deactivated')} />
            )}
          </div>
        </div>
        <div>
          <label
            id="firstName"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('userManagement.help.firstName')}
          >
            <Translate contentKey="userManagement.firstName">First Name</Translate>
          </label>
          <Tooltip target="#firstName" />
          <div>{user.firstName}</div>
        </div>
        <div>
          <label id="lastName" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('userManagement.help.lastName')}>
            <Translate contentKey="userManagement.lastName">Last Name</Translate>
          </label>
          <Tooltip target="#lastName" />
          <div>{user.lastName}</div>
        </div>
        <div>
          <label id="email" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('userManagement.help.email')}>
            <Translate contentKey="userManagement.email">Email</Translate>
          </label>
          <Tooltip target="#email" />
          <div>{user.email}</div>
        </div>
        <div>
          <label id="langKey" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('userManagement.help.langKey')}>
            <Translate contentKey="userManagement.langKey">Lang Key</Translate>
          </label>
          <Tooltip target="#langKey" />
          <div>{user.langKey ? languages[user.langKey].name : undefined}</div>
        </div>
        <div>
          <label
            id="createdBy"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('userManagement.help.createdBy')}
          >
            <Translate contentKey="userManagement.createdBy">Created By</Translate>
          </label>
          <Tooltip target="#createdBy" />
          <div>{user.createdBy}</div>
        </div>
        <div>
          <label
            id="createdDate"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('userManagement.help.createdDate')}
          >
            <Translate contentKey="userManagement.createdDate">Created Date</Translate>
          </label>
          <Tooltip target="#createdDate" />
          <div>{user.createdDate ? <TextFormat value={user.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</div>
        </div>
        <div>
          <label
            id="lastModifiedBy"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('userManagement.help.lastModifiedBy')}
          >
            <Translate contentKey="userManagement.lastModifiedBy">Last Modified By</Translate>
          </label>
          <Tooltip target="#lastModifiedBy" />
          <div>{user.lastModifiedBy}</div>
        </div>
        <div>
          <label
            id="lastModifiedDate"
            data-pr-position="top"
            data-pr-at="left+5 top-5"
            data-pr-tooltip={translate('userManagement.help.lastModifiedDate')}
          >
            <Translate contentKey="userManagement.lastModifiedDate">Last Modified Date</Translate>
          </label>
          <Tooltip target="#lastModifiedDate" />
          <div>
            {user.lastModifiedDate ? (
              <TextFormat value={user.lastModifiedDate} type="date" format={APP_DATE_FORMAT} blankOnInvalid />
            ) : null}
          </div>
        </div>
        <div>
          <label id="profiles" data-pr-position="top" data-pr-at="left+5 top-5" data-pr-tooltip={translate('userManagement.help.profiles')}>
            <Translate contentKey="userManagement.profiles">Profiles</Translate>
          </label>
          <Tooltip target="#profiles" />
          <div>
            {user.authorities
              ? user.authorities.map((authority, i) => <Tag severity="info" className="mb-1 mr-1" value={authority} />)
              : null}
          </div>
        </div>
      </div>

      <div className="l-form-footer">
        <Button label={translate('entity.action.back')} icon="pi pi-arrow-left" outlined onClick={() => navigate(-1)} />
        <Button
          label={translate('entity.action.edit')}
          icon="pi pi-save"
          onClick={() => navigate(`/admin/user-management/${user.login}/edit`)}
        />
      </div>
    </div>
  );
};

export default UserManagementDetail;
