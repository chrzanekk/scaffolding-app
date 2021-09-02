$(document).ready(function () {
    fillRoles();
});


function sendDeleteRequest(){
    $.ajax({
        url: "/admin/api/scaffolding/user/" + user.id,
        type: "DELETE"
    })
        .done(function(response) {
            window.location.href = '/admin/users';
        })
        .fail(function(jqxhr, textStatus, errorThrown){
            displayErrorInformation(jqxhr.responseText);
        });
}

 function performPasswordChange() {

        $("#change-password-button").prop( "disabled", true );

        var password = $("#new-password").val();
        var repeatedPassword = $("#repeated-new-password").val();

        if(arePasswordsValid(password, repeatedPassword)) {
            sendPasswordChangeRequest(password);
        } else {
            $("#change-password-button").prop( "disabled", false );
        }
}


function sendPasswordChangeRequest(password) {
       $.ajax({
               url: "/admin/api/scaffolding/user-change-password/"  + user.id,
               method: "put",
               contentType: "application/json",
               data: JSON.stringify(
                   {
                       newPasswordHash: hash(password),
                   })
           })
               .done(function () {
                   $("#operation-successful-modal").modal('show');
               })
               .fail(function(jqxhr, textStatus, errorThrown){
                   $("#change-password-button").prop("disabled", false);
                   showError(prepareErrorMessage(jqxhr.responseText));
               });
}

function fillRoles() {
       customer.authorities.forEach(function(authority){
           $('#'+authority).attr('checked', true);
       })
}

function getAuthorities(){
        var authorities = [];
        for(i = 0; i < authorityDict.length; i++) {
            if($('#'+authorityDict[i].code).is(":checked")){
                authorities.push(authorityDict[i].code);
            }
        }
         return authorities;
}

function sendUpdateRequest() {
    $.ajax({
        url: "/admin/api/scaffolding/user/" + user.id,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            invoiceFirstAndLastName: $("#first-and-last-name").val(),
            login: $("#login").val(),
            language: $("#language").find(":selected").val(),
            isEnabled: $("#enabled").find(":selected").val(),
            registrationDatetime: $("#registration-datetime").val(),
            regulationAccepted: $("#regulation").find(":selected").val(),
            newsletterAccepted: $("#newsletter").find(":selected").val(),
            isEmailConfirmed: $("#email-confirmed").find(":selected").val(),
            authorities : getAuthorities()

        })
    })
        .done(function () {
            $("#operation-successful-modal").modal('show');
        })
        .fail(function (jqxhr, textStatus, errorThrown) {
            displayErrorInformation(jqxhr.responseText);
            $("#save-changes-button").prop( "disabled", false );
            showError(prepareErrorMessage(jqxhr.responseText));
        })
}
function showError(text) {
     $("#error-alert-text").text(text);
     $("#error-alert").removeClass('d-none');
}
