/* Leetcode 2636
Given an array of asyncronous functions functions and a pool limit n, return an asyncronous function promisePool. It should return a promise that resolves when all the input functions resolve.
Pool limit is defined as the maximum number promises that can be pending at once. promisePool should begin execution of as many functions as possible and continue executing new functions when old promises resolve. promisePool should execute functions[i] then functions[i + 1] then functions[i + 2], etc. When the last promise resolves, promisePool should also resolve.
For example, if n = 1, promisePool will execute one function at a time in series. However, if n = 2, it first executes two functions. When either of the two functions resolve, a 3rd function should be executed (if available), and so on until there are no functions left to execute.
You can assume all functions never reject. It is acceptable for promisePool to return a promise that resolves any value. */

var promisePool = async function (functions, n) {
    return new Promise((resolve, reject) => {
        let running = 0;
        let fnIdx = 0;
        const responses = Array(functions.length).fill(null); // Keep track of all responses

        const runner = () => {
            if (fnIdx >= functions.length && running === 0) {
                resolve();
                return;
            }

            while (running < n && fnIdx < functions.length) {
                running++;
                const idx = fnIdx++;
                const fn = functions[idx]
                fn().then((res) => {
                    responses[idx] = res;
                }).catch((e) => {
                    responses[idx] = e;
                }).finally(() => {
                    running--;
                    runner();
                })
            }
        }
        runner();
    })
};

const sleep = (t) => new Promise(res => setTimeout(res, t));
promisePool([() => sleep(500), () => sleep(400)], 1).then(console.log) // After 900ms
