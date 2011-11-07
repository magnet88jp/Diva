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
        String[] types = label.split("-");
        String type = types[types.length -1];
        Logger.info("Maintenance job ...%s", label);
        Logger.info("Maintenance job ...%s", type);

        String summaryStr;
        String processStr;
        if( type == "add" ) {
          summaryStr = "新しい${name}を登録します。または、登録済みの${name}の設定を編集します。";
          processStr = "${name}の登録画面が表示されます。\n[登録]部分\n  以下の情報を入力し、[登録]をクリックします。";
        } else if ( type == "view" ) {
          summaryStr = "${name}の登録情報を表示します。";
          processStr = "${name}の登録情報の一覧が表示されます。登録情報の詳細は以下の情報です。";
        } else if ( type == "disable" ) {
          summaryStr = "${name}の登録情報を削除ます。";
          processStr = "${name}の登録情報の一覧が表示されます。登録情報の詳細は以下の情報です。";
        } else {
          summaryStr = "新しい${name}を登録します。または、登録済みの${name}の設定を編集します。";
          processStr = "${name}の登録画面が表示されます。\n[登録]部分\n  以下の情報を入力し、[登録]をクリックします。";
        }
        Map<String, Object> templateMap= new HashMap<String, Object>(16);
        templateMap.put("name", data.title);

        Template summaryTemplate = TemplateLoader.loadString(summaryStr);
        String summaryText = summaryTemplate.render(templateMap);

        Template processTemplate = TemplateLoader.loadString(processStr);
        String processText = processTemplate.render(templateMap);

        String restrictionText = "ななし";

	String supplementText = "ななし";

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
//        String summary = data.summary;
        Logger.info("DEBUG:data.summary==%s", data.summary);
//	String summary = data.summary == null ? summaryText : data.summary;
	String summary = data.summary.isEmpty() ? summaryText : data.summary;
//        String process = data.process;
	String process = data.process.isEmpty() ? processText : data.process;

//        String restriction = data.restriction;
//        String restriction = data.restriction == null ? restrictionText : data.restriction;
        String restriction = data.restriction.isEmpty() ? restrictionText : data.restriction;

        Logger.info("DEBUG:summaryText=%s", summaryText);
//        Logger.info("DEBUG:data.supplement.length()=%d", data.supplement.length());
        String supplement = data.supplement.isEmpty() ? supplementText : data.supplement ;
//	String supplement = data.supplement == null ? supplementText : data.supplement ;
//        String supplement = data.supplement;
//        supplement = supplement.isEmpty() ? summaryText : supplement;;
        Logger.info("DEBUG:supplement=%s", supplement);
        // map
        Map<String, Object> map = new HashMap<String, Object>(16);
        map.put("label", label);
        map.put("availableMode", availableMode);
        map.put("title", title);
        map.put("summary", summary);
        map.put("process", process);
        map.put("restriction", restriction);
        map.put("supplement", supplement);
//        map.put("supplement", );
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
