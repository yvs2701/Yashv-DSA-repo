/* Leetcode 2633: "Convert Object to JSON string"
Given an object, return a valid JSON string of that object. You may assume the object only inludes strings, integers, arrays, objects, booleans, and null. The returned string should not include extra spaces. The order of keys should be the same as the order returned by Object.keys().

Please solve it without using the built-in JSON.stringify method.

Example 1:
    Input: object = {"y":1,"x":2}
    Output: {"y":1,"x":2}
    Explanation: 
    Return the JSON representation. Note that the order of keys should be the same as the order returned by Object.keys().

Example 2:
    Input: object = {"a":"str","b":-12,"c":true,"d":null}
    Output: {"a":"str","b":-12,"c":true,"d":null}
    Explanation:
    The primitives of JSON are strings, numbers, booleans, and null.

Example 3:
    Input: object = {"key":{"a":1,"b":[{},null,"Hello"]}}
    Output: {"key":{"a":1,"b":[{},null,"Hello"]}}
    Explanation:
    Objects and arrays can include other objects and arrays.

Example 4:
    Input: object = true
    Output: true
    Explanation:
    Primitive types are valid inputs.
*/

const jsonStringify = function (object) {
    if (Array.isArray(object)) {
        let json = "["
        object.forEach((elem, idx) => {
            if (idx != 0)
                json += ",";
            json += jsonStringify(elem);
        });
        json += "]";

        return json;
    }

    if (typeof object === "object" && object !== null) {
        let json = "{"
        Object.keys(object).forEach((key, idx) => {
            if (idx != 0)
                json += ",";
            json += "\"" + key + "\":" + jsonStringify(object[key]);
        });
        json += "}";

        return json;
    }

    if (typeof object === 'string' || object instanceof String)
        return "\"" + String(object) + "\"";

    // else
    return String(object);
};
