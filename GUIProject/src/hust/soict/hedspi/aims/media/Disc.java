package hust.soict.hedspi.aims.media;

public class Disc extends Media{
    private int length;
    private String director;

    public Disc(int length, String director) {
        super();
        this.length = length;
        this.director = director;
    }

    public Disc() {
        super();
    }

    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        this.length = length;
        this.director = director;
    }
    public Disc(String title, String category, float cost) {
    	super(title, category, cost);
    }

    public Disc(String director) {
        this.director = director;
    }

    public int getLength() {
        return length;
    }

    public String getDirector() {
        return director;
    }
}
