package controllers;

import play.*;
import play.mvc.*;
import jobs.*;

import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;

import play.data.binding.*;
import play.utils.Java;
import play.db.Model;
import play.data.validation.*;
import play.exceptions.*;
import play.i18n.*;
import models.*;


@With(Secure.class)
public class Tasks extends CRUD {

  public static void test() {
    Logger.info("test");
    render();
  }

  public static void execute() throws Exception {
    ObjectType type = ObjectType.get(getControllerClass());
    notFoundIfNull(type);
    Constructor<?> constructor = type.entityClass.getDeclaredConstructor();
    constructor.setAccessible(true);
    Model object = (Model) constructor.newInstance();
    Binder.bind(object, "object", params.all());
    validation.valid(object);
    if (validation.hasErrors()) {
      renderArgs.put("error", Messages.get("crud.hasErrors"));
      render(request.controller.replace(".", "/") + "/blank.html", type, object);
    }
    object._save();
    // execute job
//    Task task = (Task) constructor.newInstance();
    Task task = (Task) object;
    Logger.info("param0=%s","test");
    Logger.info("param1=%s",params.all());
    Logger.info("param2=%s",task.name);
    Logger.info("param3=%s",task.taskMode.name);
    Logger.info("param4=%s",task.taskMode.id);
    Logger.info("param=%s",params.get("taskMode.id"));
//    if (params.get("object.taskMode.id") == "1") {
    if (task.taskMode.id == 1 ) {
      Logger.info("MakeRstJob");
      new MakeRstJob().now();
    } else if (task.taskMode.id == 2 ) {
      Logger.info("MakeSystemSettingRstJob");
      new MakeSystemsettingRstJob().now();
    } else if (task.taskMode.id == 3 ) {
      Logger.info("MakeFunctionCsvJob");
      new MakeFunctionCsvJob().now();
    } else if (task.taskMode.id == 4 ) {
      Logger.info("MakeMajorMessageCsvJob");
      new MakeFunctionCsvJob().now();
    } else if (task.taskMode.id == 5 ) {
      Logger.info("MakeDataColumnCsvJob");
      new MakeDataColumnCsvJob().now();
    } else {
      Logger.info("MakeRstJob");
      new MakeRstJob().now();
    }

    // redirect

    flash.success(Messages.get("crud.created", type.modelName));
    if (params.get("_save") != null) {
      redirect(request.controller + ".list");
    }
    if (params.get("_saveAndAddAnother") != null) {
      redirect(request.controller + ".execute");
    }
    redirect(request.controller + ".show", object._key());
  }

}

