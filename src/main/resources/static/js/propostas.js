$(document).ready(function(){
    $("#instituicaoNome").text(localStorage.getItem("instituicaoNome"));
    baixarPropostas();
});

var propostas = [];

function baixarPropostas() {
    $.ajax({
        method: "GET",
        url: "/instituicao/dash/propostas?instituicaoId=" + localStorage.getItem('instituicaoId'),
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function(data){
            propostas = data;
            exibirPropostas();
        },
        error: function(data){
            alert(data.responseText);
            $("#dataTable").DataTable();
        }
    });
}

function exibirPropostas() {
    
    $("#dataTable > tbody").empty();
    
    var newRow;
    var cols;
    
    for (i = 0; i < propostas.length; i++) { 
        newRow = $("<tr>");	    
        cols = "";	

        cols += "<td>" + propostas[i].nomeDoador + "</td>";
        cols += "<td>" + propostas[i].telefone + "</td>";
        cols += "<td>" + propostas[i].email + "</td>";
        cols += "<td>" + propostas[i].nomeDoacao + "</td>";
        cols += "<td>" + propostas[i].quantidade + "</td>";
        cols += "<td>" + propostas[i].dataProposta + "</td>";
        cols += '<td>';	    
        cols += "<a class='btn btn-success' onclick='aceitar(" + propostas[i].id + ")'><i class='fa fa-fw fa-check'></i></a>";
        cols += "<a class='btn btn-danger' onclick='rejeitar(" + propostas[i].id + ")'><i class='fa fa-fw fa-trash'></i></a>";
        cols += '</td>';	
        newRow.append(cols);	    

        $("#dataTable").append(newRow);
    }

    $("#dataTable").DataTable();
}