import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {
    @Test
    public void constructorSucceedsWithValidDueDate() {
        TaskItem t = new TaskItem("Task 1", "My first task", "2020-12-01");

        assertEquals("Task 1", t.getTitle());
        assertEquals("My first task", t.getDescription());
        assertEquals("2020-12-01", t.getDate().toString());
    }

    @Test
    public void constructorFailsWithInvalidDueDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaskItem("t", "", "202-22-333");
        });
    }

    @Test
    public void constructorSucceedsWithValidTitle() {
        TaskItem t = new TaskItem("Task 1", "My first task", "2020-12-01");

        assertEquals("Task 1", t.getTitle());
        assertEquals("My first task", t.getDescription());
        assertEquals("2020-12-01", t.getDate().toString());
    }

    @Test
    public void constructorFailsWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TaskItem("", "", "2020-12-01");
        });
    }

    @Test
    public void editingTitleSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        task.update("Task A", task.getDescription(), task.getDate().toString());

        assertEquals("Task A", task.getTitle());
        assertEquals("My first task", task.getDescription());
        assertEquals("2020-12-01", task.getDate().toString());
    }

    @Test
    public void editingTitleFailsWithEmptyString() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        assertThrows(IllegalArgumentException.class, () -> task.update("", task.getDescription(), task.getDate().toString()));

        assertEquals("Task 1", task.getTitle());
        assertEquals("My first task", task.getDescription());
        assertEquals("2020-12-01", task.getDate().toString());
    }

    @Test
    public void editingDescriptionSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        task.update(task.getTitle(), "Do programming assignment", task.getDate().toString());

        assertEquals("Task 1", task.getTitle());
        assertEquals("Do programming assignment", task.getDescription());
        assertEquals("2020-12-01", task.getDate().toString());
    }

    @Test
    public void editingDueDateSucceedsWithExpectedValue() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        TaskList tl1 = new TaskList();
        tl1.add(task);
        tl1.update(0, task.getTitle(), task.getDescription(), "2020-12-02");

        task.update(task.getTitle(), "My first task", task.getDate().toString());

        assertEquals("Task 1", task.getTitle());
        assertEquals("My first task", task.getDescription());
        assertEquals("2020-12-02", task.getDate().toString());
    }

    @Test
    public void editingDueDateFailsWithInvalidDateFormat() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        assertThrows(IllegalArgumentException.class, () -> task.update(task.getTitle(), task.getDescription(), "20-23-36"));

        assertEquals("Task 1", task.getTitle());
        assertEquals("My first task", task.getDescription());
        assertEquals("2020-12-01", task.getDate().toString());
    }

    @Test
    public void  editingDueDateFailsWithInvalidYYYMMDD() {
        TaskItem task = new TaskItem("Task 1", "My first task", "2020-12-01");

        assertThrows(IllegalArgumentException.class, () -> task.update("", task.getDescription(), "2020-02-31"));

        assertEquals("Task 1", task.getTitle());
        assertEquals("My first task", task.getDescription());
        assertEquals("2020-12-01", task.getDate().toString());
    }
}