import dict from 'app/entities/dict/dict.reducer';
import authority from 'app/entities/admin/authority/authority.reducer';
import employee from 'app/entities/employee/employee.reducer';
import resource from 'app/entities/resource/resource.reducer';
import leadInfo from 'app/entities/lead-info/lead-info.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  dict,
  authority,
  employee,
  resource,
  leadInfo,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
