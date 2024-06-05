import employee from 'app/entities/employee/employee.reducer';
import resource from 'app/entities/resource/resource.reducer';
import leadInfo from 'app/entities/lead-info/lead-info.reducer';
import dict from 'app/entities/dict/dict.reducer';
import authority from 'app/entities/admin/authority/authority.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  employee,
  resource,
  leadInfo,
  dict,
  authority,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
