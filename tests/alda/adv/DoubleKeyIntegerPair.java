package alda.adv;

public class DoubleKeyIntegerPair implements DoubleKeyInterface<Integer, Integer> {

    private Integer first;
    private Integer second;

    public DoubleKeyIntegerPair(int first, int second){
        this.first = first;
        this.second = second;
    }

    @Override
    public Integer getFirstKey() {
        return first;
    }

    @Override
    public Integer getSecondKey() {
        return second;
    }

    @Override
    public int compareFirstKey(DoubleKeyInterface other) {
         return this.first.compareTo(((DoubleKeyIntegerPair)other).first);
    }

    @Override
    public int compareSecondKey(DoubleKeyInterface other) {
        return this.second.compareTo(((DoubleKeyIntegerPair)other).second);
    }
}
