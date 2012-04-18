package scene;

import model.GmlFunction;
import model.GmlInteger;
import model.GmlReal;
import model.GmlStack;
import render.Hit;
import render.LightParameters;
import render.Ray;

public class Plane extends Shape {

    public Plane(GmlFunction surface) {
        super(surface);
    }
    public Plane(Plane o){
        super(o);
    }


    @Override
    public LightParameters getColorAt(Triple intersection) {

        GmlStack s = new GmlStack();
        s.push(new GmlInteger(0));
        s.push(new GmlReal(intersection.x));
        s.push(new GmlReal(intersection.z));
        surface.executeFunction(s);
        return new LightParameters(s.toArray());

    }

    @Override
    protected Hit getShapeIntersection(Ray ray) {
    	if(this==ray.getOriginShape())
    		return null;
    	Triple origin = ray.getOrigin();
    	Triple direction = ray.getDirection();
    	
    	if (direction.y==0)//parallel
            return null;
        double t = -origin.y/direction.y;
        if (t<=0)
          return null;

        return new Hit(this,
                new Triple(origin.x + direction.x*t, origin.y + direction.y*t, origin.z + direction.z*t),
                new Triple(0,origin.y>0?-1.0:1.0,0));
    }



    @Override
    public Shape copy() {
        return new Plane(this);
    }

}
