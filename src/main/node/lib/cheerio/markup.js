//@ sourceURL=src/main/node/lib/cheerio/beautify.js

/**
 * Extends cheerio.js to return _and_ beautify all HTML markup.
 *
 * For example:
 *
 * $ = cheerio.load('<html><body><div>foo</div></body></html');
 * var markup = $.beautify();
 * $(markup).beautify(options);
 * // Produces the following markup
 * <html>
 *
 * <body>
 *     <div>foo</div>
 * </body>
 *
 * </html>
 *
 * @param {Object} options A collection of js_beautify options used to tailor
 *                         the markup.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
var beautify = require('js-beautify').html;

exports.markup = function (dom, options) {
    var html = this.html(dom, options);
    return beautify(html, options);
};
