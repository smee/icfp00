package model;

public class GmlBoolean extends Token {

    final boolean b;

    public GmlBoolean(boolean b){ this.b = b; }

    @Override
    public String toString() {
        return ""+b;
    }

}
