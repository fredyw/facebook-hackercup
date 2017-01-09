package fbhc2017;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 * https://www.facebook.com/hackercup/problem/326053454264498/
 */
public class FightingTheZombie {
    public static void main(String[] args) throws Exception {
        File file = new File("src/main/resources/2017/fightingthezombie/fighting_the_zombie_example_input.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Scanner scanner = new Scanner(br);
            int testCases = scanner.nextInt();
            for (int t = 1; t <= testCases; t++) {
                int h = scanner.nextInt();
                int s = scanner.nextInt();
                String[] spells = new String[s];
                for (int i = 0; i < s; i++) {
                    spells[i] = scanner.next();
                }
                System.out.println(String.format("Case #%d: %f", t, fightingTheZombie(h, spells)));
            }
        }
    }

    private static class Damage {
        private final int min;
        private final int max;

        public Damage(int min, int max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public String toString() {
            return "(" + min + ", " + max + ")";
        }
    }

    private static Damage getDamage(String spell) {
        int z = 0;
        String firstPart = spell;
        if (spell.contains("-")) {
            String[] split = spell.split("-");
            firstPart = split[0];
            z = -Integer.parseInt(split[1]);
        } else if (spell.contains("+")) {
            String[] split = spell.split("\\+");
            firstPart = split[0];
            z = Integer.parseInt(split[1]);
        }

        String[] split = firstPart.split("d");
        int x = Integer.parseInt(split[0]);
        int y = Integer.parseInt(split[1]);

        int min = (1 * x) + z;
        int max = (y * x) + z;
        return new Damage(min, max);
    }

    private static double fightingTheZombie(int h, String[] spells) {
        // TODO
        return 0.0;
    }
}
