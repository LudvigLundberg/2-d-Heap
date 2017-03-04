package alda.adv;

public interface DoubleKeyInterface<T extends Comparable<T>, E extends Comparable<E>> {
    T getFirstKey();

    E getSecondKey();

    int compareFirstKey(DoubleKeyInterface other);
    int compareSecondKey(DoubleKeyInterface other);
}
