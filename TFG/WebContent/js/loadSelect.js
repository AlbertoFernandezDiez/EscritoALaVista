function loadChapters(id,capitulo){
	var url = $("#url").val();
	url = url + "LoadSelectChapters" ;

	$.ajax({url:url,type: 'POST',data:{obra: id,capitulo: capitulo},success:function(result){updateChapterSelect(result);},
		error:function(request,error){
			alert("Error: " + error);
		}
	});
}
var json;
function updateChapterSelect(result){
	console.log("Resultado recibido");
	json = result;	
	var $chapter = $("#selectCapitulo");

	
	if (result.campos){
		$("#titOb").val(result.campos.titulo);
		$("#resumen").val(result.campos.resumen);}

	if (result.capitulos){
		$.each(result.capitulos, function (i, item) {
			$chapter.append($('<option>', { 
				value: item.idC,
				text : item.nombre 
			}));
		});
	}

	console.log(result.camosC);
	if (result.camposC){
		$("#titCap").val(result.camposC.titulo);
		$("#capitulo").val(result.camposC.capitulo);
		$("#comentarios").val(result.camposC.comentarioA);	
	}


}