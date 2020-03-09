const { ipcRenderer } = require('electron')
const App = require('../App');

const Encryption = require('./Encryption');
const Dashboard = require('../Dashboard/Dashboard').controller

const controller = App.buildEmptyController('#inject-main', './Login/Login.html');

function submit_click() {
    ipcRenderer.send('send/login/get', $('#username').val());
}

ipcRenderer.on('reply/login/get', (event, data) => {
    if (data) {
        if (Encryption.isPasswordCorrect($('#password').val(), data.passwordHash, data.passwordSalt)) {
    Dashboard._load();
        } else {
            console.log('incorrect password');
        }
    } else {
        console.log('incorrect username');
}
});

controller._init = function () {
    console.log('LOGIN INIT');
    $('#submit').click(submit_click);
}

module.exports = {
    controller,
}
