import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***********************************************************************/
/*                                                                     */
/* Para jogar:                                                         */
/*                                                                     */
/*    - cima, baixo, esquerda, direita: movimentação do player.        */
/*    - control: disparo de projéteis.                                 */
/*    - ESC: para sair do jogo.                                        */
/*                                                                     */
/***********************************************************************/

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

class Enemy{
	private Ponto ponto;						// coordenadas

	private int state;					// estados
	private double angle;				// ângulos (indicam direção do movimento)
	private double RV;					// velocidades de rotação
	private double explosion_start;		// instantes dos inícios das explosões
	private double explosion_end;		// instantes dos finais da explosões
	private double  radius;				// raio (tamanho do inimigo 1)
	private long nextEnemy;					// instante em que um novo inimigo 1 deve aparecer

	public Enemy() {
		this.state = 0;
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
	private double  radius;				// raio (tamanho do inimigo 1)
	private long nextEnemy;	

	public Enemy1(){
		this.ponto = new Ponto(Math.random() * (GameLib.WIDTH - 20.0) + 10.0, -10.0, 0, 0.20 + Math.random() * 0.15);
		this.angle = (3 * Math.PI) / 2;
		this.RV = 0.0;
		this.state = 1;
		this.nextShoot = System.currentTimeMillis() + 500;
		this.nextEnemy = System.currentTimeMillis() + 500;
	}

	public double getY(){
		return this.ponto.getY();
	}

	public double getX(){
		return this.ponto.getX();
	}

	public long getNextEnemy() {
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
		this.radius = radius;
	}

	public void setNextEnemy(long nextEnemy) {
		this.nextEnemy = nextEnemy;
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
		this.ponto.moverParaCima(Math.cos(this.getAngle()), delta);
	}


	public void moverParaDireita(long delta){
		this.ponto.moverParaDireita(Math.cos(this.getAngle()), delta);
	}

	public void rotacionar(long delta){
		this.angle += this.RV * delta;
	}
}


class Enemy2{
	private Enemy e;
	double enemy_spawnX = GameLib.WIDTH * 0.20;
	int enemy_count = 0;							// contagem de inimigos tipo 2 (usada na "formação de voo")

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

class Stars {
	private Ponto p;
	private Double count;

	public Stars(int tipo) throws Exception {
		this.count = 0.0;

		if (tipo == 1) // estrelas de primeiro plano
		{
			this.p = new Ponto(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT, 0.070, 0);
		}
		else if (tipo == 2) // estrelas de segundo plano
		{
			this.p = new Ponto(Math.random() * GameLib.WIDTH, Math.random() * GameLib.HEIGHT, 0.045, 0);
		}

		else {
			throw new Exception("Tipo de estrela inválido");
		}
	}
}

public class Main {
	
	/* Constantes relacionadas aos estados que os elementos   */
	/* do jogo (player, projeteis ou inimigos) podem assumir. */
	
	public static final int INACTIVE = 0;
	public static final int ACTIVE = 1;
	public static final int EXPLODING = 2;
	
	/* Espera, sem fazer nada, até que o instante de tempo atual seja */
	/* maior ou igual ao instante especificado no parâmetro "time.    */
	
