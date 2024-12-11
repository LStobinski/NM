package NM.chapters;

import NM.Logger;
import NM.Profiler;
import NM.classes.Matrix;

public class Chapter3 {
    static Logger LOGGER = Logger.Get("C3");

    public static void Run() {
        LOGGER.Log("Chapter III: Linear Systems");
        LOGGER.Log("==========================");
        Task_1();
        Task_2();
        Task_3();
        Task_4();
        Task_5();
        Task_6();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        Matrix A = Matrix.of(3,
        5d, -2, 3,
                -2, 3, 1,
                -1, 2, 3
        );

        Matrix B = Matrix.of(3, 1, 21, -4, 5);

        Matrix M = A.inverse();

        Matrix X = Matrix.multiply(M, B);

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());

        LOGGER.Log("Matrix B:");
        LOGGER.Log(B.toStrings());

        LOGGER.Log("Matrix X:");
        LOGGER.Log(X.toStrings());

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        Matrix A = Matrix.of(3,
        5d, -2, 3,
                -2, 3, 1,
                -1, 2, 3
        );

        Matrix B = Matrix.of(3, 1, 21, -4, 5);

        double detA = A.determinant();
        if (detA == 0) throw new RuntimeException("Matrix is not invertible.");

        Matrix X = new Matrix(3, 1);

        for (int j = 0; j < 3; j++) {
            Matrix K = Matrix.of(A);
            for (int i = 0; i < 3; i++)
                K.setValue(i, j, B.getValue(i, 0));
            double x = K.determinant() / detA;
            X.setValue(j, 0, x);
        }

        LOGGER.Log("Matrix A:");
        LOGGER.Log(A.toStrings());

        LOGGER.Log("Matrix B:");
        LOGGER.Log(B.toStrings());

        LOGGER.Log("Matrix X:");
        LOGGER.Log(X.toStrings());


        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        Matrix M1 = Matrix.of(5, 5,
                -1, 2, -3, 3, 5,
                8, 0, 7, 4, -1,
                -3, 4, -3, 2, -2,
                8, -3, -2, 1, 2,
                -2, -1, -6, 9, 0
        );
        Matrix M2 = Matrix.of(M1);

        Matrix B1 = Matrix.of(5, 1, 56, 62, -10, 14, 28);
        Matrix B2 = Matrix.of(B1);

        var from1 = Profiler.getNanos();
        M1.triangleUpper(B1);
        var X1 = M1.substituteBackward(B1);
        var to1 = Profiler.getNanos();
        var diff1 = Profiler.differenceMillis(from1, to1);
        LOGGER.Log("Gauss time millis: " + diff1);

        var from2 = Profiler.getNanos();
        double det = M2.determinant();
        if (det == 0) throw new RuntimeException("Matrix is not invertible.");
        Matrix X2 = new Matrix(5, 1);
        for (int j = 0; j < 5; j++) {
            Matrix K = Matrix.of(M2);
            for (int i = 0; i < 5; i++)
                K.setValue(i, j, B2.getValue(i, 0));
            double x = K.determinant() / det;
            X2.setValue(j, 0, x);
        }
        var to2 = Profiler.getNanos();
        var diff2 = Profiler.differenceMillis(from2, to2);
        LOGGER.Log("Cramer time millis: " + diff2);

        LOGGER.Log("Matrix X1:");
        LOGGER.Log(X1.toStrings());
        LOGGER.Log("Matrix X2:");
        LOGGER.Log(X2.toStrings());

        LOGGER.Log("");
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");

        Matrix M = Matrix.of(5, 5,
                -1, 2, -3, 3, 5,
                8, 0, 7, 4, -1,
                -3, 4, -3, 2, -2,
                8, -3, -2, 1, 2,
                -2, -1, -6, 9, 0
        );

        Matrix B = Matrix.of(5, 1, 56, 62, -10, 14, 28);

        var from1 = Profiler.getNanos();

        M.gaussJordan(B);

        var to1 = Profiler.getNanos();
        var diff1 = Profiler.differenceMillis(from1, to1);
        LOGGER.Log("Gauss-Jordan time millis: " + diff1);

        LOGGER.Log("Matrix M:");
        LOGGER.Log(M.toStrings());

        LOGGER.Log("Matrix B=X:");
        LOGGER.Log(B.toStrings());


        LOGGER.Log("");
    }

    public static void Task_5() {
        LOGGER.Log("Task 5 >>>");

        Matrix M = Matrix.of(3,3,
                0,1,1,
                1,1,1,
                2,0,-1
        );

        Matrix B = Matrix.of(3, 1, 1, 2, 0);

        var from1 = Profiler.getNanos();
        M.triangleUpper(B);
        var X = M.substituteBackward(B);
        var to1 = Profiler.getNanos();
        var diff1 = Profiler.differenceMillis(from1, to1);
        LOGGER.Log("Pivoting time millis: " + diff1);

        LOGGER.Log("Matrix X:");
        LOGGER.Log(X.toStrings());

        LOGGER.Log("");
    }

    public static void Task_6() {
        LOGGER.Log("Task 6 >>>");

        Matrix M1 = Matrix.of(5, 5,
                -1, 2, -3, 3, 5,
                8, 0, 7, 4, -1,
                -3, 4, -3, 2, -2,
                8, -3, -2, 1, 2,
                -2, -1, -6, 9, 0
        );

        Matrix M2 = new Matrix(5);

        Matrix B = Matrix.of(5, 1, 56, 62, -10, 14, 28);

        var from1 = Profiler.getNanos();

        for (int col = 0; col < 5; col++) {
            Matrix Ei = new Matrix(5,1);
            Ei.setValue(col, 0, 1);
            var Mi = Matrix.of(M1);
            Mi.triangleUpper(Ei);
            var Xi = Mi.substituteBackward(Ei);
            for (int row = 0; row < 5; row++)
                M2.setValue(row, col, Xi.getValue(row, 0));
        }


        Matrix X = Matrix.multiply(M2, B);

        var to1 = Profiler.getNanos();
        var diff1 = Profiler.differenceMillis(from1, to1);
        LOGGER.Log("Inverting time millis: " + diff1);

        LOGGER.Log("Matrix X:");
        LOGGER.Log(X.toStrings());

        LOGGER.Log("");
    }
}
