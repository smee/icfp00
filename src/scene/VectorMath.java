package scene;

import static java.lang.Math.*;
import render.Hit;

public class VectorMath {

    public static Hit closestHit(Hit tr1, Hit tr2, Triple o) {
        if(tr1==null)
            return tr2;
        if(tr2==null)
            return tr1;
        Triple t1=tr1.getHitPoint();
        Triple t2=tr2.getHitPoint();

        if(sqrt(pow(t1.x-o.x,2) + pow(t1.y-o.y,2) + pow(t1.z-o.z,2))
           < sqrt(pow(t2.x-o.x,2) + pow(t2.y-o.y,2) + pow(t2.z-o.z,2)))
            return tr1;
        return tr2;
    }

}
