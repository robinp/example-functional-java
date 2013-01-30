import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class FuncitoTest {

    // GIVEN
    final List<House> houses = Lists.newArrayList(
            new House("I",  2, true),
            new House("Me", 4, true),
            new House("My", 5, false));

    @Test
    public void testFuncitoFuns() throws Exception {
        // WHEN
        final int result = sum(Iterables.transform(
                Iterables.filter(houses, House.IS_NICE),
                House.WINDOW_COUNT));
        // THEN
        assertThat(result).isEqualTo(6);
    }

    private int sum(Iterable<Integer> elems) {
        int accum = 0;
        final Iterator<Integer> iterator = elems.iterator();
        while (iterator.hasNext()) {
            accum += iterator.next();
        }
        return accum;
    }
}
