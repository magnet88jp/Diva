package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;


@Entity
public class Setting extends Model {
  public int settingId;
  public int adminOnlyFlag;
  public String name;
  @MaxSize(4000)
  public String fillable;
  @MaxSize(4000)
  public String note;

  public Setting(int settingId, String name, String fillable, String note) {
    this.settingId = settingId;
    this.name = name;
    this.fillable = fillable;
    this.note = note;
  }

  public String toString() {
    return Integer.toString(settingId) + ":" + name;
  }
}
