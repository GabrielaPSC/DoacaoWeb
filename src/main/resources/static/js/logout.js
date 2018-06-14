$("#btn-logout").click(logout);


function logout() {

    var login = {};
    login["instituicaoId"] = localStorage.getItem("instituicaoId");

    $.ajax({
        method: "GET",
        url: "/logout?instituicaoId=" + localStorage.getItem("instituicaoId"),
        contentType: "application/JSON",
        success: function(data){
            localStorage.setItem("token", null);
            localStorage.setItem("instituicaoId", null);
            localStorage.setItem("instituicaoNome", null);
            document.location.href = "/login";
        },
        error: function(data){
            alert(data.responseText);
        }
    });
}