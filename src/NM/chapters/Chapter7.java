package NM.chapters;

import NM.Logger;
import NM.classes.MathHelper;
import NM.classes.Pair;

import java.util.List;
import java.util.function.Function;

public class Chapter7 {
    static Logger LOGGER = Logger.Get("C7");

    public static void Run() {
        LOGGER.Log("Chapter VII: Numerical Integration");
        LOGGER.Log("==========================");
        Task_1();
        Task_2();
        Task_3();
        Task_4();
        Task_5();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        Function<Double, Double> fun = x -> 4*x*x*x + 5*x*x + 1;
        double xa = -1.0, xb = 1.0;
        int[] N = {2, 20, 200, 2000, 20000, 200000, 2000000};
        List<Pair<String, Integral>> INT = List.of(
                Pair.of("RECT", Integral.INT_RECT),
                Pair.of("TRAP", Integral.INT_TRAP),
                Pair.of("SIMP", Integral.INT_SIMP)
        );
        double exact = 16 / 3d;

        LOGGER.Log("Exact: " + exact);
        for (var n : N) {
            LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
            for (var integ : INT) {
                var name = integ.getFirst();
                var startTime = System.nanoTime();
                var value = integ.getSecond().apply(fun, xa, xb, n);
                var deltaTime = System.nanoTime() - startTime;
                LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
            }
        }

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        Function<Double, Double> fun = x -> (2 / Math.sqrt(Math.PI) * Math.exp(-1 * x * x));
        double xa = 0.0, xb = 1.0;
        int[] N = {1, 10, 100, 1000, 10000, 100000, 1000000};
        List<Pair<String, Integral>> INT = List.of(
                Pair.of("RECT", Integral.INT_RECT),
                Pair.of("TRAP", Integral.INT_TRAP),
                Pair.of("SIMP", Integral.INT_SIMP)
        );
        double exact = 0.84270079294971486934122063508;

        LOGGER.Log("Exact: " + exact);
        for (var n : N) {
            LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
            for (var integ : INT) {
                var name = integ.getFirst();
                var startTime = System.nanoTime();
                var value = integ.getSecond().apply(fun, xa, xb, n);
                var deltaTime = System.nanoTime() - startTime;
                LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
            }
        }

        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        Function<Double, Double> fun = x -> Math.cos(x) + Math.exp(x) + Math.tan(x);
        double xa = 0.0, xb = 1.0;
        int[] N = {1, 10, 100, 1000, 10000, 100000, 1000000};
        List<Pair<String, Integral>> INT = List.of(
                Pair.of("RECT", Integral.INT_RECT),
                Pair.of("TRAP", Integral.INT_TRAP),
                Pair.of("SIMP", Integral.INT_SIMP)
        );
        double exact = 3.175379283652956004159827;

        LOGGER.Log("Exact: " + exact);
        for (var n : N) {
            LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
            for (var integ : INT) {
                var name = integ.getFirst();
                var startTime = System.nanoTime();
                var value = integ.getSecond().apply(fun, xa, xb, n);
                var deltaTime = System.nanoTime() - startTime;
                LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
            }
        }

        LOGGER.Log("");
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");

        { // 1
            Function<Double, Double> fun = x -> 4*x*x*x + 5*x*x + 1;
            double xa = -1.0, xb = 1.0;
            int[] N = {2, 3, 4};
            List<Pair<String, Integral>> INT = List.of(
                    Pair.of("RECT", Integral.INT_RECT),
                    Pair.of("TRAP", Integral.INT_TRAP),
                    Pair.of("SIMP", Integral.INT_SIMP),
                    Pair.of("GAUSS", Integral.INT_GAUSS_LEGENDRE)
            );
            double exact = 16 / 3d;

            LOGGER.Log("Exact: " + exact);
            for (var n : N) {
                LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
                for (var integ : INT) {
                    var name = integ.getFirst();
                    var startTime = System.nanoTime();
                    var value = integ.getSecond().apply(fun, xa, xb, n);
                    var deltaTime = System.nanoTime() - startTime;
                    LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
                }
            }
        }

        { // 3
            Function<Double, Double> fun = x -> Math.cos(x) + Math.exp(x) + Math.tan(x);
            double xa = 0.0, xb = 1.0;
            int[] N = {2, 3, 4};
            List<Pair<String, Integral>> INT = List.of(
                    Pair.of("RECT", Integral.INT_RECT),
                    Pair.of("TRAP", Integral.INT_TRAP),
                    Pair.of("SIMP", Integral.INT_SIMP),
                    Pair.of("GAUSS", Integral.INT_GAUSS_LEGENDRE)
            );
            double exact = 3.175379283652956004159827;

            LOGGER.Log("Exact: " + exact);
            for (var n : N) {
                LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
                for (var integ : INT) {
                    var name = integ.getFirst();
                    var startTime = System.nanoTime();
                    var value = integ.getSecond().apply(fun, xa, xb, n);
                    var deltaTime = System.nanoTime() - startTime;
                    LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
                }
            }
        }

        LOGGER.Log("");
    }

