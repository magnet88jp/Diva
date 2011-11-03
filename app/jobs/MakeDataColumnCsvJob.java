package jobs;

import java.util.*;
import java.io.*;
import play.Logger;
import play.jobs.*;
import play.templates.Template;
import play.templates.TemplateLoader;
import models.*;

public class MakeDataColumnCsvJob extends Job {

  public void doJob() {
    // execute some application logic here ...
    try {
      Template template = TemplateLoader.load("conf/templates/datacolumn.csv");
      // all
      List<DataColumn> datas = DataColumn.findAll();
      Map<String, Object> map = new HashMap<String, Object>(16);
      map.put("datas", datas);
      String label = "all";
      String lines = template.render(map);
      FileWriter fw = new FileWriter("doc/source/data/datacolumn-"+label+".csv");
      fw.write(lines);
      fw.close();

      // actions
      List<Action> actions = Action.findAll();
      for (Action action : actions) {
        label = action.function.actionPath.replaceFirst("^/","").replace("/", "-");
        datas = new ArrayList();
        for (DataTable dataTable : action.dataTables) {
          List<DataColumn> dataColumns = DataColumn.find("byDataTable", dataTable).fetch();
          datas.addAll(dataColumns);
        }
        //Logger.info("funcId=%d", data.functionId);
        //Logger.info("funcName=%s", data.name);
        map = new HashMap<String, Object>(16);
        map.put("datas",datas);
        lines = template.render(map);
        fw = new FileWriter("doc/source/data/datacolumn-"+label+".csv");
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
