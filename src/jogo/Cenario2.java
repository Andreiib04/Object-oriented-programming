package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario2 extends Cenario{
	
	private Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Boss bosses[];
	
	public Cenario2(Window window) {
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario2.scn"));
		jogador = new Jogador(140,150);
		teclado = janela.getKeyboard();
		bosses = new Boss[1];
		run();
	}

	private void run() {
		
		for (int i= 0; i<bosses.length; i++) {
			bosses[i]= new Boss(30,120);
			
		}
 		
		while(true) {
			jogador.mover(janela,teclado);
			jogador.caminho(cena);
			
			
			
			cena.moveScene(jogador);
			
			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();
			
			jogador.draw();
			
			for(int i=0; i<bosses.length; i++) {
				
			bosses[i].caminho(cena);
			bosses[i].perseguir(jogador.x, jogador.y);
			bosses[i].x += cena.getXOffset();
			bosses[i].y += cena.getYOffset();
			bosses[i].draw();
			jogador.atirar(janela, cena, teclado, bosses[i]);
			bosses[i].morrer();
			bosses[i].atacar(jogador);
			}
			
			jogador.energia(janela);	
			jogador.almas(janela);
			janela.update();
			janela.delay(10);
		}
	}
}
