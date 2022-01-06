var objToDeleteId;

$(document).ready(function () {

 $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });

 $("#filter input, #filter select, [form='filter']").on("change", function () {
        findUsers();
    });

    findUsers();
});

function findUsers() {
    $.ajax({
        url: "/admin/api/scaffolding/users?" + prepareUrl(),
        type: "get",
        dataType: "json",
        contentType: "application/json"
    })
    .done(function (users) {
        fillResults(users.users);
    })
    .fail(function(jqxhr, textStatus, errorThrown){
        displayErrorInformation(jqxhr.responseText);
    });
}

function prepareUrl() {
    var url = "";

    var login = $("#login").val();
        if (login != "") {
            url += "&login=" + login;
        }

    url += preparePaginationUrl();

    return url;
}

function fillResults(users) {
    $("#records").empty();
    users.forEach(function(user){
        fillRow(user);
    });
}

function fillRow(user) {
    $('#records').append(
        "<tr>" +
            "<td class='align-middle'>" + user.firstName + "</td>" +
            "<td class='align-middle'>" + user.lastName + "</td>" +
            "<td class='align-middle'>" + user.login + "</td>" +
            "<td class='align-middle'>" + user.language + "</td>" +
            "<td class='align-middle'>" + prepareDateTime(user.registrationDatetime) + "</td>" +
            "<td class='align-middle'>" + prepareDetailsButton(user.id) + "</td>" +
            "<td class='align-middle'>" + prepareDeleteButton(user.id) + "</td>" +
        "</tr>"
    );
}

function prepareDetailsButton(id) {
    return '<button type="button" class="btn btn-primary" onclick="goToDetailsPage(' + id + ')">' + lang.Details + '</button>';
}

function goToDetailsPage(id) {
    window.location.href = "/admin/user/" + id;
}

function prepareDeleteButton(id) {
    return '<button type="button" class="btn btn-danger" onclick="setObjectToDeleteIdAndShowModal(' + id + ') ">' + lang.Delete +'</button>';
}

function setObjectToDeleteIdAndShowModal(id) {
    objToDeleteId = id;
    $('#create-modal').on('hide.bs.modal', function (e) {
        clearCreateModal();
    });
    $('#delete-object-modal').modal('show');
}

function sendDeleteRequest(){
    console.log(objToDeleteId);
     $.ajax({
        url: "/admin/api/scaffolding/user/" + objToDeleteId,
        type: "DELETE"
     })
     .done(function(response) {
        $('#delete-object-modal').modal('hide');
        $('#operation-successful-modal').modal('show');
            findCustomers();
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
     }

     function clearCreateModal() {
         $("#create-login").val('');
         $("#password").val('');
         $("#repeated-password").val('');
         $("#create-first-and-last-name").val('');
         $("#is-enabled").prop('checked', false);
         $("#regulation").prop('checked', false);
         $("#newsletter").prop('checked', false);
         $("#email-confirmed").prop('checked', false);
         $("#authorities").prop('checked', false);
     }

    function performUserRegistration() {

        $("#create-button").prop( "disabled", true );

        var login = $("#create-login").val();
        var password = $("#password").val();
        var repeatedPassword = $("#repeated-password").val();

        if(arePasswordsValid(password, repeatedPassword)) {
            sendCreateRequest(login, password);
        } else {
            $("#create-button").prop( "disabled", false );
        }
    }

    function getAuthorities(){
        var authorities = [];
          $.each($("input[name='authorities']:checked"), function(){
            authorities.push($(this).val());
            });
        return authorities;
    }

     function sendCreateRequest(login, password) {
         $.ajax({
             url: "/admin/api/scaffolding/user",
             method: "post",
             contentType: "application/json",
             data:
             JSON.stringify({
                 login: login,
                 passwordHash: hash(password),
                 invoiceFirstAndLastName: $("#create-first-and-last-name").val(),
                 language: $("#language").val(),
                 isEnabled: $("#is-enabled").prop('checked'),
                 regulationAccepted: $("#regulation").prop('checked'),
                 newsletterAccepted: $("#newsletter").prop('checked'),
                 isEmailConfirmed: $("#email-confirmed").prop('checked'),
                 authorities : getAuthorities()
             })
         })
         .done(function () {
              $("#create-modal").modal('hide');
              $("#operation-successful-modal").modal('show');
              findCustomers();
         })
         .fail(function (jqxhr, textStatus, errorThrown) {
               $("#create-button").prop( "disabled", false );
               displayErrorInformation(jqxhr.responseText);
         })
     }


