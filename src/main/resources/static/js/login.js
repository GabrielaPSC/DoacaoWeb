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
        },
        error: function(data){
            alert(data.responseText);
        }
    });
}

function abrirDash() {
    $.ajax({
        method: "GET",
        url: "/instituicao/dash",
        data: JSON.stringify(login),
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function(data){
            localStorage.setItem("token", data)
            document.location.href = "/instituicao/dash";
        },
        error: function(data){
            alert(data.responseText);
        }
    });
}