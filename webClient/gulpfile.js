'use strict';

const gulp = require('gulp');
const browserify = require('browserify');
var browserSync = require('browser-sync').create();

gulp.task('server', function() {
  browserSync.init({
    serveStatic: ['./app'],
    proxy: {
      target: 'http://localhost:3000',
      ws: true,
      middleware: (req, res, next) => {
        console.log(req.url);
        next();
      }
    },
    port: 3001
  });
});

gulp.task('default', ['server']);
