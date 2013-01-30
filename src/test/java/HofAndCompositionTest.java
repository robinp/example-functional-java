import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.fest.assertions.Assertions.assertThat;

public class HofAndCompositionTest {

    private final List<Integer> elems = Lists.newArrayList(1, 2, 3);

    @Test
    public void testListTransform() throws Exception {
        // WHEN
        final List<Integer> result = Lists.transform(elems, SomeFunctions.INCREMENT);
        // THEN
        assertThat(result).containsSequence(2, 3, 4);
    }

    @Test
    public void testIterableFilter() throws Exception {
        // WHEN
        final Iterable<Integer> result = Iterables.filter(elems, SomeFunctions.IS_EVEN);
        // THEN
        assertThat(result).containsOnly(2);
    }

    @Test
    public void testWhyNoListFilter() throws Exception {
        // GIVEN
        final AtomicInteger counter = new AtomicInteger(0);
        // WHEN
        final List<Integer> result = Lists.transform(elems, SomeFunctions.<Integer>countingIdentityTransform(counter));
        final int a = result.get(0);
        final int b = result.get(0);
        // THEN
        assertThat(counter.get()).isEqualTo(2);
    }

    @Test
    public void testWhatIfIDontWantAView() throws Exception {
        // WHEN
        final List<Integer> result = Lists.newArrayList(Iterables.filter(elems, SomeFunctions.IS_EVEN));
        // THEN
        assertThat(result).containsExactly(2);
    }

    @Test
    public void testComposition() throws Exception {
        // GIVEN
        final List<String> words = Lists.newArrayList("apple", "pear", "pie");
        // AND the task to keep only the words whose length + 1 is even
        final Predicate<String> predicate = Predicates.compose(
                SomeFunctions.IS_EVEN,
                Functions.compose(SomeFunctions.INCREMENT, SomeFunctions.STRING_LENGTH));
        // WHEN
        final Iterable<String> result = Iterables.filter(words, predicate);
        // THEN
        assertThat(result).containsOnly("apple", "pie");
    }
}
