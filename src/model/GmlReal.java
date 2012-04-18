package model;

public class GmlReal extends Token {

    public final double i;

    public GmlReal(double i){ this.i = i; }

    @Override
    public String toString() {
        return ""+i;
    }
}