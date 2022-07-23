package Main;

import Jogo.*;
import GabeLib.GameLib;

/***********************************************************************/
/*                                                                     */
/* Para jogar:                                                         */
/*                                                                     */
/*    - cima, baixo, esquerda, direita: movimentação do player.        */
/*    - control: disparo de projéteis.                                 */
/*    - ESC: para sair do jogo.                                        */
/*                                                                     */
/***********************************************************************/

public class Main {
	
	public static void main(String [] args){
		try{
			Jogo jogo = new Jogo();
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
			
			while(jogo.isRunning()){
			
				/* Usada para atualizar o estado dos elementos do jogo    */
				/* (player, projéteis e inimigos) "delta" indica quantos  */
				/* ms se passaram desde a última atualização.             */
				
				jogo.setDelta(System.currentTimeMillis() - jogo.getCurrentTime());
				
				/* Já a variável "currentTime" nos dá o timestamp atual.  */
				
				jogo.setCurrentTime(System.currentTimeMillis());

				// Exclui enemigos das listas
				jogo.ExcluirInimigosInativos();
				
				/***************************/
				/* Verificação de colisões */
				/***************************/
							
				jogo.verificarColisões();
					
				/***************************/
				/* Atualizações de estados */
				/***************************/
				
				
				jogo.AtualizaEstados();
				
				/********************************************/
				/* Verificando entrada do usuário (teclado) */
				/********************************************/
				
				jogo.verificarEntrada();
				/*******************/
				/* Desenho da cena */
				/*******************/
				jogo.desenharCena();
					
				
		}
		System.exit(0);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exclua uma das instâncias de Jogo");
		}
	}
}