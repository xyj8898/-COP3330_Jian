import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskList taskListItems = new TaskList();
        TaskItem task = new TaskItem();
        taskListItems.addTaskItems(task);
        assertEquals(1, taskListItems.getSize());
    }

    @Test
    public void completingTaskItemChangesStatus() {
        int index = 0;
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.changeStatus("completed", index));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        int index = -1;
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.changeStatus("completed", index));
    }

    @Test
    public void editingTaskItemChangesValues() {
        int index = 0;
        String title = "edit title";
        String description = "edit description";
        String dueDate = "edit due date";
        TaskList taskListValues = new TaskList();
        assertEquals(true, taskListValues.editTaskItems(title, description, dueDate, index));
    }

    @Test
    public void editingTaskItemDescriptionChangesValue() {
        int index = 0;
        String description = "new description";
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.editTaskItemDescription(description,index));
    }

    @Test
    public void editingTaskItemDescriptionFailsWithInvalidIndex() {
        int index = -1;
        String description = "new description";
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.editTaskItemDescription(description, index));
    }

    @Test
    public void editingTaskItemDueDateChangesValue() {
        int index = 0;
        String date = "2020-11-02";
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.editTaskItemDueDate(date, index));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        int index = -1;
        String date = "2020-11-02";
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.editTaskItemDueDate(date, index));
    }

    @Test
    public void editingTaskItemTitleChangesValue() {
        int index = 0;
        String title = "new title";
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.editTaskItemTitle(title,index));
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
        int index = 0;
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.isGetTaskItemDescriptionValid(index));
    }

    @Test
    public void gettingTaskItemDescriptionSucceedsWithValidIndex() {
        int index = 0;
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.isGetTaskItemDescriptionValid(index));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        int index = -1;
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.isGetTaskItemDueDateValid(index));
    }

    @Test
    public void gettingTaskItemDueDateSucceedsWithValidIndex() {
        int index = 0;
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.isGetTaskItemDueDateValid(index));
    }

    @Test
    public void gettingTaskItemTitleFailsWithInvalidIndex() {
        int index = -1;
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.isGetTaskItemTitleValid(index));
    }

    @Test
    public void gettingTaskItemTitleSucceedsWithValidIndex() {
        int index = 0;
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.isGetTaskItemTitleValid(index));
    }

    @Test
    public void newTaskListIsEmpty() {
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.isTaskListEmpty(taskList));
    }

    @Test
    public void removingTaskItemsDecreasesSize() {
        int index = 0;
        TaskList taskList = new TaskList();
        TaskItem task = new TaskItem();
        taskList.addTaskItems(task);
        taskList.removeTaskItems(index);
        assertEquals("0", taskList.getSize());
    }

    @Test
    public void removingTaskItemsFailsWithInvalidIndex() {
        int index = -1;
        TaskList taskList = new TaskList();
        assertEquals("Invalid Index", taskList.removeTaskItems(index));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        TaskList taskList = new TaskList();
        String fileName = "taskList.txt";
        taskList.saveTaskList(fileName);
        assertEquals(true, taskList.isLoadValid(fileName));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        int index = 0;
        TaskList taskList = new TaskList();
        assertEquals(true, taskList.changeStatus("uncomplete", index));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        int index = -1;
        TaskList taskList = new TaskList();
        assertEquals(false, taskList.changeStatus("uncomplete", index));
    }
}