const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('path');

const Models = require('./MedicalDoctor/Database');
const Database = Models.Database;

function createWindow() {
    const mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        webPreferences: {
            preload: path.join(__dirname, 'preload.js'),
            nodeIntegration: true,
        },
    });

    mainWindow.loadFile('./MedicalDoctor/index.html');
}

app.on('ready', createWindow);

app.on('window-all-closed', function () {
    if (process.platform !== 'darwin') {
        app.quit();
    }
    Database.close();
});

app.on('activate', function () {
    if (BrowserWindow.getAllWindows().length === 0) {
        createWindow();
    }
});

ipcMain.on('send/login/get', function (event, data) {
    Models.User.findAll({
        attributes: ['passwordHash', 'passwordSalt'],
        where: {
            username: data,
        },
    }).then(function (data) {
        if (data.length === 0) {
            event.reply('reply/login/get', false);
        } else {
            event.reply('reply/login/get', {
                passwordHash: data[0].passwordHash,
                passwordSalt: data[0].passwordSalt,
            });
        }
    });
});
