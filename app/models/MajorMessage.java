package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;


@Entity
public class MajorMessage extends Model {
  public int messageId;
  public String code;
  public String defaultMessage;
  @MaxSize(4000)
  public String note;

  public MajorMessage(int messageId, String code, String defaultMessage, String note) {
    this.messageId = messageId;
    this.code = code;
    this.defaultMessage = defaultMessage;
    this.note = note;
  }

  public String toString() {
    return Integer.toString(messageId) + ":" + defaultMessage;
  }

}
