/**
 * Gets all parameters of a listed parameters (not actual arguments) of a
 * function as an array of strings. In redbarn getParams is exposed as a Lodash
 * mixin.
 *
 * General usage:
 * function foo(bar, baz) {
 *   return bar + baz;
 * };
 * var params = getParams(foo);
 * // params => [ 'bar', 'baz' ]
 *
 * Redbarn usage:
 * function foo(bar, baz) {
 *   return bar + baz;
 * };
 * var params = _.getParams(foo);
 * // params => [ 'bar', 'baz' ]
 *
 * @param {Function} func The function to get parameters for.
 *
 * @return {Array} The names of all parameters in the function.  The array will
 *                 be empty if the function has no listed parameters.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
function getParams(func) {
    var text = func.toString(),
        start = text.indexOf('(') + 1,
        end = text.indexOf(')'),
        params = text.slice(start, end).match(/([^\s,]+)/g);
    if (!params) {
        params = [];
    }
    return params;
}

module.exports = getParams;
