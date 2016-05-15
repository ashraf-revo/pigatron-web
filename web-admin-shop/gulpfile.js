var gulp = require('gulp');
var concat = require("gulp-concat");
var babel = require("gulp-babel");
var ngAnnotate = require("gulp-ng-annotate");
var webpack = require('webpack-stream');

var paths = {
    src: 'src/main/resources/static',
    dst: 'build/resources/main/static'
};

var files = {
    jsAllJsx: paths.src+'/admin/shop/**/*.jsx',
    jsIndex: paths.dst+'/admin/shop/index.js',
    jsDestPath: paths.dst+'/admin/shop/',
    jsBundleDestPath: paths.dst+'/admin/',
    jsBundleDestFile: 'web-admin-shop.js'
};

var babelOptions = { presets: ['es2015'] };

var webpackOptions = {
    output: {
        filename: files.jsBundleDestFile,
        libraryTarget: "var",
        library: "web_admin_shop"
    }
};



gulp.task('jsBabel', function() {
    return gulp.src(files.jsAllJsx)
        .pipe(babel(babelOptions))
        .pipe(ngAnnotate())
        .pipe(gulp.dest(files.jsDestPath))
});

gulp.task('jsWebpack', ['jsBabel'], function() {
    return gulp.src(files.jsIndex)
        .pipe(webpack(webpackOptions))
        .pipe(gulp.dest(files.jsBundleDestPath));
});

gulp.task('build', ['jsBabel', 'jsWebpack']);
