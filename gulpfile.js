const gulp = require('gulp');
const noop = require("gulp-noop");

const fs = require('fs');
const sqlite3 = require('sqlite3').verbose();

var tables = ['Table.User.sql'];
var records = ['Users.sql'];

function createTables(db) {
    console.log('CREATING TABLES');
    for (var i = 0; i < tables.length; i++) {
        console.log(tables[i]);
        var sql = fs.readFileSync('./data/tables/' + tables[i], 'utf8');
        db.run(sql);
    }
}

function createTestRecords(db) {
    console.log('CREATING TEST RECORDS');
    for (var i = 0; i < records.length; i++) {
        console.log(records[i]);
        var sql = fs.readFileSync('./data/setup/' + records[i], 'utf8');
        db.run(sql);
    }
}

gulp.task('createDatabase', async function () {
    fs.copyFileSync('./data/MedicalDoctor-empty.db', './data/MedicalDoctor.db');
    var db;
    try {
        db = new sqlite3.Database('./data/MedicalDoctor.db');
        createTables(db);
        createTestRecords(db);
    } finally {
        if (db) {
            db.close();
        }
    }
    return noop();
});
