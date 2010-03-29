package scene;

import model.GmlFunction;
import render.Hit;
import render.LightParameters;
import render.Ray;

public class Union extends Shape {

  private Shape shape1;
  private Shape shape2;

  public Union(final GmlFunction surface) {
    super(surface);
    throw new UnsupportedOperationException();
  }

  public Union(final Shape sh1, final Shape sh2) {
    super();
    this.shape1 = sh1.copy();
    this.shape2 = sh2.copy();
  }
  @Override
  public void applyTransform(final Transform t) {
    shape1.applyTransform(t);
    shape2.applyTransform(t);
  }

  @Override
  public Shape copy() {
    final Union other = new Union(shape1, shape2);
    if (transform != null) {
      other.applyTransform(transform);
    }
    return other;
  }

  @Override
  public LightParameters getColorAt(final Triple intersection) {
    throw new UnsupportedOperationException();
  }
  @Override
  public String toString() {
    return "union{" + shape1 + "," + shape2 + "}";
  }

  @Override
  protected Hit getShapeIntersection(final Ray ray) {

    final Hit tr1 = shape1.getIntersection(ray);
    final Hit tr2 = shape2.getIntersection(ray);
    Hit result = null;

    if ((tr1 == null) && (tr2 == null)) {
      return null;
    }

    result = VectorMath.closestHit(tr1, tr2, ray.getOrigin());

    return result;
  }

}
