package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

@With(Secure.class)
public class DataColumns extends CRUD {

  public static void json(String datatable_name) {

    List<DataColumn> datas = new ArrayList();
    if (datatable_name == null || datatable_name.isEmpty()) {
      datas = DataColumn.findAll();
    } else {
      String [] dataTableNames = datatable_name.split(":");
      List<DataTable> dataTables = new ArrayList();
      for(int i = 0; i < dataTableNames.length; i++) {
        List<DataTable> result = DataTable.find("byName", dataTableNames[i]).fetch();
        dataTables.addAll(result);
      }
      for (DataTable dataTable : dataTables) {
        List<DataColumn> dataColumns = DataColumn.find("byDataTable", dataTable).fetch();
        datas.addAll(dataColumns);
      }
    }

    renderJSON(datas);
  }

}

