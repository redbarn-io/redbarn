// Adds useful variables to the global namespace.
//  context - The global context.
(function (context) {
    'use strict';

    // Ensure the keyword global can be used to reference
    // the global context itself.
    if (!context.global) {
        context.global = context;
    }

    // Alias print to console.log since client side developers are used to it.
    if (context.print && !context.console) {
        context.console = {
            log: print,
            warn: print,
            debug: print,
            info: print
        }
    }

}(this));

