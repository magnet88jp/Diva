package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class ActionMode extends Model {
  public String name;

  public ActionMode(String name) {
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
