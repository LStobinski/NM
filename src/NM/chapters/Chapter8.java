package NM.chapters;

import NM.Logger;

import java.util.Optional;
import java.util.function.Function;

public class Chapter8 {
    static Logger LOGGER = Logger.Get("C5");

    public static void Run() {
        LOGGER.Log("Chapter V: Interpolation & Approximation");
        LOGGER.Log("==========================");
        Task_1();
//        Task_2();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        double left = -3;
        double right = -2;
        double eps = 0.0001;
        Function<Double, Double> fun = (x) -> 2 * x * x + 4 * x - 1.0;
        Function<Double, Double> fun$ = (x) -> 4 * x + 4;

        Seek(Chapter8::Bisect, fun, left, right, eps);

        _Bisect(fun, left, right, eps);
        LOGGER.Log("");
        Falsi(fun, left, right, eps);
        LOGGER.Log("");
        Sieczna(fun, left, right, eps);
        LOGGER.Log("");
        Newton(fun, fun$, left, right, eps);

        LOGGER.Log("");
    }

    public static void Seek(
            Finder METHOD,
            Function<Double, Double> FUN,
            double L,
            double R,
            double EPS
    ) {
        var Rec = new Rec(L, R, Optional.empty());
        while (Rec.next().isEmpty()) {
             Rec = METHOD.test(FUN, Rec.left(), Rec.right(), EPS);
        }
        LOGGER.Log("[???]: f(" + Rec.next().get() + ") = " + FUN.apply(Rec.next().get()));
    }

    public static Rec Bisect(
        Function<Double, Double> FUN,
        double L,
        double R,
        double EPS
    ) {
        var M = (L + R) / 2;
        var F$M = FUN.apply(M);
        if (F$M == 0 || Math.abs(L - R) < EPS) return new Rec(L, R, Optional.of(M));
        if (FUN.apply(L) * F$M < 0) return new Rec(L, M, Optional.empty());
        else return new Rec(M, R, Optional.empty());
    }

    record Rec(double left, double right, Optional<Double> next){};

    public static void _Bisect(
            Function<Double, Double> FUN,
            double L,
            double R,
            double EPS
    ) {
        while (Math.abs(L - R) > EPS) {
            var M = (L + R) / 2;
            var F$M = FUN.apply(M);
            LOGGER.Log("[bis]: f(" + M + ") = " + F$M);

            if (F$M == 0) return;
            if (FUN.apply(L) * F$M < 0) R = M;
            else L = M;

        }
        ;
    }

    public static void Falsi(
            Function<Double, Double> FUN,
            double L,
            double R,
            double EPS
    ) {
//        Function<Double, Double> G = (x) ->
//                x * ((FUN.apply(L) - FUN.apply(R)) / (L - R)) + FUN.apply(L);

        while (FUN.apply(L) * FUN.apply(R) < 0) {
            var M = L - FUN.apply(L) / (FUN.apply(R) - FUN.apply(L)) * (R - L);
            LOGGER.Log("[fal]: f(" + M + ") = " + FUN.apply(M));

            if (Math.abs(FUN.apply(M))<EPS) return;

            if (FUN.apply(L)* FUN.apply(M)<0) R = M;
            else L = M;

        }
    }

    public static void Sieczna(
            Function<Double, Double> FUN,
            double L,
            double R,
            double EPS
    ) {
        //BiPredicate<Double,Double> predicate = (l, r) -> FUN.apply(l) * FUN.apply(r) < 0;

        var Z1 = L;
        var Z2 = R;
        while (FUN.apply(Z1) * FUN.apply(Z2) < 0) {
            var Z3 = Z1 - FUN.apply(Z1) / (FUN.apply(Z2) - FUN.apply(Z1)) * (Z2 - Z1);
            LOGGER.Log("[sie]: f(" + Z3 + ") = " + FUN.apply(Z3));

            if (Math.abs(FUN.apply(Z3)) < EPS) return;


            if (FUN.apply(Z1)* FUN.apply(Z3) < 0) Z2 = Z3;
            else Z1 = Z3;

        }
    }

    public static void Newton(
            Function<Double, Double> FUN,
            Function<Double, Double> FUN$,
            double L,
            double R,
            double EPS
    ) {
        var Z1 = L;
        var Z2 = R;
        while (FUN.apply(Z1) * FUN.apply(Z2) < 0) {
            var Z3 = Z1 - FUN.apply(Z1) / FUN$.apply(Z1);
            LOGGER.Log("[new]: f(" + Z3 + ") = " + FUN.apply(Z3));

            if (Math.abs(FUN.apply(Z3)) < EPS) return;

            if (FUN.apply(Z1)* FUN.apply(Z3) < 0) Z2 = Z3;
            else Z1 = Z3;
        }
    }

    @FunctionalInterface
    interface Finder {
        Rec test(Function<Double, Double> FUN, double L, double R, double EPS);
    }

}
