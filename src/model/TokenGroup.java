package model;

public abstract class TokenGroup {

    public void evalToken(GmlStack stack, GmlEnvironment env){
        stack.push(this);
    }



}
