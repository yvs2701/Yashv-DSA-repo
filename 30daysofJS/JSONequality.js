/* Leetcode 2628: JSON deep equal
For two objects to be deeply equal, they must contain the same keys, and the associated values must also be deeply equal. Two objects are also considered deeply equal if they pass the === equality check.
You may assume both objects are the output of JSON.parse. In other words, they are valid JSON.

Please solve it without using lodash's _.isEqual() function.

Example 1:
    Input: o1 = {"x":1,"y":2}, o2 = {"x":1,"y":2}
    Output: true
    Explanation: The keys and values match exactly.

Example 2:
    Input: o1 = {"y":2,"x":1}, o2 = {"x":1,"y":2}
    Output: true
    Explanation: Although the keys are in a different order, they still match exactly.

Example 3:
    Input: o1 = {"x":null,"L":[1,2,3]}, o2 = {"x":null,"L":["1","2","3"]}
    Output: false
    Explanation: The array of numbers is different from the array of strings.

Example 4:
    Input: o1 = true, o2 = false
    Output: false
    Explanation: true !== false
*/

const areDeeplyEqual = function(o1, o2) {
    const type1 = typeof(o1);
    const type2 = typeof(o2);
    
    const isArray1 = Array.isArray(o1);
    const isArray2 = Array.isArray(o2);

    // if both params are different type then return false
    if((type1 !== type2) || (isArray1 !== isArray2))
        return false;

    // if params are primitive then check equality
    if((type1 !== 'object') || (o1 === null))
        return o1 === o2;

    // if params are arrays
    if(isArray1){
      if(o1.length !== o2.length)
        return false;

      for(let i=0; i < o1.length; i++) {
        if(!areDeeplyEqual(o1[i], o2[i]))
            return false;
      }
    }
    
    // if params are array
    const key1 = Object.keys(o1);
    const key2 = Object.keys(o2);

    if(key1.length !== key2.length)
        return false;

    for(let i=0;i<key1.length;i++) {
      if(!areDeeplyEqual(o1[key1[i]], o2[key1[i]]))
        return false;
    }

    return true;
};

/* Algorithm:
- Check if o1 and o2 are strictly equal (===). If they are, return true since they are deeply equal.
- Check if either o1 or o2 is not an object. If one of them is not an object while the other is, they are different values, so return false.
- If the types of o1 and o2 are different (checked using String(o1) !== String(o2)), they are not equal, and false is returned.
- If o1 and o2 are arrays, they are compared element by element:
    - If the lengths of the arrays are different, they are not equal, and false is returned.
    - Each element is recursively compared using a recursive call to areDeeplyEqual.
    - If any pair of corresponding elements are not equal, false is returned.
    - If all elements are equal, true is returned.
- If o1 and o2 are objects, they are compared based on their properties:
    - If the number of keys (properties) in o1 and o2 is different, they are not equal, and false is returned.
    - Each property is recursively compared using a recursive call to areDeeplyEqual.
    - If any pair of corresponding properties are not equal, false is returned.
    - If all properties are equal, true is returned.
- If none of the previous conditions match, false is returned as a fallback mechanism. */
