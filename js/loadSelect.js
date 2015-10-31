
function loadChapterSel(id){
	var sel = $("#selCap");
	sel.val(0);
	var url = $("#url").val();
	//url = url + "LoadSelectChapters" ;
	url = "LoadSelectChapters" ;
	$("#titOb").val("");
	$("#resumen").val("");
	$("#selectCapitulo").html("<option name=default value=0>Nueva Obra</option>");
	$("#titCap").val("");
	$("#capitulo").val("");
	$("#comentarios").val("");	
if (id != 0)
	{
	$.ajax({op:1,url:url,type: 'POST',data:{op:1 ,obra: id},success:function(result){updateChapterSelect(result);},
		error:function(request,error){
			alert("Error: " + error);
		}
	});
	$('#eliminarObra').removeClass('disabled');
	$('#eliminarObra').val(id);
	}
else
	$('#eliminarObra').addClass('disabled');
}

function loadChapter(id){
	var sel = $("#selCap");
	sel.val();
	var url = $("#url").val();
	//url = url + "LoadSelectChapters" ;
	url = "LoadSelectChapters" ;

	if (id != 0)
	{
		$.ajax({op:1,url:url,type: 'POST',data:{op:2 ,capitulo: id},success:function(result){updateChapter(result);},
		error:function(request,error){
			alert("Error: " + error);
		}
	});
		if ($('#selectCapitulo option').size() > 2){
		$('#eliminarCapitulo').removeClass('disabled')
		$('#eliminarCapitulo').val(id);
		}
	}
	else
		{
		$('#eliminarCapitulo').addClass('disabled');
		$('#eliminarCapitulo').addClass('disabled');
		$("#titCap").val("");
		$("#capitulo").val("");
		$("#comentarios").val("");	
		}
}

function updateChapterSelect(result){
	console.log("Resultado recibido");
	json = result;	
	var $chapter = $("#selectCapitulo");
	console.log(result);



	$("#titOb").val(result.titulo);
	$("#resumen").val(result.resumen);
	$('#rutaP').val(result.imagen);
	
	
	$chapter.html("<option name=default value=0>Nuevo Capitulo</option>");

if (result.capitulos){
	$.each(result.capitulos, function (i, item) {
		$chapter.append($('<option>', { 
			value: item.idC,
			text : item.nombre 
		}));
	});
}

}


function updateChapter(result){
	json = result;
	$("#titCap").val("");
	$("#capitulo").val("");
	$("#comentarios").val("");	
	$("#titCap").val(result.titulo);
	
	/*$.each(result.capitulo, function(i,item){
		$("#capitulo").val($("#capitulo").val() + item);
	});*/
	var x = result.capitulo;
	for (var i = 0 ; i < x.length; i++){
		console.log(x[i]);
		$('#capitulo').val($('#capitulo').val() + '' + x[i])
		}
	
	$("#comentarios").val(result.comentarioA);
	$('#rutaC').val(result.imagen);

}

function borrarPortada()
{
	$("#rutaP").val("");
	}