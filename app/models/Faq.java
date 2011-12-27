package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;

@Entity
public class Faq extends Model {
  public String summary;
//  @MaxSize(4000)
  public String question;
  @Lob
  @MaxSize(4000)
  public String answer;
//  @MaxSize(10000)
  public String detail;
//  @MaxSize(4000)
  public String privateComment;
  @ManyToMany
  public Set<Tag> tags;

  public Faq(String summary, String question, String answer, String detail, String privateComment) {
    this.summary = summary;
    this.question = question;
    this.answer = answer;
    this.detail = detail;
    this.privateComment = privateComment;
    this.tags = new TreeSet<Tag>();
  }

  public String toString() {
    return question;
  }
}

