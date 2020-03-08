
function buildEmptyController(injectDiv, templatePath) {
    var c = {
        _injectDiv: injectDiv,
        _templatePath: templatePath,
    };
    c._init = function () { };
    c._load = function () {
        $(c._injectDiv).load(c._templatePath, function (response, status, xhr) {
            if (status == "error") { }
            c._init();
        });
    };
    return c;
}

module.exports = {
    buildEmptyController
}
