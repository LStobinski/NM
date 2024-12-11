package NM.classes;

import java.util.function.Function;

@FunctionalInterface
public interface DerivativeFunction<R> {
    R apply(Function<Double, Double> fun, double x, double h);
}
