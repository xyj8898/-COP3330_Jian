import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest {
    private String title = "t";
    private String description = "";
    private String date = "2020-11-03";

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        TaskItem task = new TaskItem(title, description, date);
        assertEquals("2020-11-03", task.getDate());
    }
    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        TaskItem task = new TaskItem(title, description, date);
        assertEquals("", task.getDate());
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        TaskItem task = new TaskItem(title, description, date);
        assertEquals("t", task.getTitle());
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        TaskItem task = new TaskItem();
        assertEquals("", task.getTitle());
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        TaskItem task = new TaskItem();
        assertEquals(true, task.setDate("2020-11-03"));
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        TaskItem task = new TaskItem();
        assertEquals(false, task.setDate("11-03"));
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        TaskItem task = new TaskItem();
        assertEquals(true, task.setTitle("t"));
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        TaskItem task = new TaskItem();
        assertEquals(false, task.setTitle(""));
    }
}