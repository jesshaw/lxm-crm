import { IUser } from 'app/shared/model/user.model';

export interface IEmployee {
  id?: number;
  title?: string | null;
  nickName?: string | null;
  user?: IUser | null;
  reportsTo?: IEmployee | null;
}

export const defaultValue: Readonly<IEmployee> = {};
