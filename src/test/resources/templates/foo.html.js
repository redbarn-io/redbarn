//@ sourceURL=src/test/resources/templates/foo.html.js

function render(lorem, fruit, markup) {
    'use strict';
    var $ = cheerio.reload(markup);

    // Add items to the 'fruit' list.
    $('ul > li').repeat(fruit, function (type, li) {
        li.text(type);
    });

    // Replace all of the meat with filler.
    $('p').text(lorem);
    return $.html();
}