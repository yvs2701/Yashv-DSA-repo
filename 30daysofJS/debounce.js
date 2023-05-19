/* Leetcode 2627
Given a function fn and a time in milliseconds t, return a debounced version of that function.
A debounced function is a function whose execution is delayed by t milliseconds and whose execution is cancelled if it is called again within that window of time.
The debounced function should also recieve the passed parameters. */

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
