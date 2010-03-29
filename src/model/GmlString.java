package model;

public class GmlString extends Token {

    final String s;

    public GmlString(String s){ this.s=s; }

    @Override
    public String toString() {
        return "\""+s+"\"";
    }
}
