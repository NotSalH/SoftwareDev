const { ipcRenderer } = require('electron')
const App = require('../App');

const Encryption = require('./Encryption');
const Dashboard = require('../Dashboard/Dashboard').controller

const controller = App.buildEmptyController('#inject-main', './Login/Login.html');

controller._init = function () {
    $('#submit').click(submit_click);
}

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

module.exports = {
    controller,
}
