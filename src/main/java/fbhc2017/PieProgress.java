package fbhc2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.facebook.com/hackercup/problem/1800890323482794/
 */
public class PieProgress {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/2017/pieprogress/pie_progress_example_input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                int n = scanner.nextInt();
                int m = scanner.nextInt();
                Cost[][] costs = new Cost[n][m];
                for (int i = 0; i < n; i++) {
                    int sum = 0;
                    for (int j = 0; j < m; j++) {
                        long c = scanner.nextLong();
                        sum += c;
                        costs[i][j] = new Cost(sum + ((j + 1) * (j + 1)), j + 1);
                    }
                    Arrays.sort(costs[i], (c1, c2) -> Long.compare(c1.cost, c2.cost));
                }
                for (Cost[] cost : costs) {
                    System.out.println(Arrays.toString(cost));
                }
                System.out.println(String.format("Case #%d: %s", t, minCost(costs, n, 0, 0)));
            }
        }
    }

    private static long minCost(Cost[][] allCosts, int nDays, int day, int count) {
        return 0;
//        if (nDays == day) {
//            return 0;
//        }
//        Cost[] costs = allCosts[day];
//        long min = Long.MAX_VALUE;
//        for (int i = 0; i < costs.length; i++) {
//            Cost cost = costs[i];
//            long minCost = minCost(allCosts, nDays, day + 1, cost.count) + cost.cost;
//            min = Math.min(min, minCost);
//        }
//        return min;
    }

    private static class Cost {
        private final long cost;
        private final int count;

        public Cost(int cost, int count) {
            this.cost = cost;
            this.count = count;
        }

        @Override
        public String toString() {
            return "(" + cost + " --> " + count + ")";
        }
    }
}
