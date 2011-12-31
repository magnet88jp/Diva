package models;

import java.util.*;
import java.security.*;
import javax.persistence.*;
import play.db.jpa.Model;
import play.data.validation.*;

@Entity
public class Faq extends Model {
  public String code;
  public String url;
  public String originalSummary;
//  public String summary;
//  @MaxSize(4000)
  @Lob
  @MaxSize(4000)
  public String originalDescription;
  public String checksum;
  public String question;
  @Lob
  @MaxSize(4000)
  public String answer;
  @Lob
  @MaxSize(4000)
  public String detail;
  @Lob
  @MaxSize(4000)
  public String privateComment;
  @ManyToMany
  public Set<Tag> tags;

  public Faq(String code, String url, String originalSummary, String originalDescription, String updatedOn) {
    this.code = code;
    this.url = url;
    this.originalSummary = originalSummary;
    this.originalDescription = originalDescription;
//    this.checksum = checkSum(originalDescription);
    this.checksum = updatedOn;
    this.question = "";
    this.answer = "";
    this.detail = "";
    this.privateComment = "";
    this.tags = new TreeSet<Tag>();
  }

  public String toString() {
    return originalSummary;
  }

  static public String checkSum(String source) {
    StringBuilder sb = null;
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");  
      md.update(source.getBytes());  
      byte[] digest = md.digest();
      sb = new StringBuilder();
      for (int i = 0; i < digest.length; i++) {
        sb.append(Integer.toHexString(0xff & digest[i]));
      }
    } catch (NoSuchAlgorithmException e) {
    }
    return sb.toString();
  }

}

