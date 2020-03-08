const App = require('../App');
const Dashboard = require('../Dashboard/Dashboard').controller

const controller = App.buildEmptyController('#inject-main', './Login/Login.html');

function submit_click(){
    Dashboard._load();
}

controller._init = function () {
    console.log('LOGIN INIT');
    $('#submit').click(submit_click);
}

module.exports = {
    controller,
}
