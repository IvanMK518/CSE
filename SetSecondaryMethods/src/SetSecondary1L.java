import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        Set<T> s1 = s.newInstance();

        for (T temp : s) {

            if (this.contains(temp)) {
                this.remove(temp);
                s1.add(temp);
            }

        }

        return s1;

    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        int len = s.size();
        for (int i = 0; i < len; i++) {
            T temp = s.removeAny();
            if (!this.contains(temp)) {
                this.add(temp);

            } else {
                s.add(temp);
            }

        }

    }

}
