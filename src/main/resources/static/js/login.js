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
            if (data == "ok"){
                document.location.href = "/instituicao/dash";
            } else {
                alert(data);
            }
        },
        error: function(data){
            alert(data.responseText);
        }
    });
}