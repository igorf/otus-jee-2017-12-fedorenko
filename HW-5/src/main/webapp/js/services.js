angular.module('empApp.services',[

]).factory('Employee', function($resource) {
    return $resource('/employee/:id', {id: '@id'}, {
        get: {
            url: '/employee/get?id=:id'
        },
        save: {
            method: 'POST',
            url: '/employee/save?id=:id'
        },
        delete: {
            url: '/employee/delete?id=:id'
        }
    });
}).factory('Position', function($resource) {
    return $resource('/positions/:id', {id: '@id'}, {
        get: {
            url: '/positions/get?id=:id'
        },
        save: {
            method: 'POST',
            url: '/positions/save?id=:id'
        },
        delete: {
            url: '/positions/delete?id=:id'
        }
    });
}).factory('Role', function($resource) {
    return $resource('/roles/:id', {id: '@id'}, {
        get: {
            url: '/roles/get?id=:id'
        },
        save: {
            method: 'POST',
            url: '/roles/save?id=:id'
        },
        delete: {
            url: '/roles/delete?id=:id'
        }
    });
}).service('popupService',function($window) {
    this.showPopup=function(message) {
        return $window.confirm(message);
    }
});