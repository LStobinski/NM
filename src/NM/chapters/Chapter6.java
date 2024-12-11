package NM.chapters;

import NM.Logger;
import NM.classes.DerivativeFunction;
import NM.classes.MathHelper;

import java.util.Arrays;
import java.util.function.Function;

public class Chapter6 {
    static Logger LOGGER = Logger.Get("C5");

    public static void Run() {
        LOGGER.Log("Chapter VI: Numerical Derivatives");
        LOGGER.Log("==========================");
//        Task_1();
//        Task_2();
//        Task_2b();
//        Task_3();
//        Task_4();
//        Task_5();
        Task_6();

    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        //...

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        Function<Double, Double> fun = x -> 3*x*x*x - 2*x*x + 1;

        double x = 0.75;
        double h = 0.01;

        var _2pf = MathHelper.derivative_1_2pf(fun, x, h);
        var _2pc = MathHelper.derivative_1_2pc(fun, x, h);
        var _3pf = MathHelper.derivative_1_3pf(fun, x, h);

        double d = 2.625;

        LOGGER.Log("2pf: " + _2pf + " | error: " + (d - _2pf));
        LOGGER.Log("2pc: " + _2pc + " | error: " + (d - _2pc));
        LOGGER.Log("3pf: " + _3pf + " | error: " + (d - _3pf));

        LOGGER.Log("");
    }

    public static void Task_2b() {
        LOGGER.Log("Task 2b >>>");

        Function<Double, Double> fun = x -> 3*x*x*x - 2*x*x + 1;

        double x = 0.75;
        double h1 = (double) 1 / 1000;
        double h2 = (double) 1 / 1024;

        var _2pc1 = MathHelper.derivative_1_2pc(fun, x, h1);
        var _2pc2 = MathHelper.derivative_1_2pc(fun, x, h2);

        double d = 2.625;

        LOGGER.Log("2pc1: " + _2pc1 + " | error: " + (d - _2pc1));
        LOGGER.Log("2pc2: " + _2pc2 + " | error: " + (d - _2pc2));

        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        Function<Double, Double> fun = x -> x * Math.sin(x * x) + 1;

        double x = 1;
        double h1 = 0.01;
        double h2 = 0.001;

        var _2pf1 = MathHelper.derivative_1_2pf(fun, x, h1);
        var _2pc1 = MathHelper.derivative_1_2pc(fun, x, h1);
        var _2pf2 = MathHelper.derivative_1_2pf(fun, x, h2);
        var _2pc2 = MathHelper.derivative_1_2pc(fun, x, h2);

        double d = 1.92207559654417594145437553651;

        LOGGER.Log("2pf1: " + _2pf1 + " | error: " + (d - _2pf1));
        LOGGER.Log("2pc1: " + _2pc1 + " | error: " + (d - _2pc1));

        LOGGER.Log("2pf2: " + _2pf2 + " | error: " + (d - _2pf2));
        LOGGER.Log("2pc2: " + _2pc2 + " | error: " + (d - _2pc2));

        LOGGER.Log("");
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");

        Function<Double, Double> fun = Math::exp;

        double x = 0;
        double h1 = 0.001;
        double h2 = 0.0001;

        var _2pf1 = MathHelper.derivative_1_2pf(fun, x, h1);
        var _2pc1 = MathHelper.derivative_1_2pc(fun, x, h1);
        var _3pf1 = MathHelper.derivative_1_3pf(fun, x, h1);

        var _2pf2 = MathHelper.derivative_1_2pf(fun, x, h2);
        var _2pc2 = MathHelper.derivative_1_2pc(fun, x, h2);
        var _3pf2 = MathHelper.derivative_1_3pf(fun, x, h2);

        double d = 1.0;

        LOGGER.Log("2pf1: " + _2pf1 + " | error: " + (d - _2pf1));
        LOGGER.Log("2pc1: " + _2pc1 + " | error: " + (d - _2pc1));
        LOGGER.Log("3pf1: " + _3pf1 + " | error: " + (d - _3pf1));

        LOGGER.Log("2pf2: " + _2pf2 + " | error: " + (d - _2pf2));
        LOGGER.Log("2pc2: " + _2pc2 + " | error: " + (d - _2pc2));
        LOGGER.Log("3pf2: " + _3pf2 + " | error: " + (d - _3pf2));

        LOGGER.Log("");
    }

    public static void Task_5() {
        LOGGER.Log("Task 5 >>>");

        Function<Double, Double> fun = x -> 3*x*x*x - 2*x*x + 1;

        double x = 0.75;
        double h1 = 0.01;
        double h2 = 0.001;

        var _2pf1 = MathHelper.derivative_2_2pf(fun, x, h1);
        var _2pc1 = MathHelper.derivative_2_2pc(fun, x, h1);

        var _2pf2 = MathHelper.derivative_2_2pf(fun, x, h2);
        var _2pc2 = MathHelper.derivative_2_2pc(fun, x, h2);

        double d = 9.5;

        LOGGER.Log("2pf1: " + _2pf1 + " | error: " + (d - _2pf1));
        LOGGER.Log("2pc1: " + _2pc1 + " | error: " + (d - _2pc1));

        LOGGER.Log("2pf2: " + _2pf2 + " | error: " + (d - _2pf2));
        LOGGER.Log("2pc2: " + _2pc2 + " | error: " + (d - _2pc2));

        LOGGER.Log("");
    }

    public static void Task_6() {
        LOGGER.Log("Task 6 >>>");

        Function<Double, Double> fun = Math::exp;
//        DerivativeFunction<Double>[] drv = new DerivativeFunction[]{MathHelper::derivative_2_2pf, MathHelper::derivative_2_2pc};
        double[] X = {0, 1};
        double[] H = {0.1, 0.0001, 0.0000001};

        for (var x : X) for (var h : H) {
            var d = x == 0 ? 1 : Math.E;
            var value1 = MathHelper.derivative_2_2pf(fun, x, h);
            var value2 = MathHelper.derivative_2_2pc(fun, x, h);
            LOGGER.Log("[2pf/x:" + x + "/h:" + h + "]: " + value1 + " | error: " + (d - value1));
            LOGGER.Log("[2pc/x:" + x + "/h:" + h + "]: " + value2 + " | error: " + (d - value2));
        }

        LOGGER.Log("");
    }

    /*

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        //...

        LOGGER.Log("");
    }

    */

}
