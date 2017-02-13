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
public class UpdateItems {
    public void updateItems(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ToDoListBean listBean = (ToDoListBean) request.getSession().getAttribute("ACTIVE_LIST");

        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String completed = request.getParameter("completed");

        //ASSUMIGN WE HAVE A WAY TO DETERMINE WHICH ONE IS SELECTED
        int item = -1;
        String frontID = request.getParameter("frontID");

        ItemBean updateitem = null;
        for (int i = 0; i < listBean.getItems().size(); i++) {
            if (listBean.getItems().get(i).getFrontMappingID() == Integer.parseInt(frontID)) {
                listBean.getItems().get(i).setCategory(category);
                listBean.getItems().get(i).setCompleted(completed);
                listBean.getItems().get(i).setEndDate(endDate);
                listBean.getItems().get(i).setStartDate(startDate);
                listBean.getItems().get(i).setDescription(description);
            }
        }


    }
}

