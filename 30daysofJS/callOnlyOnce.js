/* Leetcode 2666
Given a function fn, return a new function that is identical to the original function except that it ensures fn is called at most once.
- The first time the returned function is called, it should return the same result as fn.
- Every subsequent time it is called, it should return undefined. */

const once = function (fn) {
    var executed = false;

    return function (...args) {
        if (executed === true) {
            return undefined;
        } else {
            executed = true;
            return fn(...args);
        }
    };
};

let fn = (a, b, c) => (a + b + c);
let onceFn = once(fn);

console.log(onceFn(1, 2, 3)); // 6
console.log(onceFn(4, 5, 6)); // returns undefined without calling fn
console.log(onceFn(7, 8, 9)); // returns undefined without calling fn
console.log(onceFn(0)); // returns undefined without calling fn
