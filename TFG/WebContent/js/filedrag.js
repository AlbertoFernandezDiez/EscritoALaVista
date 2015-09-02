function gestorArchivos(nombre){
	$('#'+nombre)
	.change(
			function(e) {
				//Si el archivo es una imagen se muestra su nombre en el Spam
				if (e.target.files[0].type
						.match('image/jpeg') || e.target.files[0].type
						.match('image/png'))
					$('#'+nombre+'info').text(
							e.target.files[0].name);
				else {
					//Si no se borra del input y se borra el nombre
					resetFormElement($('#'+nombre));
					$('#'+nombre+'info').text('');
					$('#myModal').modal('show');
				}
			});
}

function resetFormElement(e) {
	e.wrap('<form>').closest('form').get(0).reset();
	e.unwrap();
}