	public static void busyWait(long time){
		
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	
	/* Encontra e devolve o primeiro índice do  */
	/* array referente a uma posição "inativa". */
	
	public static int findFreeIndex(int [] stateArray){
		
		int i;
		
		for(i = 0; i < stateArray.length; i++){
			
			if(stateArray[i] == INACTIVE) break;
		}
		
		return i;
	}
	
	/* Encontra e devolve o conjunto de índices (a quantidade */
	/* de índices é defnida através do parâmetro "amount") do */
	/* array referente a posições "inativas".                 */ 

	public static int [] findFreeIndex(int [] stateArray, int amount){

		int i, k;
		int [] freeArray = new int[amount];

		for(i = 0; i < freeArray.length; i++) freeArray[i] = stateArray.length; 
		
		for(i = 0, k = 0; i < stateArray.length && k < amount; i++){
				
			if(stateArray[i] == INACTIVE) { 
				
				freeArray[k] = i; 
				k++;
			}
		}
		
		return freeArray;
	}
	
	/* Método principal */
	
	public static void main(String [] args){

		/* Indica que o jogo está em execução */

		boolean running = true;

		/* variáveis usadas no controle de tempo efetuado no main loop */
		
		long delta;
		long currentTime = System.currentTimeMillis();

		/* variáveis do player */

		Player p1 = new Player();
		List<Projectile> projectiles = new ArrayList<Projectile>();
		List<Enemy1> enemies1 = new ArrayList<Enemy1>();
		List<Enemy2> enemies2 = new ArrayList<Enemy2>();
		List <Projectile> e_projectiles = new ArrayList<Projectile>();
		List <Stars> stars = new ArrayList<Stars>();
		/* variáveis dos inimigos tipo 2 */
		
		int [] enemy2_states = new int[10];					// estados
		double [] enemy2_X = new double[10];					// coordenadas x
		double [] enemy2_Y = new double[10];					// coordenadas y
		double [] enemy2_V = new double[10];					// velocidades
		double [] enemy2_angle = new double[10];				// ângulos (indicam direção do movimento)
		double [] enemy2_RV = new double[10];					// velocidades de rotação
		double [] enemy2_explosion_start = new double[10];			// instantes dos inícios das explosões
		double [] enemy2_explosion_end = new double[10];			// instantes dos finais das explosões
		double enemy2_spawnX = GameLib.WIDTH * 0.20;				// coordenada x do próximo inimigo tipo 2 a aparecer
		int enemy2_count = 0;							// contagem de inimigos tipo 2 (usada na "formação de voo")
		double enemy2_radius = 12.0;						// raio (tamanho aproximado do inimigo 2)
		long nextEnemy2 = currentTime + 7000;					// instante em que um novo inimigo 2 deve aparecer	
		
		/* estrelas que formam o fundo de primeiro plano */
		
		double [] background1_X = new double[20];
		double [] background1_Y = new double[20];
		double background1_speed = 0.070;
		double background1_count = 0.0;
		
		/* estrelas que formam o fundo de segundo plano */
		
		double [] background2_X = new double[50];
		double [] background2_Y = new double[50];
		double background2_speed = 0.045;
		double background2_count = 0.0;
		
		/* inicializações */

		for(int i = 0; i < enemy2_states.length; i++) enemy2_states[i] = INACTIVE;
		
		for(int i = 0; i < background1_X.length; i++){
			
			background1_X[i] = Math.random() * GameLib.WIDTH;
			background1_Y[i] = Math.random() * GameLib.HEIGHT;
		}
		
		for(int i = 0; i < background2_X.length; i++){
			
			background2_X[i] = Math.random() * GameLib.WIDTH;
			background2_Y[i] = Math.random() * GameLib.HEIGHT;
		}
						
		/* iniciado interface gráfica */
		
		GameLib.initGraphics();
		//GameLib.initGraphics_SAFE_MODE();  // chame esta versão do método caso nada seja desenhado na janela do jogo.
		
		/*************************************************************************************************/
		/*                                                                                               */
		/* Main loop do jogo                                                                             */
		/* -----------------                                                                             */
		/*                                                                                               */
		/* O main loop do jogo executa as seguintes operações:                                           */
		/*                                                                                               */
		/* 1) Verifica se há colisões e atualiza estados dos elementos conforme a necessidade.           */
		/*                                                                                               */
		/* 2) Atualiza estados dos elementos baseados no tempo que correu entre a última atualização     */
		/*    e o timestamp atual: posição e orientação, execução de disparos de projéteis, etc.         */
		/*                                                                                               */
		/* 3) Processa entrada do usuário (teclado) e atualiza estados do player conforme a necessidade. */
		/*                                                                                               */
		/* 4) Desenha a cena, a partir dos estados dos elementos.                                        */
		/*                                                                                               */
		/* 5) Espera um período de tempo (de modo que delta seja aproximadamente sempre constante).      */
		/*                                                                                               */
		/*************************************************************************************************/
		
		while(running){
		
			/* Usada para atualizar o estado dos elementos do jogo    */
			/* (player, projéteis e inimigos) "delta" indica quantos  */
			/* ms se passaram desde a última atualização.             */
			
			delta = System.currentTimeMillis() - currentTime;
			
			/* Já a variável "currentTime" nos dá o timestamp atual.  */
			
			currentTime = System.currentTimeMillis();
			
			/***************************/
			/* Verificação de colisões */
			/***************************/
						
			if(p1.getState() == ACTIVE){
				
				/* colisões player - projeteis (inimigo) */
				
				for(Projectile p : e_projectiles){
					
					double dx = p.getX() - p1.getX();
					double dy = p.getY() - p1.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);
					
					if(dist < (p1.getRadius() + p.getRadius()) * 0.8){
						
						p1.explodir();
					}
				}
			
				/* colisões player - inimigos */
	
				for(Enemy1 e : enemies1){
					
					double dx = e.getX() - p1.getX();
					double dy = e.getY() - p1.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);
					
					if(dist < (p1.getRadius() + e.getRadius()) * 0.8){
						
						p1.explodir();
					}
				}
				
				for(int i = 0; i < enemy2_states.length; i++){
					
					double dx = enemy2_X[i] - p1.getX();
					double dy = enemy2_Y[i] - p1.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);
					
					if(dist < (p1.getRadius() + enemy2_radius) * 0.8){
						
						p1.explodir();
					}
				}
			}
			
