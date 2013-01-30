import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionalTest {

    @Test
    public void testTraditionalNullTest() throws Exception {
        // GIVEN
        final String maybeNull = null;
        // WHEN
        final int result;
        if (maybeNull != null) {
            result = maybeNull.length();
        } else {
            result = -1;
        }
    }

    @Test
    public void testOptionalTest() throws Exception {
        // GIVEN
        final Optional<String> maybeEmpty = Optional.absent();
        // WHEN
        final int result;
        if (maybeEmpty.isPresent()) {
            result = maybeEmpty.get().length();
        }
        else {
            result = -1;
        }
    }

    @Test
    public void testOptionalTransform() throws Exception {
        // GIVEN
        final Optional<String> maybeEmpty = Optional.of("apple");
        // WHEN
        final Optional<Integer> maybeLength = maybeEmpty.transform(SomeFunctions.STRING_LENGTH);
        // THEN
        assertEquals(Optional.of(5), maybeLength);
    }

    @Test
    public void testSafeFind() throws Exception {
        // GIVEN
        final List<Integer> elems = Lists.newArrayList(1, 3);
        // WHEN
        final Optional<Integer> firstEven = Iterables.tryFind(elems, SomeFunctions.IS_EVEN);
        final Optional<Integer> firstOdd = Iterables.tryFind(elems, Predicates.not(SomeFunctions.IS_EVEN));
        // THEN
        assertFalse(firstEven.isPresent());
        assertTrue(firstOdd.isPresent());
        assertEquals((Integer) 1, firstOdd.get());
    }
}
