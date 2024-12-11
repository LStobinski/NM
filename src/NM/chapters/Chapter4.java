package NM.chapters;

import NM.Logger;
import NM.classes.Matrix;

public class Chapter4 {
    static Logger LOGGER = Logger.Get("C3");

    public static void Run() {
        LOGGER.Log("Chapter IV: L/U distribution");
        LOGGER.Log("==========================");
        Task_1();
        Task_2();
        Task_3();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        Matrix M = Matrix.of(5, 5,
                -1, 2, -3, 3, 5,
                8, 0, 7, 4, -1,
                -3, 4, -3, 2, -2,
                8, -3, -2, 1, 2,
                -2, -1, -6, 9, 0
        );

        var PLU = M.LU();
        var P = PLU.getFirst();
        var LU = PLU.getSecond();
        var L = LU.getFirst();
        var U = LU.getSecond();

        var M2 = Matrix.multiply(L, U);
        M2 = Matrix.multiply(P, M2);

        LOGGER.Log("Matrix M:");
        LOGGER.Log(M.toStrings());
        LOGGER.Log("Matrix L:");
        LOGGER.Log(L.toStrings());
        // LOGGER.Log("Matrix U:");
        // LOGGER.Log((Object[]) U.toStrings());
        // LOGGER.Log("Matrix P:");
        // LOGGER.Log((Object[]) P.toStrings());
        LOGGER.Log("Matrix M2:");
        LOGGER.Log(M2.toStrings());


        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        Matrix A = Matrix.of(3, 3,
                4, 8, -4,
                8, 17, -1,
                -4, -1, 57
        );

        var PLU = A.LUCB();
        var P = PLU.getFirst();
        var LU = PLU.getSecond();
        var L = LU.getFirst();
        var U = LU.getSecond();

        var A2 = Matrix.multiply(L, U);
        A2 = Matrix.multiply(P, A2);

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());
        LOGGER.Log("Matrix L:");
        LOGGER.Log(L.toStrings());
        LOGGER.Log("Matrix A2:");
        LOGGER.Log(A2.toStrings());

        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        Matrix A = Matrix.of(5, 5,
                -1, 2, -3, 3, 5,
                8, 0, 7, 4, -1,
                -3, 4, -3, 2, -2,
                8, -3, -2, 1, 2,
                -2, -1, -6, 9, 0
        );

        Matrix B = Matrix.of(5, 1, 56, 62, -10, 14, 28);

        var PLU = A.LU();
        var P = PLU.getFirst();
        var LU = PLU.getSecond();
        var L = LU.getFirst();
        var U = LU.getSecond();

        LOGGER.Log("Matrix L:");
        LOGGER.Log(L.toStrings());
        LOGGER.Log("Matrix U:");
        LOGGER.Log(U.toStrings());

        Matrix Y = L.substituteForward(B);
        Matrix X = U.substituteBackward(Y);

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());
        LOGGER.Log("Matrix Y:");
        LOGGER.Log(Y.toStrings());
        LOGGER.Log("Matrix X:");
        LOGGER.Log(X.toStrings());


        LOGGER.Log("");
    }
}
