$("#btn-login").click(logar);


function logar() {

    var login = {};
    login["usuario"] = document.getElementById("txt-email").value;
    login["senha"] = document.getElementById("txt-senha").value;

    $.ajax({
        method: "POST",
        url: "/logar",
        data: JSON.stringify(login),
        contentType: "application/JSON",
        success: function(data){
            localStorage.setItem("token", data)
            document.location.href = "/instituicao/dash?token=" + data;
        },
        error: function(data){
            alert(data.responseText);
        }
    });
}