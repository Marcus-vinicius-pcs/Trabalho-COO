package Jogo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import GabeLib.GameLib;
import Classes.*;

public class Jogo {

	/* Indica que o jogo está em execução */
		
	private static final int INACTIVE = 0;
	private static final int ACTIVE = 1;
	private static final int EXPLODING = 2;
	private boolean running;

	private static void busyWait(long time){
		
		while(System.currentTimeMillis() < time) Thread.yield();
	}
	

	/* variáveis usadas no controle de tempo efetuado no main loop */
	private static int cont = 0;
	private long delta;
	private static long currentTime;
	private Player p1;
	private List<Projectile> projectiles;
	private List<Enemy1> enemies1;
	private List<Enemy1> enemies1Remove;
	List<Enemy2> enemies2;
	List<Enemy2> enemies2Remove;
	List <Projectile> e_projectiles;
	List <Star1> stars_1;
	List <Star2> stars_2;
	List <Power> powers;

	public Jogo() throws Exception{
		if(Jogo.cont == 1)
			throw new Exception("Não é permitido instanciar mais de uma classe Jogo");
		else {
			Jogo.cont = Jogo.cont + 1;
			this.running = true;
			Jogo.currentTime = System.currentTimeMillis();
			this.p1 = new Player();
			this.projectiles = new ArrayList<Projectile>();
			this.enemies1 = new ArrayList<Enemy1>();
			this.enemies1Remove = new ArrayList<Enemy1>();
			Enemy1.setNextEnemy(currentTime + 2000);

			this.enemies2 = new ArrayList<Enemy2>();
			this.enemies2Remove = new ArrayList<Enemy2>();

			Enemy2.setNext_enemy2(currentTime + 7000);
			Enemy2.setSpawn(Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8);
				
			this.e_projectiles = new ArrayList<Projectile>();
			this.stars_1 = new ArrayList<Star1>();
			this.stars_2 = new ArrayList<Star2>();

			/*Power-up*/
			this.powers = new ArrayList<Power>();

			/*Inicializações*/
			for(int i = 0; i < 20; i ++){
				
				stars_1.add(new Star1());
			}
			
			for(int i = 0; i < 50; i++){
				
				stars_2.add(new Star2());
			}
		}
						
	}

	public void setDelta(long newDelta)
	{
		this.delta = newDelta;
	}

	public void setCurrentTime(long newCurrentTime)
	{
		Jogo.currentTime = newCurrentTime;
	}

	public long getCurrentTime()
	{
		return Jogo.currentTime;
	}
    
	public void ExcluirInimigosInativos(){
		enemies1.removeAll(enemies1Remove);
		enemies2.removeAll(enemies2Remove);
	}

