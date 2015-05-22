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
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Triple other = (Triple) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
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
