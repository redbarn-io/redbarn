/**
 * Extends cheerio.js to repeat the selected element for each element in an
 * array of items.  The selected element's parent is emptied and new elements
 * using the selected element as a template are appended to the parent.
 *
 * For example:
 *
 * $ = cheerio.load('<ul class="foo"><li>Bananas</li><li>Plums</li></ul>');
 * var apples = [
 *   { name: 'Fuji' },
 *   { name: 'Gala' }
 * ];
 *
 * // Note the selector here doesn't need to single out an element.
 * // It can identify more than one element as it does below.
 * $('.foo > li').repeat(apples, function (apple, li) {
 *   li.text(apple.name);
 * });
 *
 * $.html();
 * // => '<html><head></head><body><ul class="foo"><li>Fuji</li><li>Gala</li></ul></body></html>
 *
 * @param {Array} items The array of items to iterate through.
 * @param {Function} callback The function called when iterating through items.
 *                            callback accepts two arguments, the current item
 *                            in the array and a cheerio object containing a
 *                            copy of the selected element which will be
 *                            appended to the selected element's parent.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
exports.repeat = function (items, callback) {
    var $ = $ || cheerio,
        $ele = $(this),
        $parent = $ele.parent(),
        $first = $ele.first(),
        template = $('<div>').append($first.clone()).html();
    $parent.empty();
    if (!Array.isArray(items)) {
        items = Java.from(items);
    }
    items.forEach(function (item) {
        var $template = $(template);
        if (callback) {
            callback(item, $template);
        }
        $parent.append($template);
    });
};
