//@ sourceURL=src/test/resources/scripts/test-renderer.js

function render(fruit, request, $, _) {
    $('ul > li').repeat(fruit, function (type, li) {
        li.text(type);
    });
    return $.markup();
}