/** @type {import('tailwindcss').Config} */
module.exports = {
  corePlugins: {
    preflight: false,
  },
  content: ['./src/main/webapp/**/*.{html,js,ts,jsx,tsx,mdx}', './node_modules/primereact/**/*.{html,js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        'lxm-primary': 'var(--primary-color)',
        'lxm-surface': 'var(--surface-ground)',
        'lxm-surface-border': 'var(--surface-border)',
        'lxm-surface-card': 'var(--surface-card)',
        'lxm-surface-border': 'var(--surface-border)',
        'lxm-surface-hover': 'var(--surface-hover)',
        'lxm-highlight-bg': 'var(--highlight-bg)',
        'lxm-text': 'var(--text-color)',
        'lxm-text-secondary': 'var(--text-color-secondary)',
      },
      keyframes: {
        flash: {
          '0%,100%': { opacity: '1' },
          '50%': { opacity: '0' },
        },
      },
      animation: {
        flash: 'flash 0.5s ease-in-out',
      },
    },
  },
  plugins: [],
};
