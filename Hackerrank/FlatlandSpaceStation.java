import java.util.Arrays;
import java.util.Scanner;

/* Flatland is a country with a number of cities, some of which have space stations.
Cities are numbered consecutively and each has a road of length connecting it to the next city.
It is not a circular route, so the first city doesn't connect with the last city.
Determine the maximum distance from any city to its nearest space station.

Input Format: number of cities, number of stations and cities that are stations
Output: maximum distance from any city to its nearest space station

Sample Input:
    9 2
    2 6
Sample output:
    2
Explanation:
    0-1-(2)-3-4-5-(6)-7-8 are the cities in Flatland. 1 and 6 are space stations.
    Now the cities that are not space stations are:
    0, 1
    3, 4, 5 (this chain has a station on both the sides)
    7
    Out of these city chains the city 4 has the maximum distance from the nearest space station. */

class FlatlandSpaceStation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), len = sc.nextInt(); // flatland cities numbered 0 to n-1
        int[] stations = new int[len]; // cities that are stations; 0 <= station[i] < n
        for (int i = 0; i < len; i++)
            stations[i] = sc.nextInt();
        sc.close();

        Arrays.sort(stations);
        // let the maximum distance be
        // = max(distance from 0 to first station, distance from last station to last city)
        // (i.e. distance for cities on the extremeties; these cities are NOT between any 2 stations)
        // = max(stations[0] - 0, (n-1) - stations[len - 1])
        int max = stations[0] < (n - 1) - stations[len - 1] ? (n - 1) - stations[len - 1] : stations[0];
        int numOfCities, distance;

        // for cities between 2 stations
        // find maximum distance
        for (int i = 1; i < len; i++) {
            numOfCities = stations[i] - stations[i - 1] - 1;
            // if number of cities in between is odd
            if ((numOfCities & 1) == 1)
                distance = (numOfCities + 1) / 2;
            // if the number of cities in between is even
            else
                distance = numOfCities / 2;
            if (distance > max)
                max = distance;
        }
        System.out.println(max);
    }
}