			/* colisões projeteis (player) - inimigos */

			
			for(Projectile p : projectiles){
				
				for(Enemy1 e : enemies1){
										
					if(e.getState() == ACTIVE){
					
						double dx = e.getX() - p.getX();
						double dy = e.getY() - p.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);
						
						if(dist < e.getRadius()){
							
							e.explodir();
						}
					}
				}
				
				for(int i = 0; i < enemy2_states.length; i++){
					
					if(enemy2_states[i] == ACTIVE){
						
						double dx = enemy2_X[i] - p.getX();
						double dy = enemy2_Y[i] - p.getY();
						double dist = Math.sqrt(dx * dx + dy * dy);
						
						if(dist < enemy2_radius){
							
							enemy2_states[i] = EXPLODING;
							enemy2_explosion_start[i] = currentTime;
							enemy2_explosion_end[i] = currentTime + 500;
						}
					}
				}
			}
				
			/***************************/
			/* Atualizações de estados */
			/***************************/
			
			/* projeteis (player) */
			
			for (Projectile p : projectiles) {
				if(p.getState() == ACTIVE){
					
					/* verificando se projétil saiu da tela */
					if(p.getY() < 0) {
						
						p.setState(INACTIVE);
					}
					else {
					
						p.moverParaDireita(delta);
						p.moverParaCima(delta);
					}
				}
			}

			
			/* projeteis (inimigos) */
			
			for(Projectile p : e_projectiles){
				
				if(p.getState() == ACTIVE){
					
					/* verificando se projétil saiu da tela */
					if(p.getY() > GameLib.HEIGHT) {
						
						p.setState(INACTIVE);
					}
					else {
						p.moverParaDireita(delta);
						p.moverParaCima(delta * -1);
					}
				}
			}
			
			/* inimigos tipo 1 */
			
			for(Enemy1 e : enemies1){
				
				e.concluirExplosao();
				
				if(e.getState() == ACTIVE){
					
					/* verificando se inimigo saiu da tela */

					if(e.verificarSaiuDaTela());
					else {
					
						e.moverParaDireita(delta);
						e.moverParaCima(delta);
						e.rotacionar(delta);
						
						if(currentTime > e.getNextShoot() && e.getY() < p1.getY()){
																							
							Projectile e_p = new Projectile(e);
							e_projectiles.add(e_p);
							e.setNextShoot((long) (currentTime + 200 + Math.random() * 500));
							}
						}
					}
			}
			
			/* inimigos tipo 2 */
			
