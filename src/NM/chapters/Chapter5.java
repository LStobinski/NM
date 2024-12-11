package NM.chapters;

import NM.Logger;
import NM.classes.Matrix;

import java.util.List;
import java.util.function.Function;

public class Chapter5 {
    static Logger LOGGER = Logger.Get("C5");

    public static void Run() {
        LOGGER.Log("Chapter V: Interpolation & Approximation");
        LOGGER.Log("==========================");
        Task_1();
        Task_2();
//        Task_3();

    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        List<Double> keys = List.of(0d, 1.5d, 3d, 4d);

        List<Function<Double, Double>> base = List.of(
                x -> 1d,
                x -> x,
                Math::cos,
                Math::sin
        );

        Matrix F = Matrix.of(4, 1,
                2d, 3d, 1d, 3d
                );

        Matrix U = new Matrix(4);

        U.foreach((row, col) -> base.get(col).apply(keys.get(row)));

        var U1 = Matrix.of(U);
        U1.triangleUpper(F);

        Matrix A = U1.substituteBackward(F);

        LOGGER.Log("Matrix U:");
        LOGGER.Log(U.toStrings());

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());

        //

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        List<Double> keys = List.of(0d, 1.5d, 3d, 4d);

        List<Function<Double, Double>> base = List.of(
                x -> 1d,
                x -> x,
                x -> x * x,
                x -> x * x * x
        );

        Matrix F = Matrix.of(4, 1,
                2d, 3d, 1d, 3d
        );

        Matrix U = new Matrix(4);

        U.foreach((row, col) -> base.get(col).apply(keys.get(row)));

        var U1 = Matrix.of(U);
        U1.triangleUpper(F);

        Matrix A = U1.substituteBackward(F);

        LOGGER.Log("Matrix U:");
        LOGGER.Log(U.toStrings());

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());

        //

        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        List<Double> keys = List.of(
                (-Math.PI/2),
                (-Math.PI/3),
                (Math.PI/3),
                (Math.PI/2)
                );

        Matrix F = Matrix.of(4, 1,
                -3d, -10d, 20d, 3d
        );

        List<Function<Double, Double>> base = List.of(
                x -> 1d,
                x -> x
        );



        LOGGER.Log("");
    }
}