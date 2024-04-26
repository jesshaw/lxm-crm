import { IUser } from 'app/shared/model/user.model';

export interface IEmployee {
  id?: number;
  title?: string | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IEmployee> = {};
