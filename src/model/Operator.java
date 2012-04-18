package model;

import render.Renderer;
import scene.Cone;
import scene.Cube;
import scene.Cylinder;
import scene.Light;
import scene.Plane;
import scene.Shape;
import scene.Sphere;
import scene.Transform;
import scene.Triple;
import scene.Union;

public enum Operator {
    acos("arc cosine function"),
    addi("integer addition"),
    addf("real addition"),
    apply("function application operator"),
    asin("arc sine function"),
    clampf("clamp the range of a real number"),
    cone("a unit cone"),
    cos("cosine function"),
    cube("a unit cube"),
    cylinder("a unit cylinder"),
    difference("difference of two solids"),
    divi("integer division"),
    divf("real division"),
    eqi("integer equality comparison"),
    eqf("real equality comparison"),
    floor("real to integer conversion"),
    frac("fractional part of real number"),
    get("get an array element"),
    getx("get x component of point"),
    gety("get y component of point"),
    getz("get z component of point"),
    If("conditional control operator"),
    intersect("intersection of two solids"),
    length("array length"),
    lessi("integer less-than comparison"),
    lessf("real less-than comparison"),
    light("defines a directional light source"),
    modi("integer remainder"),
    muli("integer multiplication"),
    mulf("real multiplication"),
    negi("integer negation"),
    negf("real negation"),
    plane("the plane shape"),
    point("create a point value"),
    pointlight("defines a point-light source"),
    print("print last stack value"),
    println("print last stack value"),
    real("convert an integer to a real number"),
    render("render a scene to a file"),
    rotatex("rotation around the"),
    rotatey("rotation around the"),
    rotatez("rotation around the"),
    scale("scaling transform"),
    sin("sine function"),
    sphere("a unit sphere"),
    spotlight("defines a spotlight source"),
    sqrt("square root"),
    subi("integer subtraction"),
    subf("real subtraction"),
    translate("translation transform"),
    union("union of two solids"),
    uscale("uniform scaling transform");

