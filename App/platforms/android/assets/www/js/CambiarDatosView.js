var CambiarDatosView = function(adapter, datos) {

 var foto = '';
 var mail = datos.email;
 var enviableCorreo = true;
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
            foto =  imageData;
			$('#imagen', this.el).attr('src',"data:image/jpeg;base64," + foto);//"data:image/jpeg;base64," + imageData);
			$('#imagen', this.el).removeAttr('style');//"data:image/jpeg;base64," + imageData);
        },
        function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
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
			$('#imagen', this.el).attr('src', "data:image/jpeg;base64," + foto);//"data:image/jpeg;base64," + imageData);
			$('#imagen', this.el).removeAttr('style');//"data:image/jpeg;base64," + imageData);

       }, function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
};

    this.inicializar = function() {
        this.el = $('<div/>');
		
		
		this.el.on('click', '#SacarFotoAlbum', this.cambiarFotoAlbum);
		this.el.on('click', '#SacarFotoCamara', this.cambiarFotoCamara);
		this.el.on('blur', '#email', function(){
			if (mail != $('#email').val()){
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
			}
			});
			
		this.el.on('click', '#EnviarCambioDatos', function(){
			if (enviableCorreo == true && $('#email').val() != '' & $('#pais').val() != '' & $('#about').val() != ''){
			mostrarSpinner();
		adapter.enviarDatosActualizados($('#email').val(),$('#pais').val(),$('#about').val(),foto).done(
		function(resul){
			ocultarSpinner();
		if (resul.value == true){
			alert('Tus datos se han actualizado','Aviso',function(){window.location.reload();});
			//window.location.reload();
			}
		else{
			if (resul.desh == true){
										alert('Este usuario ha sido deshabilitado por el administrador','Aviso',null);
			}
			else{
			alert('Intentalo más tarde','Error',null);
			}
			}
		}
		);
		}
		else{
			alert('Algun campo esta vacío o el correo no es válido','Error',null);
		}
		}
		);

		//$('#SacarFoto').on('click',this.cambiarFoto);
    };
    this.render = function() {
        this.el.html(Handlebars.templates.cambiardatos(datos));
        return this.el;
    };
    this.inicializar();
}