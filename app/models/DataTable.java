package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class DataTable extends Model {
  public String name;
  public String title;

  public DataTable(String name, String title) {
    this.name = name;
    this.title = title;
  }
  
  public String toString() {
    return name;
  }
}
