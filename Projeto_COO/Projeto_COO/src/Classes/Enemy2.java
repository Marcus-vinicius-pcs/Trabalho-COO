package Classes;


public class Enemy2{
	private int state;
	private Ponto ponto;
	private double angle;
	private double RV;
	private double explosion_end;
	private double explosion_start;
	private static double enemy_spawnX;
	private static int count = 0;							// contagem de inimigos tipo 2 (usada na "formação de voo")
	private double radius = 12.0;
	private static long next_enemy2;

	public Enemy2()
	{
		this.angle = (3 * Math.PI) / 2;
		this.RV = 0;
		this.state = 1;
		next_enemy2 = System.currentTimeMillis() + 7000;
		count = count+1;
		this.ponto = new Ponto(enemy_spawnX, -10, 0.42, 0.42);
	}

	public static long getNext_enemy2() {
		return next_enemy2;
	}

	public static void setNext_enemy2(long next_enemy2) {
		Enemy2.next_enemy2 = next_enemy2;
	}

	public static void setSpawn(double spawn){
		Enemy2.enemy_spawnX = spawn;
	}

	public double getY(){
		return this.ponto.getY();
	}

	public double getX(){
		return this.ponto.getX();
	}

	public void setX(double x){
		this.ponto.setX(x);
	}
	
	public void setY(double y){
		this.ponto.setY(y);
	}

	public double getV(){
		return this.ponto.getVX();
	}

	public static int getCount(){
		return count;
	}

	public static void setCount(int count){
		Enemy2.count = count;
	}  
	
	public long getNextEnemy() {
		return next_enemy2;
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
		this.radius = radius;
	}


}

