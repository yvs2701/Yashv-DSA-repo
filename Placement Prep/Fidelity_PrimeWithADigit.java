import java.util.Scanner;
/* You are given two numbers: N and d
Find sum of all prime numbers of N digits, having maximum number of digit d */
class Fidelity_PrimeWithADigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), d = sc.nextInt();
        sc.close();
        
        double high = Math.pow(10, N);
        int count_dig, max = 0; double sum = 0;

        for (double i = Math.pow(10, N-1); i < high; i++) {
            if (isPrime((int)i)) {
                count_dig = countDigit((int)i, d);

                if (count_dig > max) {
                    max = count_dig;
                    sum = i;
                }
                
                else if (count_dig == max) {
                    sum += i;
                }
            }
        }

        System.out.println(sum);
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= n/2; i++)
            if (n % i == 0)
                return false;
        return true;
    }

    private static int countDigit(int n, int d) {
        int count = 0;

        while (n > 0) {
            if (n % 10 == d)
                count++;
            n /= 10;
        }
        return count;
    }
}
