/* Leetcode 2623
Given a function fn, return a memoized version of that function.
A memoized function is a function that will never be called twice with the same inputs. Instead it will return a cached value.
You can assume there are 3 possible input functions: sum, fib, and factorial.
- sum accepts two integers a and b and returns a + b.
- fib accepts a single integer n and returns 1 if n <= 1 or fib(n - 1) + fib(n - 2) otherwise.
- factorial accepts a single integer n and returns 1 if n <= 1 or factorial(n - 1) * n otherwise. */

function memoize(fn) {
    const cache = new Map();
    return function (...args) {
        const key = JSON.stringify(args);
        if (cache.has(key)) {
            const val = cache.get(key);
            return val;
        }
        else {
            const val = fn(...args);
            cache.set(key, val);
            return val;
        }
    }
}

let callCount = 0;
const fib = (a, b) => {
    callCount++;
    return a + b;
}

const memoizedFn = memoize(fib);

memoizedFn(2, 3); // 5
memoizedFn(2, 3); // 5
memoizedFn(3, 5); // 8
console.log(callCount); // 2
