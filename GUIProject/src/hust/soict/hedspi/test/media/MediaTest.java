package hust.soict.hedspi.test.media;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;

import java.util.ArrayList;
import java.util.List;

public class MediaTest {



    public static void main(String[] args){
        List<Media> mediae = new ArrayList<Media>();

        CompactDisc cd = new CompactDisc(
                900,
                "Director Name",
                "Artist Name",
                null
        );;
        DigitalVideoDisc dvd = new DigitalVideoDisc(
                "Inception",
                "Science Fiction",
                "Christopher Nolan",
                148,
                19.99f
        );;
        Book book = new Book();

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        for (Media m : mediae){
            System.out.println(m.toString());
        }
    }
}
