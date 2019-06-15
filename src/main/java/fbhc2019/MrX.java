package fbhc2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * https://www.facebook.com/hackercup/problem/589264531559040/
 */
public class MrX {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/2019/mr_x/mr_x_sample_input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                // TODO
            }
        }
    }
}
