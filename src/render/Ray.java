package render;

import scene.Shape;
import scene.Triple;

public class Ray {
	final Triple origin,direction;
	final Shape originShape;
	public Ray(Triple origin, Triple direction, Shape originShape) {
		super();
		this.origin = origin;
		this.direction = direction;
		this.originShape = originShape;
	}
	/**
	 * @return the origin
	 */
	public Triple getOrigin() {
		return origin;
	}
	/**
	 * @return the direction
	 */
	public Triple getDirection() {
		return direction;
	}
	/**
	 * @return the originShape
	 */
	public Shape getOriginShape() {
		return originShape;
	}
}
