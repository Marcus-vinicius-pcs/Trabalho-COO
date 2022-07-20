package Classes;


class Ponto {

	//criei nova classe (PONTO)
	
	private double X;	// coordenada x
	private double Y;	// coordenada y
	private double velocidadeX;
	private double velocidadeY;

	public Ponto(double x, double y, double vX, double vY){
		this.X = x;
		this.Y = y;
		this.velocidadeX = vX;
		this.velocidadeY = vY;
	}

	public double getX(){
		return X;
	}

	public double getY(){
		return Y;
	}

	public void setX(double x){
		this.X = x;
	}

	public void setY(double y){
		this.Y = y;
	}

	public double getVX(){
		return this.velocidadeX;
	}
	public double getVY(){
		return this.velocidadeY;
	}



	public void moverParaCima(double y, long delta){
		this.Y -= y*delta*this.velocidadeY;
	}

	public void moverParaBaixo(double y, long delta){
		this.Y += y*delta*this.velocidadeY;
	}
	public void moverParaEsquerda(double x, long delta){
		this.X -= x*delta*this.velocidadeX;
	}
	public void moverParaDireita(double x, long delta){
		this.X += x*delta*this.velocidadeX;
	}

}


class Player {

	private int state; 
	private double radius;
	private double explosionStart;
	private double explosionEnd;
	private long nextShot;

	private Ponto ponto;

	public Player() {
		this.ponto = new Ponto(GameLib.WIDTH / 2, GameLib.HEIGHT * 0.90, 0.25, 0.25);
		this.state = 1;
		this.radius = 12.0;
		this.explosionStart = 0;
		this.explosionEnd = 0;
		this.nextShot = System.currentTimeMillis();
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
		this.ponto.moverParaCima(1, delta);
	}
	
	public void moverParaDireita(long delta){
		this.ponto.moverParaDireita(1, delta);
	}

	public void moverParaBaixo(long delta){
		this.ponto.moverParaBaixo(1, delta);
	}

	public void moverParaEsquerda(long delta){
		this.ponto.moverParaEsquerda(1, delta);
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
	
}


class Enemy1{
	private long nextShoot;				// instantes do próximo tiro
	private Ponto ponto;						// coordenadas

	private int state;					// estados
	private double angle;				// ângulos (indicam direção do movimento)
	private double RV;					// velocidades de rotação
	private double explosion_start;		// instantes dos inícios das explosões
	private double explosion_end;		// instantes dos finais da explosões
	private static double radius = 9.0;				// raio (tamanho do inimigo 1)
	private static long nextEnemy;
	

	public Enemy1(){
		this.ponto = new Ponto(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, 0, 0.20 + Math.random() * 0.15);
		this.angle = (3 * Math.PI) / 2;
		this.RV = 0.0;
		this.state = 1;
		this.nextShoot = System.currentTimeMillis() + 500;
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


class Enemy2{
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


class Projectile{
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

	public void moverParaCima(long delta){
		this.ponto.moverParaCima(1, delta*-1);
	}
	
	public void moverParaDireita(long delta){
		this.ponto.moverParaDireita(1, delta);
	}
	
}

class Star1 {
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

class Star2 {
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
