package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class ActionTerm extends Model {
  public String code;
  public String name;

  public ActionTerm(String code, String name) {
    this.code = code;
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
