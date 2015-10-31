
var HomeView = function () {

	this.logout = function(){
		almacenSesion.eliminarsesion();
		alert('Has cerrado la sesión','Aviso',function(){window.location.reload();});
		//window.location.reload();
	}

    this.inicializar = function () {
        // Definimos un div para la vista. Lo usaremos para añadir eventos.
        this.el = $('<div/>');
		
		this.el.on('click', '#buttonlogout', this.logout);   
    };

	function crearJsonSesion(){
	var json = {};
	json['logged'] = almacenSesion.comprobarsesion();

	if (!json['logged'])
		{
		json['logout'] = true;
		}
	console.log(json);
	return json;
	}
	
    this.render = function() {
	var json = crearJsonSesion()
	this.el.html(Handlebars.templates.home(json)); 
        return this.el;
    };

	this.inicializar();


	}