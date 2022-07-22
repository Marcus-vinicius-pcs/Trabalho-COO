package Classes;

public class Projectile{
	private int state;					// estados
	private Ponto ponto;

	private double radius;


	public Projectile(Player p){
		this.state = 1;
		this.ponto = new Ponto(p.getX(), p.getY() - 2 * p.getRadius(), 0.0, -1.0);
		this.radius = 0;
	}

	public Projectile(Enemy1 e){
		this.state = 1;
		this.ponto = new Ponto(e.getX(), e.getY(), Math.cos(e.getAngle()) * 0.45, Math.sin(e.getAngle()) * 0.45 * (-1.0));
		this.radius = 2.0;
	}

	public Projectile(Enemy2 e, double vx, double vy){
		this.state = 1;
		this.ponto = new Ponto(e.getX(), e.getY(), vx * 0.30, vy * 0.30);
		this.radius = 2.0;
	}

	public Projectile(Enemy3 e){
		this.state = 1;
		this.ponto = new Ponto(e.getX(), e.getY(), Math.cos(e.getAngle()) * 0.45, Math.sin(e.getAngle()) * 0.45 * (-1.0));/
		this.radius = 2.0;
	}


	public void setState(int s){
		this.state = s;
	}

	public int getState(){
		return this.state;
	}

	public double getX(){
		return this.ponto.getX();
	}

	public double getRadius(){
		return this.radius;
	}

	public double getY(){
		return this.ponto.getY();
	}


	public void setX(double x){
		this.ponto.setX(x);
	}
	
	public void setY(double y){
		this.ponto.setY(y);
	}

	public void moverVertical(long delta){
		this.ponto.moverVertical(1, delta*-1);
	}
	
	public void moverHorizontal(long delta){
		this.ponto.moverHorizontal(1, delta);
	}
	
}

