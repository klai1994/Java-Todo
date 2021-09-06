import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in); 
		ToDoList toDoList = new ToDoList();
		String command = "show";
		
		String help = "Commands:\n"
				+ "Show - Lists all items\n"
				+ "Add - Allows you to add a to-do item\n"
				+ "Remove - Allows you to remove a to-do item\n"
				+ "Help - Show Commands\n"
				+ "Exit - Quits the program";
		
		System.out.println(help);
		
		while (!command.equals("exit")) {
			
			if (command.equals("show")) {
				toDoList.showList();
			}
			
			else if (command.equals("add")) {
				System.out.println("Enter item: ");
				toDoList.addItem(scanner.nextLine());
				System.out.println("Added successfully.");
			}
			
			else if (command.equals("remove")) {
				System.out.println("Enter item number to be removed: ");
				try {
					toDoList.removeItem(scanner.nextInt());
				} catch (InputMismatchException e) {
					System.out.println("Invalid input. You must choose a whole number from the list.");
				}
				scanner.nextLine();
			}
			
			else if (command.equals("help")) {
				System.out.println(help);
			}
			
			else {
				System.out.println("Invalid input. Please try again.");
			}
			
			command = scanner.nextLine().toLowerCase().trim();
		}
		
	}
	
}

class ToDoList {
	
	ToDoItem head;
	
	void showList() {
		
		ToDoItem currItem = head;
		int counter = 1;
		
		while (currItem != null) {
			System.out.println(counter + ": " + currItem.task);
			currItem = currItem.next;
			counter++;
		}
	}
	
	void addItem (String item) {
		ToDoItem newItem = new ToDoItem(item);
		
		if (head == null) {
			head = newItem;
		} else {
			ToDoItem lastItem = head;
			while (lastItem.next != null) {
				lastItem = lastItem.next;
			}
			lastItem.next = newItem;
		}
	}
	
	void removeItem (int index) {

		if (index == 1) {
			head = head.next;
			return;
		}
		
		ToDoItem previousItem = head;
		ToDoItem currentItem = head.next;
		int counter = 2;

		while (counter != index) {
			counter++;
			previousItem = currentItem;
			currentItem = currentItem.next;
			// No item at index specified
			if (currentItem == null) {
				return;
			}
		}
		
		previousItem.next = currentItem.next;
	
	}
	
}

class ToDoItem{
	
	public ToDoItem (String task) {
		this.task = task;
	}
	
	String task;
	ToDoItem next;
}
