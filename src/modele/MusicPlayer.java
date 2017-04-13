package modele;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Classe permettant de jouer la musique tant que l'application est ouverte, et
 * l'arrêter si l'utilisateur le veut.
 * 
 * @author Les génies du génome
 *
 */
public class MusicPlayer {

	private URL resource;
	private MediaPlayer mediaPlayer;
	public static final String SONG1 = "/sons/Deus Ex - UNATCO - Headquarters.mp3";
	public static final String SONG2 = "/sons/Lobo_Loco_-_02_-_Gasriese_Zeta_II_ID_82.mp3";
	public static final String SONG3 = "/sons/Deus Ex- Human Revolution Soundtrack HD - 51- Entering TYM.mp3";
	public static final String SONG4 = "/sons/Deus Ex- Human Revolution Soundtrack HD - 01- Dreams Of The Future (Main Menue).mp3";
	public static final String SONG5 = "/sons/Deus Ex- Human Revolution OST HD - 50- Tai Yong Medical Laboratories.mp3";

	public MusicPlayer(String song) {
		resource = getClass().getResource(song);
		Media media = new Media(resource.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
	}

	public void changeMute() {
		mediaPlayer.setMute(!mediaPlayer.isMute());
	}

}
