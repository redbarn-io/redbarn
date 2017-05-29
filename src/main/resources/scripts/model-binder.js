// An IIFE for model binding / HTML template processing.  At runtime the
// JavaScript expression '{ProcessFunction}' is substituted for the user
// defined "process" function.  The "process" function is, in turn, called
// by either of the conversion functions such as "that.body()" or "that.html()".
//
//  context - The global context.
//  _       - A JavaScript utility helper (lodash).
//  cheerio - A jQuery like HTML processor.
(function (context, _, cheerio) {
    'use strict';

    var $ = null,
        that = {};

    '{ProcessFunction}';

    // Clean and load cheerio with markup.
    that.markup = function(markup) {
        markup = _.replaceAll(markup, '&#039;', '\'');
        $ = cheerio.load(markup);
    };

    // Stores the model binding script into the array of binders.
    that.saveModelBinder = function(key) {
        context.redbarn.processors[key] = that;
    };

    // Executes the process function specified in the HTML template and
    // returns the markup fragment in the body tag.
    that.body = function(args) {
        var $body = $('body'),
            args = [];
        if (process) {
            args = _.argsToArray(arguments);
            process.apply(that, args);
        }
        return $body.html() || 'Something went wrong';
    };

    // Executes the process function specified in the HTML template and
    // returns the entire processed markup file.
    that.html = function() {
        var args = [];_.argsToArray(arguments);
        if (process) {
            args = _.argsToArray(arguments);
            process.apply(that, args);
        }
        return $.html();
    };

    return that;

})(global, global._, global.cheerio);
