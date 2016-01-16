var RegistrarseView  = function () {

 var foto ='';
 var enviable = false;
 var enviableCorreo = false;

 
this.cambiarFotoAlbum = function(event) {
    event.preventDefault();
    if (!navigator.camera) {
        alert("Camera API no soportada", "Error",null);
        return;
    }
    var options =   {   quality: 50,
                        destinationType: Camera.DestinationType.DATA_URL,
						targetWidth: 640,
						targetHeight: 480,
                        sourceType: 0,      // 0:Photo Library, 1=Camera, 2=Saved Album
                        encodingType: 0     // 0=JPG 1=PNG
                    };
 
    navigator.camera.getPicture(
        function(imageData) {
            foto = imageData;
			$('.imagen-portada', this.el).attr('src', "data:image/jpeg;base64," + imageData);
			$('.imagen-portada', this.el).removeAttr('style');
        },
        function() {
            alert('Error al obtener la foto', 'Error');
        },
        options);
 
    return true;
};

this.cambiarFotoCamara = function(event) {
    event.preventDefault();
    if (!navigator.camera) {
        alert("Camera API no soportada", "Error",null);
        return;
    }
    var options =   {   quality: 50,
                        destinationType: Camera.DestinationType.DATA_URL,
						targetWidth: 640,
						targetHeight: 480,
                        sourceType: 1,      // 0:Photo Library, 1=Camera, 2=Saved Album
                        encodingType: 0,     // 0=JPG 1=PNG
						mediaType: 0	//Imagenes(	
                    };
 
    navigator.camera.getPicture(
        function(imageData) {
			foto = imageData;
			$('.imagen-portada', this.el).attr('src', "data:image/jpeg;base64," + foto);//"data:image/jpeg;base64," + imageData);        },
			$('.imagen-portada', this.el).removeAttr('style');
       }, function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
};
function mostrarAviso(resultado){
			console.log(resultado);
			if (resultado.value == false){
				alert('El nombre ya esta siendo utilizado','Aviso',null);
				enviable = false;
				}
				else{
				enviable = true;
				}
			}
		
    this.inicializar = function () {
        this.el = $('<div/>');
		this.el.on('click', '#SacarFotoAlbum', this.cambiarFotoAlbum);
		this.el.on('click', '#SacarFotoCamara', this.cambiarFotoCamara);
		
		this.el.on('blur', '#nombre', function(){
			adapter.checkNombre($('#nombre').val()).done(mostrarAviso);	
			});
			
			this.el.on('blur', '#email', function(){
			adapter.comprobarEmail($('#email').val()).done(function(resultado){
			console.log(resultado.value);
			if (resultado.value == false){
				alert('El email ya esta siendo utilizado','Aviso',null);
				enviableCorreo = false;
				}
				else{
				enviableCorreo = true;
				}
			});	
			});
		
		this.el.on('click', '#EnviarRegistro', function(){
		if ($('#nombre').val() != '' && $('#contrasena').val() != '' && $('#email').val()
			 != '' &&  $('#pais').val() != '' &&  $('#about').val() != '' &&  $('#nacimiento').val() && enviable == true && enviableCorreo== true){
			adapter.registrarse($('#nombre').val(),$('#contrasena').val(),$('#email').val()
			, $('#pais').val(), $('#about').val(), $('#nacimiento').val() ,foto).done(
			function(resultado){
			if (resultado.value == true)
				alert('Se ha creado el nuevo usuario','Aviso',function(){window.location.replace(window.location.href.substr(0,window.location.href.lastIndexOf('registrarseAPI/')));});
				//window.location.replace(window.location.href.substr(0,window.location.href.lastIndexOf('registrarseAPI/')));
			}
			);
			}
			else{
				alert('Hay un campo sin llenar o el nombre o el correo ya esta siendo utilizado','Aviso',null);
			}
		});
    };

    this.render = function() {
        this.el.html(Handlebars.templates.registrarse());
        return this.el;
     
    };
	
	this.inicializar();
}