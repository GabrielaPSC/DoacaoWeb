$(document).ready(function(){
    $("#instituicaoNome").text(localStorage.getItem("instituicaoNome"));
    baixarNecessidades();
    $("#btn-adicionar-necessidade").click(adicionarNecessidade);
});

var necessidades = [];

function adicionarNecessidade() {
    
    var instituicao = {};
    instituicao["id"] = localStorage.getItem('instituicaoId');
    
    var necessidade = {};
    necessidade["necessidade"] = $("#txt-necessidade").val();
    necessidade["quantidade"]  = $("#txt-quantidade").val();
    necessidade["instituicao"]  = instituicao;
    
    $.ajax({
        method: "POST",
        url: "/instituicao/dash/registrarNecessidade",
        contentType: "application/JSON",
        data: JSON.stringify(necessidade),
        headers: {"Authorization": localStorage.getItem('token')},
        success: function(data){
            necessidades.unshift(data);
            exibirNecessidades();
            
            $("#txt-necessidade").val("");
            $("#txt-quantidade").val("");
        },
        error: function(data){
            alert(data.responseText);
            $("#dataTable").DataTable();
        }
    });
}

function removerNecessidade(row, necessidadeId) {
    $.ajax({
        method: "GET",
        url: "/instituicao/dash/removerNecessidade?necessidadeId=" + necessidadeId,
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function(data){
            var tr = $(row).closest('tr');	
            tr.fadeOut(400, function() {	      
                tr.remove();  	    
                $("#dataTable").DataTable();
            });
        },
        error: function(data){
            alert(data.responseText);
            $("#dataTable").DataTable();
        }
    });
}

function baixarNecessidades() {
    $.ajax({
        method: "GET",
        url: "/instituicao/baixarNecessidades?instituicaoId=" + localStorage.getItem('instituicaoId'),
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function(data){
            necessidades = data;
            exibirNecessidades();
        },
        error: function(data){
            alert(data.responseText);
            $("#dataTable").DataTable();
        }
    });
}

function exibirNecessidades() {
    
    $("#dataTable > tbody").empty();
    
    var newRow;
    var cols;
    
    for (i = 0; i < necessidades.length; i++) { 
        newRow = $("<tr>");	    
        cols = "";	

        cols += "<td>" + necessidades[i].necessidade + "</td>";
        cols += "<td>" + necessidades[i].quantidade + "</td>";
        cols += '<td>';	    
        cols += "<a class='btn btn-danger' onclick='removerNecessidade(this, " + necessidades[i].id + ")'><i class='fa fa-fw fa-trash'></i></a>";
        cols += '</td>';	
        newRow.append(cols);	    

        $("#dataTable").append(newRow);
    }

    $("#dataTable").DataTable();
}