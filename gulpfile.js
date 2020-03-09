const gulp = require('gulp');
const noop = require("gulp-noop");
const run = require('gulp-run-command').default
const del = require('del');

gulp.task('deleteDatabase', function () {
    return del(['data/MedicalDoctor.db']);
});

gulp.task('createDatabase', async function () {
    console.log('CREATING DATABASE');
    const db = require('./MedicalDoctor/Database');
    const t = await db.Database.transaction();
    try {
        await db.Database.sync();
        console.log('CREATING TEST RECORDS');
        const Encryption = require('./MedicalDoctor/Login/Encryption');
        require('./data/testRecords').createTestRecords(db, Encryption);
        await t.commit();
    } catch (ex) {
        await t.rollback();
        throw ex;
    } finally {
        await db.Database.close();
    }
    return noop();
});

gulp.task('setupDatabase', gulp.series('deleteDatabase', 'createDatabase'));

gulp.task('setupPackages', async function(){
    return run('npm install');
});

gulp.task('first', gulp.series('setupPackages', 'setupDatabase'));
