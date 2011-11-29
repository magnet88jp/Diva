package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;

@Entity
public class ActionColumn extends Model {
  public String actionId;
  public String columnId;

  public ActionColumn(String actionId, String columnId) {
    this.actionId = actionId;
    this.columnId = columnId;
  }
  
}
