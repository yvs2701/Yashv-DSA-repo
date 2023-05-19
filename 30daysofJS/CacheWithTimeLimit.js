/* Leetcode 2622
Write a class that allows getting and setting key-value pairs, however a time until expiration is associated with each key.
The class has three public methods:
    - set(key, value, duration): accepts an integer key, an integer value, and a duration in milliseconds. Once the duration has elapsed, the key should be inaccessible. The method should return true if the same un-expired key already exists and false otherwise. Both the value and duration should be overwritten if the key already exists.
    - get(key): if an un-expired key exists, it should return the associated value. Otherwise it should return -1.
    - count(): returns the count of un-expired keys.
Example:
    Input:
        ["TimeLimitedCache", "set", "set", "get", "get", "get", "count"]
        [[], [1, 42, 50], [1, 50, 100], [1], [1], [1], []]
        [0, 0, 40, 50, 120, 200, 250]
    Output: [null, false, true, 50, 50, -1]
    Explanation:
        At t=0, the cache is constructed.
        At t=0, a key-value pair (1: 42) is added with a time limit of 50ms. The value doesn't exist so false is returned.
        At t=40, a key-value pair (1: 50) is added with a time limit of 100ms. A non-expired value already existed so true is returned and the old value was overwritten.
        At t=50, get(1) is called which returned 50.
        At t=120, get(1) is called which returned 50.
        At t=140, key=1 expires.
        At t=200, get(1) is called but the cache is empty so -1 is returned.
        At t=250, count() returns 0 because the cache is empty. */

class TimeLimitedCache {
    constructor() {
        this.cache = new Map();
    }
};

/** 
 * @param {number} key
 * @param {number} value
 * @param {number} time until expiration in ms
 * @return {boolean} if un-expired key already existed
 */
TimeLimitedCache.prototype.set = function (key, value, duration) {
    const valueInCache = this.cache.get(key);

    if (valueInCache) {
        // if key was already present then we need to remove this key
        // along with its timer so that this timer doesn't delete the new key
        // which will replace the key currently present in the cache
        clearTimeout(valueInCache.timeout);
    }

    const timeout = setTimeout(() => this.cache.delete(key), duration); // create timer to delere this key from cache Map
    this.cache.set(key, { value, timeout });
    return Boolean(valueInCache);
};

/** 
 * @param {number} key
 * @return {number} value associated with key
 */
TimeLimitedCache.prototype.get = function (key) {
    return this.cache.has(key) ? this.cache.get(key).value : -1;
};

/** 
 * @return {number} count of non-expired keys
 */
TimeLimitedCache.prototype.count = function () {
    return this.cache.size;
};

// Testing the Cache wit TTL:
var obj = new TimeLimitedCache()
console.log(obj.set(1, 42, 50));
console.log(obj.set(1, 50, 100));
console.log(obj.get(1));
console.log(obj.get(1));
console.log(obj.get(1));
console.log(obj.count());
