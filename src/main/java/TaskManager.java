import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskManager {
    private List<Task> tasks=new ArrayList<>();
    public void jsonToFile() throws IOException {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        try (FileWriter writer = new FileWriter("tasks.json")) {
            gson.toJson(tasks, writer); // Write the tasks list to the file
        }
    }
    public void addTask(String description){
        tasks.add(tasks.size(),new Task(tasks.size()+1,description,"todo",LocalDateTime.now(),null));
    }

    public void updateTask(int id,String description){
        for(Task task:tasks){
            if(task.getId()==id){
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
            }
        }
    }

    public void deleteTask(int id){
        if (tasks.removeIf(task -> task.getId() == id)) {
            for (int i = 0; i < tasks.size(); i++) {
                tasks.get(i).setId(i+1);
            }
        }
    }
    public void changeStatus(int id,String status){
        for (Task task:tasks){
            if (task.getId()==id) {
                task.setStatus(status);
                task.setUpdatedAt(LocalDateTime.now());
            }
        }
    }

    public void list(){
        for(Task task:tasks){
            System.out.println(task.getId()+"  "+task.getDescription()+"  "+task.getStatus()+"  "+task.getCreatedAt()+"  "+task.getUpdatedAt());
        }
    }

    public void listByStatus(String status){
        for(Task task:tasks){
            if(task.getStatus().equals(status))
                System.out.println(task.getId()+"  "+task.getDescription()+"  "+task.getStatus()+"  "+task.getCreatedAt()+"  "+task.getUpdatedAt());
        }
    }
}
