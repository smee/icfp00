package model;

import scene.Shape;

public class GmlIdentifier extends Token {

    final String id;
    public GmlIdentifier(String id){
        this.id=id;
    }
    @Override
    public String toString() {
        return id;
    }
    @Override
    public void evalToken(GmlStack stack, GmlEnvironment env){
        TokenGroup tg = env.getBinding(this);
        if(tg instanceof Shape)
            stack.push(((Shape)tg).copy());
        else
            stack.push(tg);
    }
    @Override
    public boolean equals(Object obj) {
        return id.equals(((GmlIdentifier)obj).id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
