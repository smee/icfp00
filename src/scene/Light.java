package scene;

import model.Token;

public class Light extends Token {
    public enum Type{
        light, pointlight,spotlight
    }

    public Type type;
    public Triple dir;
    public Triple color;

    public Light(Type t,Triple dir, Triple color){
        this.type=t;
        this.dir = dir.normalize();
        this.color=color;
    }
    @Override
    public String toString() {
        return type.name()+" "+dir+" "+color;
    }
}
