package model;

public class GmlOperator extends TokenGroup {

    final private Operator op;

    public GmlOperator(Operator op){
        this.op=op;
    }
    @Override
    public void evalToken(GmlStack stack, GmlEnvironment env){
        op.execute(stack, env);
    }
    @Override
    public String toString() {
        return op.name();
    }
}
