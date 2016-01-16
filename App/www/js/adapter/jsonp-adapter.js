var JSONPAdapter = function() {

    this.inicializar = function(data) {
        url = "http://192.168.173.1:8080/TFG/api/";/*"http://192.168.1.67:8080/TFG/api/";*/
        var deferred = $.Deferred();
        deferred.resolve();
        return deferred.promise();
    }

  	this.cargarObras = function(){
		var dir = url + "Mostrarlistado";
		console.log(dir);
		return $.ajax({
			url : dir,
			type : 'POST',
			dataType: "json",
			data:{loggedID : loggedID},
			crossDomain: true
		});
	}
	
	this.detallesObra = function(id,previousURL){
			var dir = url + "MostrarObra?id="+id;
			console.log(dir);
			return $.ajax({
			url : dir,
			type : 'POST',
			dataType: "json",
			data: {id: id, loggedID : loggedID},
			crossDomain: true
		});
	}
	
	this.verComentarios = function(idO, idC,previousURL){
			var dir = url + "MostrarComentarios?idO=" + idO +"&idC=" + idC;
			console.log(dir);
			return $.ajax({
			url : dir,
			type : 'POST',
			dataType: "json",
			data: {idO : idO, idC : idC, loggedID : loggedID},
			crossDomain: true
		});
	}
	
	this.detallesCapitulo = function(id,previousURL){
		console.log('entrado');
			var dir = url + "MostrarCapitulo?id="+id;
			console.log(dir);
			return $.ajax({
			url : dir,
			type : 'POST',
			data: {id: id, loggedID : loggedID},
			dataType: "json",
			crossDomain: true
		});
	}
	
	this.verAutor = function(id,previousURL){
		console.log('entrado');
			var dir = url + "MostrarAutor?id="+id;
			console.log(dir);
			return $.ajax({
			url : dir,
			type : 'POST',
			dataType: "json",
			data: {id: id, loggedID : loggedID},
			crossDomain: true
		});
	}
	
	this.loginUser= function(usuario, contrasena){
		var shaContra = toSHA512(contrasena);
			
		var dir = url + "LogInApi";
	
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {usuario: usuario, contrasena : shaContra},
		crossDomain: true,	
	});
	}
	
	function toSHA512(password) {
	var jsSha = new jsSHA(password);
	var hash = jsSha.getHash("SHA-512", "HEX");
		
	return hash;
}
	
	this.logoutUser = function(){
		
		loggedID = '';
	}
	
	this.sendComentarios = function(idO,idC,comentario){
			var dir = url + "SendComentarioApi";
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid: loggedID,idO : idO, idC : idC,comentario : comentario},
		crossDomain: true	
	});
	}
	
	this.cambiarDatos = function(){
			var dir = url + "CambiarDatosApi";
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid: loggedID},
		crossDomain: true	
	});
	
	}
	
	this.enviarDatosActualizados = function(email, pais, about, foto){
	var dir = url + "CambiarDatosBDApi";
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid: loggedID, email : email, pais : pais, about : about, foto : foto},
		crossDomain: true	
	});
	}
	
	this.editarobra = function(){
	var dir = url + "CambiarObraApi";
		//console.log(previousURL);
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid: loggedID},
		crossDomain: true	
	});
	}
	
	this.listarCapitulos = function (id){
	var dir = url + "ListarCapitulosAPI";
		//console.log(previousURL);
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid : loggedID, id : id},
		crossDomain: true	
	});
	}
	
	this.cargarCapitulo = function (id){
	var dir = url + "CargarCapituloAPI";
		//console.log(previousURL);
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid : loggedID, id : id},
		crossDomain: true	
	});
	}
	
	this.sendData = function (idO,idC,titulo,resumen,tituCap,texto,comentario, fotoObra, fotoCapitulo){
	var dir = url + "ActualizarObraCapituloAPI";
		//console.log(previousURL);
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid : loggedID, idO : idO, idC : idC,
		titulo : titulo, resumen : resumen, titulocap : tituCap,
		texto : texto, comentario : comentario, imagenObra : fotoObra, imagenCapi : fotoCapitulo},
		crossDomain: true	
	});
	}
	
	this.registrarse = function(nombre,contrasena,email, pais, about, nacimiento ,foto){
	var dir = url + "RegistrarseApi";
	var password = toSHA512(contrasena);
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {nombre: nombre, nacimiento : nacimiento, contrasena : password, email : email, pais : pais, about : about, foto : foto},
		crossDomain: true	
	});
	}
	
	this.checkNombre = function(nombre){
		var dir = url + "ComprobarUsuarioAPI";
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {nombre: nombre},
		crossDomain: true	
	});
	}

this.checkTitulo = function(nombre){
		var dir = url + "ComprobarTituloAPI";
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {nombre: nombre},
		crossDomain: true	
	});
	}	
	
	this.actualizarSeguimiento = function(idU,idA,valor){
	var dir = url + "SeguimientoAPI";
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {idU : idU, idO : idA, valor : valor},
		crossDomain: true	
	});
	};
	
	this.eliminarCapitulo = function(idU,idC){
	var dir = url + "EliminarCapituloAPI";
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid : idU, capitulo : idC},
		crossDomain: true	
	});
	};
	
	this.eliminarObra = function(idU,idO){
	var dir = url + "EliminarObraAPI";
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedid : idU, obra : idO},
		crossDomain: true	
	});
	};
	
	this.contrasenaOlvidada = function(correo){
		var dir = url + "ContrasenaOlvidadaAPI";
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {email : correo},
		crossDomain: true	
	});
	};
	
	this.cambiarContrasena = function(conV,conN){
	var dir = url + "CambioContraAPI";
		var contraV = toSHA512(conV);
		var contraN = toSHA512(conN);
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {loggedID : loggedID, conV : contraV, conN : contraN},
		crossDomain: true	
	});
	};
	
	this.comprobarEmail = function(email){
	var dir = url + "ComprobarEmailAPI";
		
		
		return $.ajax({
		url : dir,
		type : 'POST',
		dataType: "json",
		data: {email : email},
		crossDomain: true	
	});
	};
    var url;

}
