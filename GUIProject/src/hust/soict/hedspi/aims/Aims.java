package hust.soict.hedspi.aims;
import java.util.Scanner;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.store.Store;

public class Aims {
    private static Store S = new Store();
    private static Cart C = new Cart();
    public static void main(String[] args){
        while(true){
            showMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            switch(choice){
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    seeCurrentCart();
                    break;
                case 0:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again");
            }
        }
    }



    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }



    public static void storeMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void viewStore(){
        while(true){
            S.printStore();
            storeMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice){
                case 1:
                    mediaDetail();
                    break;
                case 2:
                    System.out.println("Enter the title of the media to add to cart:");
                    String title = scanner.nextLine();
                    Media media = S.getMediaByTitle(title);
                    if (media != null) {
                        C.addMedia(media);
                        System.out.println("Media added to cart.");
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the title of the media to add to cart:");
                    title = scanner.nextLine();
                    media = S.getMediaByTitle(title);
                    if (media != null) {
                        if(media instanceof Playable) {
                            try {
								((Playable) media).play();
							} catch (Exception e) {
								e.printStackTrace();
							}
                        } else {
                            System.out.println("This media cannot be played.");
                        }
                    } else {
                        System.out.println("Media not found.");
                    }
                    break;
                case 4:
                    C.printCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void mediaDetail(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the title of the media to see detail:");
            String title = scanner.nextLine();
            Media media = S.getMediaByTitle(title);
            if(media != null) {
                System.out.println(media.toString());
                mediaDetailsMenu();
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        C.addMedia(media);
                        break;
                    case 2:
                        if (media instanceof Playable) {
                            try {
								((Playable) media).play();
							} catch (Exception e) {
								e.printStackTrace();
							}
                        } else {
                            System.out.println("This media cannot be played.");
                        }
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Media not found.");
            }
        }
    }

    public static void updateStore() {
        Scanner scanner = new Scanner(System.in);
        //scanner.useLocale(Locale.US);
        while (true) {
            System.out.println("Update Store: ");
            System.out.println("--------------------------------");
            System.out.println("1. Add a media");
            System.out.println("2. Remove a media");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMediaToStore(scanner);
                    break;
                case 2:
                    System.out.println("Enter the id of the media to remove:");
                    int id = scanner.nextInt();
                    S.removeMedia(id);
                    //System.out.println("Media removed from store.");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void addMediaToStore(Scanner scanner) {
        while (true) {
            System.out.println("Select the type of media to add:");
            System.out.println("1. Digital Video Disc (DVD)");
            System.out.println("2. Compact Disc (CD)");
            System.out.println("3. Book");
            System.out.println("0. Back");
            System.out.println("--------------------------------");
            System.out.println("Please choose a number: 0-1-2-3");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter DVD details (id, title, category, director, length, cost):");
                    int dvdId = scanner.nextInt();
                    scanner.nextLine();
                    String dvdTitle = scanner.nextLine();
                    String dvdCategory = scanner.nextLine();
                    String dvdDirector = scanner.nextLine();
                    int dvdLength = scanner.nextInt();
                    scanner.nextLine();
                    float dvdCost = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    DigitalVideoDisc dvd = new DigitalVideoDisc(dvdId, dvdTitle, dvdCategory, dvdDirector, dvdLength, dvdCost);
                    S.addMedia(dvd);
                    System.out.println("DVD added to store.");
                    break;
                case 2:
                    System.out.println("Enter CD details (id, title,length, category, artist, director, cost):");
                    int cdId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String cdTitle = scanner.nextLine();
                    int cdLength = scanner.nextInt();
                    scanner.nextLine();
                    String cdCategory = scanner.nextLine();
                    String cdArtist = scanner.nextLine();
                    String cdDirector = scanner.nextLine();
                    float cdCost = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    CompactDisc cd = new CompactDisc(cdId, cdTitle, cdCategory,cdCost,cdLength, cdArtist, cdDirector);

                    System.out.println("Enter the number of tracks:");
                    int trackCount = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    for (int i = 0; i < trackCount; i++) {
                        System.out.println("Enter track details (title, length):");
                        String trackTitle = scanner.nextLine();
                        int trackLength = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        cd.addTrack(new Track(trackTitle, trackLength));
                    }
                    S.addMedia(cd);
                    System.out.println("CD added to store.");
                    break;
                case 3:
                    System.out.println("Enter Book details (id, title, category, cost):");
                    int bookId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    String bookTitle = scanner.nextLine();
                    String bookCategory = scanner.nextLine();
                    float bookCost = scanner.nextFloat();
                    scanner.nextLine(); // Consume newline
                    Book book = new Book(bookId, bookTitle, bookCategory, bookCost);

                    // Adding authors to the book
                    System.out.println("Enter the number of authors:");
                    int authorCount = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    for (int i = 0; i < authorCount; i++) {
                        System.out.println("Enter author's name:");
                        String authorName = scanner.nextLine();
                        book.addAuthor(authorName);
                    }
                    S.addMedia(book);
                    System.out.println("Book added to store.");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void seeCurrentCart() {
        while (true) {
            C.printCart();
            cartMenu();
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.println("Filter by (1) ID or (2) Title:");
                    int filterChoice = scanner.nextInt();
                    if (filterChoice == 1) {
                        //scanner = new Scanner(System.in);
                        //System.out.println("Enter the id to filter:");
                        //int id = scanner.nextInt();
                        //C.filterById(id);
                        System.out.println("You was choose filter by ID");
                    } else if (filterChoice == 2) {
                        //C.filterByTitle();
                        System.out.println("You was choose filter by Title");
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 2:
                    System.out.println("Sort by (1) Title or (2) Cost:");
                    int sortChoice = scanner.nextInt();
                    if (sortChoice == 1) {
                        //C.sortByTitle();
                    } else if (sortChoice == 2) {
                        //C.sortByCost();
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
                case 3:
                    System.out.println("Enter the title of the media to remove:");
                    String title = scanner.nextLine();
                    Media mediaToRemove = S.getMediaByTitle(title);
                    C.removeMedia(mediaToRemove);
                    System.out.println("Media removed from cart.");
                    break;
                case 4:
                    System.out.println("Enter the title of the media to play:");
                    String titleToPlay = scanner.nextLine();
                    Media mediaToPlay = S.getMediaByTitle(titleToPlay);
                    if (mediaToPlay instanceof Playable) {
                        try {
							((Playable) mediaToPlay).play();
						} catch (Exception e) {
							e.printStackTrace();
						}
                    } else {
                        System.out.println("Media not found or cannot be played.");
                    }
                    break;
                case 5:
                    System.out.println("Order placed. Cart is now empty.");
                    C.cartClear();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }


}
