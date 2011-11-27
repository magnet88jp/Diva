package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;

@Entity
public class ActionType extends Model {
  public String name;
  @MaxSize(1000)
  public String summary;
  @MaxSize(1000)
  public String process;

  public ActionType(String name, String summary, String process) {
    this.name = name;
    this.summary = summary;
    this.process = process;
  }
  
  public String toString() {
    return name;
  }
}
