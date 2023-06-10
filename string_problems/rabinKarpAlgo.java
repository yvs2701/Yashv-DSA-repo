import java.util.Scanner;

public class rabinKarpAlgo {
   /* Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[]) that prints all occurrences of pat[] in txt[]. You may assume that n > m.
    Input : txt[] = "THIS IS A TEST TEXT", pat[] = "TEST"
        Output: Pattern found at index 10
    Input : txt[] =  "AABAACAADAABAABA", pat[] =  "AABA"
        Output: Pattern found at index 0 Pattern found at index 9 Pattern found at index 12
    The average and best-case running time of the Rabin-Karp algorithm is O(n+m), but its worst-case time is O(nm). Worst case of Rabin-Karp algorithm occurs when all characters of pattern and text are same as the hash values of all the substrings of txt[] match with hash value of pat[]. For example pat[] = “AAA” and txt[] = “AAAAAAA” */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine(); // enter the text which may contain the pattern to search
        String pattern = sc.nextLine(); // enter the pattern to search
        sc.close();
        searchPattern(text, pattern);
    }
    private static void searchPattern(String text, String pattern) {
        int txt_len = text.length(), pat_len = pattern.length();
        int hash_pat = 0, hash_txt = 0; // hash values for pattern and text's substrings
        
        /* FOR SOME REASON mod = 1e9+7 was not giving the right answer, however, CHANGING IT TO mod = 1e5+7 MADE THE CODE MAGICALLY WORK
        EDIT: ANSWERED !!! https://stackoverflow.com/questions/68336852/rabin-karp-not-working-for-large-primes-gives-wrong-output/68337455#68337455 
        the prime modulus shouldn't be greater than 8388608 (2^23) scroll to the end */
        final int mod = 100007;         // prime number to calculate modulo... larger modulo denominator reduces collisions in hash

        final int d = 256;              // number of characters possible in a string
        int coeff = 1;                  // stores the multiplier (or coeffecient) for the first index of the sliding window

        /*HASHING PATTERN:
        if text     = 'abcd'
        hashed text = 256^3 *'a' + 256^2 *'b' + 256^1 *'c' + 256^0 *'d'
        (each character represents its ascii code in the calculation) */

        // The value of coeff would be "(d^(pat_len - 1)) % mod"
        for (int i = 0; i < pat_len - 1; i++)
            coeff = (coeff * d) % mod;

        // calculate hash of the first window and the pattern itself
        for (int i = 0; i < pat_len; i++) {
            hash_pat = (d * hash_pat + pattern.charAt(i)) % mod;
            hash_txt = (d * hash_txt + text.charAt(i)) % mod;
        }

        for (int i = 0; i < txt_len - pat_len; i++) {
            if (hash_txt == hash_pat) {
                /* Earlier i didn't recheck the substring to match the pattern coz i thought
                our chances of collisions are quite less (1/mod) so we dont need to recheck the substring, BUT THAT'S FALSE
                "the probability is determined not by chance, but by the strings being checked. Unless you know the probability
                distribution of your inputs, you can't know what the probability of failure is. 
                That's why Rabin-Karp rechecks the string to make sure." 
                see this small discussion: 
                https://stackoverflow.com/questions/68336852/rabin-karp-not-working-for-large-primes-gives-wrong-output/68337455#68337455 */
                int j = 0;
                for (; j < pat_len; j++)
                    if (text.charAt(i+j) != pattern.charAt(j)) // pattern not matched even when the hash was same
                        break;

                if (j == pat_len) // pattern found
                    System.out.println("Pattern found at index " + i);
            }
            hash_txt = (d * (hash_txt - text.charAt(i) * coeff) + text.charAt(i + pat_len)) % mod; // calculating next window (i+1 th index)

            // We might get negative value of t, converting it to positive
            if (hash_txt < 0)
                hash_txt = hash_txt + mod;
        }
        if (hash_txt == hash_pat) {// checking for the last window
            int i = txt_len - pat_len;
            int j = 0;
                for (; j < pat_len; j++)
                    if (text.charAt(i+j) != pattern.charAt(j)) // pattern not matched even when the hash was same
                        break;

                if (j == pat_len) // pattern found
                    System.out.println("Pattern found at index " + i);
        }
    }
}
/* The Naive String Matching algorithm slides the pattern one by one. After each slide, it one by one checks characters at the current shift and if all characters match then prints the match. 
Like the Naive Algorithm, Rabin-Karp algorithm also slides the pattern one by one. But unlike the Naive algorithm, Rabin Karp algorithm matches the hash value of the pattern with the hash value of current substring of text, and if the hash values match then only it starts matching individual characters. So Rabin Karp algorithm needs to calculate hash values for following strings.
1) Pattern itself. 
2) All the substrings of the text of length m. 
Since we need to efficiently calculate hash values for all the substrings of size m of text, we must have a hash function which has the following property. 
Hash at the next shift must be efficiently computable from the current hash value and next character in text or we can say 
hash(text[s+1 .. s+m]) must be efficiently computable from hash(text[s .. s+m-1]) and text[s+m] 
i.e., hash(text[s+1 .. s+m]) = rehash(text[s+m], hash(txt[s .. s+m-1])) and rehash must be O(1) operation.
The hash function suggested by Rabin and Karp calculates an integer value. The integer value for a string is the numeric value of a string. */

/* ANSWER to why 1e9+7 was not working in the program:
In Java, an int is a 32-bit integer. If a calculation with such number mathematically yields a result that needs more binary digits, 
the extra digits are silently discarded. This is called overflow.
To avoid this, the Rabin-Karp algorithm reduces results modulo some prime in each step, thereby keeping the 
number small enough that the next step will not overflow. For this to work, the prime chosen must be suitably small that

d * (hash + max(char) * coeff) + max(char)) < max(int)

Since
0 ≤ hash < p, 1 ≤ coeff < p,
max(char) = 216 & max(int) = 231

any prime smaller than 27=128 will do. For larger primes, it depends on what their coeff ends up being, but even if we select one with the smallest possible coeff = 1, the prime must not exceed 223, which is much smaller than the prime you used.

In practice, one therefore uses Rabin-Karp with an integer datatype that is significantly bigger that the character type, such as a long (64 bits). Then, any prime < 239 will do. */

/* WHY IS IT IMPORTANT TO CHECK THE STRINGS:
my comment: "our chances of collisions are quite less (1/mod) so we dont need to recheck the substring"
is flawed, because the probability is determined not by chance, but by the strings being checked. Unless you know the 
probability distribution of your inputs, you can't know what the probability of failure is. That's why Rabin-Karp rechecks the string to make sure.
Explaination by: https://stackoverflow.com/users/183406/meriton 
source: https://stackoverflow.com/questions/68336852/rabin-karp-not-working-for-large-primes-gives-wrong-output/68337455#68337455 (stackoverflow) */