    final private String desc;
    private Operator(String desc){
        this.desc=desc;
    }
    public void execute(GmlStack s, GmlEnvironment env){
        Token tg1, tg2;
        GmlFunction fun,fun2;
        GmlInteger i1,i2;
        GmlReal r1,r2,r3;
        GmlBoolean b;
        TokenArray arr;
        Shape prim,prim2;
        Triple trip1, trip2;
        Light light;

        switch (this) {
        case acos:
            r1 = (GmlReal) s.pop();
            s.push(new GmlReal(Math.toDegrees(Math.acos(r1.i))));
            break;
        case addi:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlInteger(i1.i  + i2.i ));
            break;
        case addf:
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new GmlReal( r1.i  + r2.i ));
            break;
        case apply:
            fun=(GmlFunction) s.pop();
            fun.executeFunction(s);
            break;
        case asin:
            r1 = (GmlReal) s.pop();
            s.push(new GmlReal(Math.toDegrees(Math.asin(r1.i))));
            break;
        case clampf:
            r1 = (GmlReal) s.pop();
            if(r1.i < 0)
                r1=new GmlReal(0);
            else if(r1.i > 1)
                r1=new GmlReal(1);
            s.push(r1);
            break;
        case cone:
            fun = (GmlFunction) s.pop();
            prim = new Cone(fun);
            s.push(prim);
            break;
        case cos:
            r1 = (GmlReal) s.pop();
            s.push(new GmlReal(Math.cos(Math.toRadians(r1.i))));
            break;
        case cube:
            fun = (GmlFunction) s.pop();
            prim = new Cube(fun);
            s.push(prim);
            break;
        case cylinder:
            fun = (GmlFunction) s.pop();
            prim = new Cylinder(fun);
            s.push(prim);
            break;
        case difference:
          //TODO
            break;
        case divi:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlInteger(i1.i  / i2.i ));
            break;
        case divf:
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new GmlReal( r1.i  / r2.i ));
            break;
        case eqi:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlBoolean( i1.i == i2.i));
            break;
        case eqf:
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new GmlBoolean( Math.abs(r1.i - r2.i ) < 1e-7? true:false));
            break;
        case floor:
            r1=(GmlReal) s.pop();
            s.push(new GmlInteger((int) Math.floor(r1.i)));
            break;
        case frac:
            r1=(GmlReal) s.pop();
            s.push(new GmlReal(r1.i>=0?r1.i - Math.floor(r1.i):r1.i - Math.ceil(r1.i)));
            break;
        case get:
            i1=(GmlInteger) s.pop();
            arr=(TokenArray) s.pop();
            s.push(arr.get(i1.i));
            break;
        case getx:
            tg1=(Token) s.pop();
            s.push( new GmlReal(((Triple)tg1).x));
            break;
        case gety:
            tg1=(Token) s.pop();
            s.push( new GmlReal(((Triple)tg1).y));
            break;
        case getz:
            tg1=(Token) s.pop();
            s.push( new GmlReal(((Triple)tg1).z));
            break;
        case If:
            fun2=(GmlFunction) s.pop();
            fun=(GmlFunction) s.pop();
            b=(GmlBoolean) s.pop();
            if(b.b)
                fun.executeFunction(s);
            else
                fun2.executeFunction(s);
            break;
        case intersect:
          //TODO
            break;
        case length:
            arr=(TokenArray) s.pop();
            s.push(new GmlInteger(arr.length()));
            break;
        case lessi:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlBoolean(i1.i < i2.i));
            break;
        case lessf:
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new GmlBoolean(r1.i < r2.i));
            break;
        case light:
            trip2 = (Triple) s.pop();
            trip1 = (Triple) s.pop();
            light = new Light(Light.Type.light,trip1,trip2);
            s.push(light);
            break;
        case modi:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlInteger(i1.i % i2.i ));
            break;
        case muli:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlInteger(i1.i * i2.i ));
            break;
        case mulf:
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new GmlReal( r1.i * r2.i ));
            break;
        case negi:
            i1=(GmlInteger) s.pop();
            s.push(new GmlInteger( - i1.i ));
            break;
        case negf:
            r1=(GmlReal) s.pop();
            s.push(new GmlReal( - r1.i ));
            break;
        case plane:
            fun = (GmlFunction) s.pop();
            prim = new Plane(fun);
            s.push(prim);
            break;
        case point:
            r3=(GmlReal) s.pop();
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new Triple(r1.i, r2.i, r3.i));
            break;
        case pointlight:
            trip2 = (Triple) s.pop();
            trip1 = (Triple) s.pop();
            light = new Light(Light.Type.pointlight,trip1,trip2);
            s.push(light);
            break;
        case print:
            System.out.print(s.pop());
            break;
        case println:
            System.out.println(s.pop());
            break;
        case real:
            i1=(GmlInteger) s.pop();
            s.push(new GmlReal( i1.i ));
            break;
        case render:
            GmlString filename = (GmlString) s.pop();
            i2 = (GmlInteger) s.pop(); //height
            i1 = (GmlInteger) s.pop(); //width
            r2 = (GmlReal) s.pop(); //fov in degree
            GmlInteger depth = (GmlInteger) s.pop(); //depth (num. of reflections per ray)
            Shape sceneTg = (Shape) s.pop();
            TokenArray lights = (TokenArray) s.pop();
            Triple ambient = (Triple) s.pop(); //intensity of ambient light
            Renderer r=new Renderer(sceneTg,lights,ambient,depth);
            r.render(r2.i,i1.i,i2.i,filename.s);
            break;
        case rotatex:
            r1=(GmlReal) s.pop();
            prim=(Shape) s.pop();
            prim.applyTransform(Transform.getRotateX(r1.i));
            s.push(prim);
            break;
        case rotatey:
            r1=(GmlReal) s.pop();
            prim=(Shape) s.pop();
            prim.applyTransform(Transform.getRotateY(r1.i));
            s.push(prim);
            break;
        case rotatez:
            r1=(GmlReal) s.pop();
            prim=(Shape) s.pop();
            prim.applyTransform(Transform.getRotateZ(r1.i));
            s.push(prim);
            break;
        case scale:
            r3=(GmlReal) s.pop();
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            prim=(Shape) s.pop();
            prim.applyTransform(Transform.getScale(new Triple(r1.i, r2.i, r3.i)));
            s.push(prim);
            break;
        case sin:
            r1 = (GmlReal) s.pop();
            s.push(new GmlReal(Math.sin(Math.toRadians(r1.i))));
            break;
        case sphere:
            fun = (GmlFunction) s.pop();
            prim = new Sphere(fun);
            s.push(prim);
            break;
        case spotlight:
          //TODO
            break;
        case sqrt:
            r1 = (GmlReal) s.pop();
            s.push(new GmlReal(Math.sqrt(r1.i)));
            break;
        case subi:
            i2=(GmlInteger) s.pop();
            i1=(GmlInteger) s.pop();
            s.push(new GmlInteger(i1.i  - i2.i ));
            break;
        case subf:
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            s.push(new GmlReal( r1.i  - r2.i ));
            break;
        case translate:
            r3=(GmlReal) s.pop();
            r2=(GmlReal) s.pop();
            r1=(GmlReal) s.pop();
            prim=(Shape) s.pop();
            prim.applyTransform(Transform.getTranslate(new Triple(r1.i, r2.i, r3.i)));
            s.push(prim);
            break;
        case union:
            tg2 = (Token) s.pop();
            tg1 = (Token) s.pop();
            s.push(new Union((Shape)tg1,(Shape)tg2));
            break;
        case uscale:
            r1=(GmlReal) s.pop();
            prim=(Shape) s.pop();
            prim.applyTransform(Transform.getScale(new Triple(r1.i, r1.i, r1.i)));
            s.push(prim);
            break;
        default:
            break;
        }
    }
    private void printStack(GmlStack s) {
        for(TokenGroup t: s.stack)
            System.out.println("\t"+t);
    }
}
