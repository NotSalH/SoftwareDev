const App = require('../App');
const Navbar = require('../Navbar/Navbar').controller;

const controller = App.buildEmptyController('#inject-main', './Dashboard/Dashboard.html');

controller._init = function () {
    Navbar._load();
}

module.exports = {
    controller,
}
