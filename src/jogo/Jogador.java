package jogo;

import java.awt.Color;
import java.awt.Font;

import jplay.Keyboard;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

public class Jogador extends Ator{

	static int energia = 1000;
	static int almas = 0;
	
	public Jogador(int x, int y) {
		super(URL.sprite("jogador.png"), 20); //Jogador e os seus frames
		this.x = x;
		this.y = y;
		this.setTotalDuration(2000); //trocar de frames na tela
	}
	ControleTiros tiros = new ControleTiros();
	public void atirar(Window janela, Scene cena, Keyboard teclado, Ator inimigo) {
		if(teclado.keyDown(Keyboard.SPACE_KEY)) {
			tiros.adicionaTiro(x+5, y+12, direcao, cena);
		}
		tiros.run(inimigo);
	}
	
	public void atirar(Window janela, Scene cena, Keyboard teclado) {
		if(teclado.keyDown(Keyboard.SPACE_KEY)) {
			tiros.adicionaTiro(x+5, y+12, direcao, cena);
		}
		tiros.run();
	}
	
	public void mover(Window janela, Keyboard teclado) {
		//Trocar as direção
		if(teclado.keyDown(Keyboard.LEFT_KEY)) {
			if(this.x > 0)//não sair da tela
				this.x -= velocidade; // esquerda
			if(direcao != 1) {
				setSequence(4, 8); //frame inicio e final
				direcao = 1;
			}
			movendo = true;
		}else if(teclado.keyDown(Keyboard.RIGHT_KEY)) {
			if(this.x < janela.getWidth() - 60)//tamalho da jenale e uma margem de seguran�a pq o ponto x � do outro lado
				this.x += velocidade;// direita
			if(direcao != 2) {
				setSequence(8, 12); //frame inicio e final
				direcao = 2;
			}
			movendo = true;
		}else if(teclado.keyDown(Keyboard.UP_KEY)) {
			if(this.y > 0)//n�o sair da tela
				this.y -= velocidade;
			if(direcao != 4) {
				setSequence(12,16); //frame inicio e final
				direcao = 4;
			}
			movendo = true;
		}else if(teclado.keyDown(Keyboard.DOWN_KEY)) {
			if(this.y < janela.getHeight() - 60)//n�o sair da tela
				this.y += velocidade;
			if(direcao != 5) {
				setSequence(0,4); //frame inicio e final
				direcao = 5;
			}
			movendo = true;
		}
		//Trocar os frames
		if(movendo) {
			update();
			movendo = false;
		}
	}
	Font f = new Font("arial", Font.BOLD, 30);
	public void energia(Window janela) {
		janela.drawText("Sanidade: " + Jogador.energia, 30, 30, Color.RED, f);
	}
	
	public void almas(Window janela) {
		janela.drawText("Almas: " + Jogador.almas, 30, 60, Color.BLUE, f);
	}
}
