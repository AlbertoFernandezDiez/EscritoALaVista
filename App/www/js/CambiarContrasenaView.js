var CambiarContrasenaView  = function () {

	this.enviar = function(){
		var contraV = $('#contrasenaVieja').val();
		var contraN = $('#contrasenaNueva').val();
						mostrarSpinner();
if($('#contrasenaNueva')[0].checkValidity()){
		adapter.cambiarContrasena(contraV,contraN).done(function(resultado){
			ocultarSpinner();
		if (resultado.value == true){
			alert('La contraseña se ha cambiado correctamente','Aviso',function(){window.location.reload();});
			//window.location.reload();
		}
		else{
			if(resultado.desh == true){
				alert('Este usuario ha sido deshabilitado por el admintistrador','Aviso',null);
			}
			else{
			alert('La contraseña antigua no es correcta','Error',null);
			}
		}
		});
}
else{
				ocultarSpinner();
alert('La contraseña nueva debe tener al menos 6 carácteres','Aviso',null);
}
	};

    this.inicializar = function () {
        this.el = $('<div/>');
		this.el.on('click','#changePassword',this.enviar);
    };

    this.render = function() {
        this.el.html(Handlebars.templates.cambiarContrasena());
        return this.el;
     
    };
	
	this.inicializar();
}