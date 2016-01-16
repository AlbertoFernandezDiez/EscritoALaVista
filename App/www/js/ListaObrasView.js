var ListaObrasView = function (adapter) {

    this.inicializar = function () {
        // Definimos un div para la vista. Lo usaremos para añadir eventos.
        this.el = $('<div/>');
        this.cargarObras();

   
    };

    this.render = function() {
        return this.el;
     //esto es lo que hemos movido de app.js aquí:
     //$('body').html(Handlebars.templates.home());
     //La siguiente línea sobra, ya está puesta al inicializar: 
     //$('#btnBuscar').on('keyup', encontrarPorNombre);

    };

	this.cargarObras = function() {
		console.log('cargar obras');
       adapter.cargarObras().done(function (obras) {
           //$("#lstObras").html(Handlebars.templates.listaobras(obras)); 
		   $("body").html(Handlebars.templates.listaobras(obras)); 
	   });
	}

	this.inicializar();


	}