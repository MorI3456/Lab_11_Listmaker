import java.util.ArrayList;
import java.util.Scanner;

public class Lab11_ListMaker {

    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            displayMenu();
            String choice = SafeInput.getRegExString(scanner, "Enter a choice: ", "[AaDdIiPpQq]").toUpperCase();
            switch (choice) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    quit = quitProgram();
                    break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit");
    }

    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(scanner, "Enter item to add: ");
        list.add(item);
        System.out.println("Item added.");
    }

    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        printList();
        int itemNumber = SafeInput.getRangedInt(scanner, "Enter item number to delete: ", 1, list.size());
        list.remove(itemNumber - 1);
        System.out.println("Item deleted.");
    }

    private static void insertItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Adding item as the first element.");
            addItem();
            return;
        }
        int position = SafeInput.getRangedInt(scanner, "Enter position to insert item (1 to " + (list.size() + 1) + "): ", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(scanner, "Enter item to insert: ");
        list.add(position - 1, item);
        System.out.println("Item inserted.");
    }

    private static void printList() {
        if (list.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("\nCurrent List:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }

    private static boolean quitProgram() {
        return SafeInput.getYNConfirm(scanner, "Are you sure you want to quit? (Y/N): ");
    }
}

class SafeInput {

    public static String getRegExString(Scanner scanner, String prompt, String regEx) {
        String input;
        boolean valid;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
            valid = input.matches(regEx);
            if (!valid) {
                System.out.println("Invalid input. Please try again.");
            }
        } while (!valid);
        return input;
    }

    public static String getNonZeroLenString(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine();
        } while (input.length() == 0);
        return input;
    }

    public static int getRangedInt(Scanner scanner, String prompt, int low, int high) {
        int value = 0;
        boolean valid = false;
        do {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if (value >= low && value <= high) {
                    valid = true;
                } else {
                    System.out.println("Invalid input. Please enter a value between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
            scanner.nextLine(); // clear the buffer
        } while (!valid);
        return value;
    }

    public static boolean getYNConfirm(Scanner scanner, String prompt) {
        String input;
        System.out.print(prompt);
        input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }
}
