package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;

@Entity
public class Task extends Model {

  public String name;
  @ManyToOne
  @Required
  public TaskMode taskMode;

  public Task(String name, TaskMode taskMode) {
    this.name = name;
    this.taskMode = taskMode;
  }

}
