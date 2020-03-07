const crypto = require('crypto');

// https://stackoverflow.com/questions/17201450/salt-and-hash-password-in-nodejs-w-crypto

const HASH_ITERATIONS = 10000;

function encrypt(password, salt) {
    return crypto.pbkdf2Sync(password, salt, HASH_ITERATIONS, 64, 'sha512').toString('hex');
}

function hashPassword(password) {
    var salt = crypto.randomBytes(64).toString('hex');
    var hash = encrypt(password, salt);
    return {
        hash: hash,
        salt: salt,
    };
}

function isPasswordCorrect(password, savedHash, savedSalt) {
    return encrypt(password, savedSalt) === savedHash;
}

module.exports = {
    hashPassword,
    isPasswordCorrect,
}
