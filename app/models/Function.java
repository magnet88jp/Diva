package models;

import java.util.*;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import play.db.jpa.Model;

@Entity
public class Function extends Model {
  public int functionId;
  public String name;
  public String actionPath;
  public Action action;

  public Function(int functionId, String name, String actionPath) {
    this.functionId = functionId;
    this.name = name;
    this.actionPath = actionPath;
    this.action = null;
  }
  
  public String toString() {
    return Integer.toString(functionId) + ":" + name;
  }
}
