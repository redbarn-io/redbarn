// Ensures the global redbarn namespace exists.
//  context - The global context.
(function (context) {
    'use strict';

    var that = {
        binders: {}
    };
    context.redbarn = that;
}(global));

