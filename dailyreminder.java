import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class dailyreminder {
    private static final String TODO_FILE = "todo.txt";
    private static final String[] QUOTES = {
        "Stay positive, work hard, make it happen.",
        "Success doesn’t just find you. You have to go out and get it.",
        "Dream it. Wish it. Do it.",
        "Push yourself, because no one else is going to do it for you.",
        "Great things never come from comfort zones."
    };

    public static void main(String[] args) {
        greetUser();
        showRandomQuote();
        showToDoList();
        addNewTask();
    }

    private static void greetUser() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss");
        System.out.println("📅 Welcome! Today is: " + now.format(formatter));
        System.out.println("--------------------------------------------------");
    }

    private static void showRandomQuote() {
        Random rand = new Random();
        String quote = QUOTES[rand.nextInt(QUOTES.length)];
        System.out.println("💡 Motivational Quote of the Day:");
        System.out.println("\"" + quote + "\"");
        System.out.println("--------------------------------------------------");
    }

    private static void showToDoList() {
        System.out.println("📝 Your To-Do List:");
        try {
            List<String> tasks = Files.readAllLines(Paths.get(TODO_FILE));
            if (tasks.isEmpty()) {
                System.out.println("No tasks found. You're all caught up!");
            } else {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing to-do list found.");
        }
        System.out.println("--------------------------------------------------");
    }

    private static void addNewTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to add a new task? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {
            System.out.print("Enter your task: ");
            String newTask = scanner.nextLine().trim();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(TODO_FILE, true))) {
                writer.write(newTask);
                writer.newLine();
                System.out.println("✅ Task added successfully!");
            } catch (IOException e) {
                System.out.println("❌ Error saving the task.");
            }
        } else {
            System.out.println("No new task added. Have a productive day!");
        }

        System.out.println("--------------------------------------------------");
    }
}
