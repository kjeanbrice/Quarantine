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

        if(listBean.getItems().isEmpty()){
            System.out.println("NO ITEMS");
        }
        else {
            for (int i = 0; i < listBean.getItems().size(); i++) {
                if (listBean.getItems().get(i).getItemID() == Integer.parseInt(item)) {
                    removeitem = listBean.getItems().get(i);
                    break;
                }
            }
        }

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

            Query.Filter propertyFilter = new Query.FilterPredicate("PrimaryID", Query.FilterOperator.EQUAL,item);
            Query q = new Query("PIDItem").setFilter(propertyFilter);
            PreparedQuery pq = datastore.prepare(q);
            System.out.println(pq);
            Entity itemtoremove = pq.asSingleEntity();
            System.out.println(itemtoremove);
            if(itemtoremove == null){
                System.out.println("Did not find");
            }
            else{
                System.out.println("Found");
                datastore.delete(itemtoremove.getKey());
                listBean.getItems().remove(removeitem);
                System.out.println("ITEM REMOVED");
                System.out.println(removeitem.getItemID());
            }






    }
}
