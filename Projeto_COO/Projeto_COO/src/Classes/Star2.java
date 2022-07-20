package Classes;
import GabeLib.GameLib;

public class Star2 {
	private Ponto p;
	private static double count = 0;
	private static double speed = 0.045;

	public Star2() {
		Star2.count = Star2.count + 1;
		this.p = new Ponto(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT, Star2.speed, 0);
	}

	public static void setCount(double newcount){
		Star2.count += newcount;
	}

	public static double getSpeed(){
		return Star2.speed;
	}

	public double getX(){return this.p.getX();}
	public double getY(){return this.p.getY();}
	public static double getCount(){return Star2.count;}

}
