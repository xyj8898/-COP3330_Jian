import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    private TaskList taskListItems = new TaskList();
    private TaskItem task = new TaskItem();

    @Test
    public void addingTaskItemsIncreasesSize() {
        taskListItems.addTaskItems(task);
        assertEquals(1, taskListItems.getSize());
    }

    @Test
    public void completingTaskItemChangesStatus() {
        int index = 0;
        taskListItems.addTaskItems(task);
        assertEquals(1, taskListItems.changeStatus("completed", index));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        int index = -1;
        taskListItems.addTaskItems(task);
        assertEquals(3, taskListItems.changeStatus("completed", index));
    }

    @Test
    public void editingTaskItemChangesValues() {
        int index = 0;
        taskListItems.addTaskItems(task);
        String title = "edit title";
        String description = "edit description";
        String dueDate = "edit due date";
        assertEquals(true, taskListItems.editTaskItems(title, description, dueDate, index));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        int index = 0;
        String description = "new description";
        taskListItems.addTaskItems(task);
        assertEquals(true, taskListItems.editTaskItemDescription(description,index));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        int index = -1;
        String description = "new description";
        taskListItems.addTaskItems(task);
        assertEquals(false, taskListItems.editTaskItemDescription(description, index));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        int index = 0;
        String date = "2020-11-02";
        taskListItems.addTaskItems(task);
        assertEquals(true, taskListItems.editTaskItemDueDate(date, index));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        int index = -1;
        String date = "2020-11-02";
        taskListItems.addTaskItems(task);
        assertEquals(false, taskListItems.editTaskItemDueDate(date, index));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        int index = 0;
        String title = "new title";
        taskListItems.addTaskItems(task);
        assertEquals(true, taskListItems.editTaskItemTitle(title,index));
    }

    @Test
    public void editingTaskItemTitleFailsWithInvalidIndex() {
        int index = -1;
        String title = "new title";
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.editTaskItemTitle(title, index));
    }

    @Test
    public void gettingTaskItemDescriptionFailsWithInvalidIndex() {
        int index = -1;
        taskListItems.addTaskItems(task);
        assertEquals(false, taskListItems.isGetTaskItemDescriptionValid(index));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        int index = 0;
        taskListItems.addTaskItems(task);
        assertEquals(true, taskListItems.isGetTaskItemDescriptionValid(index));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        int index = -1;
        taskListItems.addTaskItems(task);
        assertEquals(false, taskListItems.isGetTaskItemDueDateValid(index));
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        int index = 0;
        taskListItems.addTaskItems(task);
        assertEquals(true, taskListItems.isGetTaskItemDueDateValid(index));
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        int index = -1;
        taskListItems.addTaskItems(task);
        assertEquals(false, taskListItems.isGetTaskItemTitleValid(index));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        int index = 0;
        taskListItems.addTaskItems(task);
        assertEquals(true, taskListItems.isGetTaskItemTitleValid(index));
    }

    @Test
    public void newTaskListIsEmpty() {
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.isTaskListEmpty(taskList));
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        int index = 0, size = -1;
        taskListItems.addTaskItems(task);
        if (taskListItems.removeTaskItems(index)) {
            size = taskListItems.getSize();
        }
        assertEquals(0, size);
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        int index = -1;
        taskListItems.addTaskItems(task);
        assertEquals(false, taskListItems.removeTaskItems(index));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        taskListItems.addTaskItems(task);
        String fileName = "taskList.txt";
        taskListItems.saveTaskList(fileName);
        assertEquals(true, taskListItems.isLoadValid(fileName));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        int index = 0;
        taskListItems.addTaskItems(task);
        assertEquals(2, taskListItems.changeStatus("uncomplete", index));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        int index = -1;
        TaskList taskList = new TaskList();
        assertEquals(3, taskList.changeStatus("uncomplete", index));
    }
}