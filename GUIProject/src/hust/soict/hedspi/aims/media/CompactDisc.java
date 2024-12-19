package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

import hust.soict.hedspi.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable{
    private String artist;
    private ArrayList<Track>  tracks = new ArrayList<Track>();

    public CompactDisc(int length, String director, String artist, ArrayList<Track> tracks) {
        super(length, director);
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc(String artist, ArrayList<Track> tracks) {
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc(String director, String artist, ArrayList<Track> tracks) {
        super(director);
        this.artist = artist;
        this.tracks = tracks;
    }

    public CompactDisc(int id, String title, String category, float cost, int length, String director, String artist) {
        super(id, title, category, cost, length, director);
        this.artist = artist;
    }


	public CompactDisc(String title, String category, String artist2, float cost) {
		super(title, category, cost);
		
	}

	public String getArtist(){
        return artist;
    }

    public void addTrack(Track newTrack){
        if(!tracks.contains(newTrack)){
            System.out.println("Track chua co trong Array");
            tracks.add(newTrack);
        } else {
            System.out.println("Track da co trong Array");
        }
    }
    public void removeTrack(Track reTrack){
        if(tracks.remove(reTrack)){
            System.out.println("Track da bi xoa khoi Array");
        } else {
            System.out.println("Track khong ton tai trong Array");
        }
    }

    public int getLength(){
        int sum = 0;
        for(Track track : tracks){
            sum += track.getLength();
        }
        return sum;
    }

    public void play(){
        for(Track track: tracks){
            track.play();
        }
    }
    
    public String playGUI() throws PlayerException {
        if(this.getLength() > 0) {
            String output =  "Playing CD: " + this.getTitle() + "\n" + 
                            "CD length: " + formatDuration(this.getLength()) + "\n"+ "\n";
            for (Track track : tracks) {
                try {
                    output += track.playGUI() + "\n";
                } catch (PlayerException e) {
                    output += track.getTitle() + "\n" + e.getMessage();
                }
            }
            return output;
            } else {
                throw new PlayerException("ERROR: CD length is non-positive!");
            }
    }
}
