package Jogo;

import java.util.ArrayList;
import java.util.List;

import Classes.*;

public class Jogo {
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


    public boolean running = true;
    public long delta;
    public long currentTime = System.currentTimeMillis();

    public Player p1 = new Player();
    public List<Projectile> projectiles = new ArrayList<Projectile>();
	public List<Enemy1> enemies1 = new ArrayList<Enemy1>();
	public List<Enemy1> enemies1Remove = new ArrayList<Enemy1>();
}
