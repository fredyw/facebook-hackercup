package fbhc2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * https://www.facebook.com/hackercup/problem/1254819954559001/
 */
public class ProgressPie {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/2017/progresspie/progress_pie.txt");
        // File file = new File("src/main/resources/2017/progresspie/progress_pie_example_input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                int p = scanner.nextInt();
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                System.out.println(String.format("Case #%d: %s", t, isBlackWhite(p, x, y)));
            }
        }
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static String isBlackWhite(int p, int x, int y) {
        double distance = distance(50, 50, x, y);
        if (distance > 50) {
            return "white";
        }
        double angle = Math.acos((y - 50) / distance);
        if (x < 50) {
            angle = 2 * Math.PI - angle;
            return (angle / (2 * Math.PI) * 100 <= p) ? "black" : "white";
        }
        return ((angle / (2 * Math.PI)) * 100 <= p) ? "black" : "white";
    }
}
