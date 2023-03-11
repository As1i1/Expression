package expression.generic;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 2) {
            try {
                GenericTabulator tabulator = new GenericTabulator();
                Object[][][] result = tabulator.tabulate(args[0], args[1], -2, 2, -2, 2, -2, 2);
                System.out.println(Arrays.deepToString(result));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        GenericTabulator tabulator = new GenericTabulator();
        Object[][][] result = tabulator.tabulate("u", "square((square(x) / (x + z)))", 0, 0, Integer.MAX_VALUE - 7, Integer.MAX_VALUE - 7, 0, 0);
        System.out.println(Arrays.deepToString(result));
    }
}
