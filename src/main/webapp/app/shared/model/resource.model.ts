import { IAuthority } from 'app/shared/model/admin/authority.model';

export interface IResource {
  id?: number;
  name?: string | null;
  permission?: string | null;
  authority?: IAuthority | null;
}

export const defaultValue: Readonly<IResource> = {};
