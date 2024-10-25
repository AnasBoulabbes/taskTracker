import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){
        String action="empty";
        TaskManager tm=new TaskManager();
        tm.loadTasksFromJson();
        System.out.println("Welcome to Task Tracker the ultimate tool for efficiency and productivity.");
        promptEnterKey();
        System.out.println("To provoke the guide menu hit /h and the guide menu will pop up.");
        Scanner scanner=new Scanner(System.in);
        while (!action.equals("quit")){
            action= scanner.nextLine();
            String []word=action.split(" ");
            if(word[0].equals("/h")){
                helpGuide();
            }
            if (word[0].equals("add")){
                tm.addTask(action.split("\"")[1]);
            }
            if (word[0].equals("update")){
                tm.updateTask(Integer.parseInt(word[1]),action.split("\"")[1]);
            }
            if (word[0].equals("delete")){
                tm.deleteTask(Integer.parseInt(word[1]));
            }
            if(word[0].equals("mark")){
                tm.changeStatus(Integer.parseInt(word[2]),word[1]);
            }
            if (word[0].equals("list")) {
                if (word.length > 1 && (word[1].equals("in-progress") || word[1].equals("done") || word[1].equals("todo"))) {
                    tm.listByStatus(word[1]);
                } else {
                    tm.list();
                }
            }
        }
        try {
            tm.jsonToFile();
        }catch (IOException e){System.out.println("Unknown IO error");}
    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    public static void helpGuide(){
        System.out.println("To add a task use: add \"description\"");
        System.out.println("To update a task use: update \"id\" \"description\"");
        System.out.println("To change a task status use: mark-\"status\" \"id\"");
        System.out.println("To list all tasks use: list");
        System.out.println("To list tasks by status use: list \"status\"");
        System.out.println("To delete a task use: delete \"id\"");
    }
}