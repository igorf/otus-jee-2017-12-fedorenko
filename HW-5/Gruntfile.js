'use strict';

module.exports = function (grunt) {

    require('load-grunt-tasks')(grunt);

    require('time-grunt')(grunt);

    grunt.initConfig({

            config: {
                path: {
                    webapp: {
                        root: 'src/main/webapp'
                    },
                    temp: {
                        root: 'temp'
                    },
                    build: {
                        root: 'build'
                    }
                }
            },

            clean: {
                build: [
                    '<%= config.path.temp.root %>',
                    '<%= config.path.build.root %>'
                ]
            },

            "bower-install-simple": {
                options: {
                    color:       true
                },
                "prod": {
                    options: {
                        production: true
                    }
                },
                "dev": {
                    options: {
                        production: false
                    }
                }
            },

            wiredep: {
                target: {
                    src: '<%= config.path.webapp.root %>/_index.html',
                    ignorePath: '<%= config.path.webapp.root %>'
                }
            },
            copy: {
                build: {
                    files: [
                        {
                            src: '<%= config.path.webapp.root %>/_index.html',
                            dest: '<%= config.path.build.root %>/_index.html'
                        }
                    ]
                }
            },

            htmlmin: {
                prod: {
                    options: {
                        collapseBooleanAttributes: true,
                        collapseWhitespace: true,
                        removeComments: true,
                        removeCommentsFromCDATA: true,
                        removeEmptyAttributes: true,
                        removeOptionalTags: true,
                        removeRedundantAttributes: true,
                        useShortDoctype: true
                    },
                    files: [
                        {
                            expand: true,
                            cwd: '<%= config.path.build.root %>',
                            src: ['index.html'],
                            dest: '<%= config.path.build.root %>'
                        }
                    ]
                }
            },

            useminPrepare: {
                html: '<%= config.path.webapp.root %>/_index.html',
                options: {
                    staging: '<%= config.path.temp.root %>',
                    root: '<%= config.path.webapp.root %>',
                    dest: '<%= config.path.build.root %>'
                }
            },

            usemin: {
                html: '<%= config.path.build.root %>/_index.html'
            },

            uglify: {
                options: {
                    mangle: false
                }
            }
        }
    );

    grunt.registerTask('install', [
        'clean:build',
        'bower-install-simple',
        //'concat:styles',
        'wiredep',
        'useminPrepare',
        'concat:generated',
        'cssmin',
        'uglify',
        'copy:build',
        'usemin',
        'htmlmin'
    ]);

    grunt.registerTask('process-resources', [
        'bower-install-simple',
        'wiredep',
    ]);

    grunt.registerTask('default', [
        'install'
    ]);
};
