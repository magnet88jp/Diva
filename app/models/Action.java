package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;
import play.data.validation.*;

@Entity
public class Action extends Model {
  @ManyToMany
  public List<ActionMode> actionModes;
  public String title;
  @MaxSize(4000)
  public String summary;
  @ManyToMany
  public List<Setting> settings;
  @OneToOne
  public Function function;
  @MaxSize(4000)
  public String process;
  @ManyToMany
  public List<DataTable> dataTables;
  @ManyToMany
  public List<MajorMessage> majorMessages;
  @MaxSize(4000)
  public String restriction;
  @MaxSize(4000)
  public String supplement;
  @MaxSize(4000)
  public String privateComment;
  @ManyToMany
  public List<Faq> faqs;

  public Action(String title, String summary, String process, String restriction, String supplement, String privateComment, Function function) {
    this.actionModes = new ArrayList<ActionMode>();
    this.title = title;
    this.summary = summary;
    this.settings = new ArrayList<Setting>();
    this.function = function;
    function.action = this;
    this.process = process;
    this.dataTables= new ArrayList<DataTable>();
    this.majorMessages = new ArrayList<MajorMessage>();
    this.restriction = restriction;
    this.supplement = supplement;
    this.privateComment = privateComment;
    this.faqs = new ArrayList<Faq>();
  }

  public String toString() {
    return title;
  }

//  public List<DataColumn> getDataColumns() {
//    List<DataColumn> aaa = new ArrayList<DataColumn>();
//    return aaa;
//  }

}
