// Creates the redbarn namespace.
//  context - The global context.
//  cheerio - A jQuery like HTML processor.
(function (context, _, cheerio, markup, redbarnName) {
    'use strict';

    var $ = null,
        $redbarnElement = null,
        that = {};

    // Clean and load cheerio from markup.
    markup = _.replaceAll(markup, '&#039;', '\'');
    $ = cheerio.load(markup);

    // Create a bindEach cheerio plugin.
    $.prototype.bindEach = function (items, callback) {
        var $ele = $(this),
            $first = $ele.children().first(),
            template = $('<div>').append($first.clone()).html();
        $ele.empty();
        items.forEach(function (item) {
            var $template = $(template);
            if (callback) {
                callback($template, item);
            }
            $ele.append($template);
        });
    };

    '%Replace with model binding functions%';

    // Executes the binding function specified in the HTML template and
    // returns the model bound markup fragment.
    that.body = function(args) {
        var $body = $('body');
        bind.apply(that, args);
        return $body.html() || 'Something went wrong';
    };

    that.html = function(args) {
        bind.apply(that, args);
        return $.html();
    };

    context.redbarn.binders[redbarnName] = that;
    return that;

})(global, global._, global.cheerio, '%markup%', '%redbarnName%');
