package hust.soict.hedspi.aims.media;

import java.time.Duration;
import java.util.Objects;

import hust.soict.hedspi.aims.exception.PlayerException;

public class Track implements Playable{
    private String title;
    private int length;

    public Track(){}

    public Track(String title) {
        this.title = title;
    }

    public Track(int length) {
        this.length = length;
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }
    public void play(){
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: "+this.getLength());
    }

    public boolean equals(Object obj){
        Track track = (Track) obj;
        return Objects.equals(this.title,track.getTitle()) && Objects.equals(this.length, track.getLength());
    }
    
    public String playGUI() throws PlayerException {
        if (this.getLength() > 0) {
            return "Playing track: " + this.getTitle() + "\n" + 
                "Track length: " + formatDuration(this.getLength());
        } else {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
    
        
    }
    public String formatDuration(int durationInSeconds) {
        Duration duration = Duration.ofSeconds(durationInSeconds);
        return String.format("%02d:%02d", duration.toMinutes(), duration.minusMinutes(duration.toMinutes()).getSeconds());
    }
}
