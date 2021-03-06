package task;

import java.time.LocalDateTime;

import duke.Parser;

/**
 * Task is the main functionality of Duke. It is the unit that information given by the user
 * is stored as in Duke and is also how information from Duke is read to the user. It is an
 * abstract class and can only be represented as a Deadline, Event or Todo_task.
 *
 * @author Joshua
 */
public abstract class Task {
    /**
     * taskDescription is the contents of the task.
     * isCompleted is the indicator of whether the task has been done or not.
     * date is the scheduled time for the task to occur.
     */
    protected String taskDescription;
    protected boolean isCompleted;
    protected LocalDateTime date;
    protected boolean isRepeated = false;
    protected Parser.FrequencyOfRecurrence frequency;

    /**
     * Creates the task with the given taskDescription. Initializes the task as incomplete.
     *
     * @param taskDescription the description for the task given by the user.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isCompleted = false;
    }

    public abstract void setRepeated(Parser.FrequencyOfRecurrence frequency);

    public LocalDateTime getTime() {
        return date;
    }

    public Parser.FrequencyOfRecurrence getFrequency() {
        return frequency;
    }

    /**
     * Formats the way that the task is to be saved.
     *
     * @return the formatted task.
     */
    public abstract String saveFormat();

    /**
     * Changes the task from incomplete to completed.
     */
    public void completeTask() {
        isCompleted = true;
    }

    /**
     * Returns the task description.
     *
     * @return task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }
}
