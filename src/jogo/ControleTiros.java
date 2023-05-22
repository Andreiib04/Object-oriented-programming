package jogo;

import java.util.LinkedList;

import jplay.Scene;

public class ControleTiros {

	LinkedList<Atacar> tiros = new LinkedList<>();
	
	public void adicionaTiro(double x,double y, int caminho, Scene cena) {
		Atacar tiro = new Atacar(x, y, caminho);
		tiros.addFirst(tiro);
		cena.addOverlay(tiro);
		somDisparo();
	}
	
	public void run(Ator inimigo) {
		for (int i = 0; i < tiros.size(); i++) {
			Atacar tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
			
			if (tiro.collided(inimigo)) {
				tiro.x = 10_000;
				inimigo.energia -= 250;
			}
		}
	}
	public void run() {
		for (int i = 0; i < tiros.size(); i++) {
			Atacar tiro = tiros.removeFirst();
			tiro.mover();
			tiros.addLast(tiro);
		}
	}
	
	private void somDisparo() {
		Audio.sonsTemporarios("flecha.wav");
	}
}
