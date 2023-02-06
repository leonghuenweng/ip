import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
public class Goot {
    public static void main(String[] args) {
    String DASH = "__________________________________";
    String greet = DASH + "\nI'm Goot :3\nWhat's up?\n" + DASH;
    System.out.println(greet);


    while (true) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] inputSplitBySpace = input.split(" ");

        if(inputSplitBySpace[0].equals("todo")){
            ToDo todo = new ToDo(input.substring(5), Task.lastIndex+1);
            Task.addToTaskArrayList(todo);
            todo.acknowledgeTaskAdded();
        }
        else if(inputSplitBySpace[0].equals("deadline")){
            Deadline deadline = new Deadline(Deadline.readName(input),Task.lastIndex+1,Deadline.readBy(input));
            Task.addToTaskArrayList(deadline);
            deadline.acknowledgeTaskAdded();
        }
        else if(inputSplitBySpace[0].equals("event")){
            String fromString = Event.readFromTo(input)[0];
            String toString = Event.readFromTo(input)[1];
            Event event = new Event(Event.readName(input),Task.lastIndex+1,fromString,toString);
            Task.addToTaskArrayList(event);
            event.acknowledgeTaskAdded();
        }
        else if(inputSplitBySpace[0].equals("mark")|inputSplitBySpace[0].equals("unmark")){
            int indexToMark = Integer.parseInt(inputSplitBySpace[1]);

            Task.markOrUnmark(indexToMark);
        }
        else if (input.equals("bye")) {
            System.out.println(DASH + "\nBye. Hope to see you again soon!\n" + DASH);
            break;
        }
        else if (input.equals("list")) {
            Task.printList();
        }
        else {
            System.out.println("There is no such command!");
        }
    }
}


}