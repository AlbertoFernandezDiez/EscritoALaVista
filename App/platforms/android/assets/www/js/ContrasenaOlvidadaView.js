var ContrasenaOlvidadaView  = function () {

	this.enviar = function(){
		var correo = $('#email').val();
		mostrarSpinner();
		adapter.contrasenaOlvidada(correo).done(
		function(resultado){
			ocultarSpinner();
		if (resultado.value == true){
			alert('Recibiras una nueva contraseña en tu correo en breves momentos, recuerda cambiarla desde la web.','Aviso',null);
		}
		else{
			alert('El correo que has introducido no existe en nuestra Base de Datos.','Aviso',null);
		}
		}
		);
	}

    this.inicializar = function () {
        this.el = $('<div/>');
		this.el.on('click','#enviarEmail',this.enviar);
    };

    this.render = function() {
        this.el.html(Handlebars.templates.contrasenaOlvidada());
        return this.el;
     
    };
	
	this.inicializar();
}