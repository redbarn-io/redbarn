/**
 * Finds and replaces all occurrances of a string with another string.
 * In redbarn replaceAll is exposed as a Lodash mixin.
 *
 * General usage:
 * var foo = 'The quick brown fox jumps over the lazy dogs';
 * foo = replaceAll(foo, 'o', '*');
 * // foo => 'The quick br*wn f*x jumps *ver the lazy d*gs';
 *
 * Redbarn usage:
 * var foo = 'The quick brown fox jumps over the lazy dogs';
 * foo = _.replaceAll(foo, 'o', '*');
 * // foo => 'The quick br*wn f*x jumps *ver the lazy d*gs';
 *
 * @return {String} The original string with replacements.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
function replaceAll(original, find, replace) {
    original = original || '';
    find = find || '~~%$#@~~';
    replace = replace || '';
    return original.split(find).join(replace);
}

module.exports = replaceAll;
