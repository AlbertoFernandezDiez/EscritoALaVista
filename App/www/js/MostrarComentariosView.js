var MostrarComentariosView  = function (adapter, comentarios) {

function actualizar(resultado){
	
				if (resultado.ok == true){
				alert('Tu comentarios se ha publicado','Enhorabuena',null);//,function(window.location.replace(window.location.href + '?'));	
				}				
				else{
					if(resultado.desh == true)
					{
						alert('Este usuario ha sido deshabilitado por el administrador','Aviso',null);
					}
					else{
										alert('Tu comentarios no se ha publicado, intentalo más tarde','Error',null);//,null);
					}
				}
				window.location.replace(window.location.href + '?');
			};

this.enviarComentario = function(){
		console.log('pulsado');
//		debugger;
		if($('#comentario').val() != ''){
		//	mostrarSpinner();
			adapter.sendComentarios($('#idO').val(),$('#idC').val(),$('#comentario').val()).done(actualizar);
		}
	};

	
    this.inicializar = function () {
        this.el = $('<div/>');
		
		this.el.on('click','#enviarComentario',this.enviarComentario);
    };

    this.render = function() {
		console.log('render')
        this.el.html(Handlebars.templates.comentarios(comentarios));
        return this.el;
     
    };

	
	
	this.inicializar();
}