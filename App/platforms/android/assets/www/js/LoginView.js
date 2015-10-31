var LoginView  = function () {

	this.login = function(){
					if($('#usuario').val()=='' || $('#contrasena').val()=='')
					{
						console.log('algun campo vacio');
						alert('Algun campo esta vacio','Error',null);
					}
					else
					{
												console.log('envio peticíon');
						adapter.loginUser($('#usuario').val(),$('#contrasena').val()).done(function(json){
							if (json.valido == true)
							{
								if(json.deshabilitado == false)
								{
									loggedID = json.loggedid;
								almacenSesion.iniciarsesion(loggedID);
								window.location.replace(window.location.href.substr(0,window.location.href.lastIndexOf('login/')));
								}
								else{
									alert('Este usuario esta deshabilitado, ponte en contacto con el administrador','Alerta',null);
								}
							}
							else{
								alert('Usuario o contraseña incorrectos','Error',null);
							}
						});
					}
					
				};

    this.inicializar = function () {
        this.el = $('<div/>');
		this.el.on('click','#loginbutton',this.login);
    };

    this.render = function() {
        this.el.html(Handlebars.templates.login());
        return this.el;
     
    };
	
	this.inicializar();
}