			for(int i = 0; i < enemy2_states.length; i++){
				
				if(enemy2_states[i] == EXPLODING){
					
					if(currentTime > enemy2_explosion_end[i]){
						
						enemy2_states[i] = INACTIVE;
					}
				}
				
				if(enemy2_states[i] == ACTIVE){
					
					/* verificando se inimigo saiu da tela */
					if(	enemy2_X[i] < -10 || enemy2_X[i] > GameLib.WIDTH + 10 ) {
						
						enemy2_states[i] = INACTIVE;
					}
					else {
						
						boolean shootNow = false;
						double previousY = enemy2_Y[i];
												
						enemy2_X[i] += enemy2_V[i] * Math.cos(enemy2_angle[i]) * delta;
						enemy2_Y[i] += enemy2_V[i] * Math.sin(enemy2_angle[i]) * delta * (-1.0);
						enemy2_angle[i] += enemy2_RV[i] * delta;
						
						double threshold = GameLib.HEIGHT * 0.30;
						
						if(previousY < threshold && enemy2_Y[i] >= threshold) {
							
							if(enemy2_X[i] < GameLib.WIDTH / 2) enemy2_RV[i] = 0.003;
							else enemy2_RV[i] = -0.003;
						}
						
						if(enemy2_RV[i] > 0 && Math.abs(enemy2_angle[i] - 3 * Math.PI) < 0.05){
							
							enemy2_RV[i] = 0.0;
							enemy2_angle[i] = 3 * Math.PI;
							shootNow = true;
						}
						
						if(enemy2_RV[i] < 0 && Math.abs(enemy2_angle[i]) < 0.05){
							
							enemy2_RV[i] = 0.0;
							enemy2_angle[i] = 0.0;
							shootNow = true;
						}
						/* 												
						if(shootNow){

							double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
							for(int j = 0; j < angles.length; j++){ // removendo os 5 primeiros elementos do array de projéteis inimigos
								e_projectiles.remove(j);
						
									double a = angles[j] + Math.random() * Math.PI/6 - Math.PI/12;
									double vx = Math.cos(a);
									double vy = Math.sin(a);
										
									Projectile e_projectile = new Projectile(e);
									e_projectiles.add((e_projectile));
							}
							
						}*/
					}
				}
			}
			
			/* verificando se novos inimigos (tipo 1) devem ser "lançados" */
			
			if(currentTime > enemies1.get(enemies1.size()-1).getNextShoot()){
					
					Enemy1 newEnemy1 = new Enemy1();
					enemies1.add(newEnemy1);
			}
			
			/* verificando se novos inimigos (tipo 2) devem ser "lançados" */
			
			if(currentTime > nextEnemy2){
				
				int free = findFreeIndex(enemy2_states);
								
				if(free < enemy2_states.length){
					
					enemy2_X[free] = enemy2_spawnX;
					enemy2_Y[free] = -10.0;
					enemy2_V[free] = 0.42;
					enemy2_angle[free] = (3 * Math.PI) / 2;
					enemy2_RV[free] = 0.0;
					enemy2_states[free] = ACTIVE;

					enemy2_count++;
					
					if(enemy2_count < 10){
						
						nextEnemy2 = currentTime + 120;
					}
					else {
						
						enemy2_count = 0;
						enemy2_spawnX = Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8;
						nextEnemy2 = (long) (currentTime + 3000 + Math.random() * 3000);
					}
				}
			}
			
			/* Verificando se a explosão do player já acabou.         */
			/* Ao final da explosão, o player volta a ser controlável */
			if(p1.getState() == EXPLODING){
				
				if(currentTime > p1.getExplosionEnd()){
					
					p1.setState(ACTIVE);
				}
			}
			
			/********************************************/
			/* Verificando entrada do usuário (teclado) */
			/********************************************/
			
			if(p1.getState() == ACTIVE){
				
				if(GameLib.iskeyPressed(GameLib.KEY_UP)) p1.moverParaCima(delta);
				if(GameLib.iskeyPressed(GameLib.KEY_DOWN)) p1.moverParaBaixo(delta);
				if(GameLib.iskeyPressed(GameLib.KEY_LEFT)) p1.moverParaEsquerda(delta);
				if(GameLib.iskeyPressed(GameLib.KEY_RIGHT)) p1.moverParaDireita(delta);
				
				if(GameLib.iskeyPressed(GameLib.KEY_CONTROL)) {
					
					if(currentTime > p1.getNextShot()){
						
						// int free = findFreeIndex(projectile_states);

						Projectile pro = new Projectile(p1);
						projectiles.add(pro);
							
						p1.setNextShot(currentTime+100);
						
					}	
				}
			}
			
