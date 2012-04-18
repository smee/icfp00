package scene;

import model.GmlFunction;
import render.Hit;
import render.LightParameters;
import render.Ray;

public class Cylinder extends Shape {

    public Cylinder(GmlFunction surface) {
        super(surface);
        //radius 1, center at 0,0,0 top at 0,1,0
    }

	@Override
	public Shape copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LightParameters getColorAt(Triple intersection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Hit getShapeIntersection(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}


}
