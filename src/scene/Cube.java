package scene;

import java.util.HashMap;

import model.GmlFunction;
import model.GmlInteger;
import model.GmlReal;
import model.GmlStack;
import render.Hit;
import render.LightParameters;
import render.Ray;

public class Cube extends Shape {

	private static HashMap<Triple, Integer> faces = new HashMap<Triple, Integer>(){{
		put(new Triple(-1, 0, 0), 2);//left
		put(new Triple( 1, 0, 0), 3);//right	
		put(new Triple( 0,-1, 0), 5);//bottom	
		put(new Triple( 0, 1, 0), 4);//top	
		put(new Triple( 0, 0,-1), 0);//front	
		put(new Triple( 0, 0, 1), 1);//back	
	}};
	public Cube(GmlFunction surface) {
		super(surface);
		// 0,0,0 to 1,1,1
	}

	public Cube(Cube cube) {
		super(cube);
	}

	@Override
	public Shape copy() {
		return new Cube(this);
	}

	private Triple calculateNormalOfHit(Triple intersection){
    	if(intersection.x == 0){
    		return new Triple(-1, 0, 0);//left
    	}else if(intersection.x == 1){
    		return new Triple( 1, 0, 0);//right
    	}else if(intersection.y == 0){
    		return new Triple( 0,-1, 0);//bottom
    	}else if(intersection.y == 1){
    		return new Triple( 0, 1, 0);//top
    	}else if(intersection.z == 0){
    		return new Triple( 0, 0,-1);//front
    	}else{
    		return new Triple( 0, 0, 1);//back
    	}
	}
	@Override
	public LightParameters getColorAt(Triple intersection) {
    	if(transform != null)
    		intersection=transform.inverseTriple(intersection);
        GmlStack s = new GmlStack();
        s.push(new GmlInteger(faces.get(calculateNormalOfHit(intersection))));
        s.push(new GmlReal(intersection.x));
        s.push(new GmlReal(intersection.y));
        surface.executeFunction(s);
        return new LightParameters(s.toArray());
	}

	@Override
	protected Hit getShapeIntersection(Ray ray) {
		Triple o = ray.getOrigin();
		Triple d = ray.getDirection();
		double tNear = Double.NEGATIVE_INFINITY;
		double tFar = Double.POSITIVE_INFINITY;
		
		// parallel to any cube face? no hit possible
		if (d.x == 0) {
			if (o.x < 0 || o.x > 1) {
				return null;
			}
		} else if (d.y == 0) {
			if (o.y < 0 || o.y > 1) {
				return null;
			}
		} else if (d.z == 0) {
			if (o.z < 0 || o.z > 1) {
				return null;
			}
		} else{
			//x
			double tMin = (0-o.x)/d.x;
			double tMax = (1-o.x)/d.x;
			if(tMax < tMin){
				double temp = tMax;
				tMax = tMin;
				tMin = temp;
			}
			tNear = Math.max(tNear, tMin);
			tFar  = Math.min(tFar,  tMax);
			if(tNear>tFar || tFar<0){
				return null;
			}
			//y
			tMin = (0-o.y)/d.y;
			tMax = (1-o.y)/d.y;
			if(tMax < tMin){
				double temp = tMax;
				tMax = tMin;
				tMin = temp;
			}
			tNear = Math.max(tNear, tMin);
			tFar  = Math.min(tFar,  tMax);
			if(tNear>tFar || tFar<0){
				return null;
			}
			//z
			tMin = (0-o.z)/d.z;
			tMax = (1-o.z)/d.z;
			if(tMax < tMin){
				double temp = tMax;
				tMax = tMin;
				tMin = temp;
			}
			tNear = Math.max(tNear, tMin);
			tFar  = Math.min(tFar,  tMax);
			if(tNear>tFar || tFar<0){
				return null;
			}
			Triple intersection = o.add(d.mul(tNear));
			// make sure that at least one coordinate is zero
			intersection = new Triple(valueOrZero(intersection.x),
					valueOrZero(intersection.y),
					valueOrZero(intersection.z));
			return new Hit(this, intersection, calculateNormalOfHit(intersection));//FIXME totally wrong
		}
		return null;
	}

	private double valueOrZero(double v) {
		if(v<1e-6 && v>-1e-6){
			return 0;
		}
		return v;
	}

}
