package NM.classes;

import java.util.*;
import java.util.function.BiFunction;

import NM.Logger;

public class Matrix {
    private static final Logger LOGGER = Logger.Get("Matrix");

    private final double[][] values;
    public final int height;
    public final int width;
    public final int size;

    public double getValue(int row, int col) {
        return values[row][col];
    }

    public void setValue(int row, int col, double val) {
        double d = values[row][col];
        values[row][col] = val;
    }

    public Matrix(int size) {
        this(size, size);
    }

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) throw new IllegalArgumentException("Cannot create Matrix of size 0 or smaller.");
        this.values = new double[rows][cols];
        this.size = (rows == cols) ? rows : -1;
        this.height = rows;
        this.width = cols;
    }

    public static Matrix of(int size, double... values) {
        return of(size, size, values);
    }

    public static Matrix of(int rows, int cols, double... values) {
        if (rows * cols != values.length)
            throw new IllegalArgumentException("Value count mismatch: expected " + rows * cols + ", but got " + values.length);
        Matrix M = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++)
            System.arraycopy(values, row * cols, M.values[row], 0, cols);
        return M;
    }

    public static Matrix of(Matrix origin) {
        Matrix M = new Matrix(origin.height, origin.width);
        for (int row = 0; row < origin.height; row++)
            if (origin.width >= 0) System.arraycopy(origin.values[row], 0, M.values[row], 0, origin.width);
        return M;
    }

    public static Matrix Identity(int size) {
        return Identity(size, size);
    }

    public static Matrix Identity(int rows, int cols) {
        Matrix M = new Matrix(rows, cols);
        var k = Math.min(rows, cols);
        for (int i = 0; i < k; i++)
            M.values[i][i] = 1;
        return M;
    }

    public static Matrix multiply(Matrix A, Matrix B) {
        if (A.width != B.height) throw new IllegalArgumentException("Matrices incompatible for operation.");
        int height = A.height;
        int width = B.width;
        Matrix M = new Matrix(height, width);

        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                for (int step = 0; step < A.width; step++)
                    M.values[row][col] += A.values[row][step] * B.values[step][col];

        return M;
    }

    public void foreach(BiFunction<Integer, Integer, Double> function) {
        for (int row = 0; row < 4; row++)
            for (int col = 0; col < 4; col++)
                this.setValue(row, col, function.apply(row, col));
    }

    public Matrix minor(int xrow, int xcol) {
        // x- skip r/c
        // i- iteration r/c
        // t- target r/c
        Matrix M = new Matrix(height - 1, width - 1);
        for (int irow = 0, trow = 0; irow < height; irow++) {
            if (irow == xrow) continue;
            for (int icol = 0, tcol = 0; icol < width; icol++) {
                if (icol == xcol) continue;
                M.values[trow][tcol] = values[irow][icol];
                tcol++;
            }
            trow++;
        }
        return M;
    }

    public Matrix transpose() {
        Matrix M = new Matrix(width, height);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                M.values[col][row] = values[row][col];
            }
        }

        return M;
    }

    // TODO: optimize; add 0-search
    public Matrix inverse() {
        if (height != width) throw new IllegalArgumentException("Matrix must be square to calculate inverse.");

        double det = determinant();
        if (det == 0) throw new RuntimeException("Matrix is not invertible.");

        Matrix M = new Matrix(height);
        double scalar = 1.0 / det;
        int sign;
        Matrix T = transpose();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                sign = ((row + col) % 2 == 1) ? -1 : 1;
                M.values[row][col] = scalar * sign * T.minor(row, col).determinant();
            }
        }

        return M;
    }

    public double determinant() {
        if (height != width) throw new IllegalArgumentException("Matrix must be square to calculate determinant.");
        if (width == 1) return values[0][0];
        if (width == 2 || width == 3) return determinantSarrus();
        double determinant = 0;
        for (int col = 0; col < width; col++) {
            var sign = (col % 2 == 0) ? 1 : -1;
            determinant += values[0][col] * sign * minor(0, col).determinant();
        }
        return determinant;
    }

    public double determinantLaplace() {
        if (height != width) throw new IllegalArgumentException("Matrix must be square to calculate determinant.");
        if (width == 1) return values[0][0];
        double determinant = 0;
        for (int col = 0; col < width; col++) {
            var sign = (col % 2 == 0) ? 1 : -1;
            determinant += values[0][col] * sign * minor(0, col).determinantLaplace();
        }
        return determinant;
    }

    public double determinantSarrus() {
        if (height != width) throw new IllegalArgumentException("Matrix must be square to calculate determinant.");
        if (width == 1) return values[0][0];
        if (width == 2) return values[0][0] * values[1][1] - values[0][1] * values[1][0];
        if (width == 3) return
                values[0][0] * values[1][1] * values[2][2] +
                        values[1][0] * values[2][1] * values[0][2] +
                        values[2][0] * values[0][1] * values[1][2] -
                        values[1][0] * values[0][1] * values[2][2] -
                        values[0][0] * values[2][1] * values[1][2] -
                        values[2][0] * values[1][1] * values[0][2];
        throw new IllegalArgumentException("Cannot calculate Matrix of size greater than 3.");
    }

    public Pair<Matrix, Pair<Matrix, Matrix>> LU() {
        if (size == -1) throw new IllegalArgumentException();

        var U = Matrix.of(this);
        var L = new Matrix(size);

        Stack<Pair<Integer, Integer>> pivots = new Stack<>();
        for (int i = 0; i < size; i++) {
            var pivot = U.findPivot(i, i);
            pivots.push(pivot);
            U.pivot(pivot);
            L.pivot(pivot);

            L.values[i][i] = 1;

            for (int row = i + 1; row < size; row++) {
                double factor = U.values[row][i] / U.values[i][i];
                L.values[row][i] = factor;
                for (int col = i; col < size; col++)
                    U.values[row][col] -= (factor * U.values[i][col]);
            }
        }

        var P = Matrix.Identity(size);
        while (!pivots.empty()) P.pivot(pivots.pop());

        return Pair.of(P, Pair.of(L, U));
    }

    public Pair<Matrix, Pair<Matrix, Matrix>> LUCB() {
        if (size == -1) throw new IllegalArgumentException();

        var L = new Matrix(size);

        Stack<Pair<Integer, Integer>> pivots = new Stack<>();

        for (int d = 0; d < size; d++) {

            int k = d;
            var Lkk = Math.sqrt(values[k][k] - MathHelper.sum(0, k - 1, p -> L.values[k][p] * L.values[k][p]));
            L.values[k][k] = Lkk;
            for (int d1 = k + 1; d1 < size; d1++) {
                var i = d1;
                L.values[i][k] = (values[i][k] - MathHelper.sum(0, k - 1, p -> L.values[i][p] * L.values[k][p])) / Lkk;
            }
        }

        var U = L.transpose();

        var P = Matrix.Identity(size);
        while (!pivots.empty()) P.pivot(pivots.pop());

        return Pair.of(P, Pair.of(L, U));
    }

    public Matrix triangleUpper(Matrix B) {
        if (height != width || B.height != height || B.width != 1)
            throw new IllegalArgumentException();

        Stack<Pair<Integer, Integer>> pivots = new Stack<>();
        for (int i = 0; i < width; i++) {
            var pivot = findPivot(i, i);
            pivots.push(pivot);
            pivot(pivot);
            B.pivot(pivot);

            for (int row = i + 1; row < height; row++) {
                double factor = values[row][i] / values[i][i];
                for (int col = i; col < width; col++)
                    values[row][col] -= (factor * values[i][col]);
                B.values[row][0] -= (factor * B.values[i][0]);
            }
        }

        var P = Matrix.Identity(height);
        while (!pivots.empty()) P.pivot(pivots.pop());

        return P;
    }

    public Matrix substituteBackward(Matrix B) {
        Matrix X = new Matrix(height, 1);
        int n = height - 1;
        X.values[n][0] = B.values[n][0] / values[n][n];
        for (int k = n - 1; k >= 0; k--) {
            double s = 0;
            for (int j = k + 1; j < n + 1; j++)
                s += values[k][j] * X.values[j][0];
            X.values[k][0] = (B.values[k][0] - s) / values[k][k];
        }
        return X;
    }

    public Matrix substituteForward(Matrix B) {
        Matrix X = new Matrix(height, 1);
        X.values[0][0] = B.values[0][0] / values[0][0];
        for (int i = 1; i < height; i++) {
            int k = i;
            X.values[k][0] = (B.values[k][0] - MathHelper.sum(0, k - 1, j -> values[k][j] * X.values[j][0])) / values[k][k];
        }
        return X;
    }

    public void gaussJordan(Matrix B) {
        if (height != width || B.height != height || B.width != 1)
            throw new IllegalArgumentException();

        for (int i = 0; i < width; i++) {
            var pivot = findPivot(i, i);
            pivot(pivot);
            B.pivot(pivot);

            double head = values[i][i];
            for (int col = i; col < width; col++)
                values[i][col] /= head;
            B.values[i][0] /= head;

            for (int row = 0; row < height; row++) {
                if (row == i) continue;
                double factor = values[row][i] / values[i][i];
                for (int col = i; col < width; col++)
                    values[row][col] -= (factor * values[i][col]);
                B.values[row][0] -= (factor * B.values[i][0]);
            }
        }
    }

    public void pivot(Pair<Integer, Integer> rows) {
        if (Objects.equals(rows.getFirst(), rows.getSecond())) return;
        var tmp = values[rows.getFirst()];
        values[rows.getFirst()] = values[rows.getSecond()];
        values[rows.getSecond()] = tmp;
    }

    private Pair<Integer, Integer> findPivot(int row, int col) {
        int target = -1;
        double t_val = 0;
        for (int i = row; i < height; i++)
            if (Math.abs(getValue(i, col)) > Math.abs(t_val)) {
                target = i;
                t_val = getValue(i, col);
            }

        if (target == -1) { // no valid pivot
            LOGGER.Log(this.toStrings());
            throw new IllegalArgumentException("Zero col: " + col);
        }

        return Pair.of(row, target);
    }

    public List<String> toStrings() {
        return Arrays.stream(values)
            .map(doubles -> String.join(" ", Arrays.stream(doubles)
                .mapToObj(d -> String.format("% 3.3f", d))
                .map(s -> String.format("%8s", s))
                .toList()))
            .map(string -> "| " + string + " |")
            .toList();
    }

}
