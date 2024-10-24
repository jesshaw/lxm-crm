const webpack = require('webpack');
const webpackMerge = require('webpack-merge').merge;
const BrowserSyncPlugin = require('browser-sync-webpack-plugin');
const SimpleProgressWebpackPlugin = require('simple-progress-webpack-plugin');
const WebpackNotifierPlugin = require('webpack-notifier');
const path = require('path');
const sass = require('sass');

const utils = require('./utils.js');
const commonConfig = require('./webpack.common.js');

const ENV = 'development';

module.exports = async options =>
  webpackMerge(await commonConfig({ env: ENV }), {
    devtool: 'cheap-module-source-map', // https://reactjs.org/docs/cross-origin-errors.html
    mode: ENV,
    entry: ['./src/main/webapp/app/index'],
    output: {
      path: utils.root('target/classes/static/'),
      filename: '[name].[contenthash:8].js',
      chunkFilename: '[name].[chunkhash:8].chunk.js',
    },
    optimization: {
      moduleIds: 'named',
      splitChunks: {
        chunks: 'all', // 分割所有类型的代码
        minSize: 20000, // 最小大小20K
        maxSize: 2000000, // 超过2M则分隔
        maxAsyncRequests: 10, // 按需加载时最大的并行请求数
        maxInitialRequests: 5, // 入口文件加载时最大的并行请求数
        automaticNameDelimiter: '~', // 文件名连接符
        name: false,
        cacheGroups: {
          //拆成4个大文件
          vendorReact: {
            test: /[\\/]node_modules[\\/](react|react-dom)[\\/]/,
            name: 'vendor-react',
            chunks: 'all',
          },
          vendorLodash: {
            test: /[\\/]node_modules[\\/](lodash)[\\/]/,
            name: 'vendor-lodash',
            chunks: 'all',
          },
          vendorPrimeReact: {
            test: /[\\/]node_modules[\\/](primereact)[\\/]/,
            name: 'vendor-primereact',
            chunks: 'all',
          },
          vendorOthers: {
            test: /[\\/]node_modules[\\/]/,
            name: 'vendor-others',
            chunks: 'all',
            priority: -10,
          },

          // vendor: {
          //   test: /[\\/]node_modules[\\/]/,
          //   name: 'vendors',
          //   chunks: 'all',
          // },
        },
      },
    },
    module: {
      rules: [
        {
          test: /\.(sa|sc|c)ss$/,
          use: [
            'style-loader',
            {
              loader: 'css-loader',
              options: { url: false },
            },
            {
              loader: 'postcss-loader',
            },
            {
              loader: 'sass-loader',
              options: { implementation: sass },
            },
          ],
        },
      ],
    },
    devServer: {
      hot: true,
      static: {
        directory: './target/classes/static/',
      },
      port: 9060,
      proxy: [
        {
          context: ['/api', '/services', '/management', '/v3/api-docs', '/h2-console'],
          target: `http${options.tls ? 's' : ''}://localhost:8088`,
          secure: false,
          changeOrigin: options.tls,
        },
      ],
      historyApiFallback: true,
    },
    stats: process.env.JHI_DISABLE_WEBPACK_LOGS ? 'none' : options.stats,
    plugins: [
      process.env.JHI_DISABLE_WEBPACK_LOGS
        ? null
        : new SimpleProgressWebpackPlugin({
            format: options.stats === 'minimal' ? 'compact' : 'expanded',
          }),
      new BrowserSyncPlugin(
        {
          https: options.tls,
          host: 'localhost',
          port: 9000,
          proxy: {
            target: `http${options.tls ? 's' : ''}://localhost:${options.watch ? '8088' : '9060'}`,
            ws: true,
            proxyOptions: {
              changeOrigin: false, //pass the Host header to the backend unchanged  https://github.com/Browsersync/browser-sync/issues/430
            },
          },
          socket: {
            clients: {
              heartbeatTimeout: 60000,
            },
          },
          /*
      ,ghostMode: { // uncomment this part to disable BrowserSync ghostMode; https://github.com/jhipster/generator-jhipster/issues/11116
        clicks: false,
        location: false,
        forms: false,
        scroll: false
      } */
        },
        {
          reload: false,
        },
      ),
      new WebpackNotifierPlugin({
        title: 'Lxmcrm',
        contentImage: path.join(__dirname, 'logo.png'),
      }),
    ].filter(Boolean),
  });
