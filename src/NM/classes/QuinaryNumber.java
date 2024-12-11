package NM.classes;

public class QuinaryNumber {

    private final byte sign;
    private final byte[] digits;

    public static byte BASE = 5;
    public static byte SIZE = 15;

    public QuinaryNumber(long number) {
        this.sign = (byte)Long.signum(number);
        this.digits = new byte[SIZE];
        for (int i = 0; i < SIZE; i++) {
            digits[i] = (byte) (number % BASE);
            number /= BASE;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (sign < 0) builder.append('-');
        boolean skip = true;
        for (int i = SIZE - 1; i >= 0; i--) {
            var d = digits[i];
            if (skip && d == 0)
                continue;
            builder.append(d);
            skip = false;
        }
        return builder.toString();
    }

    public long toLong() {
        if (sign == 0)
            return 0;

        long l = 0;
        int d = 1;
        for (int i = 0; i < SIZE; i++) {
            l += (long) digits[i] * d;
            d *= BASE;
        }
        l *= sign;
        return l;
    }
}