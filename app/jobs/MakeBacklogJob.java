package jobs;

import java.util.*;
import java.io.*;
import play.Logger;
import play.jobs.*;
import play.templates.Template;
import play.templates.TemplateLoader;
import play.libs.XML;
import play.libs.XPath;
import play.libs.WS;
import play.libs.WS.*;
import org.w3c.dom.*;
import models.*;

public class MakeBacklogJob extends Job {

  public void doJob() {
    // execute some application logic here ...
    try {
      // get backlog data
      String user = "nishino";
      String pass = "lepures8";

      WSRequest wsreq = WS.url("https://shanonpj.backlog.jp/XML-RPC").authenticate(user, pass);

      Template template = TemplateLoader.load("conf/templates/backlog.xml");
      Map<String, Object> map = new HashMap<String, Object>(16);
      map.put("projectId", "5816");
      map.put("limit", "100");
      map.put("offset", "2");
      String strXml = template.render(map);
      Logger.info("strXml=" + strXml);

      wsreq.body = strXml;

      HttpResponse res = wsreq.post();
//      List<Http.Header> headers = res.getHeaders();
//      for( Http.Header header : headers) {
//        Logger.info(header.toString());
//      }   
  
      Document xmlDoc = res.getXml();
  
      int cnt = 0;
      String strKey = null;
      String strSummary = null;
      String strDescription = null;
      for(Node member: XPath.selectNodes("//data/value/struct/member", xmlDoc)) {
        String name = XPath.selectText("name", member);
        String value = XPath.selectText("value", member);
  //      Logger.info("name=" + name);
  
        if(name.equals("key")) {
          cnt++;
          strKey = value;
          Logger.info("key=" + value);
        } else if (name.equals("summary")) {
          cnt++;
          strSummary = value;
          Logger.info("summary=" + value);
        } else if (name.equals("description")) {
          cnt++;
          strDescription = value;
        }   

        if(cnt == 3){
          // create faq model
          // save faq data
          cnt = 0;
//          Logger.info("key=" + strKey + ";summary=" + strSummary + ";desc=" + strDescription );
//          Logger.info("desc length=%d", strDescription.length());
//          String aaa = strDescription.substring(0,300);
//          Logger.info("aaa=" +aaa);
//          Faq faq = new Faq(strKey, strSummary, aaa, "", "");
          Faq faq = new Faq(strKey, strSummary, strDescription, "", "");
//          faq.answer = strDescription;
          faq._save();
        }
      
      }   


    } catch (Exception e){
      Logger.error(e, e.getMessage());
    } finally {
      Logger.info("write finally.");
    }

  }

}
