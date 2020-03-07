const fs = require('fs');
var options = JSON.parse(fs.readFileSync('./config.json', 'utf8'));

options.databasePassword = 'hydrogiraffe';

module.exports = {
    options,
};
