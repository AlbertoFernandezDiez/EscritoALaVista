var DetalleCapituloView = function(adapter, detallecapitulo) {
    this.inicializar = function() {
        this.el = $('<div/>');
    };
    this.render = function() {
        this.el.html(Handlebars.templates.detallescapitulo(detallecapitulo));
        return this.el;
    };
    this.inicializar();
}