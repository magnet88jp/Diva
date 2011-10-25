package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class DataColumn extends Model {
  @OneToOne
  public DataTable dataTable;
  public String name;

  public DataColumn(DataTable dataTable, String name) {
    this.dataTable = dataTable;
    this.name = name;
  }
  
  public String toString() {
    return dataTable.name + ":" + name;
  }

//  public DataColumn getDataColumn() {
//    if( dataTable.name == "visitor_data" ) {
//      return this;
//    } else {
//      throw NullPointerException();
//    }
//  }

}
