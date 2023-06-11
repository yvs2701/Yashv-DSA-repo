/* Leetcode 1146: "Snapshot Array"
Implement a SnapshotArray that supports the following interface:
- SnapshotArray(int length):  initializes an array-like data structure with the given length. Initially, each element equals 0.
- void set(index, val):  sets the element at the given index to be equal to val.
- int snap(): takes a snapshot of the array and returns the snap_id (the total number of times we called snap() minus 1).
- int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id.

Example 1:
    Input: ["SnapshotArray","set","snap","set","get"]
        [[3],[0,5],[],[0,6],[0,0]]
    Output:
        [null,null,0,null,5]
    Explanation: 
        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
        snapshotArr.set(0,5);  // Set array[0] = 5
        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
        snapshotArr.set(0,6);
        snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
*/

import java.util.TreeMap;

class SnapshotArray {
    private static class ArrayShot {
        int snapId = 0;
        TreeMap<Integer, Integer>[] snaps;

        // Instead of storing an entire copy of the array for each snap,
        // at each index we save the snap of the value it stores.
        // Each of these snaps can be fetched by looking for the snap id.
        // This reduces the space complexity to store too many snaps.

        ArrayShot(int length) {
            this.snaps = new TreeMap[length]; // array of tree maps

            for (int i = 0; i < length; i++) {
                this.snaps[i] = new TreeMap<Integer, Integer>();
                this.snaps[i].put(0, 0);
                // sets all indices to have a tree map who store the history from snapshot = 0
                // i.e. all indices have the init history of val = 0
            }
        }

        private void set(int index, int val) {
            snaps[index].put(snapId, val); // set val for given index for current snap
        }

        private int snap() {
            return snapId++; // stop editing the current snap and start saving in new ones
        }

        private int get(int index, int snapId) {
            // if the given index was not edited in newer snaps
            // then tree maps of the given index will not contains those values
            // hence we need to look in most recent snap which is present in the tree
            // this can be done using floorEntry

            return snaps[index]
                    .floorEntry(snapId) // returns entry set associated with greatest key <= snapId
                    .getValue(); // extracts value from the returned entry set
        }
    }

    public static void main(String[] args) {
        int length = 3;
        ArrayShot obj = new ArrayShot(length);

        obj.set(0, 5);

        int snapId1 = obj.snap();
        obj.set(0, 6);
        int snapId2 = obj.snap();

        int val = obj.get(0, snapId1);
        System.out.println(val);

        val = obj.get(0, snapId2);
        System.out.println(val);
    }
}
