import java.lang.Math;

public class Route extends Arc{

	/**
	 * @param args
	 */
	double distance;
	
	/**CONSTRUCTEURS*/
	public Route(Ville source, Ville dest){
		super(source,dest);
		this.distance=calculDistanceEuc(source, dest);
	}

	/**METHODES*/
	public double calculDistanceEuc(Ville source, Ville dest){
		int x1=source.x;
		int x2=dest.x;
		int y1=source.y;
		int y2=dest.y;
		double d=Math.sqrt((x2-x1)^2+(y2-y1)^2);
		return d;
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		sb.append("Route : ");
		sb.append(this.id);
		sb.append(", source : ");
		sb.append(this.source.id);
		sb.append(", dest : ");
		sb.append(this.dest.id);
		sb.append(", longueur : ");
		sb.append(this.distance);
		return sb.toString();
	}
}
