var map;
var marker;

function initMap() {
    var uluru = {lat: -25.363, lng: 131.044};
    map = new google.maps.Map(document.getElementById('map'), {
      zoom: 4,
      center: uluru
    });
}

var button = document.getElementById("btn");
var locationValue;

// getting address value
txtEndereco = document.getElementById("inputEndereco");
txtNumero   = document.getElementById("inputNumero");
txtBairro   = document.getElementById("inputBairro");
txtCidade   = document.getElementById("inputCidade");
txtEstado   = document.getElementById("inputEstado");
txtPais     = document.getElementById("inputPais");

txtEndereco.addEventListener('focusout', getValue, false);
txtNumero.addEventListener('focusout', getValue, false);
txtBairro.addEventListener('focusout', getValue, false);
txtCidade.addEventListener('focusout', getValue, false);
txtEstado.addEventListener('focusout', getValue, false);
txtPais.addEventListener('focusout', getValue, false);

function getValue(){
        var endereco = txtEndereco.value + ", " + txtNumero.value + ", " + txtBairro.value + ", " + txtCidade.value + ", "; //+ txtPais.value;
	locationValue = endereco;
	getCoords();
}

// getcords 
function getCoords(){

    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function(){
        if(this.readyState == 4 && this.status == 200){
            var myData = JSON.parse(this.responseText);
            
            var lat = myData.results[0].geometry.location.lat;
            var lng = myData.results[0].geometry.location.lng;
            var coords = {lat: lat, lng: lng};

            map.setCenter(coords);

            if (marker !== undefined) {
                marker.setMap(null);
            }

            marker = new google.maps.Marker({
                position: coords,
                map: map
            });

        }//if condition ends here
    }//onreadystatechange method ends here

    xmlhttp.open('GET', 'https://maps.googleapis.com/maps/api/geocode/json?address='+locationValue+'&key=AIzaSyB7F4NjyBWsKqlvRqG4kPl6wLORFa2eLhE',true);
    xmlhttp.send();

}