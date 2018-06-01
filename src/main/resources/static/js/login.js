$("#btn-login").click(logar);


function logar() {

    var necessidade = {};
    login["necessidade"] = document.getElementById("txt_necessidade").value;
    login["quantidade"] = document.getElementById("txt_quantidade").value;

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