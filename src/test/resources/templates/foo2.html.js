//@ sourceURL=src/test/resources/templates/foo2.html.js

function render(lorem, fruit) {
    'use strict';

    // Replace the inner html of the lone div
    $('div').html("foo bar baz");

    // Modify the fruit list
    $('ul').empty();
    fruit = Java.from(fruit);
    fruit.forEach(function (type) {
        $('ul').append('<li>' + type + '</li>');
    });

    // Replace all of the meat with filler.
    $('p').html(lorem);
}