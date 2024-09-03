import React, { useEffect } from 'react';
// import { Button, Col, Row } from 'reactstrap';
import {
  CacheMetrics,
  DatasourceMetrics,
  GarbageCollectorMetrics,
  HttpRequestMetrics,
  JvmMemory,
  JvmThreads,
  EndpointsRequestsMetrics,
  SystemMetrics,
  Translate,
  translate,
} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_TIMESTAMP_FORMAT, APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT, APP_WHOLE_NUMBER_FORMAT } from 'app/config/constants';
import { getSystemMetrics, getSystemThreadDump } from '../administration.reducer';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { Button } from 'primereact/button';

export const MetricsPage = () => {
  const dispatch = useAppDispatch();
  const metrics = useAppSelector(state => state.administration.metrics);
  const isFetching = useAppSelector(state => state.administration.loading);
  const threadDump = useAppSelector(state => state.administration.threadDump);

  useEffect(() => {
    dispatch(getSystemMetrics());
    dispatch(getSystemThreadDump());
  }, []);

  const getMetrics = () => {
    if (!isFetching) {
      dispatch(getSystemMetrics());
      dispatch(getSystemThreadDump());
    }
  };

  return (
    <div>
      <h2 id="metrics-page-heading" data-cy="metricsPageHeading">
        <Translate contentKey="metrics.title">Application Metrics</Translate>
      </h2>
      <p>
        <Button
          label={translate('health.refresh.button')}
          icon={`pi ${isFetching ? 'pi-spin' : ''} pi-refresh`}
          onClick={getMetrics}
          disabled={isFetching}
        />
      </p>
      <hr />

      <div className="grid columns-1 md:columns-3">
        <h3 className="md:col-span-3">
          <Translate contentKey="metrics.jvm.title">JVM Metrics</Translate>
        </h3>
        <div>{metrics?.jvm ? <JvmMemory jvmMetrics={metrics.jvm} wholeNumberFormat={APP_WHOLE_NUMBER_FORMAT} /> : ''}</div>
        <div>{threadDump ? <JvmThreads jvmThreads={threadDump} wholeNumberFormat={APP_WHOLE_NUMBER_FORMAT} /> : ''}</div>
        <div>
          {metrics?.processMetrics ? (
            <SystemMetrics
              systemMetrics={metrics.processMetrics}
              wholeNumberFormat={APP_WHOLE_NUMBER_FORMAT}
              timestampFormat={APP_TIMESTAMP_FORMAT}
            />
          ) : (
            ''
          )}
        </div>
      </div>

      {metrics?.garbageCollector ? (
        <GarbageCollectorMetrics garbageCollectorMetrics={metrics.garbageCollector} wholeNumberFormat={APP_WHOLE_NUMBER_FORMAT} />
      ) : (
        ''
      )}
      {metrics && metrics['http.server.requests'] ? (
        <HttpRequestMetrics
          requestMetrics={metrics['http.server.requests']}
          twoDigitAfterPointFormat={APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT}
          wholeNumberFormat={APP_WHOLE_NUMBER_FORMAT}
        />
      ) : (
        ''
      )}
      {metrics?.services ? (
        <EndpointsRequestsMetrics endpointsRequestsMetrics={metrics.services} wholeNumberFormat={APP_WHOLE_NUMBER_FORMAT} />
      ) : (
        ''
      )}

      {/* {metrics?.cache ? (
        <Row>
          <Col sm="12">
            <CacheMetrics cacheMetrics={metrics.cache} twoDigitAfterPointFormat={APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT} />
          </Col>
        </Row>
      ) : (
        ''
      )} */}

      {/* {metrics?.databases && JSON.stringify(metrics.databases) !== '{}' ? (
        <Row>
          <Col sm="12">
            <DatasourceMetrics datasourceMetrics={metrics.databases} twoDigitAfterPointFormat={APP_TWO_DIGITS_AFTER_POINT_NUMBER_FORMAT} />
          </Col>
        </Row>
      ) : (
        ''
      )} */}
    </div>
  );
};

export default MetricsPage;
