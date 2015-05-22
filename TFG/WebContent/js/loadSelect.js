/*function loadChapters(id,capitulo){
	var url = $("#url").val();
	url = url + "LoadSelectChapters" ;

	$.ajax({url:url,type: 'POST',data:{obra: id,capitulo: capitulo},success:function(result){updateChapterSelect(result);},
		error:function(request,error){
			alert("Error: " + error);
		}
	});
}*/

function loadChapterSel(id){
	var sel = $("#selCap");
	sel.val(0);
	var url = $("#url").val();
	url = url + "LoadSelectChapters" ;
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
	url = url + "LoadSelectChapters" ;
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
	
	$chapter.html("<option name=default value=0>Nueva Obra</option>");

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
	$("#capitulo").val(result.capitulo);
	$("#comentarios").val(result.comentarioA);	

}