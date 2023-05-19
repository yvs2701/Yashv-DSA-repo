/* Leetcode 2637
Given an asyncronous function fn and a time t in milliseconds, return a new time limited version of the input function.
A time limited function is a function that is identical to the original unless it takes longer than t milliseconds to fullfill. In that case, it will reject with "Time Limit Exceeded".  Note that it should reject with a string, not an Error. */

var timeLimit = function (fn, t) {

    return async function (...args) {
        let timeout = null
        const timer = new Promise((_, reject) => {
            timeout = setTimeout(() => {
                reject("Time Limit Exceeded")
            }, t)
        })

        return Promise.race([timer, fn(...args)]).finally(res => {
            clearTimeout(timeout)
            return res
        })
        // // another approach:
        // return new Promise((resolve, reject) => {
        //     const timer = setTimeout(() => {
        //         reject("Time Limit Exceeded")
        //     }, t)

        //     fn(...args).then(resolve).catch(reject).finally(() => clearTimeout(timer))
        // })
    }
};

const limited = timeLimit((t) => new Promise(res => setTimeout(res, t)), 100);
limited(150).catch(console.log) // "Time Limit Exceeded" at t=100ms
