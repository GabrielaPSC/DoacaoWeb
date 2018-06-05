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
            localStorage.setItem("token", data.token);
            localStorage.setItem("instituicaoId", data.instituicaoId);
            localStorage.setItem("instituicaoNome", data.instituicaoNome);
            document.location.href = "/instituicao/dash";
        },
        error: function(data){
            alert(data.responseText);
        }
    });
}