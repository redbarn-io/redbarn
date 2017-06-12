/**
 * The implicit 'arguments' property of a JavaScript function is not a true
 * array as mentioned here (https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/arguments).
 * The argsToArray function converts arguments to a real JavaScript array.  This
 * is useful when you need to 'apply' the arguments of one function to another.
 * In redbarn getParams is exposed as a Lodash mixin.
 *
 * General usage:
 * function process(foo, bar) {
 *   console.log(foo);
 *   console.log(bar);
 * }
 *
 * function print() {
 *   var args = argsToArray(arguments);
 *   process.apply(this, args);
 * }
 *
 * print('foo', 'bar');
 * // => foo
 * // => bar
 *
 * Redbarn usage:
 * function process(foo, bar) {
 *   console.log(foo);
 *   console.log(bar);
 * }
 *
 * function print() {
 *   var args = _.argsToArray(arguments);
 *   process.apply(this, args);
 * }
 *
 * print('foo', 'bar');
 * // => foo
 * // => bar
 *
 * @param {Object} arguments The array like arguments property of a function.
 *
 * @return {Array} The arguments of a function as an actual JavaScript array.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
function argsToArray(args) {
    var value = [],
        index = 0,
        length = args.length;
    for(index; index < length; index++) {
        value.push(args[index]);
    }
    return value;
}

module.exports = argsToArray;
