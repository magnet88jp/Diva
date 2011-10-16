package jobs;

import java.util.*;
import java.io.*;
import play.Logger;
import play.jobs.*;
import play.templates.Template;
import play.templates.TemplateLoader;
import models.*;

public class MakeMajorMessageCsvJob extends Job {

  public void doJob() {
    // execute some application logic here ...
    try {
      Template template = TemplateLoader.load("conf/templates/majormessage.csv");
      // all
      List<MajorMessage> datas = MajorMessage.findAll();
      Map<String, Object> map = new HashMap<String, Object>(16);
      map.put("datas", datas);
      String label = "all";
      String lines = template.render(map);
      FileWriter fw = new FileWriter("doc/source/data/majormessage-"+label+".csv");
      fw.write(lines);
      fw.close();

      // actions
      List<Action> actions = Action.findAll();
      for (Action action : actions) {
        label = action.function.actionPath.replaceFirst("^/","").replace("/", "-");
        datas = action.majorMessages;
        map = new HashMap<String, Object>(16);
        map.put("datas",datas);
        lines = template.render(map);
        fw = new FileWriter("doc/source/data/majormessage-"+label+".csv");
        fw.write(lines);
        fw.close();
      }




//    } catch (FileNotFoundException e){
    } catch (IOException e){
      Logger.error(e, e.getMessage());
    } finally {
      Logger.info("write finally.");
    }

  }

}
