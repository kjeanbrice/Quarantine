package com.quarantine.controllers.savecontroller;

import com.google.appengine.api.datastore.Entity;
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
    public void processSaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = UserServiceFactory.getUserService();

        ToDoListBean todolist = (ToDoListBean)request.getSession().getAttribute("ACTIVE_LIST");
        System.out.println("Active List output: " + todolist.toString());

        //Save bean object to datastore

    }
}