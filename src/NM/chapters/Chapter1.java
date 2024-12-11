package NM.chapters;

import NM.Logger;
import NM.classes.ComplexD;
import NM.classes.ComplexF;
import NM.classes.MathHelper;
import NM.classes.QuinaryNumber;

public class Chapter1 {
    static Logger LOGGER = Logger.Get("C1");

    public static void Run() {
        LOGGER.Log("Chapter I: Intro");
        LOGGER.Log("====================");
        Task_1();
        Task_2();
        Task_3();
        Task_4();
    }

    public static void Task_1() {
        LOGGER.Log("Task 1 >>>");

        long L = 132;
        QuinaryNumber Q = new QuinaryNumber(L);

        LOGGER.Log("Number 132 (base 10):");
        LOGGER.Log("  as Long: " + L);
        LOGGER.Log("  as Quinary: " + Q);
        LOGGER.Log("  converted back: " + Q.toLong());

        LOGGER.Log("");
    }

    public static void Task_2() {
        LOGGER.Log("Task 2 >>>");

        ComplexF Z1f = new ComplexF(1.23456789123456789f, 4f / 3f);
        ComplexF Z2f = new ComplexF(1f, 2f);
        ComplexF Z3f = Z1f.add(Z2f);
        ComplexF Z4f = Z1f.mul(Z2f);

        ComplexD Z1d = new ComplexD(1.23456789123456789d, 4d / 3d);
        ComplexD Z2d = new ComplexD(1d, 2d);
        ComplexD Z3d = Z1d.add(Z2d);
        ComplexD Z4d = Z1d.mul(Z2d);

        LOGGER.Log("ComplexF:");
        LOGGER.Log("  Z1f: " + Z1f);
        LOGGER.Log("  Z2f: " + Z2f);
        LOGGER.Log("  sum: " + Z3f);
        LOGGER.Log("  mul: " + Z4f);
        LOGGER.Log("ComplexD:");
        LOGGER.Log("  Z1d: " + Z1d);
        LOGGER.Log("  Z2d: " + Z2d);
        LOGGER.Log("  sum: " + Z3d);
        LOGGER.Log("  mul: " + Z4d);

        LOGGER.Log("");
    }

    public static void Task_3() {
        LOGGER.Log("Task 3 >>>");

        int N = 20;
        short s = MathHelper.factorialS(N);
        int i = MathHelper.factorialI(N);
        long l = MathHelper.factorialL(N);
        
        LOGGER.Log("20! = 2432902008176640000");
        LOGGER.Log("  short: " + s);
        LOGGER.Log("  int  : " + i);
        LOGGER.Log("  long : " + l);

        LOGGER.Log("");
    }

    public static void Task_4() {
        LOGGER.Log("Task 4 >>>");

        LOGGER.Log("Number e for:");
        LOGGER.Log("  N=5 : " + MathHelper.euler(5));
        LOGGER.Log("  N=7 : " + MathHelper.euler(7));
        LOGGER.Log("  N=10: " + MathHelper.euler(10));
        LOGGER.Log("  N=15: " + MathHelper.euler(15));
        LOGGER.Log("  N=20: " + MathHelper.euler(20));
        LOGGER.Log("  Math: " + Math.E);

        LOGGER.Log("");
    }

}
