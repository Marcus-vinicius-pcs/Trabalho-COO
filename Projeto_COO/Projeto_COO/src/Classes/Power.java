package Classes;
import GabeLib.GameLib;
import java.util.Random;

public class Power {

	// este power-up, quando em contato com o player, dobra a velocidade dele
	
	private Ponto ponto;						// coordenadas
	private double  radius;				// raio (tamanho do power-up)
	private static long nextPower;					// instante em que um novo power-up deve aparecer
	private int status;		// status do power-up

	public Power() {
		Random random = new Random();
		int random_width = random.nextInt(10);
		int random_height = random.nextInt(10);
		if (random_width == 0 || random_width == 1) random_width = 2;
		if (random_height == 0 || random_height == 1) random_height = 2;

		this.ponto = new Ponto(GameLib.WIDTH / random_width, GameLib.HEIGHT / random_height, 0.25, 0.25);
		this.radius = 6.0;
		Power.nextPower = System.currentTimeMillis() + 30000;   // 30 segundos para aparecer um novo power-up
		this.status = 1;
	}

	public double getX(){
		return this.ponto.getX();
	}

	public double getY(){
		return this.ponto.getY();
	}

	public double getRadius() {
		return this.radius;
	}

	public static long getNextPower() {
		return Power.nextPower;
	}

	public int getStatus() {
		return this.status;
	}

	public void setX(double x){
		this.ponto.setX(x);
	}

	public void setY(double y){
		this.ponto.setY(y);
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public static void setNextPower(long nextPower) {
		Power.nextPower = nextPower;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
