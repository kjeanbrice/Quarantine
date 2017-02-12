package com.quarantine.controllers.deletecontroller;

import com.google.appengine.api.datastore.*;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Raymond on 2/12/2017.
 */
@Controller
public class DeleteController {
    @RequestMapping(value = "deleteitem.htm", method = RequestMethod.GET)
    public void processDeleteRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ToDoListBean listBean = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");

        //ASSUMIGN WE HAVE A WAY TO DETERMINE WHICH ONE IS SELECTED
        String item = request.getParameter("itemID");

        ItemBean removeitem = null;

        for(int i = 0; i < listBean.getItems().size(); i++){
            if(listBean.getItems().get(i).getItemID() == Integer.parseInt(item)){
                removeitem = listBean.getItems().get(i);
            }
        }
        listBean.getItems().remove(removeitem);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Query query = new Query();

        query.setFilter(Query.FilterOperator.EQUAL.of("PrimaryID",item));
        PreparedQuery result = datastore.prepare(query);
        Entity resultitem = result.asSingleEntity();
        datastore.delete(resultitem.getKey());



    }
}
