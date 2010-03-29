package model;

public class GmlInteger extends Token {

    public final int i;

    public GmlInteger(int i){ this.i = i; }

    @Override
    public String toString() {
        return ""+i;
    }
}
