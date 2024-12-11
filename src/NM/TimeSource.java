package NM;

import java.util.concurrent.TimeUnit;
import java.util.function.LongSupplier;

@FunctionalInterface
public interface TimeSource {
    long get(TimeUnit unit);

    public interface NanoTimeSource extends TimeSource, LongSupplier {
        default long get(TimeUnit unit) {
            return unit.convert(this.getAsLong(), TimeUnit.NANOSECONDS);
        }
    }
}
