
function createTestRecords(db, encryption) {
    createTestUsers(db, encryption);
}

function createTestUsers(db, encryption){
    var password = encryption.hashPassword('password123');
    db.User.build({
        firstName: 'Network',
        lastName: 'Admin',
        username: 'admin',
        passwordHash: password.hash,
        passwordSalt: password.salt,
    }).save();
}

module.exports = {
    createTestRecords,
}
