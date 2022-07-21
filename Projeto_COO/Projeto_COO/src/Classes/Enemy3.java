package Classes;
import GabeLib.GameLib;

public  class Enemy3{
	private long nextShoot;				// instantes do próximo tiro
	private Ponto ponto;						// coordenadas

	private int state;					// estados
	private double angle;				// ângulos (indicam direção do movimento)
	private double RV;					// velocidades de rotação
	private double explosion_start;		// instantes dos inícios das explosões
	private double explosion_end;		// instantes dos finais da explosões
	private static double radius = 10.0;				// raio (tamanho do inimigo 1)
	private static long nextEnemy;
	

	public Enemy1(){
		this.ponto = new Ponto(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, 0, 0.20 + Math.random() * 0.15);
		this.angle = (3 * Math.PI) / 2;
		this.RV = 0.0;
		this.state = 1;
		this.nextShoot = System.currentTimeMillis() + 300;
	}

	public double getY(){
		return this.ponto.getY();
	}

	public double getX(){
		return this.ponto.getX();
	}

	public static long getNextEnemy() {
		return nextEnemy;
	}

	public int getState() {
		return state;
	}

	public double getAngle() {
		return angle;
	}

	public double getRV() {
		return RV;
	}

	public double getExplosion_start() {
		return explosion_start;
	}

	public double getExplosion_end() {
		return explosion_end;
	}

	public double getRadius() {
		return radius;
	}

	public long getNextShoot() {
		return nextShoot;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setRV(double rV) {
		this.RV = rV;
	}
	public void setExplosion_start(double explosion_start) {
		this.explosion_start = explosion_start;
	}

	public void setExplosion_end(double explosion_end) {
		this.explosion_end = explosion_end;
	}

	public void setRadius(double radius) {
		Enemy1.radius = radius;
	}

	public static void setNextEnemy(long nextEnemy) {
		Enemy1.nextEnemy = nextEnemy;
	}

	public void setNextShoot(long nextShoot) {
		this.nextShoot = nextShoot;
	}

	public void explodir(){
		setState(2);
		setExplosion_start(System.currentTimeMillis());
		setExplosion_end(System.currentTimeMillis() + 500);
	}

	public boolean verificarSaiuDaTela(){
		if(this.getY() >  GameLib.HEIGHT + 10) {
			this.setState(0);
			return true;
		}
		return false;

	}

	public boolean concluirExplosao(){
		if(this.getState() == 2){
			if (System.currentTimeMillis() > this.getExplosion_end()){
				this.setState(0);
				return true;
			}
		}
		return false;
	}


	public void moverParaCima(long delta) {
		this.ponto.moverParaCima(Math.sin(this.getAngle()), delta);
	}


	public void moverParaDireita(long delta){
		this.ponto.moverParaDireita(Math.cos(this.getAngle()), delta);
	}

	public void rotacionar(long delta){
		this.angle += this.RV * delta;
	}
}

