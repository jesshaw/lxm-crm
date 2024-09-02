import dayjs from 'dayjs';
import { IUser } from 'app/shared/model/user.model';

export interface ILeadInfo {
  id?: number;
  salutation?: string | null;
  firstName?: string | null;
  lastName?: string | null;
  title?: string | null;
  department?: string | null;
  doNotCall?: boolean | null;
  phoneHome?: string | null;
  phoneMobile?: string | null;
  phoneWork?: string | null;
  phoneOther?: string | null;
  phoneFax?: string | null;
  dateReviewed?: dayjs.Dayjs | null;
  lawfulBasis?: string | null;
  lawfulBasisSource?: string | null;
  primaryAddressStreet?: string | null;
  primaryAddressCity?: string | null;
  primaryAddressState?: string | null;
  primaryAddressPostalcode?: string | null;
  primaryAddressCountry?: string | null;
  altAddressStreet?: string | null;
  altAddressCity?: string | null;
  altAddressState?: string | null;
  altAddressPostalcode?: string | null;
  altAddressCountry?: string | null;
  assistant?: string | null;
  assistantPhone?: string | null;
  converted?: boolean | null;
  referedBy?: string | null;
  leadSource?: string | null;
  leadSourceDescription?: string | null;
  status?: string | null;
  statusDescription?: string | null;
  birthdate?: dayjs.Dayjs | null;
  description?: string | null;
  reportsTo?: ILeadInfo | null;
  assignedUser?: IUser | null;
}

export const defaultValue: Readonly<ILeadInfo> = {
  doNotCall: false,
  converted: false,
};
