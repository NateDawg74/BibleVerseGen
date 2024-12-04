package Main;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibleVerseGenerator generator = new BibleVerseGenerator("src/resources/verses.json");

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the Random Bible Verse Generator!");
        System.out.println("============================================");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Display a random Bible verse");
            System.out.println("2. Search for a Bible verse by keyword");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("\nRandom Bible Verse:");
                    System.out.println(generator.getRandomVerse());
                    break;
                case 2:
                    System.out.print("\nEnter a keyword to search: ");
                    String keyword = scanner.nextLine();
                    List<String> results = generator.searchVerses(keyword);
                    if (results.isEmpty()) {
                        System.out.println("No verses found with the keyword \"" + keyword + "\".");
                    } else {
                        System.out.println("\nSearch Results:");
                        for (String verse : results) {
                            System.out.println("- " + verse);
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nThank you for using the Random Bible Verse Generator. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
