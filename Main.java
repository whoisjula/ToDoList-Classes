package todolist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		System.out.println("----- Main Menu -----");
		System.out.println(" ");
		System.out.println("1. Create a new task");
		System.out.println("2. List all tasks");
		System.out.println("3. Remove a task");
		System.out.println("4. Exit");
		System.out.println(" ");

		// ArrayList for tasks
		ArrayList<Task> tasklist = new ArrayList<>();

		loadTasks(tasklist);

		while (true) {
			// Get the user choice
			System.out.print("What would you like to do? Enter 1-4: ");
			Scanner userChoice = new Scanner(System.in);
			int userNum = userChoice.nextInt();
			userChoice.nextLine();

			// Respond to user choice
			if (userNum == 1) {
				addTasks(tasklist, userChoice);
			}

			else if (userNum == 2) {
				showTasklist(tasklist);
			}

			else if (userNum == 3) {
				removeTask(tasklist, userChoice);
			}

			else if (userNum == 4) {
				System.out.print("Exiting Progam...");
				break;
			}

			else {
				System.out.println("Input was invalid. Enter 1-4.");
			}
		}
	}

	// Function to add tasks to the list.
	public static void addTasks(ArrayList<Task> tasks, Scanner choice) {
		System.out.print("Enter name of the task: ");
		String title = choice.nextLine();

		System.out.print("Describe the task: ");
		String description = choice.nextLine();

		Task task = new Task();
		task.title = title;
		task.description = description;

		tasks.add(task);
		saveTasks(tasks);
	}

	// Function to show all the tasks in the list
	public static void showTasklist(ArrayList<Task> tasks) {
		int count = 0;
		if (tasks.isEmpty()) {
			System.out.println("There are no tasks in the list.");
		} else {
			System.out.println("Here are all the current tasks: ");

			for (Task task : tasks) {
				System.out.println((count + 1) + ". " + task.getWholetask());
				count++;
			}
		}
	}

	// Function used to remove a task from the list
	public static void removeTask(ArrayList<Task> tasks, Scanner choice) {
		System.out.print("Which task would you like to remove? Enter index: ");
		int removeTask = choice.nextInt();
		tasks.remove(removeTask - 1);
		saveTasks(tasks);
	}

	// Function to load task list from a file
	public static void loadTasks(ArrayList<Task> tasks) {
		try {
			File myObj = new File("tasklist.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String str = myReader.nextLine();
				String[] data = str.split(",");

				String title = data[0];
				String description = data[1];

				Task task = new Task();
				task.title = title;
				task.description = description;
				tasks.add(task);
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	// Function to save tasks to a file
	public static void saveTasks(ArrayList<Task> tasks) {
		try {
			File arList = new File("tasklist.txt");
			FileWriter myWriter = new FileWriter(arList);

			for (int i = 0; i < tasks.size(); i++) {
				myWriter.write(tasks.get(i).title + "," + tasks.get(i).description);
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("Error occurred.");
			e.printStackTrace();
		}
	}
}
