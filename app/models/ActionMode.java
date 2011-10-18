package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class ActionMode extends Model {
  public int modeId;
  public String name;

  public ActionMode(int modeId, String name) {
    this.modeId = modeId;
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
