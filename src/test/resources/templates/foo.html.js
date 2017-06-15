//@ sourceURL=src/test/resources/templates/foo.html.js

function render(lorem, fruit) {

    // Modify the list of fruits.
    $('ul').empty();
    fruit = Java.from(fruit);
    fruit.forEach(function (type) {
        $('ul').append('<li>' + type + '</li>')
    });

    // Replace all of the meat with filler.
    $('p').html(lorem);
}