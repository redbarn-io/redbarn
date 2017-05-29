/**
 * Adds utility functions to lodash.
 */
(function (context, _) {
    'use strict';

    // Creates a new UUID.
    // Slightly modified from http://jsfiddle.net/briguy37/2MVFd/
    function uuid() {
        var d = new Date().getTime(),
            uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx';
        return uuid.replace(/[xy]/g, function(c) {
            var r = (d + Math.random()*16)%16 | 0;
            d = Math.floor(d/16);
            return (c=='x' ? r : (r&0x3|0x8)).toString(16);
        });
    }

    // Replaces all occurances of a substring in a string.
    function replaceAll(original, find, replace) {
        original = original || '';
        find = find || '~~%$#@~~';
        replace = replace || '';
        return original.split(find).join(replace);
    }

    // Gets the parameter names of a function as an array.
    //  fn - The function for which to get parameter names.
    function getParams(func) {
        var text = func.toString(),
            start = text.indexOf('(') + 1,
            end = text.indexOf(')');
        return text.slice(start, end).match(/([^\s,]+)/g);
    }

    // Converts arguments of a method into a real JavaScript array.
    //  args - The arguments to convert.
    function argsToArray(args) {
        var value = [],
            index = 0,
            length = args.length;
        for(index; index < length; index++) {
            value.push(args[index]);
        }
        return value;
    }

    _.mixin({ 'uuid': uuid });
    _.mixin({ 'replaceAll': replaceAll });
    _.mixin({ 'getParams': getParams });
    _.mixin({ 'argsToArray': argsToArray });
}(global, _))