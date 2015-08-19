function toSHA512(password) {
	var jsSha = new jsSHA(password);
	var hash = jsSha.getHash("SHA-512", "HEX");

	return hash;


}

function presubmit(){
	$( "#registro" ).submit(function( event ) {
		var $contra1 = $("#contrasena1");
		var $contra2 = $("#contrasena2");
		if ($contra1.val() == $contra2.val() && registro) {
			$("#contra").val(toSHA512($contra1.val()));
			$contra1.remove();
			$contra2.remove();
			return;
		}
		else{}
		$( "#errorContr" ).show(500);
		event.preventDefault();
	});}

function loginSubmit(){
	$( "#login" ).submit(function( event ) {
		var $contras = $("#contrasena");

		$("#contra").val(toSHA512($contras.val()));
		$contras.remove();
		return;
		event.preventDefault();

	});}

function checkUserName(){
	var nombre = $('#usuario').val();
	console.log(nombre);
	if (nombre != '')
	{
		$.ajax({
			url : "CU",
			type : 'POST',
			data : {
				nombre : nombre
			},
			success : function(result) {
				if (result == 'true') {
					$('#comprobacionUsuario').addClass("has-success"); 
					$('#usado').hide();
					correcto = true;
				} else {
					$('#usado').show();
					$('#comprobacionUsuario').addClass("has-error");  
					correcto = false;
				}
			},
			error : function(request, error) {
				$("#bad").show(200);
			}
		}
		);
	}
}

function checkEmail(){
	var email = $('#email').val();
	console.log(email);
	if (email != '')
	{
		$.ajax({
			url : "api/ComprobarEmailAPI",
			type : 'POST',
			dataType: "json",
			data : {
				email : email
			},
			success : function(result) {
				if (result.value == true) {
					$('#comprobacionEmail').addClass("has-success"); 
					$('#usadoEmail').hide();
					correcto = true;
				} else {
					$('#usadoEmail').show();
					$('#comprobacionEmail').addClass("has-error");  
					correcto = false;
				}
			},
			error : function(request, error) {
				$("#bad").show(200);
			}
		}
		);
	}
}

function checkTitulo(){
	var nombre = $.trim($('#titOb').val());
	var selObra =$.trim($('#selectObra option:selected').text());
	console.log(selObra);
	console.log(nombre);
	if (nombre != '' && nombre != selObra /*&& selObra == 0*/)
	{
		$.ajax({
			url : "CT",
			type : 'POST',
			data : {
				nombre : nombre
			},
			success : function(result) {
				console.log(result);
				if (result == 'true') {
					$('#comprobacionTitulo').addClass("has-success"); 
					$('#usado').hide();
					correcto = true;
				} else {
					$('#usado').show();
					$('#comprobacionTitulo').addClass("has-error");  
					correcto = false;
				}
			},
			error : function(request, error) {
				$("#bad").show(200);
			}
		}
		);
	}
	else
		{
		correcto = true;
		$('#comprobacionTitulo').addClass("has-success"); 
		$('#usado').hide();
		}
}

function cambiarContrasena() {
	var n = $('#contraN').val();
	var v = $('#contraV').val();
	//Validamos los campos del formulario
					$('#fail').hide();
					$('#ok').hide();
					$('#bad').hide();
	if ($('#cambioContra')[0].checkValidity()) {
		
		var sn = toSHA512(n);
		var sv = toSHA512(v);

		$.ajax({
			url : "CC",
			type : 'POST',
			data : {
				old : sv,
				newC : sn
			},
			success : function(result) {
				if (result == 'true') {
					$('#ok').show();
					$('#bad').hide();
					$('#contraN').val('');
					$('#contraV').val('');
				} else {
					$('#bad').show();
					$('#ok').hide();
				}
			},
			error : function(request, error) {
				$("#bad").show(200);
			}
		});
	} else {
		$('#fail').show();
	}
}

function cambiarContrasenaAdmin() {
	var n = $('#contraN').val();
	var v = $('#contraV').val();
	//Validamos los campos del formulario
					$('#fail').hide();

	if ($('#cambioContra')[0].checkValidity()) {
		
		var sn = toSHA512(n);
		var sv = toSHA512(v);

		$.ajax({
			url : "CCA",
			type : 'POST',
			data : {
				old : sv,
				newC : sn
			},
			success : function(result) {
				if (result == 'true') {
					/*$('#ok').show();
					$('#bad').hide();
					$('#contraN').val('');
					$('#contraV').val('');*/
					$('#ok').modal('show');
					$('#bad').modal('hide');
					$('#contraN').val('');
					$('#contraV').val('')
				} else {
					/*$('#bad').show();
					$('#ok').hide();*/
					$('#bad').modal('show');
					$('#ok').modal('hide');
				}
			},
			error : function(request, error) {
				//$("#bad").show(200);
				$("#bad").modal('show');
			}
		});
	} else {
		//$('#fail').show();
		$('#fail').modal('show');
	}
}



function presubmitBook (){
$( "#obra" ).submit(function( event ) {
	
	if (correcto) {
		
		return;
	}
	else{}
	$( "span" ).text( "El titulo no es valido!" ).show().fadeOut( 2000 );
	event.preventDefault();
});}