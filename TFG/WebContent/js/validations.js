function toSHA512(password) {
	var jsSha = new jsSHA(password);
	var hash = jsSha.getHash("SHA-512", "HEX");

	return hash;


}

function presubmit(){
	$( "#registro" ).submit(function( event ) {
		var $contra1 = $("#contrasena1");
		var $contra2 = $("#contrasena2");
		if ($contra1.val() == $contra2.val()) {
			$("#contra").val(toSHA512($contra1.val()));
			$contra1.remove();
			$contra2.remove();
			return;
		}
		else{}
		$( "span" ).text( "Not valid!" ).show().fadeOut( 1000 );
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