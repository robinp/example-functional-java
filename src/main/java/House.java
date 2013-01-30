import com.google.common.base.Function;
import com.google.common.base.Predicate;

import static org.funcito.FuncitoGuava.callsTo;
import static org.funcito.FuncitoGuava.functionFor;
import static org.funcito.FuncitoGuava.predicateFor;

public class House {

    private static House CALLS_TO = callsTo(House.class);
    public static final Function<House, String> OWNER_NAME = functionFor(CALLS_TO.getOwnerName());
    public static final Function<House, Integer> WINDOW_COUNT = functionFor(CALLS_TO.getWindowCount());
    public static final Predicate<House> IS_NICE = predicateFor(CALLS_TO.isNice());

    private final String ownerName;
    private final int windowCount;
    private final boolean nice;

    public House(String ownerName, int windowCount, boolean nice) {
        this.ownerName = ownerName;
        this.windowCount = windowCount;
        this.nice = nice;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getWindowCount() {
        return windowCount;
    }

    public boolean isNice() {
        return nice;
    }
}
