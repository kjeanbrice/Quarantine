package com.quarantine.controllers.savecontroller;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.quarantine.beans.ToDoListBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class SaveController {

    ArrayList listnames = new ArrayList();
    ArrayList emailnames = new ArrayList();
    boolean overwritestatus = false;

    @RequestMapping(value = "savelist.htm", method = RequestMethod.GET)
    public void processSaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = UserServiceFactory.getUserService();
        ToDoListBean todolist = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");
        System.out.println("Active List output: " + todolist.toString());

        String listname = request.getParameter("listName");

        if (listname == null || listname.trim().length() == 0) {
                out.println("FAILURE");
        } else {
            String id = null;
            //Do a check to see if list name already exists if it does overwrite else do normally
            if(todolist.checkSaved() == false){
                if(!listname.isEmpty()){
                    for(int i = 0; i < listnames.size(); i++){
                        if(listnames.get(i).equals(listname) && emailnames.get(i).equals(userService.getCurrentUser().getEmail())){
                            System.out.println("List will be overwritten now");
                            overwritestatus = true;
                        }
                        else{
                            overwritestatus = false;
                        }
                    }
                    if(overwritestatus == true){
                        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                        Query query = new Query("Item");
                        query.setFilter(Query.FilterOperator.EQUAL.of("ListName",listname));
                        PreparedQuery result = datastore.prepare(query);
                        for(Entity e:result.asIterable()){
                            System.out.println(e.getProperty("FrontMappingID") + ", " + e.getProperty("Listname"));
                            datastore.delete(e.getKey());
                        }
                    }
                }
                //Sets the name of the list to be saved.
                todolist.setListname(listname);
                id = todolist.generateId();
                todolist.setId(id);
                listnames.add(listname);
                emailnames.add(userService.getCurrentUser().getEmail());
            }
            //Save bean object to datastore
            for (int i = 0; i < todolist.getItems().size(); i++) {
                Key itemKey;
                itemKey = KeyFactory.createKey("PID", todolist.getItems().get(i).getItemID());
                Entity item = new Entity("Item", itemKey);
                item.setProperty("Category", todolist.getItems().get(i).getCategory());
                item.setProperty("Description", todolist.getItems().get(i).getDescription());
                item.setProperty("StartDate", todolist.getItems().get(i).getStartDate());
                item.setProperty("EndDate", todolist.getItems().get(i).getEndDate());
                item.setProperty("Completed", todolist.getItems().get(i).getCompleted());
                item.setProperty("isPrivate", todolist.isPrivate());
                item.setProperty("Listname", todolist.getListname());
                item.setProperty("FrontMappingID", todolist.getItems().get(i).getFrontMappingID());
                item.setProperty("SpecialID", id);
                item.setProperty("Email", userService.getCurrentUser().getEmail());
                item.setProperty("PrimaryID", todolist.getItems().get(i).getItemID());
                item.setProperty("ListName",listname);
                DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
                datastore.put(item);
            }
            todolist.setSaved(true);
            out.println("SUCCESS");

        }


    }
}
