var DetalleObraView = function(adapter, detalleobra) {

	this.seguirHistoria = function(){
		var obra = $('#idobra').val();
		var checked = $('#seguir').is(':checked');
		adapter.actualizarSeguimiento(loggedID, obra, checked).done(function(resul){
			if (resul.value == true){
				if (checked == true)
					alert('Ahora sigues esta obra', 'Aviso',null);
				else
					alert('Ya no sigues esta obra', 'Aviso',null);
			}
			else
			{
				if (resul.desh == true){
					alert('Este usuario ha sido deshabilitado por el administrador','Aviso',null);
					console.log(checked);
					if (checked == true){
					$('#seguir').prop('checked',false);
					}
					else
					{
					$('#seguir').prop('checked',true);
					}
				}
				else
			alert('Ha ocurrido un error','Error',null);
			}
		});
	};

   this.inicializar = function() {
        this.el = $('<div/>');
		this.el.on('change', '#seguir', this.seguirHistoria);

    };
    this.render = function() {
        this.el.html(Handlebars.templates.detalleobra(detalleobra));
        return this.el;
    };
    this.inicializar();
}