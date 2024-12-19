package hust.soict.hedspi.aims.media;

import hust.soict.hedspi.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable{
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc(String title) {
        super();
        this.setTitle(title);
        DigitalVideoDisc.nbDigitalVideoDiscs += 1;
        this.setId(DigitalVideoDisc.nbDigitalVideoDiscs);
    }


    public DigitalVideoDisc(String title, String category, float cost) {
    	super();
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        DigitalVideoDisc.nbDigitalVideoDiscs += 1;
        this.setId(DigitalVideoDisc.nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super(director);
        this.setCategory(category);
        this.setTitle(title);
        this.setCost(cost);
        DigitalVideoDisc.nbDigitalVideoDiscs += 1;
        this.setId(DigitalVideoDisc.nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(length, director);
        this.setTitle(title);
        this.setCategory(category);
        this.setCost(cost);
        DigitalVideoDisc.nbDigitalVideoDiscs += 1;
        this.setId(DigitalVideoDisc.nbDigitalVideoDiscs);
    }

    public DigitalVideoDisc(int id, String title, String category, String director, int length,float cost ) {
        super(id, title, category, cost, length, director);
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    public boolean isMatch(String keywords) {
        String[] keywordArray = keywords.toLowerCase().split(" ");
        String lowerCaseTitle = getTitle().toLowerCase();
        for (String keyword : keywordArray) {
            if (lowerCaseTitle.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "DVD - " + getTitle() + " - " + getCategory() + " - " + getDirector() + " - " + getLength() + ": " + getCost() + " $";
    }

    public void play(){
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
    public String playGUI() throws PlayerException {
        if (this.getLength() > 0) {
                return "Playing DVD: " + this.getTitle() + "\n" + 
                    "DVD length: " + formatDuration(this.getLength());
            } else {
                throw new PlayerException("ERROR: DVD length is non-positive!");
            }
    }
}
