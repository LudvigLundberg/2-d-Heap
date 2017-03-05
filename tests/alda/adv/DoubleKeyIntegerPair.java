package alda.adv;

import java.util.Objects;

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
    public boolean equals(Object other){
        if(other instanceof DoubleKeyIntegerPair){
            return first.equals(((DoubleKeyIntegerPair) other).first) && second.equals(((DoubleKeyIntegerPair) other).second);
        }
        return false;
    }

    @Override
    public int compareFirstKey(DoubleKeyInterface other) {
         return this.first.compareTo(((DoubleKeyIntegerPair)other).first);
    }

    @Override
    public int compareSecondKey(DoubleKeyInterface other) {
        return this.second.compareTo(((DoubleKeyIntegerPair)other).second);
    }

    @Override
    public String toString(){
        return "{" +first.toString() + ", " + second.toString() + "}";
    }
}
