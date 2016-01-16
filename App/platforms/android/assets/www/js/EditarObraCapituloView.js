var EditarObraCapituloView  = function (adapter, datos) {
	
	var fotoObra = '';
	var fotoCapitulo = '';

	var enviable = true;
	
	this.cambiarFotoObra = function(event) {
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
			fotoObra = imageData;
		$('#portadaObra').removeAttr('style');
		$('#portadaObra').attr('src',"data:image/jpeg;base64," +fotoObra);
       }, function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
};

	this.cambiarFotoObraAlbum = function(event) {
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
                        encodingType: 0,     // 0=JPG 1=PNG
						mediaType: 0	//Imagenes(	
                    };
 
    navigator.camera.getPicture(
        function(imageData) {
			fotoObra = imageData;
		$('#portadaObra').removeAttr('style');
		$('#portadaObra').attr('src',"data:image/jpeg;base64," +fotoObra);
       }, function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
};

this.cambiarFotoCapitulo = function(event) {
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
			fotoCapitulo =imageData;

					$('#portadaCapi').removeAttr('style');
		$('#portadaCapi').attr('src',"data:image/jpeg;base64," + fotoCapitulo);
       }, function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
};

this.cambiarFotoCapituloAlbum = function(event) {
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
                        encodingType: 0,     // 0=JPG 1=PNG
						mediaType: 0	//Imagenes(	
                    };
 
    navigator.camera.getPicture(
        function(imageData) {
			fotoCapitulo =imageData;

					$('#portadaCapi').removeAttr('style');
		$('#portadaCapi').attr('src',"data:image/jpeg;base64," + fotoCapitulo);
       }, function() {
            alert('Error al obtener la foto', 'Error',null);
        },
        options);
 
    return false;
};

	this.loadCapitulos = function(){
		var id = $('#selectorObra').val();
		$('#tituloObra').val('');
		$('#resumenObra').val('');
		$('#tituloCapitulo').val('');
		$('#textoCapitulo').val('');
		if (id == 0){
		$('#borrarObra').attr('disabled','')
		$('#borrarCapitulo').attr('disabled','')
		}
		else{
		$('#borrarObra').removeAttr('disabled')	
		}
		
		var selector = $('#selectorCapitulo');
		selector.html("<option value = '0'>Nueva Obra</option>");
		adapter.listarCapitulos(id).done(function(json){
		$('#tituloObra').val(json.titulo);
		$('#resumenObra').val(json.resumen);
		if (json.imagen)
		{ 
		$('#portadaObra').removeAttr('style');
		$('#portadaObra').attr('src',json.imagen);
		}
		else{
		$('#portadaObra').attr('style','display:none');
		}
		
		if (json.lista){
	$.each(json.lista, function (i, item) {
		selector.append($('<option>', { 
			value: item.id,
			text : item.nombre 
		}));
		});
	}});
	}
	
	this.loadCapitulo = function(){
		var id = $('#selectorCapitulo').val();
			$('#tituloCapitulo').val('');
			$('#textoCapitulo').val('');
			$('#comentarioAutor').val('');

		if (id == 0 || $('#selectorCapitulo option').size() <= 2){
		$('#borrarCapitulo').attr('disabled','')
		}
		else{
		$('#borrarCapitulo').removeAttr('disabled')	
		}
		
		adapter.cargarCapitulo(id).done(function(json){
		$('#tituloCapitulo').val(json.titulo);
		$('#comentarioAutor').val(json.comentario);
		if (json.imagen)
		{ 
		$('#portadaCapi').removeAttr('style');
		$('#portadaCapi').attr('src',json.imagen);
		}
		else{
		$('#portadaCapi').attr('style','display:none');
		}
		var selector = $('#textoCapitulo');
		console.log(json.texto);
	$.each(json.texto, function (i, item) {
		selector.append(item.par);
		});
		selector.val(selector.text());
	});
	}
	
	this.sendData = function(){
	console.log('pulsado');
				mostrarSpinner();

	var idO,idC,titulo,resumen,tituCap,texto,comentario;

	idO = $('#selectorObra').val();
	idC = $('#selectorCapitulo').val();
	titulo = $('#tituloObra').val();
	resumen = $('#resumenObra').val();
	tituCap = $('#tituloCapitulo').val();
	texto = $('#textoCapitulo').val();
	comentario = $('#comentarioAutor').val();
	if (titulo == ''  || resumen == ''  || tituCap == ''  || texto  == ''  || enviable == false){
		alert('Hay algun campo obligatorio sin llenar o el titulo de la obra no es válido','Aviso',null);
		ocultarSpinner();
	}
	else
		adapter.sendData(idO,idC,titulo,resumen,tituCap,texto,comentario,fotoObra,fotoCapitulo).done(function(resultado){
			ocultarSpinner();
		if (resultado.value == true){
				alert('Has modificado la obra','Aviso',function(){window.location.reload();});
				//window.location.reload();
				}
				else{
					if(resultado.desh == true)
					{
						alert('Este usuario ha sido deshabilitado por el administrador','Aviso',null);
					}
					else{
				alert('Ha habido un error','Error');
					}
				}
		}
		);
	}

	function mostrarAviso(resultado){
			console.log(resultado);
			var select = $.trim($('#selectorObra option:selected').text());
			var titulo =  $.trim($('#tituloObra').val())
			if (resultado.value == false && select != titulo)
			{
				enviable = false;
				alert('El titulo ya esta siendo utilizado','Aviso',null);
			}
				else
			{
				enviable = true;
			}			
	}
	
	this.eliminarCapitulo = function(){
		var capitulo = $('#selectorCapitulo').val()
		
		adapter.eliminarCapitulo(loggedID,capitulo).done(function(result){
		if (result.value == true){
			alert('Has eliminado el capitulo','Aviso',function(){window.location.href = window.location.href + '?'});
			}
			else{
				if(result.desh == true)
					{
						alert('Este usuario ha sido deshabilitado por el administrador','Aviso',null);
					}
					else{
						alert('Ha habido un error, intentalo más tarde','Error',null);
			}
			
		}});
	};
	
	this.eliminarObra = function(){
		var obra = $('#selectorObra').val();
		alert('entra','entra',null);
		adapter.eliminarObra(loggedID,obra).done(function(result){
		if (result.value == true){
			alert('Has eliminado la Obra','Aviso',function(){window.location.href = window.location.href + '?'});
			//window.location.href = window.location.href + '?'
			}else{
				if(result.desh == true)
					{
						alert('Este usuario ha sido deshabilitado por el administrador','Aviso',null);
					}
					else{
						alert('Ha habido un error, intentalo más tarde','Error',null);
					}
			
		}});
	};
	
    this.inicializar = function () {
        this.el = $('<div/>');
		this.el.on('change', '#selectorObra', this.loadCapitulos);
		this.el.on('change', '#selectorCapitulo', this.loadCapitulo);
		this.el.on('click','#enviarCambios',this.sendData);
		this.el.on('click','#fotoObra',this.cambiarFotoObra);
		this.el.on('click','#fotoCapitulo',this.cambiarFotoCapitulo);
		this.el.on('click','#fotoObraAlbum',this.cambiarFotoObraAlbum);
		this.el.on('click','#fotocapituloAlbum',this.cambiarFotoCapituloAlbum);
		this.el.on('blur', '#tituloObra', function(){
			adapter.checkTitulo($('#tituloObra').val()).done(mostrarAviso);	
			});

	
			this.el.on('click', '#borrarObra',function(){confirm('¿De verdad?','¿Deseas eliminar la obra?',function(index){
			if(index == 1){
			var obra = $('#selectorObra').val();
		adapter.eliminarObra(loggedID,obra).done(function(result){
		if (result.value == true){
			alert('Has eliminado la Obra','Aviso',function(){window.location.href = window.location.href + '?'});
			}
			});
			}});
		});
			
			
			this.el.on('click', '#borrarCapitulo', function(){confirm('¿De verdad?','¿Deseas eliminar la obra?',
			function(index){
			if(index == 1){
			var capitulo = $('#selectorCapitulo').val()
		
		adapter.eliminarCapitulo(loggedID,capitulo).done(function(result){
		if (result.value == true){
			alert('Has eliminado el capitulo','Aviso',function(){window.location.href = window.location.href + '?'});
			/*$('#selectorCapitulo').val(0);
			$('#portadaCapi').attr('style','display:none');
			$('#tituloCapitulo').val('');
			$('#textoCapitulo').val('');
			$('#comentarioAutor').val('');*/
			//window.location.href = window.location.href + '?'
			}
			
		});
			}}
			
			);});
    };

    this.render = function() {
		console.log('render')
        this.el.html(Handlebars.templates.editarobracapitulo(datos));
        return this.el;
     
    };

	
	
	this.inicializar();
}