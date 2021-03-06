const withCss = require('@zeit/next-css');
const withLess = require('@zeit/next-less');
const FilterWarningsPlugin = require('webpack-filter-warnings-plugin');

// fix: prevents error when .css files are required by node
if (typeof require !== 'undefined') {
  require.extensions['.css'] = (file) => {
  };
}

// fix: prevents error when .less files are required by node
if (typeof require !== 'undefined') {
  require.extensions['.less'] = file => {
  };
}

module.exports = withLess(
  withCss({
    // extends webpack config
    webpack(config, options) {
      config.plugins.push(
        // hidden style conflict warning
        new FilterWarningsPlugin({
          exclude: /mini-css-extract-plugin[^]*Conflicting order between:/,
        })
      );
      // production environment compress css
      if (config.mode === 'production' && Array.isArray(config.optimization.minimizer)) {
        const OptimizeCSSAssetsPlugin = require("optimize-css-assets-webpack-plugin");
        config.optimization.minimizer.push(new OptimizeCSSAssetsPlugin({}));
      }
      return config;
    },
    generateBuildId: async () => {
      return 'v1.0.5';
    },
    distDir: '../.next'
  })
);