	public void verificarColisões(){

		if(p1.getState() == 1){
				
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
			
			for (Enemy2 enemy : enemies2) {
				double dx = enemy.getX() - p1.getX();
				double dy = enemy.getY() - p1.getY();
				double dist = Math.sqrt(dx * dx + dy * dy);

				if(dist < (p1.getRadius() + enemy.getRadius()) * 0.8){
					
					p1.explodir();
				}
			}

			/* colisões player - power-ups */

			for(Power powerUp : powers){

				double dx = powerUp.getX() - p1.getX();
				double dy = powerUp.getY() - p1.getY();
				double dist = Math.sqrt(dx * dx + dy * dy);

				if(dist < (p1.getRadius() + powerUp.getRadius()) * 0.8){

					p1.dobraVelocidade();
					p1.setStartPowerTime(currentTime);
					powerUp.setStatus(0);
				}
			}
		}
		
		/* colisões projeteis (player) - inimigos */

		
		for(Projectile p : projectiles){
			
			for(Enemy1 e : enemies1){
									
				if(e.getState() == 1){
				
					double dx = e.getX() - p.getX();
					double dy = e.getY() - p.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);
					
					if(dist < e.getRadius()){
						
						e.explodir();
					}
				}
			}

			for (Enemy2 enemy : enemies2) {
				if(enemy.getState() == 1){
					double dx = enemy.getX() - p.getX();
					double dy = enemy.getY() - p.getY();
					double dist = Math.sqrt(dx * dx + dy * dy);

					if(dist < enemy.getRadius()){
						
						enemy.setState(2);
						enemy.setExplosion_start(currentTime);
						enemy.setExplosion_end(currentTime + 500);
					}
				}
			}

			
		}
	}

	public boolean isRunning(){
		return this.running;
	}

	public void AtualizaEstados(){
		for (Projectile p : projectiles) {
			if(p.getState() == 1){
				
				/* verificando se projétil saiu da tela */
				if(p.getY() < 0) {
					
					p.setState(0);
				}
				else {
				
					p.moverHorizontal(delta);
					p.moverVertical(delta);
				}
			}
		}

		
		/* projeteis (inimigos) */
		
		for(Projectile p : e_projectiles){
			
			if(p.getState() == 1){
				
				/* verificando se projétil saiu da tela */
				if(p.getY() > GameLib.HEIGHT) {
					
					p.setState(0);
				}
				else {
					p.moverHorizontal(delta);
					p.moverVertical(delta);
				}
			}
		}
		
		/* inimigos tipo 1 */
		
		for(Enemy1 e : enemies1){
			
			if(e.concluirExplosao()) enemies1Remove.add(e);
			
			if(e.getState() == 1){
				
				/* verificando se inimigo saiu da tela */

				if(e.verificarSaiuDaTela()) enemies1Remove.add(e);
				else {
				
					e.moverHorizontal(delta);
					e.moverVertical(delta);
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
		
		for (Enemy2 enemy : enemies2) {
			if( enemy.getState() == 2){
				
				if(currentTime > enemy.getExplosion_end()){
					
					enemy.setState(0);
					enemies2Remove.add(enemy);
				}
			}

			if(enemy.getState() == 1){
				/* verificando se inimigo saiu da tela */
				if(	enemy.getX() < -10 || enemy.getX() > GameLib.WIDTH + 10 ) {
					
					enemy.setState(0);
					enemies2Remove.add(enemy);
				}
				else {
					
					boolean shootNow = false;
					double previousY = enemy.getY();
											
					enemy.setX(enemy.getX() + enemy.getV() * Math.cos(enemy.getAngle()) * delta);
					enemy.setY(enemy.getY() + enemy.getV() * Math.sin(enemy.getAngle()) * delta * (-1.0));
					enemy.setAngle(enemy.getAngle() + enemy.getRV() * delta);
					
					double threshold = GameLib.HEIGHT * 0.30;
					
					if(previousY < threshold && enemy.getY() >= threshold) {
						
						if(enemy.getX() < GameLib.WIDTH / 2) enemy.setRV(0.003);
						else enemy.setRV(-0.003);
					}
					
					if(enemy.getRV() > 0 && Math.abs(enemy.getAngle() - 3 * Math.PI) < 0.05){
						
						enemy.setRV(0.0);
						enemy.setAngle(3 * Math.PI);
						shootNow = true;
					}
					
					if(enemy.getRV() < 0 && Math.abs(enemy.getAngle()) < 0.05){
						
						enemy.setRV(0.0);
						enemy.setAngle(0.0);
						shootNow = true;
					}							
					if(shootNow){

						double [] angles = { Math.PI/2 + Math.PI/8, Math.PI/2, Math.PI/2 - Math.PI/8 };
						for(int j = 0; j < angles.length; j++){ // removendo os 5 primeiros elementos do array de projéteis inimigos
							
								double a = angles[j] + Math.random() * Math.PI/6 - Math.PI/12;
								double vx = Math.cos(a);
								double vy = Math.sin(a);
									
								Projectile e_projectile = new Projectile(enemy, vx, vy);
								e_projectiles.add((e_projectile));
						}
						
					}
				}
			}
		}
		
		
		/* verificando se novos inimigos (tipo 1) devem ser "lançados" */
		if (enemies1.size() == 0 && currentTime > Enemy1.getNextEnemy()) {
			Enemy1 newEnemy1 = new Enemy1();
			enemies1.add(newEnemy1);
			Enemy1.setNextEnemy(currentTime + 500);
		}
		else {
			if(currentTime > Enemy1.getNextEnemy()){
				Enemy1 newEnemy1 = new Enemy1();
				enemies1.add(newEnemy1);
				Enemy1.setNextEnemy(currentTime + 500);
			}
			
		}
		
		
		/* verificando se novos inimigos (tipo 2) devem ser "lançados" */
		
		if(currentTime > Enemy2.getNext_enemy2()){
			
			Enemy2 newEnemy2 = new Enemy2();
			
			
			if(Enemy2.getCount() < 10){
				enemies2.add(newEnemy2);
				Enemy2.setNext_enemy2( currentTime + 120);
			}
			else {
				Enemy2.setSpawn(Math.random() > 0.5 ? GameLib.WIDTH * 0.2 : GameLib.WIDTH * 0.8);
				Enemy2.setCount(0);					
				Enemy2.setNext_enemy2((long) (currentTime + 3000 + Math.random() * 3000));
			}
		
		}

		/* verificando se novos power-ups devem ser "lançados" */
		if (powers.size() == 0) {
			Power power = new Power();
			powers.add(power);
		}
		else {
			if(currentTime > Power.getNextPower()){

				Power newPower = new Power();
				powers.add(newPower);
			}

		}
		
		/* Verificando se a explosão do player já acabou.         */
		/* Ao final da explosão, o player volta a ser controlável */
		if(p1.getState() == 2){
			
			if(currentTime > p1.getExplosionEnd()){
				
				p1.setState(1);
			}
		}
	}

	public void verificarEntrada(){
		if(p1.getState() == 1){
				
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

	}

	public void desenharCena(){
		/* desenhando plano fundo distante */
			
		GameLib.setColor(Color.DARK_GRAY);
			
		Star2.setCount(Star2.getSpeed() * delta);
		for(Star2 star : stars_2){
			
			GameLib.fillRect(star.getX(), (star.getY() + Star2.getCount()) % GameLib.HEIGHT, 2, 2);
		}
		
		/* desenhando plano de fundo próximo */
		
		GameLib.setColor(Color.GRAY);
		Star1.setCount(Star1.getSpeed() * delta);
		for(Star1 star : stars_1){
			
			GameLib.fillRect(star.getX(), (star.getY() + Star2.getCount()) % GameLib.HEIGHT, 3, 3);
		}
					
		/* desenhando player */
		
		if(p1.getState() == 2){
			
			double alpha = (currentTime - p1.getExplosionStart()) / (p1.getExplosionEnd() - p1.getExplosionStart());
			GameLib.drawExplosion(p1.getX(), p1.getY(), alpha);
		}
		else{
			
			GameLib.setColor(Color.BLUE);
			GameLib.drawPlayer(p1.getX(), p1.getY(), p1.getRadius());
		}
			
		/* deenhando projeteis (player) */

		for (Projectile p : projectiles) {
			if(p.getState() == 1){
				
				GameLib.setColor(Color.GREEN);
				GameLib.drawLine(p.getX(), p.getY() - 5, p.getX(), p.getY() + 5);
				GameLib.drawLine(p.getX() - 1, p.getY() - 3, p.getX() - 1, p.getY() + 3);
				GameLib.drawLine(p.getX() + 1, p.getY() - 3, p.getX() + 1, p.getY() + 3);
			}
		}
	
		
		/* desenhando projeteis (inimigos) */
	
		for(Projectile ep : e_projectiles){
			
			if(ep.getState() == 1){

				GameLib.setColor(Color.RED);
				GameLib.drawCircle(ep.getX(), ep.getY(), ep.getRadius());
			}
		}
		
		/* desenhando inimigos (tipo 1) */
		
		for(Enemy1 e : enemies1){
			
			if(e.getState() == 2){
				
				double alpha = (currentTime - e.getExplosion_start()) / (e.getExplosion_end() - e.getExplosion_start());
				GameLib.drawExplosion(e.getX(), e.getY(), alpha);
			}
			
			if(e.getState() == 1){
		
				GameLib.setColor(Color.CYAN);
				GameLib.drawCircle(e.getX(), e.getY(), e.getRadius());
			}
		}
		
		/* desenhando inimigos (tipo 2) */

		for (Enemy2 enemy : enemies2) {
			if(enemy.getState() == 2){
				
				double alpha = (currentTime - enemy.getExplosion_start()) / (enemy.getExplosion_end() - enemy.getExplosion_start());
				GameLib.drawExplosion(enemy.getX(), enemy.getY(), alpha);
			}

			if(enemy.getState() == 1){
		
				GameLib.setColor(Color.MAGENTA);
				GameLib.drawDiamond(enemy.getX(), enemy.getY(), enemy.getRadius());
			}
		}

		// Desenhando powerups
		for (Power power : powers) {
			if(power.getStatus() == 1){
		
				GameLib.setColor(Color.PINK);
				GameLib.drawDiamond(power.getX(), power.getY(), power.getRadius());
			}
		}

		/* interrompe o efeito do power-up (se o player o pegou) após 10 segundos */
		if (currentTime > p1.getStartPowerTime() + 10000)
			p1.voltaVelocidadeNormal();
		
		/* chamada a display() da classe GameLib atualiza o desenho exibido pela interface do jogo. */
		
		GameLib.display();
		
		/* faz uma pausa de modo que cada execução do laço do main loop demore aproximadamente 3 ms. */
		
		Jogo.busyWait(currentTime + 3);
	}
}
