const { Sequelize, Model, DataTypes } = require('sequelize');
const Config = require('./config').options

const Database = new Sequelize(null, null, Config.databasePassword, {
    dialect: 'sqlite',
    dialectModulePath: '@journeyapps/sqlcipher',
    storage: Config.databasePath,
    logging: console.log,
});

Database.query('PRAGMA cipher_compatibility = 3');
Database.query("PRAGMA key = '" + Config.databasePassword + "'");

const User = Database.define('User', {
    id: {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
    },
    firstName: {
        type: Sequelize.STRING(100),
        allowNull: false,
    },
    lastName: {
        type: Sequelize.STRING(100),
        allowNull: false,
    },
    username: {
        type: Sequelize.STRING(50),
        allowNull: false,
        unique: true,
    },
    passwordHash: {
        type: Sequelize.STRING(128),
        allowNull: false,
    },
    passwordSalt: {
        type: Sequelize.STRING(128),
        allowNull: false,
    },
});

module.exports = {
    Database,
    User,
};
