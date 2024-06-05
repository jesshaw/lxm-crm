import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { byteSize, Translate, TextFormat, getPaginationState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './lead-info.reducer';

export const LeadInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getPaginationState(pageLocation, ITEMS_PER_PAGE, 'id'), pageLocation.search),
  );

  const leadInfoList = useAppSelector(state => state.leadInfo.entities);
  const loading = useAppSelector(state => state.leadInfo.loading);
  const totalItems = useAppSelector(state => state.leadInfo.totalItems);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(pageLocation.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [pageLocation.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = paginationState.sort;
    const order = paginationState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="lead-info-heading" data-cy="LeadInfoHeading">
        <Translate contentKey="lxmcrmApp.leadInfo.home.title">Lead Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="lxmcrmApp.leadInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/lead-info/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="lxmcrmApp.leadInfo.home.createLabel">Create new Lead Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {leadInfoList && leadInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('description')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.description">Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('description')} />
                </th>
                <th className="hand" onClick={sort('salutation')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.salutation">Salutation</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('salutation')} />
                </th>
                <th className="hand" onClick={sort('firstName')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.firstName">First Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('firstName')} />
                </th>
                <th className="hand" onClick={sort('lastName')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.lastName">Last Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lastName')} />
                </th>
                <th className="hand" onClick={sort('title')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.title">Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('title')} />
                </th>
                <th className="hand" onClick={sort('department')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.department">Department</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('department')} />
                </th>
                <th className="hand" onClick={sort('doNotCall')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.doNotCall">Do Not Call</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('doNotCall')} />
                </th>
                <th className="hand" onClick={sort('phoneHome')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneHome">Phone Home</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phoneHome')} />
                </th>
                <th className="hand" onClick={sort('phoneMobile')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneMobile">Phone Mobile</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phoneMobile')} />
                </th>
                <th className="hand" onClick={sort('phoneWork')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneWork">Phone Work</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phoneWork')} />
                </th>
                <th className="hand" onClick={sort('phoneOther')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneOther">Phone Other</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phoneOther')} />
                </th>
                <th className="hand" onClick={sort('phoneFax')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.phoneFax">Phone Fax</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('phoneFax')} />
                </th>
                <th className="hand" onClick={sort('dateReviewed')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.dateReviewed">Date Reviewed</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('dateReviewed')} />
                </th>
                <th className="hand" onClick={sort('lawfulBasis')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasis">Lawful Basis</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lawfulBasis')} />
                </th>
                <th className="hand" onClick={sort('lawfulBasisSource')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.lawfulBasisSource">Lawful Basis Source</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lawfulBasisSource')} />
                </th>
                <th className="hand" onClick={sort('primaryAddressStreet')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressStreet">Primary Address Street</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('primaryAddressStreet')} />
                </th>
                <th className="hand" onClick={sort('primaryAddressCity')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCity">Primary Address City</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('primaryAddressCity')} />
                </th>
                <th className="hand" onClick={sort('primaryAddressState')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressState">Primary Address State</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('primaryAddressState')} />
                </th>
                <th className="hand" onClick={sort('primaryAddressPostalcode')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressPostalcode">Primary Address Postalcode</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('primaryAddressPostalcode')} />
                </th>
                <th className="hand" onClick={sort('primaryAddressCountry')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.primaryAddressCountry">Primary Address Country</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('primaryAddressCountry')} />
                </th>
                <th className="hand" onClick={sort('altAddressStreet')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressStreet">Alt Address Street</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('altAddressStreet')} />
                </th>
                <th className="hand" onClick={sort('altAddressCity')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressCity">Alt Address City</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('altAddressCity')} />
                </th>
                <th className="hand" onClick={sort('altAddressState')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressState">Alt Address State</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('altAddressState')} />
                </th>
                <th className="hand" onClick={sort('altAddressPostalcode')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressPostalcode">Alt Address Postalcode</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('altAddressPostalcode')} />
                </th>
                <th className="hand" onClick={sort('altAddressCountry')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.altAddressCountry">Alt Address Country</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('altAddressCountry')} />
                </th>
                <th className="hand" onClick={sort('assistant')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.assistant">Assistant</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('assistant')} />
                </th>
                <th className="hand" onClick={sort('assistantPhone')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.assistantPhone">Assistant Phone</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('assistantPhone')} />
                </th>
                <th className="hand" onClick={sort('converted')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.converted">Converted</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('converted')} />
                </th>
                <th className="hand" onClick={sort('referedBy')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.referedBy">Refered By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('referedBy')} />
                </th>
                <th className="hand" onClick={sort('leadSource')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.leadSource">Lead Source</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('leadSource')} />
                </th>
                <th className="hand" onClick={sort('leadSourceDescription')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.leadSourceDescription">Lead Source Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('leadSourceDescription')} />
                </th>
                <th className="hand" onClick={sort('status')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.status">Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('status')} />
                </th>
                <th className="hand" onClick={sort('statusDescription')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.statusDescription">Status Description</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('statusDescription')} />
                </th>
                <th className="hand" onClick={sort('birthdate')}>
                  <Translate contentKey="lxmcrmApp.leadInfo.birthdate">Birthdate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('birthdate')} />
                </th>
                <th>
                  <Translate contentKey="lxmcrmApp.leadInfo.reportsTo">Reports To</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="lxmcrmApp.leadInfo.assignedUser">Assigned User</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {leadInfoList.map((leadInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/lead-info/${leadInfo.id}`} color="link" size="sm">
                      {leadInfo.id}
                    </Button>
                  </td>
                  <td>{leadInfo.description}</td>
                  <td>{leadInfo.salutation}</td>
                  <td>{leadInfo.firstName}</td>
                  <td>{leadInfo.lastName}</td>
                  <td>{leadInfo.title}</td>
                  <td>{leadInfo.department}</td>
                  <td>{leadInfo.doNotCall ? 'true' : 'false'}</td>
                  <td>{leadInfo.phoneHome}</td>
                  <td>{leadInfo.phoneMobile}</td>
                  <td>{leadInfo.phoneWork}</td>
                  <td>{leadInfo.phoneOther}</td>
                  <td>{leadInfo.phoneFax}</td>
                  <td>
                    {leadInfo.dateReviewed ? <TextFormat type="date" value={leadInfo.dateReviewed} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{leadInfo.lawfulBasis}</td>
                  <td>{leadInfo.lawfulBasisSource}</td>
                  <td>{leadInfo.primaryAddressStreet}</td>
                  <td>{leadInfo.primaryAddressCity}</td>
                  <td>{leadInfo.primaryAddressState}</td>
                  <td>{leadInfo.primaryAddressPostalcode}</td>
                  <td>{leadInfo.primaryAddressCountry}</td>
                  <td>{leadInfo.altAddressStreet}</td>
                  <td>{leadInfo.altAddressCity}</td>
                  <td>{leadInfo.altAddressState}</td>
                  <td>{leadInfo.altAddressPostalcode}</td>
                  <td>{leadInfo.altAddressCountry}</td>
                  <td>{leadInfo.assistant}</td>
                  <td>{leadInfo.assistantPhone}</td>
                  <td>{leadInfo.converted ? 'true' : 'false'}</td>
                  <td>{leadInfo.referedBy}</td>
                  <td>{leadInfo.leadSource}</td>
                  <td>{leadInfo.leadSourceDescription}</td>
                  <td>{leadInfo.status}</td>
                  <td>{leadInfo.statusDescription}</td>
                  <td>
                    {leadInfo.birthdate ? <TextFormat type="date" value={leadInfo.birthdate} format={APP_LOCAL_DATE_FORMAT} /> : null}
                  </td>
                  <td>{leadInfo.reportsTo ? <Link to={`/lead-info/${leadInfo.reportsTo.id}`}>{leadInfo.reportsTo.lastName}</Link> : ''}</td>
                  <td>{leadInfo.assignedUser ? leadInfo.assignedUser.login : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/lead-info/${leadInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/lead-info/${leadInfo.id}/edit?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() =>
                          (window.location.href = `/lead-info/${leadInfo.id}/delete?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`)
                        }
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="lxmcrmApp.leadInfo.home.notFound">No Lead Infos found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={leadInfoList && leadInfoList.length > 0 ? '' : 'd-none'}>
          <div className="justify-content-center d-flex">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </div>
          <div className="justify-content-center d-flex">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </div>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};

export default LeadInfo;
