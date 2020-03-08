const gulp = require('gulp');
const noop = require("gulp-noop");
const minimist = require("minimist");
const del = require('del');

const encryption = require('./MedicalDoctor/Login/encryption');

var argstemplate = {
    string: ['password'],
    default: {
        password: '',
    }
};

var args = minimist(process.argv.slice(2), argstemplate);

gulp.task('deleteDatabase', function () {
    return del(['data/MedicalDoctor.db']);
});

gulp.task('createDatabase', async function () {
    console.log('CREATING DATABASE');
    const db = require('./MedicalDoctor/database');
    const t = await db.Database.transaction();
    try {
        await db.Database.sync();
        console.log('CREATING TEST RECORDS');
        require('./data/testRecords').createTestRecords(db, encryption);
        await t.commit();
    } catch (ex) {
        await t.rollback();
        throw ex;
    } finally {
        await db.Database.close();
    }
    return noop();
});

gulp.task('setupDatabase', gulp.series('deleteDatabase', 'createDatabase'))

gulp.task('hashPassword', async function () {
    var result = encryption.hashPassword(args.password);
    console.log('ENCRYPTING PASSWORD: ' + args.password);
    console.log('COPY THE FOLLOWING');
    console.log('PASSWORD HASH');
    console.log(result.hash);
    console.log('PASSWORD SALT');
    console.log(result.salt);
    return noop();
});
