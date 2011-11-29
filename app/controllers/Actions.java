package controllers;

import java.util.*;
import play.*;
import play.data.binding.*;
import play.mvc.*;
import play.db.Model;
import play.exceptions.*;
import play.i18n.*;

import models.*;

@With(Secure.class)
public class Actions extends CRUD {

  public static void save(String id) throws Exception {
    ObjectType type = ObjectType.get(getControllerClass());
    notFoundIfNull(type);
    Model object = type.findById(id);
    notFoundIfNull(object);
    Binder.bind(object, "object", params.all());
    validation.valid(object);
    if (validation.hasErrors()) {
      renderArgs.put("error", Messages.get("crud.hasErrors"));
      try {
        render(request.controller.replace(".", "/") + "/show.html", type, object);
      } catch (TemplateNotFoundException e) {
        render("CRUD/show.html", type, object);
      }
    }
    object._save();

    // action and column 
    List<ActionColumn> actionColumns = ActionColumn.find("byActionId", id).fetch();
    for( ActionColumn actionColumn : actionColumns ) {
      actionColumn._delete();
    }
//    Logger.info("params.all=%s", params.all() );
//    Logger.info("params.object.datatable.datacolumn=%s", params.get("object.datatable.datacolumn") );
//    Logger.info("params.object.title=%s", params.get("object.title") );
//    Logger.info("params.object.dataTables.id=%s", params.get("object.dataTables.id").toString() );
//    Logger.info("params.object.dataTables=%s", params.get("object.dataTables") );
//    Logger.info("params.object.actionModes.id=%s", params.get("object.actionModes[0].id") );
    String[] columns = params.getAll("object.datatable.datacolumn");
    Logger.info("size=%d", columns.length );

//  
    Action action = Action.findById(Long.parseLong(id));
    int colCount = 0;
    for(DataTable datatable : action.dataTables) {
//      Logger.info("count=%d", DataColumn.find("byDataTable", datatable).fetch().size() );
      colCount += DataColumn.find("byDataTable", datatable).fetch().size();
    }

    if( colCount != columns.length-1) {
      for(String col : columns) {
        Logger.info("col.val=%s", col);
        if(!col.isEmpty()){
          ActionColumn data = new ActionColumn(id, col);
          data._save();
        }
      }
    }

    flash.success(Messages.get("crud.saved", type.modelName));
    if (params.get("_save") != null) {
      redirect(request.controller + ".list");
    }
    redirect(request.controller + ".show", object._key());
  }

}

