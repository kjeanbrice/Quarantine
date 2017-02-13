package com.quarantine.controllers.loadcontroller;

import com.google.appengine.api.datastore.*;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Xiangbin on 2/12/2017.
 */
public class UpdateItem {
    public void updateItems(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();


        ToDoListBean listBean = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");

        //ASSUMIGN WE HAVE A WAY TO DETERMINE WHICH ONE IS SELECTED
        int item = -1;
        String res = "FAILURE";
        int itemIndex = -1;

        String frontID = request.getParameter("frontID");

        ItemBean updateitem = null;
        for(int i = 0; i < listBean.getItems().size(); i++){
            if(listBean.getItems().get(i).getFrontMappingID() == Integer.parseInt(frontID)){
                updateitem = listBean.getItems().get(i);
                itemIndex = i;
            }
        }

        if(updateitem != null){
            //listBean.getItems().remove(itemIndex);
            // res = "SUCCESS";

            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            Query query = new Query("Item");

            query.setFilter(Query.FilterOperator.EQUAL.of("PrimaryID",item));
            PreparedQuery result = datastore.prepare(query);
            Entity thisitem=result.asSingleEntity();
            thisitem.setProperty("Category",  updateitem.getCategory());
            thisitem.setProperty("Description",  updateitem.getDescription());
            thisitem.setProperty("StartDate", updateitem.getStartDate());
            thisitem.setProperty("EndDate", updateitem.getEndDate());
            thisitem.setProperty("Completed",  updateitem.getCompleted());
            thisitem.setProperty("FrontMappingID",  updateitem.getFrontMappingID());
            thisitem.setProperty("PrimaryID", updateitem.getItemID());
            datastore.put(thisitem);
//                for(Entity e:result.asIterable()){
//                    System.out.println(e.getProperty("FrontMappingID") + ", " + e.getProperty("Listname"));
//                    datastore.delete(e.getKey());
//                }
        }

        // out.println(res);

    }









}

