package jogo;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Cenario1 extends Cenario{
	
	private Window janela;
	private Scene cena;
	private Jogador jogador;
	private Keyboard teclado;
	private Inimigo inimigos[];
	
	public Cenario1(Window window) {
		janela = window;
		cena = new Scene();
		cena.loadFromFile(URL.scenario("Cenario1.scn"));
		jogador = new Jogador(140,150);
		teclado = janela.getKeyboard();
		inimigos = new Inimigo[20];
		
		Audio.play("Audio.mid"); 
		run();
	}

	private void run() {
		
		for (int i= 0; i<inimigos.length; i++) {
			inimigos[i]= new Inimigo(10 * i, 52 * i);
			
		}
		
		while(true) {
			jogador.mover(janela,teclado);
			jogador.caminho(cena);
			
			
			
			cena.moveScene(jogador);
			
			jogador.x += cena.getXOffset();
			jogador.y += cena.getYOffset();
			
			jogador.draw();
			
			for(int i=0; i<inimigos.length; i++) {
				
			inimigos[i].caminho(cena);
			inimigos[i].perseguir(jogador.x, jogador.y);
			inimigos[i].x += cena.getXOffset();
			inimigos[i].y += cena.getYOffset();
			inimigos[i].draw();
			jogador.atirar(janela, cena, teclado, inimigos[i]);
			inimigos[i].morrer();
			inimigos[i].atacar(jogador);
			}
			
			jogador.energia(janela);	
			jogador.almas(janela);
			janela.update();
			janela.delay(10);
			mudarCenario();
			addAlmas();
		}
	}
	//tile referente ao teleporte de cenario
	private void mudarCenario() {
		if(tileCollision(19, jogador, cena)==true) {
			new Cenario2(janela);
			Jogador.almas = 100;
		}
	}
	
	//Moeda do jogo
	private void addAlmas() {
		if(tileCollision(14, jogador, cena)==true) {
			if(teclado.keyDown(Keyboard.ENTER_KEY)) {
				Jogador.almas = Jogador.almas + 10; 
			}
		}
	}
}
