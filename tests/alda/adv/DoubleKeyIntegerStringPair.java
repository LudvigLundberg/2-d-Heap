package alda.adv;

public class DoubleKeyIntegerStringPair implements DoubleKeyInterface<Integer, String>{
    private Integer first;
    private String second;

    public DoubleKeyIntegerStringPair(int first, String second){
        this.first = first;
        this.second = second;
    }

    @Override
    public Integer getFirstKey() {
        return first;
    }

    @Override
    public String getSecondKey() {
        return second;
    }

    @Override
    public int compareFirstKey(DoubleKeyInterface other) {
        return this.first.compareTo(((DoubleKeyIntegerStringPair)other).first);
    }

    @Override
    public int compareSecondKey(DoubleKeyInterface other) {
        return this.second.compareTo(((DoubleKeyIntegerStringPair)other).second);
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof DoubleKeyIntegerStringPair){
            return first.equals(((DoubleKeyIntegerStringPair) other).first) && second.equals(((DoubleKeyIntegerStringPair) other).second);
        }
        return false;
    }
}
