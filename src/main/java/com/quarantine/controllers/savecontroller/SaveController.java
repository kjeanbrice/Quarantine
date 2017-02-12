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
import java.util.Random;

@Controller
public class SaveController {
    @RequestMapping(value="savelist.htm", method = RequestMethod.GET)
    public void processSaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = UserServiceFactory.getUserService();

        ToDoListBean todolist = (ToDoListBean)request.getSession().getAttribute("ACTIVE_LIST");
        System.out.println("Active List output: " + todolist.toString());


        String id = todolist.generateId();
        todolist.setId(id);

        //Save bean object to datastore

        for(int i = 0; i < todolist.getItems().size(); i++){
            Key itemKey;
            itemKey = KeyFactory.createKey("email",userService.getCurrentUser().getEmail());
            Entity item = new Entity("Item",itemKey);
            item.setProperty("Category",todolist.getItems().get(i).getCategory());
            item.setProperty("Description",todolist.getItems().get(i).getDescription());
            item.setProperty("StartDate",todolist.getItems().get(i).getDescription());
            item.setProperty("EndDate",todolist.getItems().get(i).getDescription());
            item.setProperty("Completed",todolist.getItems().get(i).getCompleted());
            item.setProperty("isPrivate",todolist.isPrivate());
            item.setProperty("SpecialID",id);

            Random rand = new Random();

            int  n = rand.nextInt(10000) + 1;

            item.setProperty("UniqueID",todolist.getItems().get(i).getItemID());
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            datastore.put(item);
        }


    }
}
