// An IIFE for HTML template processing / model binding.  At runtime the
// JavaScript expression '{ProcessFunction}' is substituted for the user
// defined "process" function.  The "process" function is, in turn, called
// by one of the conversion functions such as "processor.body()" or "processor.html()".
//
//  context - The global context.
//  _       - A JavaScript utility helper (lodash).
//  cheerio - A jQuery like HTML processor.
(function (context, _, cheerio) {
    'use strict';

    var $ = null,
        processor = {};

    '{ProcessFunction}';

    // Clean and load cheerio with markup.
    processor.markup = function(markup, options) {
        markup = _.replaceAll(markup, '&#039;', '\'');
        $ = cheerio.load(markup, options);
    };

    // Stores the processor script into the array of processors.
    processor.save = function(key) {
        context.redbarn.processors[key] = processor;
    };

    // Gets all parameters listed in the "process" function as non-null array.
    processor.params = function () {
        return _.getParams(process);
    };

    // Executes the process function specified in the HTML template and
    // returns the markup fragment in the body tag.
    processor.body = function() {
        var $body = $('body'),
            args = [];
        if (process) {
            args = _.argsToArray(arguments);
            process.apply(processor, args);
        }
        return $body.html() || 'Something went wrong';
    };

    // Executes the process function specified in the HTML template and
    // returns the entire processed markup file.
    processor.html = function() {
        var args = [];
        if (process) {
            args = _.argsToArray(arguments);
            process.apply(processor, args);
        }
        return $.html();
    };

    // Beautifies HTML markup.
    processor.beautify = function(markup, options) {
        markup = markup || '';
        return beautifyHtml(markup, options);
    };

    return processor;

})(this, this._, this.cheerio);