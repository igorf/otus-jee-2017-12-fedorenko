angular.module('empApp.controllers',[

]).controller('EmployeeListController', function($scope, $state, popupService, $window, Employee) {

    $scope.employees = Employee.query();

    $scope.deleteEmployee = function(employee) {
        if(popupService.showPopup('Really delete this?')) {
            employee.$delete(function() {
                $state.go('employee');
            });
        }
    }

}).controller('EmployeeViewController', function($scope,$stateParams, Employee) {

    $scope.employee = Employee.get({id:$stateParams.id});

}).controller('EmployeeCreateController', function($scope, $state, $stateParams, Employee, Position, Role) {

    $scope.employee = new Employee();
    $scope.positions = Position.query();
    $scope.roles = Role.query();

    $scope.addEmployee = function() {
        $scope.employee.$save(function() {
            $state.go('employee');
        });
    }

}).controller('EmployeeEditController', function($scope, $state, $stateParams, Employee, Position, Role) {
    $scope.positions = Position.query();
    $scope.roles = Role.query();

    $scope.updateEmployee = function() {
        $scope.employee.$save(function() {
            $state.go('employee');
        });
    };

    $scope.loadEmployee = function() {
        $scope.employee = Employee.get({id: $stateParams.id});
    };

    $scope.loadEmployee();
}).controller('PositionsListController', function($scope, $state, popupService, $window, Position) {

    $scope.positions = Position.query();

    $scope.deletePosition = function(position) {
        if(popupService.showPopup('Really delete this?')) {
            position.$delete(function() {
                $state.go('positions');
            });
        }
    }

}).controller('PositionsViewController', function($scope,$stateParams, Position) {

    $scope.position = Position.get({id:$stateParams.id});

}).controller('PositionCreateController', function($scope, $state, $stateParams, Position) {

    $scope.position = new Position();
    $scope.addPosition = function() {
        $scope.position.$save(function() {
            $state.go('positions');
        });
    }

}).controller('PositionEditController', function($scope, $state, $stateParams, Position) {

    $scope.updatePosition = function() {
        $scope.position.$save(function() {
            $state.go('positions');
        });
    };

    $scope.loadPosition = function() {
        $scope.position = Position.get({id: $stateParams.id});
    };

    $scope.loadPosition();
}).controller('RolesListController',function($scope, $state, popupService, $window, Role) {

    $scope.roles = Role.query();

    $scope.deleteRole = function(role) {
        if(popupService.showPopup('Really delete this?')) {
            role.$delete(function() {
                $state.go('roles');
            });
        }
    }

}).controller('RolesViewController',function($scope,$stateParams, Role) {

    $scope.role = Role.get({id:$stateParams.id});

}).controller('RoleCreateController', function($scope, $state, $stateParams, Role) {

    $scope.role = new Role();
    $scope.addRole = function() {
        $scope.role.$save(function() {
            $state.go('roles');
        });
    }

}).controller('RoleEditController', function($scope, $state, $stateParams, Role) {

    $scope.updateRole = function() {
        $scope.role.$save(function() {
            $state.go('roles');
        });
    };

    $scope.loadRole = function() {
        $scope.role = Role.get({id: $stateParams.id});
    };

    $scope.loadRole();
}).controller('LoginController', function($scope, $state, $http) {

    $scope.data = {};
    $scope.login = function() {
        var config = {
            headers : {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
            }
        }

        var send = {
            'login': $scope.data.login,
            'password': $scope.data.password
        };

        $http.post("/j_login", send, config)
            .success(function (data, status, headers, config) {
                $state.go('employee');
            })
            .error(function (data, status, header, config) {
                console.log(status);
            });
    }

});