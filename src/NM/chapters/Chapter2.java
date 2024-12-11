package NM.chapters;

import NM.Logger;
import NM.classes.Matrix;

public class Chapter2 {
    static Logger LOGGER = Logger.Get("C2");

    public static void Run() {
        LOGGER.Log("Chapter II: Matrix Algebra");
        LOGGER.Log("==========================");
        Task_1();
        Task_2();
        Task_3();
        Task_4();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        Matrix A = Matrix.of(3,4,
    -1,  4,  2, -2,
             1,  2, -3,  0,
            -1,  0,  0,  5
        );

        Matrix B = Matrix.of(4,2,
     2, -1,
             1,  3,
            -2,  0,
             0, -4
        );

        Matrix C = Matrix.multiply(A, B);
        LOGGER.Log("Matrix mult:");
        LOGGER.Log(C.toStrings());

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER. Log("Task 2 >>>");

        Matrix M = Matrix.of(3,
    1d,  3,  2,
             4, -1,  2,
             1, -1,  0
        );

        LOGGER.Log("Matrix:");
        LOGGER.Log(M.toStrings());
        LOGGER.Log("Det. Sarrus : " + M.determinantSarrus());
        LOGGER.Log("Det. Laplace: " + M.determinantLaplace());

        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER. Log("Task 3 >>>");

        Matrix M = Matrix.of(5,
    2d,  7, -1,  3,  2,
             0,  0,  1,  0,  1,
            -2,  0,  7,  0,  2,
            -3, -2,  4,  5,  3,
             1,  0,  0,  0,  1
        );

        LOGGER.Log("Matrix:");
        LOGGER.Log(M.toStrings());
        LOGGER.Log("Det. : " + M.determinant());

        LOGGER.Log("");
    }

    public static void Task_4() {
        LOGGER. Log("Task 4 >>>");

        Matrix A = Matrix.of(3,
    1d,  3,  2,
             4, -1,  2,
             1, -1,  0
        );

        Matrix B = A.inverse();
        Matrix I = Matrix.multiply(A, B);

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());

        LOGGER.Log("Matrix B:");
        LOGGER.Log(B.toStrings());

        LOGGER.Log("Matrix AB:");
        LOGGER.Log(I.toStrings());

        LOGGER.Log("");
    }
}
