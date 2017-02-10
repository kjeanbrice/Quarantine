$(document).ready(function () {

    hide_list_area();
    check_user_status();


    $("#new_list").on('click', function () {
        hide_list_area();
        show_list_area();
        $('#new_modal').modal('show');
        var url = "/newlist.htm";
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (newlist_status) {
                console.log(newlist_status);
            },
            error: function () {
                console.log("NEW LIST FAILURE: Aw, It didn't connect to the servlet :(");
            }
        });
    });


    $('#btn_add_submit').on('click', function (e){
       // var group_id = $('#group_id').text().trim();
        //var user_id = $('#guser_id').text().trim();

        var category = $('#txt_category').val().trim();
        var description = $('#txt_description').val().trim();
        var startdate =  $('#txt_startdate').val().trim();
        var enddate = $('#txt_enddate').val().trim();
        var completed = $('#chkbox_completed').prop("checked");
        var err_label = $('#err_add_item');
        err_label.hide();


        var url = "/additem.htm?category=" + category + "&description="+ description + "&startDate="+ startdate + "&endDate="+ enddate + "&completed="+ completed;
        $.ajax({
            method: 'get',
            url: url,
            dataType: 'text',
            success: function (additem_status) {
                if(additem_status.trim() === "SUCCESS"){
                    $('#add_modal').modal('hide');
                }else{
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
                if(user_status.trim() == "NOT_LOGGED_IN"){
                    user_email.hide();
                    user_login.hide();

                    user_email.text("");
                    user_login.text("Sign in");

                    user_email.fadeIn(600);
                    user_login.fadeIn(600);

                }else{
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


