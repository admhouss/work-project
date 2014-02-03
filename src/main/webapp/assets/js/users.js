function usersControls (contextPath) {
    var userForEdit = {login: "", firstName: "", lastName: ""};
    var userLogin;
    $('#inputPassword3').tooltip({trigger: "focus"});
    var editClick = function (e, $this) {
        e.preventDefault();
        userLogin = $this.attr("id");
        userLogin = userLogin.substr(5);
        openModal(userLogin);
    };
    var repoClick = function (e, $this) {
        e.preventDefault();
        var $i = $($($this.children('i'))[0]);
        userLogin = $this.attr("id");
        userLogin = userLogin.substr(5);
        $.ajax({
            url: contextPath+"/auth/administration/users/edit/repo/" + userLogin,
            type: 'POST',
            cashed: false,
            'success': function (repo) {
                if (repo == "star") {
                    $i.removeClass('icon-star-empty');
                    $i.addClass('icon-star');
                } else {
                    $i.removeClass('icon-star');
                    $i.addClass('icon-star-empty');
                }
            }
        });
    };
    var removeClick = function (e, $this) {
        e.preventDefault();
        userLogin = $this.attr("id");
        userLogin = userLogin.substr(7);
        $.ajax({
            url: contextPath+"/auth/administration/users/edit/remove/",
            type: 'POST',
            cashed: false,
            data: {login: userLogin},
            'success': function () {
                $('#user-row-' + userLogin).hide(1000, function () {
                    $('#user-row-' + userLogin).remove();
                });
            }
        });
    };
    $('.add-new').click(function (e) {
        e.preventDefault();
        userLogin = "add-new-user";
        openModal(userLogin);
    });
    $(".user-edit").click(function (e) {
        editClick(e, $(this));
    });
    $(".user-repo").click(function (e) {
        repoClick(e, $(this));
    });
    $('.user-remove').click(function (e) {
        removeClick(e, $(this));
    });
    $('.edit-save').click(function (e) {
        e.preventDefault();
        sendRequest();
    });

    $('input').on('keypress', function (e) {
        if (e.keyCode == 13) {
            e.preventDefault();
            sendRequest();
        } else {
            cleanMessages();
        }
    });

    function sendRequest() {
        cleanMessages();
        if (nameCheck(((userLogin == "add-new-user")))) {
            var dataJSON = JSON.stringify({
                login: userForEdit.login,
                firstName: userForEdit.firstName,
                lastName: userForEdit.lastName,
                newFirstName: $('#inputFirstName').val(),
                newLastName: $('#inputLastName').val(),
                newPassword: $('#inputPassword3').val(),
                newLogin: $('#inputLogin').val(),
                role: "1"});
            $.ajax({
                url: contextPath+"/auth/administration/users/do/edit/",
                type: 'POST',
                contentType: 'application/json',
                cashed: false,
                data: dataJSON,
                beforeSend: function () {
                    $('#loading').removeClass('hidden');
                },
                success: function (data) {
                    $('#loading').addClass('hidden');
                    if (data.loginIsFree == false && data.success == false) {
                        $('#loginIsExist').removeClass("hidden");
                        $('#add-success').addClass('hidden');
                        $('#success').addClass("hidden");
                    }
                    if (data.success == true) {
                        if (userLogin == "add-new-user") {
                            $('#add-success').removeClass('hidden');
                        } else {
                            $('#success').removeClass("hidden");
                        }
                        refreshRow(userLogin, data);
                        $('#error').addClass("hidden");
                        $('#loginIsExist').addClass("hidden");
                    }
                }
            });
        }
    }

    function nameCheck(isNew) {
        var firstNameStr = $('#inputFirstName').val();
        var lastNameStr = $('#inputLastName').val();
        var loginStr = $('#inputLogin').val();
        var passStr = $('#inputPassword3').val();
        if (firstNameStr.length == 0 || lastNameStr.length == 0 || loginStr.length == 0 || (isNew && passStr.length == 0)) {
            if (isNew) {
                $('#error-add').removeClass("hidden");
            } else {
                $('#error').removeClass("hidden");
            }
            return false;
        } else {
            cleanMessages();
            return true;
        }
    }

    function resetModal(obj) {
        $('#inputFirstName').val(obj.firstName);
        $('#inputLastName').val(obj.lastName);
        $('#inputLogin').val(obj.login);
        $('#inputPassword3').val("");
    }

    function cleanMessages() {
        $('#success').addClass("hidden");
        $('#error').addClass("hidden");
        $('#error-add').addClass("hidden");
        $('#loginIsExist').addClass("hidden");
        $('#add-success').addClass("hidden");
    }

    function openModal(userLogin) {
        userForEdit = {login: "", firstName: "", lastName: ""};
        cleanMessages();
        if (userLogin != "add-new-user") {
            $.ajax({
                url: contextPath+"/auth/administration/users/edit/get/" + userLogin,
                type: 'POST',
                cashed: false,
                'success': function (editUser) {
                    resetModal(editUser);
                    $('#myModalLabel').html("Редактирование");
                    $('#inputPassword3').tooltip('enable');
                    $("#editModal").modal("show");
                    userForEdit.login = editUser.login;
                    userForEdit.firstName = editUser.firstName;
                    userForEdit.lastName = editUser.lastName;
                }
            });
        } else {
            $('#myModalLabel').html("Добавление");
            userForEdit = {login: "", firstName: "", lastName: ""};
            resetModal(userForEdit);
            $('#inputPassword3').tooltip('disable');
            $("#editModal").modal("show");
        }


    }
    function refreshRow(login, data) {
        if (login == "add-new-user") {
            var lastRow = $('.table tr:last');
            var index = parseInt(lastRow.find('.index').text()) + 1;
            var star = "<i class='icon-star-empty'></i>";
            lastRow.after("<tr id='" + 'user-row-' + data.newLogin + "' class='userInfo'>" +
                "<td class='index'>" + index + "</td>" +
                "<td>" + data.newLogin + "</td>" +
                "<td>" + data.newLastName + " " + data.newFirstName + "</td>" +
                "<td><a href='' id='edit-" + data.newLogin + "' class='user-edit'><i class='icon-pencil'></i></a>&nbsp;\n" +
                "<a href='' id='repo-" + data.newLogin + "' class='user-repo'>" + star + "</a>&nbsp;\n" +
                "<a href='' id='remove-" + data.newLogin + "'><i class='icon-remove'></i></a></td></tr>");
            var editHref = $('#edit-' + data.newLogin);
            var repoHref = $('#repo-' + data.newLogin);
            var removeHref = $('#remove-' + data.newLogin);
            editHref.click(function (e) {
                editClick(e, editHref);
            });
            repoHref.click(function (e) {
                repoClick(e, repoHref);
            });
            removeHref.click(function (e) {
                removeClick(e, removeHref);
            });

            $('.table tr:last').show(1000);
        }
        var $row = $('#user-row-' + login);
        $row.attr('id', 'user-row-' + data.newLogin);
        $row.find('.login').text(data.newLogin);
        $row.find('.fullName').text(data.newLastName + " " + data.newFirstName);
        $row.find('.user-edit').attr('id','edit-' + data.newLogin);
        $row.find('.user-repo').attr('id','repo-' + data.newLogin);
        $row.find('.user-remove').attr('id','remove-' + data.newLogin);
    }
}