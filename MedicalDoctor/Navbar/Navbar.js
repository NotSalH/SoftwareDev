const App = require('../App');

const controller = App.buildEmptyController('#inject-navbar', './Navbar/Navbar.html');

controller._init = function () {
}

module.exports = {
    controller,
}
