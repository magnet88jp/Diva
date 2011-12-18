package controllers;

import java.util.*;
import play.*;
import play.mvc.*;

import models.*;

@With(Secure.class)
public class ActionColumns extends CRUD {

  public static void json(Long action_id) {

    List<DataColumn> datas = new ArrayList();
    if (action_id == null) {
      datas = DataColumn.findAll();
    } else {
      Action action = Action.findById(action_id);
      List<ActionColumn> actionColumns = ActionColumn.find("byActionId", action.id.toString() ).fetch();
      for( ActionColumn actionColumn : actionColumns) {
        DataColumn dataColumn = DataColumn.findById(Long.parseLong(actionColumn.columnId));
        datas.add(dataColumn);
      }
    }

    renderJSON(datas);
  }


}

