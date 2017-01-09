package fbhc2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.facebook.com/hackercup/problem/169401886867367/
 */
public class LazyLoading {
    public static void main(String[] args) throws Exception {
        // File file = new File("src/main/resources/2017/lazyloading/lazy_loading_example_input.txt");
        File file = new File("src/main/resources/2017/lazyloading/lazy_loading.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                int n = scanner.nextInt();
                int[] weights = new int[n];
                for (int i = 0; i < n; i++) {
                    weights[i] = scanner.nextInt();
                }
                System.out.println(String.format("Case #%d: %d", t, lazyLoading(weights)));
            }
        }
    }

    private static int lazyLoading(int[] weights) {
        Arrays.sort(weights);
        int max = 0;
        int j = 0;
        for (int i = weights.length - 1; i >= j; i--) {
            int weight = weights[i];
            int tmp = weight;
            int count = 1;
            while (tmp < 50) {
                count++;
                tmp = weight * count;
            }
            j += count - 1;
            if (j <= i) {
                max++;
            }
        }
        return max;
    }
}
