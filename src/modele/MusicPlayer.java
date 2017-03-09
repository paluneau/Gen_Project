package modele;

import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {
	
	private URL resource;
	private MediaPlayer mediaPlayer;
	
	public MusicPlayer() {
		resource = getClass().getResource("/sons/Lobo_Loco_-_02_-_Gasriese_Zeta_II_ID_82.mp3");
		Media media = new Media(resource.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
	}
	
	public void changeMute(){
		mediaPlayer.setMute(!mediaPlayer.isMute());
	}
	
	

}
