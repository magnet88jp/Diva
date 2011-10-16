package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class TaskMode extends Model {
  public String name;

  public TaskMode(String name) {
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
