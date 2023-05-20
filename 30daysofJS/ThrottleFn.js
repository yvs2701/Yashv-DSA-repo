/* Leetcode 2676: "Throttle"
Given a function fn and a time in milliseconds t, return a throttled version of that function.
A throttled function is first called without delay and then, for a time interval of t milliseconds, can't be executed but should store the latest function arguments provided to call fn with them after the end of the delay.

For instance, t = 50ms, and the function was called at 30ms, 40ms, and 60ms. The first function call would block calling functions for the following t milliseconds. The second function call would save arguments, and the third call arguments should overwrite currently stored arguments from the second call because the second and third calls are called before 80ms. Once the delay has passed, the throttled function should be called with the latest arguments provided during the delay period, and it should also create another delay period of 80ms + t.

A simple way to think about WHEN TO USE debounce and when to use throttle:
    - Debounce protects the user from unwanted events that could create lag (like trying to re-render a large grid of search results every time a character is typed). This is achieved by only executing code AFTER the user is done with their interaction.
    - Throttle prevents code from being called more frequently than the infrastructure/app can handle (like the user trying to spam click download). This is achieved by guaranteeing a limit on how frequently some code can be called. It generally doesn't hurt to apply throttling to most network requests, provided t is reasonably small. */

/**
 * @param {Function} fn
 * @param {number} t
 * @return {Function}
 */
var throttle = function (fn, t) {
    let lastArgs = null;
    let throttled = false;

    function runner() {
        if (!throttled && lastArgs) {
            fn(...lastArgs); // call the fn
            lastArgs = null; // reset lastArgs to null (also prevents endless recursion)

            throttled = true; // start throttling the function
            setTimeout(() => {
                throttled = false;
                runner();
            }, t);
        }
    }

    return function (...args) {
        lastArgs = args; // update with latest arguments
        runner();
    };
};

const start = Date.now();
const throttled = throttle((...args) => {
    console.log(...args, 'Time:', Date.now() - start);
}, 1000);
throttled("Namaste"); // logged immediately.
throttled("Hello"); // overwritten by next call
throttled("World!"); // logged at t=1000ms
