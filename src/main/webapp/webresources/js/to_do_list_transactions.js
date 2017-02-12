$(document).ready(function () {

    hide_list_area();
    check_user_status();


    var list_items_template = "" +
        "{{#Items}}" +
        "<tr class='list_item' item_id = '{{itemID}}'>" +
        "<td class= 'category'  item_value='{{category}}'>{{category}}</td>" +
        "<td class= 'description' item_value='{{description}}'>{{description}}</td>" +
        "<td class= 'startDate' item_value='{{startDate}}'>{{startDate}}</td>" +
        "<td class= 'endDate' item_value='{{endDate}}'>{{endDate}}</td>" +
        "<td class= 'completed' item_value='{{completed}}'>{{completed}}</td>" +
        "</tr>{{/Items}}"
        + "<tr listnum ='null'>" +
        "<td>&nbsp;</td>" +
        "<td>&nbsp;</td>" +
        "<td>&nbsp;</td>" +
        "<td>&nbsp;</td>" +
        "<td>&nbsp;</td>";


    function load_table_items() {
        var item_area = $('#item_area');
        var $url = "/requestitems.htm";

        $.ajax({
            method: 'get',
            url: $url,
            dataType: 'json',
            success: function (item_table) {
                console.log("Get List Items:Success");
                var JSON_list_items = item_table;

                var item_category_output = "First member " + JSON_list_items.email + " " + JSON_list_items.owner;
                console.log("Items: " + item_table.Items[0].category);


                var list_data = Mustache.render(list_items_template, JSON_list_items);
                item_area.hide();
                item_area.html(list_data);
                item_area.show();
                console.log(list_data);

            },
            error: function () {
                console.log("Loading the table: Aw, It didn't connect to the servlet :(");
            }

        });
    }


    $('body').on('dblclick', 'tr.list_item', function (e) {
        var item_row = $(this)
        var item_id = item_row.attr("item_id");
        var category = item_row.find('td[class="category"]').attr("item_value");
        var description = item_row.find('td[class="description"]').attr("item_value");
        var startDate = item_row.find('td[class="startDate"]').attr("item_value");
        var endDate = item_row.find('td[class="endDate"]').attr("item_value");
        var completed = item_row.find('td[class="completed"]').attr("item_value");

        $('#edit_category').val(category);
        $('#edit_description').val(description);
        $('#edit_startdate').val(startDate);
        $('#edit_enddate').val(endDate);
        $('#edit_completed').prop("checked", "checked");
        $('#edit_id').attr("edit_id", item_id);

        $('#edit_modal').modal('show');

    });


    $("#new_list").on('click', function () {
        var new_textarea = $('#new_textarea');

        var url = "/newlist.htm";
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (newlist_status) {
                if (newlist_status.trim() === "NEWLIST:SUCCESS") {
                    //hide_welcome_screen();
                    console.log("NEWLIST:SUCCESS");
                    hide_list_area();
                    show_list_area();

                    new_textarea.text("A new list has been created.")
                    $('#new_modal').modal('show');
                    //clear table();
                }
                else {
                    hide_list_area();
                    //show_welcome_screen();

                    console.log("NEWLIST:FAILURE");
                    new_textarea.text("You must login first before you can create a new list.")
                    $('#new_modal').modal('show');
                    hide_list_area();
                }

                console.log(newlist_status);
            },
            error: function () {
                console.log("NEW LIST FAILURE: Aw, It didn't connect to the servlet :(");
            }
        });
    });


    $('#btn_add_submit').on('click', function (e) {
        // var group_id = $('#group_id').text().trim();
        //var user_id = $('#guser_id').text().trim();

        var category = $('#txt_category').val().trim();
        var description = $('#txt_description').val().trim();
        var startdate = $('#txt_startdate').val().trim();
        var enddate = $('#txt_enddate').val().trim();
        var completed = $('#chkbox_completed').prop("checked");
        var err_label = $('#err_add_item');
        err_label.hide();


        var url = "/additem.htm?category=" + category + "&description=" + description + "&startDate=" + startdate + "&endDate=" + enddate + "&completed=" + completed;
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (additem_status) {
                if (additem_status.trim() === "SUCCESS") {
                    $('#add_modal').modal('hide');
                    load_table_items();
                } else {
                    err_label.text("One or more fields are empty. All fields are required.");
                    err_label.fadeIn(1000);
                    console.log("ADD ITEM:ERROR");
                }
            },
            error: function () {
                console.log("Add Item Failure: Aw, It didn't connect to the servlet :(");
            }

        });
    });


    $('#btn_edit_submit').on('click', function (e) {
        // var group_id = $('#group_id').text().trim();
        //var user_id = $('#guser_id').text().trim();

        var item_id = $('#edit_id').attr('edit_id');
        var category = $('#edit_category').val().trim();
        var description = $('#edit_description').val().trim();
        var startdate = $('#edit_startdate').val().trim();
        var enddate = $('#edit_enddate').val().trim();
        var completed = $('#edit_completed').prop("checked");
        var err_label = $('#err_edit_item');
        err_label.hide();


        var url = "/edititem.htm?category=" + category + "&itemID=" + item_id + "&description=" + description + "&startDate=" + startdate + "&endDate=" + enddate + "&completed=" + completed;
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (edititem_status) {
                if (edititem_status.trim() === "SUCCESS") {
                    $('#edit_modal').modal('hide');
                    load_table_items();
                } else {
                    err_label.text("One or more fields are empty. All fields are required.");
                    err_label.fadeIn(1000);
                    console.log("Edit ITEM:ERROR");
                }
            },
            error: function () {
                console.log("Edit Item Failure: Aw, It didn't connect to the servlet :(");
            }

        });
    });


    function check_user_status() {
        var user_email = $('#a_email');
        var user_login = $('#a_login');
        var url = "/checkuserstatus.htm";
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (user_status) {
                console.log(user_status);
                if (user_status.trim() == "NOT_LOGGED_IN") {
                    hide_list_area();
                    user_email.hide();
                    user_login.hide();

                    user_email.text("");
                    user_login.text("Sign in");

                    user_email.fadeIn(600);
                    user_login.fadeIn(600);

                } else {
                    user_email.hide();
                    user_login.hide();

                    user_email.text("");
                    user_email.text(user_status.trim());
                    user_login.text("Sign out")

                    user_login.fadeIn(600);
                    user_email.fadeIn(600);
                }
            },
            error: function () {
                console.log("NEW LIST FAILURE: Aw, It didn't connect to the servlet :(");
            }

        });


    }


    function hide_list_area() {
        var list_area = $('[area="content_area"]');
        list_area.removeClass("hide-tag");
        list_area.hide();
    }


    function show_list_area() {
        var list_area = $('[area="content_area"]');
        list_area.fadeIn(600);
    }


});


