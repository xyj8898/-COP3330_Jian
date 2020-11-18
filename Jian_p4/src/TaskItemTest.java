import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskItemTest {
    private String title = "t";
    private String description = "";
    private String date = "2020-11-03";
    private TaskItem task = new TaskItem();

    @Test
    public void creatingTaskItemSucceedsWithValidDueDate() {
        System.out.println(date.length());
        assertEquals(true, task.setDate(date));
    }

    @Test
    public void creatingTaskItemFailsWithInvalidDueDate() {
        date = "202-22-333";
        assertEquals(false, task.setDate(date));
    }

    @Test
    public void creatingTaskItemSucceedsWithValidTitle() {
        assertEquals(true, task.setTitle(title));
    }

    @Test
    public void creatingTaskItemFailsWithInvalidTitle() {
        title = "";
        assertEquals(false, task.setTitle(title));
    }

    @Test
    public void settingTaskItemDueDateSucceedsWithValidDate() {
        assertEquals(true, task.setDate("2020-11-03"));
    }

    @Test
    public void settingTaskItemDueDateFailsWithInvalidDate() {
        assertEquals(false, task.setDate("11-03"));
    }

    @Test
    public void settingTaskItemTitleSucceedsWithValidTitle() {
        assertEquals(true, task.setTitle("t"));
    }

    @Test
    public void settingTaskItemTitleFailsWithInvalidTitle() {
        assertEquals(false, task.setTitle(""));
    }
}