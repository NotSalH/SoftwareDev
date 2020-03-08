const Login = require('./Login/Login').controller;

window.$ = window.jQuery = require('jquery');

$(function () {
    Login._load();
});
