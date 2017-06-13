var _ = { merge: require('lodash/merge') };

/**
 * The reload function works the same as the native Cheerio.load method in that
 * it loads Cheerio with markup and returns a jQuery like 'DOM' instance.  It
 * has been added here to merge in convenience methods such as 'markup' into the
 * jQuery like dom.
 *
 * To be perfectly honest, I'm not sure what all of the arguments really do.
 * That said, you can find out more about them by looking at the source code
 * for Cheerio's static "load" method (found in static.js as of this writing).
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
exports.reload = function(content, options, isDocument) {
    var $ = cheerio.load(content, options, isDocument);

    // Merge in extensions to the $ object that should be executed like
    // the $.html() method.
    _.merge($, require('./markup'));

    return $;
}