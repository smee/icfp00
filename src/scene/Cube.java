package scene;

import model.GmlFunction;
import render.Hit;
import render.LightParameters;
import render.Ray;

public class Cube extends Shape {

    public Cube(GmlFunction surface) {
        super(surface);
        // 0,0,0 to 1,1,1
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
