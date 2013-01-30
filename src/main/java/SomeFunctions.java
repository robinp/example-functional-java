import com.google.common.base.Function;
import com.google.common.base.Predicate;

import java.util.concurrent.atomic.AtomicInteger;

public class SomeFunctions {

    public static final Increment INCREMENT = new Increment();

    public static final IsEven IS_EVEN = new IsEven();

    public static final <A> CountingIdentityTransform<A> countingIdentityTransform(AtomicInteger counter) {
        return new CountingIdentityTransform<A>(counter);
    }

    public static final StringLength STRING_LENGTH = new StringLength();

    private SomeFunctions() {
        // can't instantiate
    }

    static class Increment implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer input) {
            return input + 1;
        }
    }

    static class IsEven implements Predicate<Integer> {
        @Override
        public boolean apply(Integer input) {
            return input % 2 == 0;
        }
    }


    static class CountingIdentityTransform<A> implements Function<A, A> {
        private final AtomicInteger counter;

        public CountingIdentityTransform(AtomicInteger counter) {
            this.counter = counter;
        }

        @Override
        public A apply(A input) {
            counter.incrementAndGet(); // a kitten cries for each mutation
            return input;
        }
    }

    static class StringLength implements Function<String, Integer> {
        @Override
        public Integer apply(String input) {
            return input.length();
        }
    }
}
