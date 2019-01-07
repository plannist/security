<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
	<script src = "https://code.angularjs.org/1.6.9/angular.js"> </script> 
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script> -->
<title>AngularJS Ex</title>
<input type="hidden" id="myName" name="myName" value="${name}" />
</head>



<body>
	<!-- div 에서 ng-app 을 선언 해야 한다. 그뒤 model의 name 과 {{name}} 값 매칭 해야한다. -->
	<div ng-app="DemoApp" ng-controller="DemoController">
		<input type="text" ng-model="yourName">
		<p> 안녕하세요, {{yourName}} !!!</p>
		</br>
		<p>My first expression: {{ 5+5 }}</p>
		</br>
		<input style="background-color:{{myCol}}" ng-model="yourName">
		<input type="hidden" ng-model="numb=['1', '2', '3', '4', '5']" />
		<ui>
			<li ng-repeat="x in numb">
				{{ x }}
			</li>
		</ui>

		<h3>Tutorial Name : <input type="text" ng-model="tutorialName"></h3>
		<h3> This tutorial is {{tutorialName}} </h3>
		<h3> 내이름은 {{name}} </h3>
	</div>
	
	<div ng-app="apply" >
		<input type="text" ng-model="lastName"/>
		<input type="text" ng-model="firstName"/>
		<h1> Hello {{firstName}} {{lastName}}</h1>
	</div>

	<script>
		var name = $("#myName").val();
		console.log("{}{}: "+name);
		
		var app = angular.module('DemoApp', []);
		app.controller('DemoController', function($scope){
			$scope.tutorialName = "Angular JS";
			$scope.name = name;
			$scope.myCol = "red";
		});
		
		
		var apply = angular.module('Apply', []);
		apply.controller('apllyController', function ($scope){
			$scope.firstName = "박";
			set.timeout(function(){
				$scope.lastName = "종석";
				$scope.$apply();
			}, 1000);
			
		});
		
		
// 		$(document).ready(function(){

// 		});
		
	</script>
</body>
</html>