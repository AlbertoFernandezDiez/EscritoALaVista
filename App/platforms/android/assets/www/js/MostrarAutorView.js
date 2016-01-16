var MostrarAutorView = function(adapter, detalleautor) {
    this.inicializar = function() {
        this.el = $('<div/>');
    };
    this.render = function() {
        this.el.html(Handlebars.templates.detalleautor(detalleautor));
        return this.el;
    };
    this.inicializar();
}