package jobs;

import java.util.*;
import java.io.*;
import play.Logger;
import play.jobs.*;
import play.templates.Template;
import play.templates.TemplateLoader;
import models.*;

public class MakeRstJob extends Job {

  public void doJob() {
    // execute some application logic here ...
    Logger.info("Maintenance job ...");
    try {
      Template template = TemplateLoader.load("conf/templates/add.rst");
      List<Action> datas = Action.findAll();
//      int i = 0;
      for (Action data : datas) {
        String label = data.function.actionPath.replaceFirst("^/","").replace("/", "-");
        Logger.info("Maintenance job ...%s", label);
        String mode = "";

        for (ActionMode actionMode : data.actionModes) {
          mode = mode + actionMode.name;
        }
//        if(data.availableMode == 0) {
//          mode = "利用可能モード：[全キャンペーン管理モード]/[個別キャンペーン管理モード]";
//        } else if(data.availableMode == 1) {
//          mode = "利用可能モード：[全キャンペーン管理モード]";
//        } else if(data.availableMode == 2) {
//          mode = "利用可能モード：[個別キャンペーン管理モード]";
//        } else {
//          mode = "利用可能モード：[全キャンペーン管理モード]/[個別キャンペーン管理モード]";
//        }
        String availableMode = mode;
        String title = data.title;
        String summary = data.summary;
        String process = data.process;

        String restriction = data.restriction;
        String supplement = data.supplement;
        // map
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("label", label);
        map.put("availableMode", availableMode);
        map.put("title", title);
        map.put("summary", summary);
        map.put("process", process);
        map.put("restriction", restriction);
        map.put("supplement", supplement);
        String str2 = template.render(map);
        Logger.info(str2);
        FileWriter fw = new FileWriter("doc/source/actions/"+label+".rst");
        fw.write(str2);
        fw.close();
//        i++;
      }

//    } catch (FileNotFoundException e){
    } catch (IOException e){
      Logger.error(e, e.getMessage());
    } finally {
      Logger.info("write finally.");
    }

  }

}
