package NM;

public class Profiler {
    public static TimeSource.NanoTimeSource timeSource;

    public static long getMillis() {
        return getNanos() / 1000000L;
    }

    public static long getNanos() {
        return timeSource.getAsLong();
    }

    public static double differenceMillis(long from, long to) {
        return (double) (to - from) / 1000000d;
    }

    static {
        timeSource = System::nanoTime;
    }
}
