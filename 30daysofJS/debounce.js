/* Leetcode 2627
Given a function fn and a time in milliseconds t, return a debounced version of that function.
A debounced function is a function whose execution is delayed by t milliseconds and whose execution is cancelled if it is called again within that window of time.
The debounced function should also recieve the passed parameters.

A simple way to think about WHEN TO USE debounce and when to use throttle:
    - Debounce protects the user from unwanted events that could create lag (like trying to re-render a large grid of search results every time a character is typed). This is achieved by only executing code AFTER the user is done with their interaction.
    - Throttle prevents code from being called more frequently than the infrastructure/app can handle (like the user trying to spam click download). This is achieved by guaranteeing a limit on how frequently some code can be called. It generally doesn't hurt to apply throttling to most network requests, provided t is reasonably small. */

var debounce = function(fn, t) {
    let timeout;

    return function(...args) {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            fn(...args)
            console.log(Date.now() - start)
        }, t)
    }
};

const start = Date.now()
const log = debounce(console.log, 200);
setTimeout(() => {log('Hello')}, 50); // gets cancelled by the next fn
setTimeout(() => {log('Hello')}, 50); // timer resets, gets cancelled by the next fn
setTimeout(() => {log('Hello')}, 50); // timer resets, gets cancelled by the next fn
setTimeout(() => {log('Hello')}, 50); // timer resets, will be printed finally
