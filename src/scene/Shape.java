package scene;

import model.GmlFunction;
import model.Token;
import render.Hit;
import render.LightParameters;
import render.Ray;

public abstract class Shape extends Token implements Cloneable{

    GmlFunction surface;
    Transform transform;

    public Shape(GmlFunction surface){
        this.surface=surface;
    }
    public Shape(Shape o){
        this.surface=o.surface;
        if(o.transform==null)
            this.transform=null;
        else
            this.transform=Transform.getIdentity().apply(o.transform);//copy
    }
    public Shape(){
        this.surface=null;
        this.transform=null;
    }

    public void applyTransform(Transform t){
        if(transform == null)
            transform = t;
        else
            transform=t.apply(transform);
    }
    public abstract Shape copy();
    @Override
    public String toString() {
        return getClass().getName()+": ( surface= +surface+ )";
    }
    public Hit getIntersection(Ray ray){
    	if(ray.getOriginShape()==this)
    		return null;
        if(transform!=null){
        	ray=new Ray(transform.inverseTriple(ray.getOrigin()),
            transform.inverseVector(ray.getDirection()).normalize(),ray.getOriginShape());
        }

        Hit h = getShapeIntersection(ray);
        if(h == null || h.getShape()==ray.getOriginShape())
            return null;
        if(transform==null)
            return h;
        else
        return new Hit(h.getShape(),
                transform.applyTriple(h.getHitPoint()),
                transform.applyVector(h.getNormal()));
    }

    abstract protected Hit getShapeIntersection(Ray ray);

    abstract public LightParameters getColorAt(Triple intersection);
}
