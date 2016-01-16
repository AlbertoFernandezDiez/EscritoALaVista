	/*----------------------------------- Variables globales --------------------------------- */
//var urlStack;
var loggedID;
var adapter;
var almacenSesion;

function mostrarSpinner(){
			cordova.plugins.pDialog.init({
			theme : 'TRADITIONAL',
			progressStyle : 'SPINNER',
			cancelable : false,
			title : 'Por favor, espere',
			message : 'Contactando con el servidor'
		});
	}
	
	
	function ocultarSpinner(){
		cordova.plugins.pDialog.dismiss();
	}

	
(function () {
    /* ---------------------------------- Variables locales ---------------------------------- */
	var previousURL = '#';
	 urlStack = new Array();
	 loggedID = '';
	
	almacenSesion = new LocalStorageAdapter();
    adapter = new JSONPAdapter();    
	
    var obraURL = /^#obra\/(\d{1,})/; 
	var capituloURL = /^#capitulo\/(\d{1,})/; 
	var comentarioURL = /^#comentarios\/(\d{1,})\/(\d{1,})/; 
	var autorURL = /^#autor\/(\d{1,})/; 
	var loginURL = /^#login/;
	var cambiarDatosURL = /^#cambiarDatos/;
	var cambiarObraURL = /^#cambiarObraApi/;
	var registrarseURL = /^#registrarseAPI/;
	var listadoObraURL = /^#listadoAPI/;
	var contrasenaOlvidadaURL = /^#contrasenaOlvidada/; 
	var cambiarContrasenaURL = /^#cambiarContrasena/;
	
	almacenSesion.inicializar().done(function () {
        console.log("Inicializado: Adaptador de datos");
    });
	
   adapter.inicializar().done(function () {
        console.log("Inicializado: Adaptador de datos");
        $('body').html(new HomeView().render());
     //   route();
    });

	
	
	
    /* --------------------------------- Registro de eventos -------------------------------- */
    document.addEventListener('deviceready', function () {
    	if (navigator.notification) { // Si disponemos de notificaciones nativas, sobreescribimos el alert del navegador:
        	window.alert = function (message, title, callback) {
            		navigator.notification.alert(
                	message,    // mensaje
                	callback,  // función de callback
                	title, // título
                	'OK'        // Nombre botón
            	);
              };
			  
			  window.confirm = function (message, title, callback) {
            		navigator.notification.confirm(
                	message,    // mensaje
                	callback,  // función de callback
                	title // título
            	);
              };
			/*  window.confirm = function(message,title, confirmCallback){
					navigator.notification.confirm(
					message,	//mensaje
					callback,	//función de callback
					title					//titulo
					//,['Sí','No']	//Nombre de los botones
				);
			  };*/
    	}
    }, false);	

	$('#btnLogout').on('click',adapter.logoutUser)
	
	
	
   $(window).on('hashchange', route);

    /* ---------------------------------- Funciones locales ---------------------------------- */
	
	/*function enviarComentario(){
		console.log('pulsado');
		if($('#comentario').val() != ''){
			adapter.sendComentarios($('#idO').val(),$('#idC').val(),$('#comentario').val()).done(function(resultado){
				console.log('respuesta: ' + resultado);
				alert(resultado,'aviso');
				window.location.replace(window.location.href + '?');
				
			});
		}
	}*/
	/*
	function mostrarSpinner(){
			cordova.plugins.pDialog.init({
			theme : 'TRADITIONAL',
			progressStyle : 'SPINNER',
			cancelable : false,
			title : 'Por favor, espere',
			message : 'Contactando con el servidor'
		});
	}
	
	
	function ocultarSpinner(){
		cordova.plugins.pDialog.dismiss();
	}
	*/
	function logout(){
		adapter.logoutUser();
		loggedID = '';		
	}
	
  function route() {
    var hash = window.location.hash;
    if (!hash) {
        $('body').html(new HomeView(adapter).render());
		if(previousURL == '#')
		urlStack.push(previousURL);
		else{
		urlStack.pop();
		previousURL = '#';
		}
        return;
    }
 

  
  var match = hash.match(obraURL);

    if (match) {
		
	
		//updateStack(hash);
				mostrarSpinner();

        adapter.detallesObra(Number(match[1]),urlStack[urlStack.length - 1]).done(function(obra) {
            $('body').html(new DetalleObraView(adapter, obra).render());
			ocultarSpinner();
        });
    }
	
	match = hash.match(capituloURL);

    if (match) {
		
		////updateStack(hash);
		mostrarSpinner();
        adapter.detallesCapitulo(Number(match[1]),urlStack[urlStack.length - 1]).done(function(capitulo) {
            $('body').html(new DetalleCapituloView(adapter, capitulo).render());

			ocultarSpinner();
			});
    }
	
	match = hash.match(comentarioURL);

    if (match) {
	
		//updateStack(hash);
			mostrarSpinner();

        adapter.verComentarios(Number(match[2]),Number(match[1]),urlStack[urlStack.length - 1]).done(function(comentarios) {
            $('body').html(new MostrarComentariosView(adapter, comentarios).render());
			//previousURL = hash;
		ocultarSpinner();
		
		
	});
		
    }
	
	match = hash.match(autorURL);

    if (match) {
	
	
				mostrarSpinner();

		//Cargo los datos de la pantalla siguiente
        adapter.verAutor(Number(match[1]),urlStack[urlStack.length - 1]).done(function(autor) {
			
			//Compruebo si existe la imagen, si no la descargo
			
			//Cargo la siguiente pantalla
				$('body').html(new MostrarAutorView(adapter, autor).render());
ocultarSpinner();
		});
	}
	
	match = hash.match(loginURL);

    if (match) {
	
	console.log(hash);
	
			//Cargo la siguiente pantalla
				$('body').html(new LoginView().render());
    }
	
	match = hash.match(listadoObraURL);

    if (match) {
	
	console.log(hash);

	mostrarSpinner();
			//Cargo la siguiente pantalla
				$('body').html(new ListaObrasView(adapter).render());
				
				ocultarSpinner();
	}
	
	match = hash.match(cambiarDatosURL);
	
	 if (match) {
	
		mostrarSpinner();
        adapter.cambiarDatos().done(function(datos) {
            $('body').html(new CambiarDatosView(adapter, datos).render());
			ocultarSpinner();
	});
	}
	
	match = hash.match(cambiarObraURL);
	
	 if (match) {
	console.log(previousURL);
		
		mostrarSpinner();
        adapter.editarobra().done(function(datos) {
            $('body').html(new EditarObraCapituloView(adapter, datos).render());
			ocultarSpinner();
	});
	}
	
	match = hash.match(registrarseURL);
	
	 if (match) {
	console.log(previousURL);
		
            $('body').html(new RegistrarseView().render());
	}
	
		match = hash.match(contrasenaOlvidadaURL);
	
	 if (match) {
	console.log(previousURL);
		
            $('body').html(new ContrasenaOlvidadaView().render());
	}
	
		match = hash.match(cambiarContrasenaURL);
	
	 if (match) {
	console.log(previousURL);
		
            $('body').html(new CambiarContrasenaView().render());
	}
	
	console.log(urlStack);
	
} 
}());