			if(GameLib.iskeyPressed(GameLib.KEY_ESCAPE)) running = false;
			
			/* Verificando se coordenadas do player ainda estão dentro */
			/* da tela de jogo após processar entrada do usuário.      */
			
			p1.verificarCoordenadas();

			/*******************/
			/* Desenho da cena */
			/*******************/
			
			/* desenhando plano fundo distante */
			
			GameLib.setColor(Color.DARK_GRAY);
			background2_count += background2_speed * delta;
			
			for(int i = 0; i < background2_X.length; i++){
				
				GameLib.fillRect(background2_X[i], (background2_Y[i] + background2_count) % GameLib.HEIGHT, 2, 2);
			}
			
			/* desenhando plano de fundo próximo */
			
			GameLib.setColor(Color.GRAY);
			background1_count += background1_speed * delta;
			
			for(int i = 0; i < background1_X.length; i++){
				
				GameLib.fillRect(background1_X[i], (background1_Y[i] + background1_count) % GameLib.HEIGHT, 3, 3);
			}
						
			/* desenhando player */
			
			if(p1.getState() == EXPLODING){
				
				double alpha = (currentTime - p1.getExplosionStart()) / (p1.getExplosionEnd() - p1.getExplosionStart());
				GameLib.drawExplosion(p1.getX(), p1.getY(), alpha);
			}
			else{
				
				GameLib.setColor(Color.BLUE);
				GameLib.drawPlayer(p1.getX(), p1.getY(), p1.getRadius());
			}
				
			/* deenhando projeteis (player) */

			for (Projectile p : projectiles) {
				if(p.getState() == ACTIVE){
					
					GameLib.setColor(Color.GREEN);
					GameLib.drawLine(p.getX(), p.getY() - 5, p.getX(), p.getY() + 5);
					GameLib.drawLine(p.getX() - 1, p.getY() - 3, p.getX() - 1, p.getY() + 3);
					GameLib.drawLine(p.getX() + 1, p.getY() - 3, p.getX() + 1, p.getY() + 3);
				}
			}
		
			
			/* desenhando projeteis (inimigos) */
		
			for(Projectile ep : e_projectiles){
				
				if(ep.getState() == ACTIVE){
	
					GameLib.setColor(Color.RED);
					GameLib.drawCircle(ep.getX(), ep.getY(), ep.getRadius());
				}
			}
			
			/* desenhando inimigos (tipo 1) */
			
			for(Enemy1 e : enemies1){
				
				if(e.getState() == EXPLODING){
					
					double alpha = (currentTime - e.getExplosion_start()) / (e.getExplosion_end() - e.getExplosion_start());
					GameLib.drawExplosion(e.getX(), e.getY(), alpha);
				}
				
				if(e.getState() == ACTIVE){
			
					GameLib.setColor(Color.CYAN);
					GameLib.drawCircle(e.getX(), e.getY(), e.getRadius());
				}
			}
			
			/* desenhando inimigos (tipo 2) */
			
			for(int i = 0; i < enemy2_states.length; i++){
				
				if(enemy2_states[i] == EXPLODING){
					
					double alpha = (currentTime - enemy2_explosion_start[i]) / (enemy2_explosion_end[i] - enemy2_explosion_start[i]);
					GameLib.drawExplosion(enemy2_X[i], enemy2_Y[i], alpha);
				}
				
				if(enemy2_states[i] == ACTIVE){
			
					GameLib.setColor(Color.MAGENTA);
					GameLib.drawDiamond(enemy2_X[i], enemy2_Y[i], enemy2_radius);
				}
			}
			
			/* chamada a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */
			
			GameLib.display();
			
			/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 3 ms. */
			
			busyWait(currentTime + 3);
		}
		
		System.exit(0);
	}
}
