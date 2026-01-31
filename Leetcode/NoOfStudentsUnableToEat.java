/* LeetCode Quest Queue Q1: "Number of Students Unable to Eat Lunch"
The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1
respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.
The number of sandwiches in the cafeteria is equal to the number of students. At each step:
    - If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and
      leave the queue.
    - Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the
stack (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial queue
(j = 0 is the front of the queue). Return the number of students that are unable to eat.

Example 1:
    Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
    Output: 0
    Explanation:
        - Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
        - Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
        - Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
        - Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
        - Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
        - Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
        - Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
        - Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
    Hence all students are able to eat.
Example 2:
    Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
    Output: 3 */

class NoOfStudentsUnableToEat {
    public static int countStudents(int[] students, int[] sandwiches) {
        int n = students.length, zero = 0, one = 0, idx = 0;

        // store counts for shapes
        for (int student : students) {
            if (student == 0) {
                zero++;
            } else {
                one++;
            }
        }

        // consume/eat the sandwiches of the shapes 'zero' and 'one'
        while (idx < n) {
            if (sandwiches[idx] == 0 && zero > 0) {
                zero--;
                idx++;
            } else if (sandwiches[idx] == 1 && one > 0) {
                one--;
                idx++;
            } else {
                break;
            }
        }

        return n - idx;
    }

    public static void main(String[] args) {
        int[][][] testcases = {
                {{1, 1, 0, 0}, {0, 1, 0, 1}},
                {{1, 1, 1, 0, 0, 1}, {1, 0, 0, 0, 1, 1}}
        };

        for (int[][] testcase : testcases) {
            for (int student : testcase[0]) {
                System.out.print(student + " ");
            }
            System.out.println();
            for (int sandwich : testcase[1]) {
                System.out.print(sandwich + " ");
            }
            System.out.println();

            int result = countStudents(testcase[0], testcase[1]);
            System.out.println("Number of students unable to eat: " + result);
        }
    }
}