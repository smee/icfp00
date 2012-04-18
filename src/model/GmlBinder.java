package model;


public class GmlBinder extends Token {

    final private GmlIdentifier id;
    public GmlBinder(GmlIdentifier id){
        this.id=id;
    }
    public void evalToken(GmlStack stack, GmlEnvironment env) {
        TokenGroup t=stack.pop();
        env.addBinding(id, t);
    }
    public String toString(){
        return "/"+id;
    }

}
