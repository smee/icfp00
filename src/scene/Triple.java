package scene;

import model.Token;

public class Triple extends Token{
    public final double x,y,z;

    public Triple(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    @Override
    public String toString() {
        return "("+x+","+y+","+z+")";
    }
    /*
     * v1*v2=|v1|*|v2|*cos(winkel)
     * cos(winkel)==0 heisst rechtwinklig
     */
    public double dotProduct(Triple other){
        return this.x*other.x+this.y*other.y+this.z*other.z;
    }
    public double len(){
        return Math.sqrt(dotProduct(this));
    }
    public Triple normalize(){
        return this.mul(1d/this.len());
    }

    public Triple add(Triple o){
        return new Triple(x+o.x,y+o.y,z+o.z);
    }
    public Triple sub(Triple o){
    	return new Triple(x-o.x,y-o.y,z-o.z);
    }

    public Triple mul(double s){
        return new Triple(x*s,y*s,z*s);
    }
	public Triple crossProduct(Triple o) {
		return new Triple(y*o.z-z*o.y,z*o.x-x*o.z,x*o.y-y*o.x);
	}
}
