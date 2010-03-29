package model;


public class TokenArray extends TokenGroup{
    private static final boolean DEBUG = false;
    TokenList arr;
    public TokenArray(TokenList l){
        this.arr=l;
    }
    public TokenGroup get(int idx){
        return arr.getGroups().get(idx);
    }
    public int length() {
        return arr.getGroups().size();
    }
    @Override
    public void evalToken(GmlStack stack, GmlEnvironment env){
        if(DEBUG) System.out.println("Array.evalToken: "+this);
        GmlStack s = new GmlStack();

        for (TokenGroup tg : arr.getGroups()) {
            tg.evalToken(s, env);
        }
        arr=new TokenList();
        arr.addAll(s.stack);

        stack.push(this);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(TokenGroup tg:arr.getGroups())
            sb.append(tg).append(" ");
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }
}
