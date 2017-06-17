//@ sourceURL=src/test/resources/templates/foo.html.js

function render(lorem, fruit) {
    'use strict';

    var grapes = [ 'concord', 'white', 'red' ];

    // Modify the fruit list
    $('ul').empty();
    fruit = Java.from(fruit);
    fruit.forEach(function (type) {
        $('ul').append('<li>' + type + '</li>');
    });

    load('classpath:scripts/lodash.js');
    $('ul').append('<li>' + _.nth(grapes, 1) + ' grapes</li>');

    // Replace all of the meat with filler.
    $('p').html(lorem);

    // Add stuff to the body
    var filler = '<p>The quick brown fox jumps over the lazy dog</p>';
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
    $('body').append(filler);
}
