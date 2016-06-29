var ExtractTextPlugin = require("extract-text-webpack-plugin");
module.exports = {
     entry: './src/main/app/app.js',
     output: {
         path: './target/classes/static/',
         filename: 'app.bundle.js'
     },
     module: {
         loaders: [
             { test: /\.scss$/, loader:  ExtractTextPlugin.extract("style","css!sass") },
             { test: /\.css$/, loader: ExtractTextPlugin.extract("style-loader", "css-loader") },
             { test: /\.eot(\?v=\d+\.\d+\.\d+)?$/, loader: "file" },
             { test: /\.(woff|woff2)$/, loader:"url?prefix=font/&limit=5000" },
             { test: /\.ttf(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=application/octet-stream" },
             { test: /\.svg(\?v=\d+\.\d+\.\d+)?$/, loader: "url?limit=10000&mimetype=image/svg+xml" },
             { test: /\.html$/, loaders: ['ngtemplate', 'html'] }
         ]
     },
     plugins: [
         new ExtractTextPlugin("styles.bundle.css", {
            allChunks: true
         })
     ],
     resolve: {
        root: __dirname,
        extensions: ['', '.js'],
        modulesDirectories: [
            'node_modules', '.'
        ],
        alias: {
        }
    }
 };
