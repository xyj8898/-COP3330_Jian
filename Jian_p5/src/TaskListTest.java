import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void newTaskListIsEmpty() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    public void addingTaskItemsIncreasesSize() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertEquals(1, taskListItems.size());
    }

    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.update(0, task.getTitle(), "My second task", task.getDate().toString());

        assertEquals("My second task", taskListItems.getTaskDescription(0));
    }

    @Test
    public void editingItemDescriptionFailsWithInvalidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.update(1, task.getTitle(), "New description", task.getDate().toString()));
    }

    @Test
    public void editingItemDueDateSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.update(0, task.getTitle(), task.getDescription(), "2020-12-02");

        assertEquals("2020-12-02", taskListItems.getTaskDueDate(0).toString());
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.update(1, task.getTitle(), task.getDescription(), task.getDate().toString()));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IllegalArgumentException.class, () -> taskListItems.update(0, task.getTitle(), task.getDescription(), "2020-02-30"));
    }

    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IllegalArgumentException.class, () -> taskListItems.update(0, task.getTitle(), task.getDescription(), "202-012-30"));
    }

    @Test
    public void editingItemTitleSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.update(0, "Task A", task.getDescription(), task.getDate().toString());

        assertEquals("Task A", taskListItems.getTaskTitle(0));
    }

    @Test
    public void editingItemTitleFailsWithInvalidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.update(1, "Task A", task.getDescription(), task.getDate().toString()));
    }

    @Test
    public void editingItemTitleFailsWithEmptyString() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IllegalArgumentException.class, () -> taskListItems.update(0, "", task.getDescription(), task.getDate().toString()));
    }

    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex() {
        TaskList taskListItems = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.getTaskDescription(0));
    }

    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertEquals("My first task", taskListItems.getTaskDescription(0));
    }

    @Test
    public void gettingTaskItemDueDateFailsWithInvalidIndex() {
        TaskList taskListItems = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.getTaskDueDate(0));
    }

    @Test
    public void gettingItemDueDateSucceedsWithValidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertEquals("2020-12-01", taskListItems.getTaskDueDate(0));
    }

    @Test
    public void gettingItemTitleFailsWithInvalidIndex() {
        TaskList taskListItems = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.getTaskTitle(0));
    }

    @Test
    public void gettingItemTitleSucceedsWithValidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertEquals("Task 1", taskListItems.getTaskTitle(0));
    }

    @Test
    public void removingItemsDecreasesSize() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.remove(0);

        assertEquals(0, taskListItems.size());
    }

    @Test
    public void removingItemsFailsWithInvalidIndex() {
        TaskList taskList1 = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList1.remove(0));
    }

    @Test
    public void savedTaskListCanBeLoaded() {
        TaskList taskListItems = new TaskList();
        taskListItems.add(new TaskItem("Task 1", "My first task", "2020-12-01"));
        taskListItems.add(new TaskItem("Task 2", "My second task", "2020-12-01"));
        taskListItems.save("test_tasks.txt");

        taskListItems.load("test_tasks.txt");

        assertEquals(2, taskListItems.size());
        assertEquals("[2020-12-01] Task 1: My first task", taskListItems.getTaskText(0));
        assertEquals("[2020-12-01] Task 2: My second task", taskListItems.getTaskText(1));
    }

    @Test
    public void completingTaskItemChangesStatus() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.complete(0, true);

        assertTrue(taskListItems.isTaskComplete(0));
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.complete(0, true);

        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.complete(1, true));
    }

    @Test
    public void uncompletingTaskItemChangesStatus() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);
        taskListItems.complete(0, false);

        assertFalse(taskListItems.isTaskComplete(0));
    }

    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList taskListItems = new TaskList();
        taskListItems.add(task);

        assertThrows(IndexOutOfBoundsException.class, () -> taskListItems.complete(1, false));
    }
}