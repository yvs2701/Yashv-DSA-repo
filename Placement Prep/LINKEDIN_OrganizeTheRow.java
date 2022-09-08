import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/* A shopkeeper likes to keep his display shelves organized. A row is organized if no two adjacent
shelves have their modulo of 2 equal. (Each shelf has some items stacked up).

The shopkeeper can organize the shelves by taking out half of the items of the piled up stack, at once.
Technically in this operation he takes off floor(arr[i]/2) items at once.

Find the minimum number of operations needed to organize the shelf.

Example input:
    5
    4 10 10 6 3
Example output:
    3
Explanation:
    The final row will be: (1) 10 (5) 6 3
    Shopkeeper has performed 3 operations here
*/

class LINKEDIN_OrganizeTheRow {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < n; i++)
            arr.add(sc.nextInt());

        System.out.println(minimumOperations(arr));
        sc.close();
    }
    private static int minimumOperations(List<Integer> arr) {
        int n = arr.size(), countFront = 0, countBack = 0;
        List<Integer> copy = new ArrayList<>(arr);

        // Modulo 2 of any number is can basically be only 0 or 1
        // this is also used to check even and odd and can be done using binary AND
        // if number & 1 results in 0 it is even (and modulo 2 would be 0),
        // if results in 1 then odd (and modulo 2 would be 1)

        // always remove half of the items of the second element
        for (int i = 1; i < n; i++) {
            if ((arr.get(i-1) & 1) == (arr.get(i) & 1)) {
                arr.set(i, (int)Math.floor((double)arr.get(i) / 2));
                countFront++;
                i--; // Math.floor can produce a value which can again make this condition true, so recheck it
            }
        }
        
        // always remove half of the items from the first element
        for (int i = n - 2; i >= 0; i--) {
            if ((copy.get(i+1) & 1) == (copy.get(i) & 1)) {
                copy.set(i, (int)Math.floor((double)copy.get(i) / 2));
                countBack++;
                i++; // Math.floor can produce a value which can again make this condition true, so recheck it
            }
        }
        
        return Math.min(countFront, countBack);
    }
}
