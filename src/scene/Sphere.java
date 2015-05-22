package scene;

import static java.lang.Math.*;
import model.GmlFunction;
import model.GmlInteger;
import model.GmlReal;
import model.GmlStack;
import render.Hit;
import render.LightParameters;
import render.Ray;

public class Sphere extends Shape {

    public Sphere(GmlFunction surface) {
        super(surface);
        //radius 1, center at 0,0,0
    }
    public Sphere(Sphere o){
        super(o);
    }
    /*
     * A ray is defined by: R(t) = R0 + t * Rd , t > 0 with R0 = [X0, Y0, Z0] and Rd = [Xd, Yd, Zd]

A sphere can be defined by its center and radius with Sc = [xc, yc, zc] So a sphere of radius Sr is:


S = the set of points[xs, ys, zs], where (xs - xc)2 + (ys - yc)2 + (zs - zc)2 = Sr2

To solve algebraically, substitute the ray equation into sphere equation and solve for t.

For ray:
X = X0 + Xd * t
Y = Y0 + Yd * t
Z = Z0 + Zd * t

putting X, Y, Z into the sphere equation for Xs, Ys, Zs

(X0 + Xd * t - Xc)2 + (Y0 + Yd * t - Yc)2 + (Z0 + Zd * t - Zc)2 = Sr^2

or A*t^2 + B*t + C = 0
with: A = Xd^2 + Yd^2 + Zd^2
B = 2 * (Xd * (X0 - Xc) + Yd * (Y0 - Yc) + Zd * (Z0 - Zc))
C = (X0 - Xc)^2 + (Y0 - Yc)^2 + (Z0 - Zc)^2 - Sr^2

Note: If |Rd| = 1 (normalized), then A = 1. So we can compute Sr^2 once.
So with A = 1, the solution of the quadratic equation is

t0, t1 = (- B + (B^2 - 4*C)^1/2) / 2 where t0 is for (-) and t1 is for (+)

If the discriminant is < 0.0 then there is no real root and no intersection. If there is a real root (Disc. > = 0.0) then the smaller positive root is the closest intersection point. So we can just compute t0 and if it is positive, then we are done, else compute t1. The intersection point is:

Ri = [xi, yi, zi] = [x0 + xd * ti ,  y0 + yd * ti,  z0 + zd * ti]

Unit N at surface
 SN = [(xi - xc)/Sr,   (yi - yc)/Sr,   (zi - zc)/Sr]

Note: For code optimization, "*" is faster than "/" so may compute 1/Sr and multiply. Look at the algorithm analysis:

Summary of steps:
compute A, B, C: 7 (*), 8 (+/-)
compute discriminant: 2 (*), 1 compare
compute t0 and determine if positive: 1 (-), 1 (*) /(/), 1 sqrt, 1 compare
possibly compute t1 and determine if positive: 1 (-), 1 (*), 1 compare
compute intersection point: 3 (*), 3 (+)
compute SN: 3 (-), 3 (*)
Total worst case = 17 (+/-), 17 (*), 1 sqrt, 3 compares.
Best case = steps 1, 2 = 9 (+/-), 9 (*), 1 compare.
Best case hit: 1, 2, 3, 5, 6 --> 16 (*), 16 (+/-), 1 sqrt, 3 compare.(non-Javadoc)
     * @see scene.Shape#getIntersection(scene.Ray)
     */
    @Override
    protected Hit getShapeIntersection(Ray ray) {
    	Triple origin = ray.getOrigin();
    	Triple direction = ray.getDirection();
    	
        double B= 2*(direction.x*origin.x + direction.y*origin.y + direction.z*origin.z);
        double C= origin.x*origin.x + origin.y*origin.y + origin.z*origin.z -1.0;
        double underRoot = B*B-4*C;
        if(underRoot > 0){
            double useT=(-B-sqrt(underRoot))/2.0;
            if(useT < 0)
                useT=(-B+sqrt(underRoot))/2.0;

            if(useT <= 0)
                return null;

            double hitX=origin.x+direction.x*useT;
            double hitY=origin.y+direction.y*useT;
            double hitZ=origin.z+direction.z*useT;

            return new Hit(this,
                    new Triple(hitX,hitY,hitZ),
                    new Triple(-hitX,-hitY,-hitZ));//kugel ist standard,
        }else
            return null;
    }
    @Override
    public LightParameters getColorAt(Triple intersection){
    	//TODO there is a bug, see the coloring of spheres in foo.gml
    	if(transform != null)
    		intersection=transform.inverseTriple(intersection);
        GmlStack s = new GmlStack();
        s.push(new GmlInteger(0));
        s.push(new GmlReal(atan2in1(intersection.x,intersection.z)));
        s.push(new GmlReal((intersection.y+1)/2));
        surface.executeFunction(s);
        return new LightParameters(s.toArray());
    }
    private double atan2in1(double a, double b) {
        double r = Math.toDegrees(Math.atan2(a,b))/360.0;
        return r<0 ? r+1.0 : r;
      }

    @Override
    public Shape copy() {
        return new Sphere(this);
    }
}
