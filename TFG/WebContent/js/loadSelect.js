var json;
function loadChapters(id,capitulo){
	var url = $("#url").val();
	url = url + "LoadSelectChapters" ;
	
	$.ajax({url:url,type: 'POST',data:{obra: id,capitulo: capitulo},success:function(result){updateChapterSelect(result);},
         error:function(request,error){
             alert("Error: " + error);
         }
        });
}

function updateChapterSelect(result){
	console.log("Resultado recibido");
	
var $chapter = $("#selectCapitulo");

/*$.each(items, function (i, item) {
    $('#mySelect').append($('<option>', { 
        value: item.value,
        text : item.text 
    }));
});*/
$.each(result.capitulos, function (i, item) {
    $chapter.append($('<option>', { 
        value: item.idC,
        text : item.nombre 
    }));
});
/*$.each(result.capitulos, function(x){
	console.log(result.capitulos[x]);
	});*/
}