    public static void Task_5() {
        LOGGER.Log("Task 5 >>>");

        Integral INT_COMPOUND = (f, a, b, n) -> {
            var h = (b - a) / 2;
            return (b - a) / (2 * 5) * MathHelper.sum(1, 5, k ->
                    MathHelper.sum(0, n - 1, i -> {
                                var x1 = a + k * h;
                                var x2 = x1 + h;
                                return Gauss.weight(n, i) * f.apply((x1 + x2) / 2 + ((x2 - x1) / 2) * Gauss.node(n, i));
                            }
                    )
            );
        };

        { // 1
            Function<Double, Double> fun = x -> 4*x*x*x + 5*x*x + 1;
            double xa = -1.0, xb = 1.0;
            int[] N = {2, 3, 4};
            List<Pair<String, Integral>> INT = List.of(
                    Pair.of("RECT", Integral.INT_RECT),
                    Pair.of("TRAP", Integral.INT_TRAP),
                    Pair.of("SIMP", Integral.INT_SIMP),
                    Pair.of("GAUSS", Integral.INT_GAUSS_LEGENDRE),
                    Pair.of("COMP", INT_COMPOUND)
            );
            double exact = 16 / 3d;

            LOGGER.Log("Exact: " + exact);
            for (var n : N) {
                LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
                for (var integ : INT) {
                    var name = integ.getFirst();
                    var startTime = System.nanoTime();
                    var value = integ.getSecond().apply(fun, xa, xb, n);
                    var deltaTime = System.nanoTime() - startTime;
                    LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
                }
            }
        }

        { // 3
            Function<Double, Double> fun = x -> Math.cos(x) + Math.exp(x) + Math.tan(x);
            double xa = 0.0, xb = 1.0;
            int[] N = {2, 3, 4};
            List<Pair<String, Integral>> INT = List.of(
                    Pair.of("RECT", Integral.INT_RECT),
                    Pair.of("TRAP", Integral.INT_TRAP),
                    Pair.of("SIMP", Integral.INT_SIMP),
                    Pair.of("GAUSS", Integral.INT_GAUSS_LEGENDRE),
                    Pair.of("COMP", INT_COMPOUND)
            );
            double exact = 3.175379283652956004159827;

            LOGGER.Log("Exact: " + exact);
            for (var n : N) {
                LOGGER.Log("n = " + n + " -> h = " + (xb - xa) / n);
                for (var integ : INT) {
                    var name = integ.getFirst();
                    var startTime = System.nanoTime();
                    var value = integ.getSecond().apply(fun, xa, xb, n);
                    var deltaTime = System.nanoTime() - startTime;
                    LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value) + " | time (nano): " + deltaTime);
                }
            }
        }

        LOGGER.Log("");
    }

    @FunctionalInterface
    public interface Integral {
        double apply(Function<Double, Double> f, double a, double b, int n);

        Integral INT_RECT = (f, a, b, n) -> {
            var h = (b - a) / n;
			return h * MathHelper.sum(0, n, i ->
                    f.apply(a + i * h)
            );
        };

        Integral INT_TRAP = (f, a, b, n) -> {
            var h = (b - a) / n;
            return (h / 2) * MathHelper.sum(0, n - 1, i ->
                    f.apply(a + i * h) + f.apply(a + (i + 1) * h)
            );
        };

        Integral INT_SIMP = (f, a, b, n) -> {
            var h = (b - a) / n;
            return (h / 6) * MathHelper.sum(0, n - 1, i ->
                    f.apply(a + i * h) + 4 * f.apply(a + i * h + h / 2) + f.apply(a + (i + 1) * h)
            );
        };

        Integral INT_GAUSS_LEGENDRE = (f, a, b, n) ->
                (b - a) / 2 * MathHelper.sum(0, n - 1, i ->
                    Gauss.weight(n, i) * f.apply((b - a) / 2 * Gauss.node(n, i) + (b + a) / 2)
                );
    }

    private static class Gauss {

        private static final double[][] WEIGHTS = {
                {1.0, 1.0},
                {5.0/9.0, 8.0/9.0, 5.0/9.0},
                {0.347854845, 0.652145155, 0.652145155, 0.347854845}
        };

        private static final double[][] NODES = {
                {-0.577350269, 0.577350269},
                {-0.774596669, 0.0, 0.774596669},
                {-0.861136312, -0.339981044, 0.339981044, 0.339981044}
        };

        public static double weight(int n, int i) {
            if (n < 2 || n > 4) throw new IllegalArgumentException("Unexpected n value: " + n);
            if (i < 0 || i >= n) throw new IllegalArgumentException("Unexpected i value: " + i);
            return WEIGHTS[n-2][i];
        }

        public static double node(int n, int i) {
            if (n < 2 || n > 4) throw new IllegalArgumentException("Unexpected n value: " + n);
            if (i < 0 || i >= n) throw new IllegalArgumentException("Unexpected i value: " + i);
            return NODES[n-2][i];
        }
    }
}
