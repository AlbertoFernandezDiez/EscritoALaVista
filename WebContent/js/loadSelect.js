
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
	}
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
	}
	else
		{
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
	
	$.each(result.capitulo, function(i,item){
		$("#capitulo").append(item);
	});
	
	$("#capitulo").val(result.capitulo);
	$("#comentarios").val(result.comentarioA);
	$('#rutaC').val(result.imagen);

}

function borrarPortada()
{
	$("#rutaP").val("");
	}