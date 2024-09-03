import { TranslatorContext, Storage } from 'react-jhipster';

import { setLocale } from 'app/shared/reducers/locale';

TranslatorContext.setDefaultLocale('zh-cn');
TranslatorContext.setRenderInnerTextForMissingKeys(false);

export const languages: { [key: string]: { name: string } } = {
  'zh-cn': { name: '中文（简体）' },
  en: { name: 'English' },
  // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
};

export const languageArray = Object.entries(languages).map(([key, { name }]) => ({
  key,
  value: name,
}));

export const locales = Object.keys(languages).sort();

export const registerLocale = store => {
  store.dispatch(setLocale(Storage.session.get('locale', 'zh-cn')));
};
