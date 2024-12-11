package NM;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Logger {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String name;

    public static Logger Get(String name) {
        return new Logger(name);
    }

    private Logger(String name) {
        this.name = name;
    }

    public void Log(Object... objects) {
        for (var o : objects)
            Log(o);
    }

    public void Log(List<?> list) {
        for (var o : list)
            Log(o);
    }

    public void Log(Object o) {
        Log(String.valueOf(o));
    }

    public void Log(String str) {
        System.out.printf("[%s][%s]: %s%n", LocalTime.now().format(format), name, str);
    }

}
