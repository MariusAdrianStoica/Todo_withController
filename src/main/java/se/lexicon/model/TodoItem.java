package se.lexicon.model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    //fields
    private Integer id;
    private String title;
    private String description;
    private LocalDate deadline;
    private boolean done;
    private Person assignee;

    //constructor to fetch data
    public TodoItem(Integer id, String title, String description, LocalDate deadline, boolean done, Person assignee) {
        setId(id);        //set instead of this.value : you already run validations from setter
        setTitle(title);
        setDescription(description);
        setDeadline(deadline);
        setDone(done);
        setAssignee(assignee);
    }

    //just to create a task
    public TodoItem(String title, String description, LocalDate deadline) {
        setTitle(title);
        setDescription(description);
        setDeadline(deadline);
    }
    //getters &setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        if (id == null) throw new RuntimeException("id was null");
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) throw new IllegalArgumentException("title was null");
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        if (deadline == null) throw new IllegalArgumentException("deadline was null");
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) { //if null, it means that the task is available - no need to validate
        this.assignee = assignee;
    }

    //equals & hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TodoItem)) return false;
        TodoItem todoItem = (TodoItem) o;
        return isDone() == todoItem.isDone() && Objects.equals(getId(), todoItem.getId()) && Objects.equals(getTitle(), todoItem.getTitle()) && Objects.equals(getDescription(), todoItem.getDescription()) && Objects.equals(getDeadline(), todoItem.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getDeadline(), isDone());
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", done=" + done +
                ", personName=" + assignee.getFirstName() + " " + assignee.getLastName() +
                '}';
        // instead of assignee - a Person object, we can display firstname and lastname of the person
    }
}

