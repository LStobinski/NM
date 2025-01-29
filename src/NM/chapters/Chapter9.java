package NM.chapters;

import NM.Logger;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Chapter9 {
    static Logger LOGGER = Logger.Get("C5");

    public static void Run() {
        LOGGER.Log("Chapter V: Interpolation & Approximation");
        LOGGER.Log("==========================");
//        Task_1();
//        Task_2();
//        Task_3();
        Task_4();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        //y' + y + e^-x = 0
        //y' = -y - e^-x
        //y(0) = 1
        //x = 0..4
        var h = 0.2d;
        var x0 = 0.0d;
        var xmax = 4.0d;
        var y0 = 1.0d;

        double x = x0;
        double y = y0;
        while (x < xmax) {
            y = stepRK1((x_, y_) -> -y_ - Math.exp(-x_), x, y, h);
            x += h;
            LOGGER.Log("x = " + x + ", y = " + y + ", err: " + (-3 * Math.exp(-4) - y));
        }
//        LOGGER.Log("y := " + (-3 * Math.exp(-4)));

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        //y' + 2x^3 -12x^2 + 20x - 8.5 = 0
        //y' = -2x^3 12x^2 - 20x + 8.5
        //y(0) = 1
        //x = 0..4
        var h = 0.1d;
        var x0 = 0.0d;
        var xmax = 4.0d;
        var y0 = 1.0d;

        double x = x0;
        double y = y0;
        while (x < xmax) {
            y = stepRK4((x_, y_) -> -2 * x_ * x_ * x_ + 12 * x_ * x_ - 20 * x_ + 8.5, x, y, h);
            x += h;
            var exact = -.5*Math.pow(x, 4) + 4*Math.pow(x, 3) - 10*Math.pow(x, 2) + 8.5*x + 1;
            LOGGER.Log("x = " + x + ", y = " + y + ", err: " + (exact - y));
        }

        LOGGER.Log("");
    }


    public static void Task_3() {
        LOGGER.Log("Task 1 >>>");

        var t0 = 0.0d;
        var T0 = 550.0d;
        var Dt = 60.0d;
        var dt = 1.0d;
        var k = 0.0245d;
        var Tr = 300.0d;

        var t = t0;
        var T = T0;
        while (t < Dt) {
            T = stepRK4((t_, T_) -> -k * (T_ - Tr), t, T, dt);
            t += dt;
            LOGGER.Log("t = " + t + ", T = " + T);
        }
        LOGGER.Log("diff = " + (357.481 - T));

        LOGGER.Log("");
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");

        var m0 = 541000.0d;
        var t0 = 0.0d;
        var s0 = 0.0d;
        var w = 2770.0d; // m/s
        var Dt = 40.0d;
        var dt = 1.0d;
        var df = 1260.0d; // kg

        var t = t0;
        var m = m0;
        var s = s0;
        while (t < Dt) {
            double finalM = m;
            s = stepRK4((t_, s_) -> w * Math.log(m0/ finalM), t, s, dt);
            m -= df * dt;
            t += dt;
            LOGGER.Log("t = " + t + ", m = " + m + ", s = " + s);
        }

        LOGGER.Log("");
    }

    public static double stepRK1(BiFunction<Double, Double, Double> f, double x, double y, double h) {
        return y + h * f.apply(x, y);
    }

    public static double stepRK2(BiFunction<Double, Double, Double> f, double x, double y, double h) {
        var k1 = h * f.apply(x, y);
        var k2 = h * f.apply(x + h, y + k1);
        return y + (k1 + k2) / 2;
    }

    public static double stepRK4(BiFunction<Double, Double, Double> f, double x, double y, double h) {
        var k1 = h * f.apply(x, y);
        var k2 = h * f.apply(x + h / 2, y + k1 / 2);
        var k3 = h * f.apply(x + h / 2, y + k2 / 2);
        var k4 = h * f.apply(x + h, y + k3);
        return y + (k1 + 2 * k2 + 2 * k3 + k4) / 6;
    }

    public static double[] stepRK4(BiFunction<Double, Double[], Double>[] f, double x, Double[] y, double h) {
        double[] k1 = new double[4];
        double[] k2 = new double[4];
        double[] k3 = new double[4];
        double[] k4 = new double[4];
        for (int i = 0; i < y.length; i++)
            k1[i] = (h * f[i].apply(x, y));
        for i in range(len(y)):
        k2.append(h * f[i](x + h / 2, [z + k1[j] / 2 for j, z in enumerate(y)]))
        for i in range(len(y)):
        k3.append(h * f[i](x + h / 2, [z + k2[j] / 2 for j, z in enumerate(y)]))
        for i in range(len(y)):
        k4.append(h * f[i](x + h, [z + k3[j] for j, z in enumerate(y)]))
        for i in range(len(y)):
        y[i] += (k1[i] + 2 * k2[i] + 2 * k3[i] + k4[i]) / 6
        return y
    }
}
