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

@Controller
public class SaveController {
    @RequestMapping(value="savelist.htm", method = RequestMethod.GET)
    public void processSaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, EntityNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = UserServiceFactory.getUserService();

        ToDoListBean todolist = (ToDoListBean)request.getSession().getAttribute("ACTIVE_LIST");
        System.out.println("Active List output: " + todolist.toString());


        String id = todolist.generateId();
        todolist.setId(id);

        //Save bean object to datastore

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        for(int i = 0; i < todolist.getItems().size(); i++){
            Key itemKey;
            itemKey = KeyFactory.createKey("PIDKey",todolist.getItems().get(i).getItemID());
            Entity item = new Entity("PIDItem",itemKey);
            item.setProperty("Category",todolist.getItems().get(i).getCategory());
            item.setProperty("Description",todolist.getItems().get(i).getDescription());
            item.setProperty("StartDate",todolist.getItems().get(i).getDescription());
            item.setProperty("EndDate",todolist.getItems().get(i).getDescription());
            item.setProperty("Completed",todolist.getItems().get(i).getCompleted());
            item.setProperty("isPrivate",todolist.isPrivate());
            item.setProperty("SpecialID",id);
            item.setProperty("Email",userService.getCurrentUser().getEmail());
            item.setProperty("PrimaryID",todolist.getItems().get(i).getItemID());
            datastore.put(item);
            System.out.println(todolist.getItems().get(i).getItemID());
        }
        System.out.println("SAVED TO DATASTORE");



    }
}
