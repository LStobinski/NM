package NM.chapters;

import NM.Logger;
import NM.classes.MathHelper;

import java.util.function.Function;

public class Chapter7 {
    static Logger LOGGER = Logger.Get("C5");

    public static void Run() {
        LOGGER.Log("Chapter V: Interpolation & Approximation");
        LOGGER.Log("==========================");
        Task_4();
//        Task_2();
    }

    public static double prost(
            Function<Double, Double> f,
            double a,
            double b,
            double h
    ) {
        var tmp = 0.0d;
        for (double d = a; d < b; d += h) {
            tmp += h*f.apply(d);
        }
        return tmp;
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");



        Function<Double, Double> f = (x) -> 4*x*x*x + 5*x*x + 1;

        Function<Double, Double> p = (x) -> {
            return 1.0;
        };

        Function<Double, Double> F = (x) -> {
            return 2.0;
        };



        LOGGER.Log("");
    }

    static double[] wi = {
            1.0d,
            1.0d
    };

    static double[] xi = {
            -0.577350269,
            0.577350269
    };

    static double X(int n, int i) {
        return switch (n) {
            case 2 -> new double[] {
                    -0.577350269,
                    0.577350269
            }[i];
            case 3 -> new double[] {
                    0.774596669,
                    0.0,
                    0.774596669
            }[i];
            //...
            default -> throw new IllegalStateException("Unexpected value: " + n);
        };
    }

    public static double gauss(Function<Double, Double> f, double a, double b, int n) {
        var G = 0.5 * (b - a) * MathHelper.sum(0, n - 1, i ->
                    wi[i] * f.apply(0.5 * (b - a) * X(n, i) + 0.5 * (b + a)));
        

//        return 0.5 * (b - a) * MathHelper.sum(0, n - 1, i ->
//                wi[i] * f.apply(0.5 * (b - a) * xi[i] + 0.5 * (b + a))
//        );
    };

    /*
    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        // ...

        LOGGER.Log("");
    }
    */
}
