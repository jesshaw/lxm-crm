import React, { useRef } from 'react';
import { PrimeReactContext } from 'primereact/api';
import { ReactNode, createContext, useContext, useEffect, useState } from 'react';
import { Toast } from 'primereact/toast';
import { setToastInstance } from './toast-manager';

interface ThemeContextType {
  theme: string;
  handleChangeTheme(newTheme: string): void;
}

export const ThemeContext = createContext<ThemeContextType | undefined>(undefined);

export const ThemeProvider = ({ children }) => {
  const { changeTheme } = useContext(PrimeReactContext);
  const initialTheme = 'light/blue';
  const [theme, setTheme] = useState<string>(initialTheme);
  const [themeChanged, setThemeChanged] = useState<boolean>(false); // State to track theme change

  let ranonce = false;
  useEffect(() => {
    if (!ranonce) {
      //Run you code
      const localTheme = localStorage.getItem('theme');
      if (localTheme) {
        handleChangeTheme(localTheme);
        setTheme(localTheme);
      } else {
        handleChangeTheme(initialTheme);
        setTheme(initialTheme);
      }
      ranonce = true;
    }

    if (toast.current) {
      setToastInstance(toast.current);
    }
  }, []);

  const handleChangeTheme = (newTheme: string) => {
    if (!changeTheme) {
      throw new Error('ChangeTheme context not initialized yet.');
    }

    changeTheme(theme, newTheme, 'theme-link', () => {
      setTheme(newTheme);
      localStorage.setItem('theme', newTheme);
      setThemeChanged(true); // Set themeChanged to true after theme change
    });
  };

  const toast = useRef<Toast>(null);

  const value = {
    theme,
    handleChangeTheme,
    toast,
  };

  // Render children only after theme change
  return (
    <ThemeContext.Provider value={value}>
      <Toast ref={value.toast} position="top-left" />
      {themeChanged ? children : null}
    </ThemeContext.Provider>
  );
};
