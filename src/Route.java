import java.lang.Math;

public class Route extends Arc{

	/**
	 * @param args
	 */
	double distance;
	
	/**CONSTRUCTEURS*/
	public Route(Ville idn1, Ville idn2){
		super(idn1,idn2);
		this.distance=CalculDistanceEuc(idn1, idn2);
	}
	/**MAIN*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	/**METHODES*/
	public double CalculDistanceEuc(Ville idn1, Ville idn2){
		int x1=idn1.x;
		int x2=idn2.x;
		int y1=idn1.y;
		int y2=idn2.y;
		double d=Math.sqrt((x2-x1)^2+(y2-y1)^2);
		return d;
	}
}
