package model;

import java.util.HashMap;

public class GmlEnvironment {

    private HashMap<GmlIdentifier, TokenGroup> prop;

    public GmlEnvironment(){
        this.prop=new HashMap<GmlIdentifier, TokenGroup>();
    }

    public GmlEnvironment(GmlEnvironment parent){
        this.prop=new HashMap<GmlIdentifier, TokenGroup>();
        this.prop.putAll(parent.prop);
    }

    public void addBinding(GmlIdentifier id, TokenGroup t){
        prop.put(id, t);
    }

    @Override
    public String toString() {
        return prop.toString();
    }

    public TokenGroup getBinding(GmlIdentifier id) {
        return (TokenGroup) prop.get(id);
    }
}
