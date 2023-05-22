package jogo;

import jplay.Sound;
import jplay.URL;

public class Audio {

	private static Sound musica;
	
	public static void play(String audio) {
		stop();
		musica = new Sound(URL.audio(audio));
		Audio.musica.play();
		Audio.musica.setRepeat(true);
	}
	
	public static void stop (){
		if(Audio.musica != null) {
			musica.stop();
		}
	}
	
	public static void sonsTemporarios(String audio) {
		musica = new Sound(URL.audio(audio));
		Audio.musica.setVolume(0.1f);
		Audio.musica.play();
	}
}
