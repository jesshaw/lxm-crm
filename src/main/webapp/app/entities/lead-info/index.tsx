import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import LeadInfo from './lead-info';
import LeadInfoDetail from './lead-info-detail';
import LeadInfoUpdate from './lead-info-update';

const LeadInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<LeadInfo />} />
    <Route path="new" element={<LeadInfoUpdate />} />
    <Route path=":id">
      <Route index element={<LeadInfoDetail />} />
      <Route path="edit" element={<LeadInfoUpdate />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LeadInfoRoutes;
