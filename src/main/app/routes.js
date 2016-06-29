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
    .state('root.itemListNew', {
      url: "/new",
      template: "<item-list-new-item></item-list-new-item>"
    })
    .state('root.detailsItemList', {
      url: "/:id",
      template: "<item-list-details></item-list-details>"
    });
});
