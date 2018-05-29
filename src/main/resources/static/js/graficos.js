$(document).ready(function () {
    $("#instituicaoNome").text(localStorage.getItem("instituicaoNome"));
    
        Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif',
            Chart.defaults.global.defaultFontColor = "#292b2c";
    
    baixarQuantidadePropostas();
    baixarPropostasAceitas();
    baixarStatusPropostas();
});

function baixarQuantidadePropostas() {
    $.ajax({
        method: "GET",
        url: "/instituicao/dash/quantidadePropostas?instituicaoId=" + localStorage.getItem('instituicaoId'),
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function (data) {
            exibirQuantidadePropostas(data);
        },
        error: function (data) {
            alert(data.responseText);
        }
    });
}

function baixarPropostasAceitas() {
    $.ajax({
        method: "GET",
        url: "/instituicao/dash/propostasAceitas?instituicaoId=" + localStorage.getItem('instituicaoId'),
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function (data) {
            exibirPropostasAceitas(data);
        },
        error: function (data) {
            alert(data.responseText);
        }
    });
}

function baixarStatusPropostas() {
    $.ajax({
        method: "GET",
        url: "/instituicao/dash/statusPropostas?instituicaoId=" + localStorage.getItem('instituicaoId'),
        contentType: "application/JSON",
        headers: {"Authorization": localStorage.getItem('token')},
        success: function (data) {
            exibirStatusPropostas(data);
        },
        error: function (data) {
            alert(data.responseText);
        }
    });
}

function exibirStatusPropostas(statusPropostas) {

    var labels = [];
    var data = [];
    
    var maiorQuantidade = 0;

    for (i = 0; i < statusPropostas.length; i++) {
        labels.push(statusPropostas[i].data);
        data.push(statusPropostas[i].quantidade);
        
        if (statusPropostas[i].quantidade > maiorQuantidade) {
            maiorQuantidade = statusPropostas[i].quantidade;
        }
    }
        
    ctx = document.getElementById("myPieChart"), 
    myPieChart = new Chart(
            ctx, 
            {
                type: "pie", 
                data: {
                    labels: labels, 
                    datasets: [
                        {
                            data: data, 
                            backgroundColor: [
                                "#007bff", 
                                "#dc3545", 
                                "#ffc107"
                            ]
                        }
                    ]
                }
            }
    );
}


function exibirPropostasAceitas(propostasAceitas) {

    var labels = [];
    var data = [];
    
    var maiorQuantidade = 0;

    for (i = 0; i < propostasAceitas.length; i++) {
        labels.push(propostasAceitas[i].data);
        data.push(propostasAceitas[i].quantidade);
        
        if (propostasAceitas[i].quantidade > maiorQuantidade) {
            maiorQuantidade = propostasAceitas[i].quantidade;
        }
    }
    
    ctx = document.getElementById("myBarChart"),
        myLineChart = new Chart(
                ctx, 
                {
                    type: "bar", 
                    data: {
                        labels: labels, 
                        datasets: [
                            {
                                label: "Revenue", 
                                backgroundColor: "rgba(2,117,216,1)", 
                                borderColor: "rgba(2,117,216,1)", 
                                data: data
                            }
                        ]
                    }, 
                    options: {
                        scales: {
                            xAxes: [
                                {
                                    time: {
                                        unit: "month"
                                    }, 
                                    gridLines: {
                                        display: !1
                                    }, 
                                    ticks: {
                                        maxTicksLimit: 6
                                    }
                                }
                            ], 
                            yAxes: [
                                {
                                    ticks: {
                                        min: 0, 
                                        max: maiorQuantidade, 
                                        maxTicksLimit: 5
                                    }, 
                                    gridLines: {
                                        display: !0
                                    }
                                }
                            ]
                        }, 
                        legend: {
                            display: !1
                        }
                    }
                }
        );
}

function exibirQuantidadePropostas(quantidadePropostas) {

    var labels = [];
    var data = [];
    
    var maiorQuantidade = 0;

    for (i = 0; i < quantidadePropostas.length; i++) {
        labels.push(quantidadePropostas[i].data);
        data.push(quantidadePropostas[i].quantidade);
        
        if (quantidadePropostas[i].quantidade > maiorQuantidade) {
            maiorQuantidade = quantidadePropostas[i].quantidade;
        }
    }


    var ctx = document.getElementById("myAreaChart"),
            myLineChart = new Chart(ctx, {type: "line",
                data: {
                    labels: labels,
                    datasets: [
                        {
                            label: "Sessions",
                            lineTension: .3,
                            backgroundColor: "rgba(2,117,216,0.2)",
                            borderColor: "rgba(2,117,216,1)",
                            pointRadius: 5,
                            pointBackgroundColor: "rgba(2,117,216,1)",
                            pointBorderColor: "rgba(255,255,255,0.8)",
                            pointHoverRadius: 5,
                            pointHoverBackgroundColor: "rgba(2,117,216,1)",
                            pointHitRadius: 20,
                            pointBorderWidth: 2,
                            data: data
                        }
                    ]
                },
                options: {
                    scales: {
                        xAxes: [
                            {
                                time: {
                                    unit: "date"
                                },
                                gridLines: {
                                    display: !1
                                },
                                ticks: {
                                    maxTicksLimit: 7
                                }
                            }
                        ],
                        yAxes: [
                            {
                                ticks: {
                                    min: 0,
                                    max: maiorQuantidade,
                                    maxTicksLimit: 5
                                },
                                gridLines: {
                                    color: "rgba(0, 0, 0, .125)"
                                }
                            }
                        ]
                    },
                    legend: {
                        display: !1
                    }
                }
            }
            );            
        
}