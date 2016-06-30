var angular = require('angular');
var module = angular.module('AngularSpringRestDemo');

module.config(function($stateProvider, $urlRouterProvider) {
  //
  // For any unmatched url, redirect to /
  $urlRouterProvider.otherwise("/");
  //
  // Now set up the states
  $stateProvider
    .state('root', {
      template: "<main></main>"
    })
    .state('root.itemList', {
        url: "/",
        template: "<item-list></item-list>"
    })
    .state('root.itemNew', {
      url: "/new",
      template: "<item-new></item-new>"
    })
    .state('root.ItemDetails', {
      url: "/:id",
      template: "<item-details></item-details>"
    });
});
