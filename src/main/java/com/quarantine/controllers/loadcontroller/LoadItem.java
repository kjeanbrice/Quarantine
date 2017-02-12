package com.quarantine.controllers.loadcontroller;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Query;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Xiangbin on 2/12/2017.
 */
public class LoadItem {

    public List<ItemBean> loadItems(HttpServletRequest request) throws IOException {
        DatastoreService datastore= DatastoreServiceFactory.getDatastoreService();
        ToDoListBean todoList=(ToDoListBean)request.getSession().getAttribute("ACTIVE_LIST");
        String id=todoList.getId();
        Query query=new Query("PID");
        query.setFilter(new Query.FilterPredicate("SpecialID",Query.FilterOperator.EQUAL,id));
        System.out.println(query);

        List<ItemBean> results=(List<ItemBean>) datastore.prepare(query);
        return results;
    }
}
