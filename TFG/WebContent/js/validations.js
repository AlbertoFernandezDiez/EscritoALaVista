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
			$contra1.val(toSHA512($contra1.val()));
			$contra2.val(toSHA512($contra2.val()));
			return;
		}
		else{}
		$( "span" ).text( "Not valid!" ).show().fadeOut( 1000 );
		event.preventDefault();
	});}

function loginSubmit(){
	$( "#login" ).submit(function( event ) {
		var $contra = $("#contrasena");

		$contra.val(toSHA512($contra.val()));
		return;
		event.preventDefault();

	});}