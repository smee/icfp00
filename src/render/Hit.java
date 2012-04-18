package render;

import scene.Shape;
import scene.Triple;

public class Hit {
    private final Shape shape;
    private final Triple hitPoint;
    private final Triple normal;

    public Hit(Shape s, Triple hitPoint,Triple normal){
        this.shape=s;
        this.hitPoint=hitPoint;
        this.normal=normal.normalize();
    }

    public Triple getHitPoint() {
        return hitPoint;
    }

    public LightParameters getColor() {
        return shape.getColorAt(hitPoint);
    }

    public Shape getShape() {
        return shape;
    }

    public Triple getNormal() {
        return normal;
    }

}
