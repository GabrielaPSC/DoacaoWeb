$(document).ready(baixarPropostas);

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
        }
    });
}

function exibirPropostas() {
    
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
        cols += "<button onclick='aceitar(" + propostas[i].id + ")' type='button'>Aceitar</button>";
        cols += "<button onclick='rejeitar(" + propostas[i].id + ")' type='button'>Rejeitar</button>";
        cols += '</td>';	
        newRow.append(cols);	    

        $("#dataTable").append(newRow);
    }
    

}