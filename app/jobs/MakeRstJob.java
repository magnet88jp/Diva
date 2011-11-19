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
        Logger.info("Maintenance job label ...%s", label);
        Logger.info("Maintenance job type ...%s", type);

        String summaryStr;
        String processStr;
        if( type.equals("add") ) {
          Logger.info("summartyStr add type ...%s", type);
          summaryStr = "新しい${name}を登録します。または、登録済みの${name}の設定を編集します。";
          processStr = "${name}の登録画面が表示されます。\n[登録]部分\n  以下の情報を入力し、[登録]をクリックします。";
        } else if ( type.equals("view") ) {
          Logger.info("summartyStr view type ...%s", type);
          summaryStr = "${name}の登録情報を表示します。";
          processStr = "${name}の登録情報の一覧が表示されます。以下は登録情報の詳細です。";
        } else if ( type.equals("disable") ) {
          Logger.info("summartyStr view disable ...%s", type);
          summaryStr = "${name}の登録情報を削除します。";
          processStr = "削除する${name}の確認画面が表示されます。以下は確認画面に表示される登録情報です。";
        } else if ( type.equals("list") ) {
          Logger.info("summartyStr view disable ...%s", type);
          summaryStr = "${name}を管理します。";
          processStr = "登録されている${name}の一覧が表示されます。";
          processStr += "\n[検索]部分\n  [検索開始]: クリックし、検索条件を指定して表示される${name}を限定できます。";
          processStr += "\n  [CSVダウンロード]: クリックし、設定項目の一覧をCSVファイルとしてダウンロードできます。";
          processStr += "\n[一覧]部分\n  [新規登録]: クリックし、新規の${name}を登録します。";
          processStr += "\n  [表示項目設定]: クリックし、画面一覧に表示されている各行の表示項目を変更できます。";
        } else {
          Logger.info("summartyStr view else ...%s", type);
          summaryStr = "新しい${name}を登録します。または、登録済みの${name}の設定を編集します。";
          processStr = "${name}の登録画面が表示されます。\n[登録]部分\n  以下の情報を入力し、[登録]をクリックします。";
        }
        Map<String, Object> templateMap= new HashMap<String, Object>(16);
        templateMap.put("name", data.title);

        Template summaryTemplate = TemplateLoader.loadString(summaryStr);
        String summaryText = summaryTemplate.render(templateMap);

        Template processTemplate = TemplateLoader.loadString(processStr);
        String processText = processTemplate.render(templateMap);

        String restrictionText = "";

	String supplementText = "";

        String mode = "利用可能モード: ";

        for (ActionMode actionMode : data.actionModes) {
          mode = mode + "[" + actionMode.name + "]";
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
