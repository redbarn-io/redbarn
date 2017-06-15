//@ sourceURL=src/test/resources/templates/multiple-script-functions.html.js

// Replace all of the meat with filler.
function replaceMeat(lorem) {
    $('.meat').html(lorem);
}

// Modify the list of fruits.
function listFruit(fruit) {
    $('ul').empty();
    fruit = Java.from(fruit);
    fruit.forEach(function (type) {
        $('ul').append('<li>' + type + '</li>')
    });
}

// Main script called by renderer.
function render(lorem, fruit) {
    replaceMeat(lorem);
    listFruit(fruit);
}
