/**
 * Produces a Universally Unique ID (uuid) based on the current date and time.
 * In redbarn uuid is exposed as a Lodash mixin.
 *
 * General usage:
 * var id = uuid();
 * // id => 2f3064b6-1d8f-49dd-bb4a-580dbc38960c
 *
 * Redbarn usage:
 * var id = _.uuid();
 * // id => 7dc9c55a-c7d5-45a0-beb1-3ce3c99724dd
 *
 * @return {String} The uuid as a string.
 *
 * @author Mike Atkisson
 * @since 0.1.0
 */
function uuid() {
    var d = new Date().getTime(),
        uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx';
    return uuid.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
}

module.exports = uuid;

