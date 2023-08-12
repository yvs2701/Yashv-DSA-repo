/* Leetcode 380: Insert Delete GetRandom O(1)
Implement the RandomizedSet class:
    - RandomizedSet(): Initializes the RandomizedSet object.
    - bool insert(int val): Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
    - bool remove(int val): Removes an item val from the set if present. Returns true if the item was present, false otherwise.
    - int getRandom(): Returns a random element from the current set of elements (it's guaranteed that at least one element exists when
        this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.
Example:
Input:
    ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
    [[], [1], [2], [2], [], [1], [2], []]
Output:
    [null, true, false, true, 2, true, false, 2]
Explanation:
    - INIT RandomSet
    - Inserts 1 to the set. Returns true as 1 was inserted successfully.
    - Returns false as 2 does not exist in the set.
    - Inserts 2 to the set, returns true. Set now contains [1,2].
    - getRandom() should return either 1 or 2 randomly.
    - Removes 1 from the set, returns true. Set now contains [2].
    - 2 was already in the set, so return false.
    - Since 2 is the only number in the set, getRandom() will always return 2.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InsertDeleteGetInConstTime {
    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();

        System.out.println(obj.insert(1));
        System.out.println(obj.remove(2));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(1));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
    }

    static class RandomizedSet {
        private List<Integer> nums;
        private Map<Integer, Integer> idxMap;

        public RandomizedSet() {
            nums = new ArrayList<>();
            idxMap = new HashMap<>();
        }

        public boolean insert(int val) {
            if (idxMap.containsKey(val))
                return false;

            // nums.size() is going to be the index of `val` in `nums`
            // after inserting it in the `nums`
            idxMap.put(val, nums.size());
            nums.add(val);

            return true;
        }

        public boolean remove(int val) {
            if (!idxMap.containsKey(val))
                return false;

            int idx = idxMap.get(val); // get index of `val` to remove
            int last = nums.size() - 1; // gives last index in `nums`

            if (idx != last) {
                int lastVal = nums.get(last);
                nums.set(idx, lastVal); // move last value to the current index
                idxMap.put(lastVal, idx); // update index of the last value
            }

            // removing the last index is an O(1) operation in ArrayList
            // as it does not remove the last index, but assumes it to be empty
            // and reduces .size() accordingly
            nums.remove(last);
            idxMap.remove(val);

            return true;
        }

        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }
}
