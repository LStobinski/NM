package NM.classes;

import java.util.function.Function;

public class MathHelper {

    public static short factorialS(int N) {
        if (N == 0 || N == 1)
            return 1;

        short result = 1;
        for (int i = 1; i <= N; i++)
            result *= (short) i;
        return result;
    }

    public static int factorialI(int N) {
        if (N == 0 || N == 1)
            return 1;

        int result = 1;
        for (int i = 1; i <= N; i++)
            result *= (short) i;
        return result;
    }

    public static long factorialL(int N) {
        if (N == 0 || N == 1)
            return 1;

        long result = 1;
        for (int i = 1; i <= N; i++)
            result *= (short) i;
        return result;
    }

    public static double euler(int N) {
        double e = 0;
        for (int i = 0; i<=N; i++)
            e += 1d/factorialL(i);
        return e;
    }

    public static double sum(int o, int n, Function<Integer, Double> formula) {
        double sum = 0;
        for (int i = o; i <= n; i++)
            sum += formula.apply(i);
        return sum;
    }

    // derivatives
    public static double derivative_1_2pf(Function<Double, Double> function, double x, double h) {
        return (function.apply(x + h) - function.apply(x)) / h;
    }

    public static double derivative_1_2pc(Function<Double, Double> function, double x, double h) {
        return (function.apply(x + h) - function.apply(x - h)) / (2 * h);
    }

    public static double derivative_1_3pf(Function<Double, Double> function, double x, double h) {
        return ((4 * function.apply(x + h)) - (3 * function.apply(x)) - (function.apply(x + 2 * h))) / (2 * h);
    }

    public static double derivative_2_2pf(Function<Double, Double> function, double x, double h) {
        return (function.apply(x) - (2 * function.apply(x + h)) + function.apply(x + 2 * h)) / (h * h);
    }

    public static double derivative_2_2pc(Function<Double, Double> function, double x, double h) {
        return (function.apply(x + h) - (2 * function.apply(x)) + function.apply(x - h)) / (h * h);
    }

}
