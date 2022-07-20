package Classes;
import GabeLib.GameLib;

public class Star1 {
	private Ponto p;
	private static double count = 0;
	private static double speed = 0.070;

	public Star1() {
		Star1.count = Star1.count + 1;
		this.p = new Ponto(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT, Star1.speed, 0);
	}

	public static void setCount(double newcount){
		Star1.count += newcount;
	}

	public static double getSpeed(){
		return Star1.speed;
	}

	public double getX(){return this.p.getX();}
	public double getY(){return this.p.getY();}
	public static double getCount(){return Star1.count;}
}
