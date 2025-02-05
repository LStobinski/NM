package NM.chapters;

import NM.Logger;
import NM.classes.MathHelper;
import NM.classes.Pair;

import java.util.List;
import java.util.function.Function;

public class Chapter6 {
	static Logger LOGGER = Logger.Get("C6");

	public static void Run() {
		LOGGER.Log("Chapter VI: Numerical Derivatives");
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

		//...

		LOGGER.Log("");
	}

	public static void Task_2() {
		LOGGER.Log("Task 2 >>>");

		Function<Double, Double> fun = x -> 3 * x * x * x - 2 * x * x + 1;
		double x = 0.75;
		double[] H = {
				0.1,
				0.01,
				0.001
		};
		List<Pair<String, Derivative>> DRV = List.of(
				Pair.of("2PF", Derivative.DRV1_2PF),
				Pair.of("2PC", Derivative.DRV1_2PC),
				Pair.of("3PF", Derivative.DRV1_3PF)
		);
		double exact = 2.0625;

		for (var h : H) {
			LOGGER.Log("h = " + h);
			for (var drv : DRV) {
				var name = drv.getFirst();
				var value = drv.getSecond().apply(fun, x, h);
				LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value));
			}
		}

		LOGGER.Log("");
	}

	public static void Task_3() {
		LOGGER.Log("Task 3 >>>");

		Function<Double, Double> fun = x -> x * Math.sin(x * x) + 1;
		double x = 1;
		double[] H = {
				0.1,
				0.01,
				0.001
		};
		List<Pair<String, Derivative>> DRV = List.of(
				Pair.of("2PF", Derivative.DRV1_2PF),
				Pair.of("2PC", Derivative.DRV1_2PC)
		);
		double exact = 1.92207559654417594145437553651;

		for (var h : H) {
			LOGGER.Log("h = " + h);
			for (var drv : DRV) {
				var name = drv.getFirst();
				var value = drv.getSecond().apply(fun, x, h);
				LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value));
			}
		}

		LOGGER.Log("");
	}

	public static void Task_4() {
		LOGGER.Log("Task 4 >>>");

		Function<Double, Double> fun = Math::exp;
		double x = 0;
		double[] H = {
				0.1,
				0.01,
				0.001
		};
		List<Pair<String, Derivative>> DRV = List.of(
				Pair.of("2PF", Derivative.DRV1_2PF),
				Pair.of("2PC", Derivative.DRV1_2PC),
				Pair.of("3PF", Derivative.DRV1_3PF)
		);
		double exact = 1.0;

		for (var h : H) {
			LOGGER.Log("h = " + h);
			for (var drv : DRV) {
				var name = drv.getFirst();
				var value = drv.getSecond().apply(fun, x, h);
				LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value));
			}
		}

		LOGGER.Log("");
	}

	public static void Task_5() {
		LOGGER.Log("Task 5 >>>");

		Function<Double, Double> fun = x -> 3 * x * x * x - 2 * x * x + 1;
		double x = 0.75;
		double[] H = {
				0.1,
				0.01,
				0.001
		};
		List<Pair<String, Derivative>> DRV = List.of(
				Pair.of("3PF", Derivative.DRV2_3PF),
				Pair.of("3PC", Derivative.DRV2_3PC)
		);
		double exact = 9.5;

		for (var h : H) {
			LOGGER.Log("h = " + h);
			for (var drv : DRV) {
				var name = drv.getFirst();
				var value = drv.getSecond().apply(fun, x, h);
				LOGGER.Log(" -> " + name + ": " + value + " | error: " + (exact - value));
			}
		}

		LOGGER.Log("");
	}

	public static void Task_6() {
		LOGGER.Log("Task 6 >>>");

		Function<Double, Double> fun = Math::exp;
		double[] X = {0, 1};
		double[] H = {0.1, 0.0001, 0.0000001};
		List<Pair<String, Derivative>> DRV = List.of(
				Pair.of("3PF", Derivative.DRV2_3PF),
				Pair.of("3PC", Derivative.DRV2_3PC)
		);

		for (var x : X) {
			for (var h : H) {
				LOGGER.Log("x = " + x + ", h = " + h);
				for (var drv : DRV) {
					var name = drv.getFirst();
					var value = drv.getSecond().apply(fun, x, h);
					LOGGER.Log(" -> " + name + ": " + value + " | error: " + ((x == 0 ? 1 : Math.E) - value));
				}
			}
		}

		LOGGER.Log("");
	}

	@FunctionalInterface
	public interface Derivative {
		double apply(Function<Double, Double> f, double x, double h);

		Derivative DRV1_2PF = (f, x, h) ->
				(f.apply(x + h) - f.apply(x)) / h;

		Derivative DRV1_2PC = (f, x, h) ->
				(f.apply(x + h) - f.apply(x - h)) / (2 * h);

		Derivative DRV1_3PF = (f, x, h) ->
				((4 * f.apply(x + h)) - (3 * f.apply(x)) - (f.apply(x + 2 * h))) / (2 * h);

		Derivative DRV2_3PF = (f, x, h) ->
				(f.apply(x) - (2 * f.apply(x + h)) + f.apply(x + 2 * h)) / (h * h);

		Derivative DRV2_3PC = (f, x, h) ->
				(f.apply(x + h) - (2 * f.apply(x)) + f.apply(x - h)) / (h * h);
	}

}
