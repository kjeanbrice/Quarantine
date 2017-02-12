package com.quarantine.services;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Karl on 2/8/2017.
 */

@Controller
public class ServiceManager {
    @RequestMapping(value="checkuserstatus.htm", method = RequestMethod.GET)
    public void checkUserStatus(HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        UserService userService = UserServiceFactory.getUserService();
        if(userService.isUserLoggedIn()){
            User user = userService.getCurrentUser();
            out.println("" + user.getEmail());

        }
        else{
            out.println("NOT_LOGGED_IN");
        }
    }

    @RequestMapping(value="requestitems.htm", method = RequestMethod.GET)
    public void processItemRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();


        ToDoListBean list = (ToDoListBean)request.getSession().getAttribute("ACTIVE_LIST");
        System.out.println(list.generateJSON());
        out.println(list.generateJSON());


    }

}
