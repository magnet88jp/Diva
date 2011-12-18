package controllers;

import java.util.*;
import java.io.*;
import play.*;
import play.data.binding.*;
import play.mvc.*;
import play.db.Model;
import play.exceptions.*;
import play.i18n.*;
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.apache.commons.io.FileUtils;


import models.*;

@With(Secure.class)
public class Actions extends CRUD {

  @After( only={"blank", "show"} )
  static void set( String id ) {
    Logger.info("debug set");
  }

  @After( only={"create", "save"} )
  static void take( Long id ) {
    String[] datas1 = params.getAll("object.url");
    // action and column 

//    Action action = new Action("","","","","","",new Function(1,"",""));
    Action action = null;
//    Action action;
    if ( id == null ) {
      List<Action> actions = Action.find(
        "select a1 from Action a1 " +
        "where a1.id = (select max(a2.id) from Action a2)").fetch();
      for( Action data : actions ) {
        action = data;
        id = action.id;
      }
    } else {
      action = Action.findById(id);
    }

    List<ActionColumn> actionColumns = ActionColumn.find("byActionId", id.toString()).fetch();
    for( ActionColumn actionColumn : actionColumns ) {
      actionColumn._delete();
    }

    String[] columns = params.getAll("object.datatable.datacolumn");

    int colCount = 0;
    for(DataTable datatable : action.dataTables) {
//      Logger.info("count=%d", DataColumn.find("byDataTable", datatable).fetch().size() );
      colCount += DataColumn.find("byDataTable", datatable).fetch().size();
    }

    if( colCount != columns.length-1) {
      for(String col : columns) {
        Logger.info("col.val=%s", col);
        if(!col.isEmpty()){
          ActionColumn data = new ActionColumn(id.toString(), col);
          data._save();
        }
      }
    }

  }


//  public static void save(String id) throws Exception {
//    ObjectType type = ObjectType.get(getControllerClass());
//    notFoundIfNull(type);
//    Model object = type.findById(id);
//    notFoundIfNull(object);
//    Binder.bind(object, "object", params.all());
//    validation.valid(object);
//    if (validation.hasErrors()) {
//      renderArgs.put("error", Messages.get("crud.hasErrors"));
//      try {
//        render(request.controller.replace(".", "/") + "/show.html", type, object);
//      } catch (TemplateNotFoundException e) {
//        render("CRUD/show.html", type, object);
//      }
//    }
//    object._save();
//
//    // action and column 
//    List<ActionColumn> actionColumns = ActionColumn.find("byActionId", id).fetch();
//    for( ActionColumn actionColumn : actionColumns ) {
//      actionColumn._delete();
//    }
////    Logger.info("params.all=%s", params.all() );
////    Logger.info("params.object.datatable.datacolumn=%s", params.get("object.datatable.datacolumn") );
////    Logger.info("params.object.title=%s", params.get("object.title") );
////    Logger.info("params.object.dataTables.id=%s", params.get("object.dataTables.id").toString() );
////    Logger.info("params.object.dataTables=%s", params.get("object.dataTables") );
////    Logger.info("params.object.actionModes.id=%s", params.get("object.actionModes[0].id") );
//    String[] columns = params.getAll("object.datatable.datacolumn");
//    Logger.info("size=%d", columns.length );
//
////  
//    Action action = Action.findById(Long.parseLong(id));
//    int colCount = 0;
//    for(DataTable datatable : action.dataTables) {
////      Logger.info("count=%d", DataColumn.find("byDataTable", datatable).fetch().size() );
//      colCount += DataColumn.find("byDataTable", datatable).fetch().size();
//    }
//
//    if( colCount != columns.length-1) {
//      for(String col : columns) {
//        Logger.info("col.val=%s", col);
//        if(!col.isEmpty()){
//          ActionColumn data = new ActionColumn(id, col);
//          data._save();
//        }
//      }
//    }
//
//// capture test
////    WebDriver driver = new FirefoxDriver();
////    driver.get("http://www.google.com/");
////    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//    // Now you can do whatever you need to do with it, for example copy somewhere     
////    FileUtils.copyFile(scrFile, new File("screenshot.png"));
////    driver.quit();
//
//// capture test end
//
//    flash.success(Messages.get("crud.saved", type.modelName));
//    if (params.get("_save") != null) {
//      redirect(request.controller + ".list");
//    }
//    redirect(request.controller + ".show", object._key());
//  }

}

