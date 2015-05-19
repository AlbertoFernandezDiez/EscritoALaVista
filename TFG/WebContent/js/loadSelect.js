
function loadChapters(id){
	var url = $("#url").val();
	url = url + "LoadSelectChapters" ;
	
	$.ajax({url:url,type: 'POST',data:{obra: id },success:function(result){updateChapterSelect(result);},
         error:function(request,error){
             alert("Error: " + error);
         }
        });
}

function updateChapterSelect(result){
	console.log("Resultado recibido");
	console.log(result);
}