package render;

import model.GmlReal;
import scene.Triple;

public class LightParameters {

    final public Triple color;
    final public double kDiffuse,kSpecular,phongExp;

    public LightParameters(Object[] array) {
        double x=((Triple)array[0]).x;
        double y=((Triple)array[0]).y;
        double z=((Triple)array[0]).z;
        if(x>1)
            x=1;
        if(x<0)
            x=0;
        if(y>1)
            y=1;
        if(y<0)
            y=0;
        if(z>1)
            z=1;
        if(z<0)
            z=0;
        this.color=new Triple(x,y,z);

        kDiffuse=((GmlReal)array[1]).i;
        kSpecular=((GmlReal)array[2]).i;
        phongExp=((GmlReal)array[3]).i;
    }
    @Override
    public String toString() {
        return "col="+color+", kd="+kDiffuse+", ks="+kSpecular+", phong="+phongExp;
    }
}
