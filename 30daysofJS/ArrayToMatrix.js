/* 2675: "Array of Objects to Matrix"
Example 1:
    Input: 
        arr = [
        {"a": 1, "b": 2},
        {"c": 3, "d": 4},
        {}
        ]
    Output: 
        [
        ["a", "b", "c", "d"],
        [1, 2, "", ""],
        ["", "", 3, 4],
        ["", "", "", ""]
        ]
    Explanation:
        There are 4 unique column names: "a", "b", "c", "d".
        The first object has values associated with "a" and "b".
        The second object has values associated with "c" and "d".
        The third object has no keys, so it is just a row of empty strings.

Example 2:
    Input: 
        arr = [
        {"a": {"b": 1, "c": 2}},
        {"a": {"b": 3, "d": 4}}
        ]
    Output: 
        [
        ["a.b", "a.c", "a.d"],
        [1, 2, ""],
        [3, "", 4]
        ]
    Explanation: In this example, the objects are nested. The keys represent the full path to each value separated by periods.
    There are three paths: "a.b", "a.c", "a.d".

Example 3:
    Input: 
        arr = [
        [{"a": null}],
        [{"b": true}],
        [{"c": "x"}]
        ]
    Output: 
        [
        ["0.a", "0.b", "0.c"],
        [null, "", ""],
        ["", true, ""],
        ["", "", "x"]
        ]
    Explanation: Arrays are also considered objects with their keys being their indices.
    Each array has one element so the keys are "0.a", "0.b", and "0.c".
*/

const jsonToMatrix = function (arr) {
    // Helper function to check if a value is an object
    const isObject = x => (x !== null && typeof x === 'object');

    // Recursive function to extract keys from nested objects
    const getKeys = object => {
        if (!isObject(object)) return ['']; // If the value is not an object, return an empty key
        const result = [];
        for (const key of Object.keys(object)) {
            const childKeys = getKeys(object[key]); // Recursively get keys from nested objects
            for (const childKey of childKeys) {
                result.push(childKey ? `${key}.${childKey}` : key); // Append child keys with dot notation
            }
        }
        return result;
    };

    // Extract all unique keys from the array of objects and sort them
    const keys = Array.from(new Set(arr.reduce((acc, curr) => {
        getKeys(curr).forEach(k => acc.add(k));
        return acc;
    }, new Set()))).sort();

    // Retrieve the value for a given key path in an object
    const getValue = (obj, path) => {
        const paths = path.split('.');
        let i = 0;
        let value = obj;
        while (i < paths.length && isObject(value)) {
            value = value[paths[i++]];
        }
        // If the value is not found or is an object, return an empty string
        return i < paths.length || isObject(value) || value === undefined ? '' : value;
    };

    // Create the matrix representation with keys as the first row and values as subsequent rows
    const matrix = [keys];
    arr.forEach(obj => {
        matrix.push(keys.map(key => getValue(obj, key)));
    });

    return matrix;
};
