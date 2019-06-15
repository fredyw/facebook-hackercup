package fbhc2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * https://www.facebook.com/hackercup/problem/2426282194266338/
 */
public class LeapfrogCh2 {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2019/leapfrog_ch2/leapfrog_ch__sample_input.txt");
        File file = new File("src/main/resources/2019/leapfrog_ch2/leapfrog_ch_.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                String string = scanner.next();
                String answer = isPossible(string) ? "Y" : "N";
                System.out.println(String.format("Case #%d: %s", t, answer));
            }
        }
    }

    private static boolean isPossible(String string) {
        int count = 0;
        for (char c : string.toCharArray()) {
            if (c == 'B') {
                count++;
            }
        }
        return (count >= 2 && count + 1 < string.length()) || (count == 1 && string.length() == 3);
    }
}
