package Classes;
import GabeLib.GameLib;

public class Player {

	private int state; 
	private double radius;
	private double explosionStart;
	private double explosionEnd;
	private long nextShot;
	private long startPowerTime;

	private Ponto ponto;

	public Player() {
		this.ponto = new Ponto(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25);
		this.state = 1;
		this.radius = 12.0;
		this.explosionStart = 0;
		this.explosionEnd = 0;
		this.nextShot = System.currentTimeMillis();
		this.startPowerTime = System.currentTimeMillis();
	}


	public double getRadius(){
		return this.radius;
	}
	
	public double getExplosionStart(){
		return this.explosionStart;
	}

	public double getExplosionEnd(){
		return this.explosionEnd;
	}

	public double getNextShot(){
		return this.nextShot;
	}

	public int getState(){
		return this.state;
	}

	public double getX(){
		return this.ponto.getX();
	}


	public double getY(){
		return this.ponto.getY();
	}

	public double getStartPowerTime() {
		return this.startPowerTime;
	}

	public void setStartPowerTime(long time) {
		this.startPowerTime = time;
	}

	public void setState(int s){
		this.state = s;
	}

	public void setX(double x){
		this.ponto.setX(x);
	}
	
	public void setY(double y){
		this.ponto.setY(y);
	}

	public void verificarFimExplosao(){
		if(this.state == 2){
	
			if(System.currentTimeMillis() > this.explosionEnd){
	
				this.state = 1;
			}
		}
	}

	public void explodir(){
		setState(2);
		setExplosionStart(System.currentTimeMillis());
		setExplosionEnd(System.currentTimeMillis() + 2000);
	}

	public void verificarCoordenadas(){
		if(this.getX() < 0.0) this.setX(0.0);
		if(this.getX() >= GameLib.WIDTH) this.setX(GameLib.WIDTH - 1);
		if(this.getY() < 25.0) this.setY(25.0);
		if(this.getY() >= GameLib.HEIGHT) this.setY(GameLib.HEIGHT - 1);
	}

	public void moverParaCima(long delta){
		this.ponto.moverVertical(1, delta);
	}
	
	public void moverParaDireita(long delta){
		this.ponto.moverHorizontal(1, delta);
	}

	public void moverParaBaixo(long delta){
		this.ponto.moverVertical(1, delta);
	}

	public void moverParaEsquerda(long delta){
		this.ponto.moverHorizontal(1, delta);
	}

	public void setExplosionStart(double time) {
		this.explosionStart = time;
	}

	public void setExplosionEnd(double time) {
		this.explosionEnd = time;
	}

	public void setNextShot(Long time) {
		this.nextShot = time;
	}

	
	public void setVX(double vx){
		this.ponto.setVX(vx);
	}

	public void setVY(double vy){
		this.ponto.setVY(vy);
	}

	public double getVX(){
		return this.ponto.getVX();
	}

	
	public double getVY(){
		return this.ponto.getVY();
	}

	public void dobraVelocidade() {
		this.setVX(0.5);
		this.setVY(0.5);
	}

	public void voltaVelocidadeNormal() {
		this.setVX(0.25);
		this.setVY(0.25);
	}
	
}

