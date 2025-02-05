package NM.chapters;

import NM.Logger;
import NM.classes.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Chapter8 {
    static Logger LOGGER = Logger.Get("C8");

    public static void Run() {
        LOGGER.Log("Chapter VIII: Nonlinear Equations");
        LOGGER.Log("==========================");
        Task_1();
        Task_2();
        Task_3();
        Task_4();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        double left = -3, right = -2;
        double eps = 0.0001;
        Function<Double, Double> fun = (x) -> 2 * x * x + 4 * x - 1.0;
        Function<Double, Double> fun$ = (x) -> 4 * x + 4;

        Bisect(fun, left, right, eps); LOGGER.Log("");
        Falsi(fun, left, right, eps); LOGGER.Log("");
        Secant(fun, left, right, eps); LOGGER.Log("");
        Newton(fun, fun$, left, right, eps); LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        double left = -1.5, right = 0;
        double eps = 0.00001;
        Function<Double, Double> fun = (x) -> x + Math.exp(Math.tan(x));
        Function<Double, Double> fun$ = (x) -> 1 + Math.exp(Math.tan(x)) * (1 / Math.pow(Math.cos(x), 2));

        Bisect(fun, left, right, eps); LOGGER.Log("");
        Falsi(fun, left, right, eps); LOGGER.Log("");
        Secant(fun, left, right, eps); LOGGER.Log("");
        Newton(fun, fun$, left, right, eps); LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        double left = -1.2, right = -0.4;
        double eps = 0.00001;
        Function<Double, Double> fun = (x) -> 1/x + 2*x + 3;
        Function<Double, Double> fun$ = (x) -> -1/(x*x) + 2;

        if (fun.apply(left) * fun.apply(right) < 0) {
            Bisect(fun, left, right, eps); LOGGER.Log("");
            Falsi(fun, left, right, eps); LOGGER.Log("");
            Secant(fun, left, right, eps); LOGGER.Log("");
            Newton(fun, fun$, left, right, eps); LOGGER.Log("");
        } else {
            var mid = (right - left) / 2;

            Bisect(fun, left, mid, eps); LOGGER.Log("");
            Bisect(fun, mid, right, eps); LOGGER.Log("");

            Falsi(fun, left, mid, eps); LOGGER.Log("");
            Falsi(fun, mid, right, eps); LOGGER.Log("");

            Secant(fun, left, mid, eps); LOGGER.Log("");
            Secant(fun, mid, right, eps); LOGGER.Log("");

            Newton(fun, fun$, left, mid, eps); LOGGER.Log("");
            Newton(fun, fun$, mid, right, eps); LOGGER.Log("");
        }
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");

        double left = -1.0, right = 0.5;
        double eps = 0.0001;
        Function<Double, Double> fun = (x) -> x*x*x;
        Function<Double, Double> fun$ = (x) -> 3*x*x;

        Bisect(fun, left, right, eps); LOGGER.Log("");
        Falsi(fun, left, right, eps); LOGGER.Log("");
        Secant(fun, left, right, eps); LOGGER.Log("");
        Newton(fun, fun$, left, right, eps); LOGGER.Log("");
        // niska wartość pochodnej (funkcja jest płaska w pobliżu miejsca 0)
        // powoduje, że różnica pomiędzy kolejnymi punktami iteracyjnymi jest mała
        // co sprawia, że metody zbiegają powoli
        // nie ma to jednak wpływu na m. bisekcji, ponieważ nie wspomaga się wartoścą badanej funkcji
    }

    public static double Bisect(Function<Double, Double> f, double a, double b, double epsilon) {
        double l = a, r = b;
        double x = a, fx;
        int it = 0;
        while (r - l > epsilon) {
            x = (l + r) / 2;
            fx = f.apply(x);
            LOGGER.Log("[bisect][" + (++it) + "]: f(" + x + ") = " + fx);
            if (fx == 0) break;
            if (f.apply(l) * fx < 0) r = x;
            else l = x;
        }
        return x;
    }

    public static double Falsi(Function<Double, Double> f, double a, double b, double epsilon) {
        double l = a;
		double x, fx;
        int it = 0;
        do {
            x = l - (f.apply(l) / (f.apply(b) - f.apply(l))) * (b - l);
            fx = f.apply(x);
            LOGGER.Log("[falsi][" + (++it) + "]: f(" + x + ") = " + fx);
            if (fx == 0) break;
            l = x;
        } while (Math.abs(fx) > epsilon);
        return x;
    }

    public static double Secant(Function<Double, Double> f, double a, double b, double epsilon) {
        double x1 = a, x2 = b;
        double x3 = a, fx;
        int it = 0;
        while (Math.abs(x2 - x1) > epsilon) {
            x3 = x2 - f.apply(x2) / (f.apply(x2) - f.apply(x1)) * (x2 - x1);
            fx = f.apply(x3);
            LOGGER.Log("[secant][" + (++it) + "]: f(" + x3 + ") = " + fx);
            if (fx == 0) break;
            x1 = x2;
            x2 = x3;
        }
        return x3;
    }

    public static double Newton(Function<Double, Double> f, Function<Double, Double> f$, double a, double b, double epsilon) {
        double x = (b-a)/2;
        double fx, f$x;
        int it = 0;
        do {
            fx = f.apply(x);
            f$x = f$.apply(x);
            if (f$x == 0) throw new IllegalStateException("f'(x) = 0");
            x = x - fx / f$x;
            LOGGER.Log("[newton][" + (++it) + "]: f(" + x + ") = " + fx);
        } while (Math.abs(fx) > epsilon);
        return x;
    }
}
