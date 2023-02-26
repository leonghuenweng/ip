import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static File file;

    public Storage(){
        file =new File("C:\\Users\\leong\\Desktop\\Comp_eng\\Y2S2\\CS2113\\Duke\\ip\\src\\data.txt");
    }
    //format of file = number.type.done.name.from.to
    public static void loadFileContentsToTaskArray() throws FileNotFoundException {
        Scanner scanner = new Scanner(file); // create a Scanner using the File as the source
        while (scanner.hasNext()) {
            String[] taskDetails = (scanner.nextLine()).split("\\.");
            if(taskDetails[1].equals("T")){
                ToDo todo= new ToDo(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[1]);
                todo.isDone = taskDetails[2].equals("[X]");
                todo.updateTaskDescription();
                Tasklist.addToTaskArrayList(todo);
            }
            else if(taskDetails[1].equals("E")){
                Event event = new Event(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[4],taskDetails[5],taskDetails[1]);
                event.isDone = taskDetails[2].equals("[X]");
                event.updateTaskDescription();
                Tasklist.addToTaskArrayList(event);
            }
            else if(taskDetails[1].equals("D")){
                Deadline deadline = new Deadline(taskDetails[3],Integer.parseInt(taskDetails[0]),taskDetails[4],taskDetails[1]);
                deadline.isDone = taskDetails[2].equals("[X]");
                deadline.updateTaskDescription();
                Tasklist.addToTaskArrayList(deadline);
            }
        }
    }

    public static void makeNewFile(){
        try{
            file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void save(){
        try{
            if(Tasklist.lastIndex>0){     //possible to use continue here instead?
                FileWriter fileWriter = new FileWriter(file);
                for(int index=0;index<Tasklist.lastIndex;index++){
                    fileWriter.append((Tasklist.get(index)).createEntry());
                }
                fileWriter.close();
            }
            if(Tasklist.lastIndex==0){
                FileWriter fw = new FileWriter(file);
                fw.close();
            }
            UserInterface.savedMessage();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}