$(document).ready(function() {
    trace('page.show', 'register');
});

function performUserRegistration() {

    $("#register-button").prop( "disabled", true );

    var login = $("#login").val();
    var firstName = $("#first-name").val();
    var lastName = $("#last-name").val();
    var password = $("#password").val();
    var repeatedPassword = $("#repeated-password").val();
    var language = $("#language").find(":selected").val();

    if(arePasswordsValid(password, repeatedPassword)) {
        sendUserPostRequest(login, firstName, lastName, password, language);
    } else {
        $("#register-button").prop( "disabled", false );
    }
}

function sendUserPostRequest(login, firstName, lastName, password, language) {
   $.ajax({
           url: "/api/scaffolding/register-user",
           method: "post",
           contentType: "application/json",
           data: JSON.stringify(
               {
                   login: login,
                   firstName: firstName,
                   lastName: lastName,
                   passwordHash: hash(password),
                   language: language,
                   regulationAccepted: $("#regulation").prop('checked'),
                   newsletterAccepted: $("#newsletter").prop('checked'),
                   isEnabled: 'true',
                   isEmailConfirmed: 'false'
               })
       })
           .done(function () {
               window.location.href = '/registered-successfully';
           })
           .fail(function(jqxhr, textStatus, errorThrown){
               $("#register-button").prop( "disabled", false );
               showError(prepareErrorMessage(jqxhr.responseText));
           });
}

function showError(text) {
    $("#error-alert-text").text(text);
    $("#error-alert").removeClass('d-none');
}
