import java.io.*;
import java.nio.file.Paths;
import java.util.*;

public class TaskList {
    private List<TaskItem> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public int size() {
        return items.size();
    }

    public void add(TaskItem task) {
        items.add(task);
    }

    private TaskItem get(int index) {
        return items.get(index);
    }

    public void remove(int index) {
        items.remove(index);
    }

    public String view() {
        StringBuilder s = new StringBuilder();
        TaskItem item;
        for (int i = 0; i < this.size(); i++) {
            item = this.get(i);
            if (item.isComplete()) {
                s.append(String.format("%d) *** %s%n", i, item));
            }
            else {
                s.append(String.format("%d) %s%n", i, item));
            }
        }
        return s.toString();
    }

    public String viewCompletedTasks() {
        StringBuilder s = new StringBuilder();
        TaskItem item;
        for (int i = 0; i < this.size(); i++) {
            item = this.get(i);
            if (item.isComplete()) {
                s.append(String.format("%d) %s%n", i, item));
            }
        }
        return s.toString();
    }

    public String viewUncompletedTasks() {
        StringBuilder s = new StringBuilder();
        TaskItem item;
        for (int i = 0; i < this.size(); i++) {
            item = this.get(i);
            if (!item.isComplete()) {
                s.append(String.format("%d) %s%n", i, item));
            }
        }
        return s.toString();
    }

    public void update(int index, String title, String description, String dueDate) {
        this.get(index).update(title, description, dueDate);
    }

    public void complete(int index, boolean completed) {
        this.get(index).complete(completed);
    }

    public void save(String fileName) {
        try (Formatter output = new Formatter(fileName)) {
            output.format("tasks%n");
            for (TaskItem item : items) {
                output.format("%s%n", item.getDate());
                output.format("%s%n", item.getTitle());
                output.format("%s%n", item.getDescription());
                if (item.isComplete()) {
                    output.format("completed%n");
                }
                else {
                    output.format("incomplete%n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String fileName) {
        List<TaskItem> backupList = items;

        items = new ArrayList<>();
        try (Scanner scan = new Scanner(Paths.get(fileName))) {
            String header = scan.nextLine();
            if (header.equalsIgnoreCase("tasks")) {
                while (scan.hasNext()) {
                    String dueDate = scan.nextLine();
                    String title = scan.nextLine();
                    String description = scan.nextLine();
                    String complete = scan.nextLine();

                    TaskItem item = new TaskItem(title, description, dueDate);
                    item.complete(complete.equalsIgnoreCase("complete"));
                    this.add(item);
                }
            }
            else {
                items = backupList;
                throw new InputMismatchException("WARNING: Filename is a not a valid task list. No data loaded");
            }
        } catch (FileNotFoundException e) {
            items = backupList;
            throw new IllegalArgumentException("WARNING: Task file not found. No task list loaded");
        } catch (IOException e) {
            items = backupList;
            throw new IllegalArgumentException("WARNING: Error loading the file. No task list loaded");
        }
    }

    public String getTaskTitle(int index) {
        return this.get(index).getTitle();
    }

    public String getTaskDescription(int index) {
        return this.get(index).getDescription();
    }

    public String getTaskDueDate(int index) {
        return this.get(index).getDate().toString();
    }

    public boolean isTaskComplete(int index) {
        return this.get(index).isComplete();
    }

    public String getTaskText(int index) {
        return this.get(index).toString();
    }
}
