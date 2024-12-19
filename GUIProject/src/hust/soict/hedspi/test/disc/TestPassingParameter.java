package hust.soict.hedspi.test.disc;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

class DiscWrapper {
    public DigitalVideoDisc disc;

    public DiscWrapper(DigitalVideoDisc disc) {
        this.disc = disc;
    }
}

public class TestPassingParameter {

    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        DiscWrapper jungleDVDWrapper = new DiscWrapper(new DigitalVideoDisc("Jungle"));
        DiscWrapper cinderellaDVDWrapper = new DiscWrapper(new DigitalVideoDisc("Cinderella"));

        swap(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());

        swap(jungleDVDWrapper, cinderellaDVDWrapper);
        System.out.println("jungle dvd title: " + jungleDVDWrapper.disc.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVDWrapper.disc.getTitle());


    }

    public static void swap(Object o1, Object o2) {
        Object tmp = o1;
        o1 = o2;
        o2 = tmp;
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }

    public static void swap(DiscWrapper d1, DiscWrapper d2) {
        DigitalVideoDisc tmp = d1.disc;
        d1.disc = d2.disc;
        d2.disc = tmp;
    }
}
