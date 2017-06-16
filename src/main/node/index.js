/**
 * Loads and extends scripts via Browserify / require into Nashorn.
 *
 * The steps to create a bundle are
 *  > npm install
 *  > npm install -g browserify
 *  > browserify index.js -o redbarn-bundle.js
 *
 * Once the bundle is created it should be loaded into the JDK's jjs REPL to
 * ensure that it can be used in redbarn.
 * > jjs
 * jjs> load('redbarn-bundle.js');
 * jjs> // Each of the following commands should print functions or objects
 * jjs> console.log
 * jjs> redbarn
 * jjs> _.concat
 * jjs> cheerio
 */

// Export these items all to the global namespace.
console = require('./lib/global/console');
cheerio = require('cheerio');
_ = require('lodash');
redbarn = require('./lib/global/redbarn');

// Extend lodash
_.mixin({'uuid': require('./lib/lodash/uuid')});
_.mixin({'replaceAll': require('./lib/lodash/replace-all')});
_.mixin({'getParams': require('./lib/lodash/get-params')});
_.mixin({'argsToArray': require('./lib/lodash/args-to-array')});

// Extend cheerio
_.extend(cheerio.prototype, require('./lib/cheerio/repeat'));
_.extend(cheerio, require('./lib/cheerio/reload'));

