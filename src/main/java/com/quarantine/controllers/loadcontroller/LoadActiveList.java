package com.quarantine.controllers.loadcontroller;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.quarantine.beans.ItemBean;
import com.quarantine.beans.ToDoListBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Xiangbin on 2/12/2017.
 */
public class LoadActiveList {
    public ArrayList<ToDoListBean> LoadActiveList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        ToDoListBean listBean = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");
        DatastoreService datastore= DatastoreServiceFactory.getDatastoreService();
        UserService userService = UserServiceFactory.getUserService();
        String email=userService.getCurrentUser().getEmail();
        ArrayList<ToDoListBean>  ActiveList=new ArrayList<ToDoListBean>();


        //S item and found the item belongs to this this user
        Query query=new Query("Item");
//        query.setFilter(new Query.FilterPredicate("Email",Query.FilterOperator.EQUAL,email));
//        query.addFilter(new Query.FilterPredicate("isPrivate",Query.FilterOperator.EQUAL,email));
        PreparedQuery AllItems = datastore.prepare(query);


        boolean found=false;
        for(Entity e:AllItems.asIterable()){

            for(int x=0;x<ActiveList.size();x++){
                if(ActiveList.get(x).getEmail().equals((String)e.getProperty("Email"))){
                    ItemBean newItem=new ItemBean((String)e.getProperty("Category"),(String)e.getProperty("Description"),(String)e.getProperty("StartDate"),(String)e.getProperty("EndDate"),(String) e.getProperty("Completed"));
                    ActiveList.get(x).getItems().add(newItem);
                }
                found=true;
            }
            if(found!=true){
                if((((String)e.getProperty("Email")).equals(email))||e.getProperty("isPrivate").equals(false)){
                    boolean isP=true;
                    if(((String)e.getProperty("isPrivate")).equals("false")){
                        isP=false;
                    }
                    ToDoListBean newToDoListBean=new ToDoListBean((String)e.getProperty("Email"),(String)e.getProperty("Listname"),(String)e.getProperty("SpecialID"),"Def",isP);
                    ActiveList.add(newToDoListBean);
                }
            }
            found=false;
        }
        return ActiveList;
    }
}
