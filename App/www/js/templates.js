(function() {
  var template = Handlebars.template, templates = Handlebars.templates = Handlebars.templates || {};
templates['cambiarContrasena'] = template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n<a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n<span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n</a>\r\n</div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Cambiar contraseña</h1>\r\n        </div>\r\n</div>\r\n<div id='loginForm'>\r\n<input type=\"password\" id='contrasenaVieja' class=\"topcoat-text-input full\" placeholder=\"Contraseña Vieja\" value=\"\"><br><br>\r\n<input type=\"password\" id='contrasenaNueva' class=\"topcoat-text-input full\" pattern=\"[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}\" placeholder=Contraseña Nueva\" value=\"\"><br><br>\r\n<button id='changePassword' class=\"topcoat-button--cta full\">Cambiar Contraseña</button><br><br>\r\n</div>";
},"useData":true});
templates['cambiardatos'] = template({"1":function(depth0,helpers,partials,data) {
    var helper;

  return "	<center><img id='imagen' src=\""
    + this.escapeExpression(((helper = (helper = helpers.imagen || (depth0 != null ? depth0.imagen : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0,{"name":"imagen","hash":{},"data":data}) : helper)))
    + "\" class=\"imagen-portada\"></center>\r\n";
},"3":function(depth0,helpers,partials,data) {
    return "	<center><img id='imagen' src=\"\" class=\"imagen-portada\" style='display:none;'></center>\r\n";
},"5":function(depth0,helpers,partials,data) {
    var helper;

  return this.escapeExpression(((helper = (helper = helpers.texto || (depth0 != null ? depth0.texto : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0,{"name":"texto","hash":{},"data":data}) : helper)));
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1, helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n            <a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n                <span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n            </a>\r\n    </div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Modificar Datos Autor</h1>\r\n        </div>\r\n   \r\n    <div class='detalles scroller'>\r\n"
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.imagen : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.program(3, data, 0),"data":data})) != null ? stack1 : "")
    + "		<p><strong>Email:</strong></p><input id='email' maxlength='45' type=\"text\" class=\"topcoat-text-input full\" placeholder=\"Email\" value=\""
    + alias3(((helper = (helper = helpers.email || (depth0 != null ? depth0.email : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"email","hash":{},"data":data}) : helper)))
    + "\">	\r\n        <p><strong>Pais:</strong></p><input id='pais' maxlength='20'  type=\"text\" class=\"topcoat-text-input full\" placeholder=\"Pais\" value=\""
    + alias3(((helper = (helper = helpers.pais || (depth0 != null ? depth0.pais : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"pais","hash":{},"data":data}) : helper)))
    + "\">	\r\n		<p><strong>Sobre el autor:</strong></p><textarea id='about' class=\"topcoat-textarea full\" placeholder=\"Sobre mi\" cols=\"5\" rows=\"6\">"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.about : depth0),{"name":"each","hash":{},"fn":this.program(5, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "</textarea>\r\n	<br><br>\r\n	<button id='SacarFotoCamara' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--camara\"></span> Sacar Foto</button><br><br>\r\n	<button id='SacarFotoAlbum' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--image\"></span> Elegir Foto</button><br><br>\r\n	<a href='#cambiarContrasena'><button class=\"topcoat-button--cta full\">Cambiar Contraseña</button></a><br><br>\r\n	<button id='EnviarCambioDatos' class=\"topcoat-button--cta full\">Enviar</button>\r\n    </div>";
},"useData":true});
templates['comentarios'] = template({"1":function(depth0,helpers,partials,data) {
    return "<input type='text' class=\"topcoat-text-input full\" id='comentario' value=''/><br><br>\r\n<button id='enviarComentario' class='topcoat-button--cta full'>Enviar</button>\r\n<br>\r\n<br>\r\n";
},"3":function(depth0,helpers,partials,data) {
    var helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "    <li class=\"topcoat-list__item\">\r\n	<a href='#autor/"
    + alias3(((helper = (helper = helpers.idAutor || (depth0 != null ? depth0.idAutor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"idAutor","hash":{},"data":data}) : helper)))
    + "'>\r\n		   <table class='tituloautor'>\r\n			<tr>\r\n				<td>"
    + alias3(((helper = (helper = helpers.autor || (depth0 != null ? depth0.autor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"autor","hash":{},"data":data}) : helper)))
    + "</td>\r\n				<td>"
    + alias3(((helper = (helper = helpers.fecha || (depth0 != null ? depth0.fecha : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"fecha","hash":{},"data":data}) : helper)))
    + "</td>\r\n			</tr>\r\n		   </table>        \r\n            <p>"
    + alias3(((helper = (helper = helpers.texto || (depth0 != null ? depth0.texto : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"texto","hash":{},"data":data}) : helper)))
    + "</p>\r\n			</a>\r\n      </li>\r\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1, helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "\r\n<div class=\"topcoat-navigation-bar__item left quarter\">\r\n<a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n<span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n</a>\r\n</div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Comentarios</h1>\r\n        </div>\r\n\r\n<input type='hidden' value='"
    + alias3(((helper = (helper = helpers.idC || (depth0 != null ? depth0.idC : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"idC","hash":{},"data":data}) : helper)))
    + "' id='idC'/>\r\n<input type='hidden' value='"
    + alias3(((helper = (helper = helpers.idO || (depth0 != null ? depth0.idO : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"idO","hash":{},"data":data}) : helper)))
    + "' id='idO'/>\r\n"
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.loggedID : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "<div class=\"topcoat-list\">\r\n    <ul class=\"topcoat-list__container list\" id=\"lstComent\">\r\n <h3 class=\"topcoat-list__header\">Comentarios</h3>\r\n"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.comentarios : depth0),{"name":"each","hash":{},"fn":this.program(3, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "  </ul>\r\n</div>";
},"useData":true});
templates['contrasenaOlvidada'] = template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n<a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n<span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n</a>\r\n</div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Contraseña Olvidada</h1>\r\n        </div>\r\n</div>\r\n<div id='dataEnter'>\r\n<input type=\"email\" id='email' maxlength='45' class=\"topcoat-text-input full\" placeholder=\"Email\" value=\"\"><br><br>\r\n<button id='enviarEmail' class=\"topcoat-button--cta full\">Enviar</button>\r\n</div>\r\n<!--\r\n<div id='success' style = 'display:none'>\r\n<h1>Enhorabuena</h1>\r\n<p>Recibiras una nueva contraseña en tu correo en breves momentos, recuerda cambiarla desde la web.</p>\r\n</div>\r\n<div id='error' style = 'display:none'>\r\n<h1>Error</h1>\r\n<p>El correo que has introducido no existe en nuestra Base de Datos.</p>\r\n</div>-->";
},"useData":true});
templates['detalleautor'] = template({"1":function(depth0,helpers,partials,data) {
    var helper;

  return "		<center><img src=\""
    + this.escapeExpression(((helper = (helper = helpers.imagen || (depth0 != null ? depth0.imagen : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0,{"name":"imagen","hash":{},"data":data}) : helper)))
    + "\" class=\"imagen-portada\"></center>\r\n";
},"3":function(depth0,helpers,partials,data) {
    var helper;

  return "				            <p>"
    + this.escapeExpression(((helper = (helper = helpers.text || (depth0 != null ? depth0.text : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0,{"name":"text","hash":{},"data":data}) : helper)))
    + "</p>\r\n";
},"5":function(depth0,helpers,partials,data) {
    var helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "    <li class=\"topcoat-list__item\">\r\n      <a href=\"#obra/"
    + alias3(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"id","hash":{},"data":data}) : helper)))
    + "\">\r\n           <!-- <img src=\"assets/img/"
    + alias3(((helper = (helper = helpers.imagen || (depth0 != null ? depth0.imagen : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"imagen","hash":{},"data":data}) : helper)))
    + "\">-->\r\n		   <table class='tituloautor'>\r\n			<tr>\r\n				<td>"
    + alias3(((helper = (helper = helpers.titulo || (depth0 != null ? depth0.titulo : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"titulo","hash":{},"data":data}) : helper)))
    + "</td>\r\n				<!--<td>"
    + alias3(((helper = (helper = helpers.fechain || (depth0 != null ? depth0.fechain : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"fechain","hash":{},"data":data}) : helper)))
    + "</td>-->\r\n				<!--<td>Modificado: "
    + alias3(((helper = (helper = helpers.fechamod || (depth0 != null ? depth0.fechamod : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"fechamod","hash":{},"data":data}) : helper)))
    + "</td>-->\r\n				<td>"
    + alias3(((helper = (helper = helpers.autor || (depth0 != null ? depth0.autor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"autor","hash":{},"data":data}) : helper)))
    + "</td>\r\n			</tr>\r\n		   </table>\r\n        <!--    <p>"
    + alias3(((helper = (helper = helpers.titulo || (depth0 != null ? depth0.titulo : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"titulo","hash":{},"data":data}) : helper)))
    + " "
    + alias3(((helper = (helper = helpers.autor || (depth0 != null ? depth0.autor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"autor","hash":{},"data":data}) : helper)))
    + "</p>-->\r\n            <p>"
    + alias3(((helper = (helper = helpers.resumen || (depth0 != null ? depth0.resumen : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"resumen","hash":{},"data":data}) : helper)))
    + "</p>\r\n          <!--  <span class=\"chevron\"></span>-->\r\n        </a>\r\n      </li>\r\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1, helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n            <a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n                <span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n            </a>\r\n    </div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Autor</h1>\r\n        </div>\r\n   \r\n    <div class='detalles scroller'>\r\n"
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.imagen : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "        <p><strong>Nombre:</strong> "
    + alias3(((helper = (helper = helpers.nombre || (depth0 != null ? depth0.nombre : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"nombre","hash":{},"data":data}) : helper)))
    + " </p>\r\n		<p><strong>Naciminento:</strong> "
    + alias3(((helper = (helper = helpers.nacimiento || (depth0 != null ? depth0.nacimiento : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"nacimiento","hash":{},"data":data}) : helper)))
    + "</p>\r\n        <p><strong>Pais:</strong> "
    + alias3(((helper = (helper = helpers.pais || (depth0 != null ? depth0.pais : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"pais","hash":{},"data":data}) : helper)))
    + "</p>\r\n		<p><strong>Sobre el autor:</strong></p>\r\n"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.about : depth0),{"name":"each","hash":{},"fn":this.program(3, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "				<div class=\"topcoat-list clearfix\">\r\n <h3 class=\"topcoat-list__header\">Obras</h3>\r\n        <ul class=\"topcoat-list__container \">\r\n"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.obras : depth0),{"name":"each","hash":{},"fn":this.program(5, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "            </ul>\r\n        </div>\r\n    </div>";
},"useData":true});
templates['detalleobra'] = template({"1":function(depth0,helpers,partials,data) {
    var helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "        <center><img src=\""
    + alias3(((helper = (helper = helpers.imagen || (depth0 != null ? depth0.imagen : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"imagen","hash":{},"data":data}) : helper)))
    + "\" alt='"
    + alias3(((helper = (helper = helpers.imagen || (depth0 != null ? depth0.imagen : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"imagen","hash":{},"data":data}) : helper)))
    + "' class=\"imagen-portada\"></center>\r\n";
},"3":function(depth0,helpers,partials,data) {
    return "disabled";
},"5":function(depth0,helpers,partials,data) {
    return "checked";
},"7":function(depth0,helpers,partials,data) {
    var helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "				                <li class=\"topcoat-list__item\"><a href=\"#capitulo/"
    + alias3(((helper = (helper = helpers.idCap || (depth0 != null ? depth0.idCap : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"idCap","hash":{},"data":data}) : helper)))
    + "\" class=\"change-pic-btn\"><p>"
    + alias3(((helper = (helper = helpers.nombre || (depth0 != null ? depth0.nombre : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"nombre","hash":{},"data":data}) : helper)))
    + "</p></a></li>\r\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1, helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n            <a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n                <span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n            </a>\r\n    </div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Detalle Obra</h1>\r\n        </div>\r\n   \r\n   <div class='detalles scroller'>\r\n"
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.imagen : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "		<input id='idobra' type='hidden' value='"
    + alias3(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"id","hash":{},"data":data}) : helper)))
    + "'/>\r\n		<label class=\"topcoat-checkbox\">\r\n		<input id='seguir' type=\"checkbox\" "
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.notlogged : depth0),{"name":"if","hash":{},"fn":this.program(3, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.seguidor : depth0),{"name":"if","hash":{},"fn":this.program(5, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + ">\r\n		<div class=\"topcoat-checkbox__checkmark\"></div>\r\n		Seguir\r\n		</label>\r\n        <h1>"
    + alias3(((helper = (helper = helpers.nombre || (depth0 != null ? depth0.nombre : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"nombre","hash":{},"data":data}) : helper)))
    + " "
    + alias3(((helper = (helper = helpers.apellido || (depth0 != null ? depth0.apellido : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"apellido","hash":{},"data":data}) : helper)))
    + "</h1>\r\n        <p><strong>Titulo:</strong> "
    + alias3(((helper = (helper = helpers.titulo || (depth0 != null ? depth0.titulo : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"titulo","hash":{},"data":data}) : helper)))
    + " </p>\r\n        <p><strong>Autor: </strong><a href='#autor/"
    + alias3(((helper = (helper = helpers.autorId || (depth0 != null ? depth0.autorId : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"autorId","hash":{},"data":data}) : helper)))
    + "'><button class='topcoat-button--cta'>"
    + alias3(((helper = (helper = helpers.autor || (depth0 != null ? depth0.autor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"autor","hash":{},"data":data}) : helper)))
    + "</button></a></p>\r\n		<p><strong>Fecha Inicio:</strong> "
    + alias3(((helper = (helper = helpers.fechamod || (depth0 != null ? depth0.fechamod : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"fechamod","hash":{},"data":data}) : helper)))
    + "</p>\r\n        <p><strong>Modificaci&oacute;n:</strong> "
    + alias3(((helper = (helper = helpers.fechamod || (depth0 != null ? depth0.fechamod : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"fechamod","hash":{},"data":data}) : helper)))
    + "</p>\r\n        <p><em>"
    + alias3(((helper = (helper = helpers.desc || (depth0 != null ? depth0.desc : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"desc","hash":{},"data":data}) : helper)))
    + "</em></p>\r\n     \r\n					<div class=\"topcoat-list clearfix\">\r\n <h3 class=\"topcoat-list__header\">Cap&iacute;tulos</h3>\r\n        <ul class=\"topcoat-list__container list actions\">\r\n"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.capitulos : depth0),{"name":"each","hash":{},"fn":this.program(7, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "            </ul>\r\n        </div>\r\n    </div>\r\n";
},"useData":true});
templates['detallescapitulo'] = template({"1":function(depth0,helpers,partials,data) {
    var helper;

  return "        <center><img src=\""
    + this.escapeExpression(((helper = (helper = helpers.imagen || (depth0 != null ? depth0.imagen : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0,{"name":"imagen","hash":{},"data":data}) : helper)))
    + "\" class=\"imagen-portada\"></center>\r\n";
},"3":function(depth0,helpers,partials,data) {
    var helper;

  return "	 <p style='text-align:justify;'>"
    + this.escapeExpression(((helper = (helper = helpers.par || (depth0 != null ? depth0.par : depth0)) != null ? helper : helpers.helperMissing),(typeof helper === "function" ? helper.call(depth0,{"name":"par","hash":{},"data":data}) : helper)))
    + "</p>\r\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1, helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n<a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n<span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n</a>\r\n</div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">"
    + alias3(((helper = (helper = helpers.titulo || (depth0 != null ? depth0.titulo : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"titulo","hash":{},"data":data}) : helper)))
    + "</h1>\r\n        </div>\r\n <div class='detalles scroller'>\r\n"
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.imagen : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.texto : depth0),{"name":"each","hash":{},"fn":this.program(3, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "	      <div class=\"topcoat-list__container clearfix\">\r\n			<h1 class=\"topcoat-navigation-bar__title\">Comentario del autor</h1>\r\n		</div>\r\n		<p>"
    + alias3(((helper = (helper = helpers.comentarioAutor || (depth0 != null ? depth0.comentarioAutor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"comentarioAutor","hash":{},"data":data}) : helper)))
    + "</p>\r\n		<br>\r\n		<br>\r\n	   <div class=\"topcoat-list__container\">\r\n			<a href=\"#comentarios/"
    + alias3(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"id","hash":{},"data":data}) : helper)))
    + "/"
    + alias3(((helper = (helper = helpers.obra || (depth0 != null ? depth0.obra : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"obra","hash":{},"data":data}) : helper)))
    + "\" class=\"change-pic-btn\"><button class=\"topcoat-button--cta full\">Ver Comentarios</button></a>\r\n		</div>\r\n		<br>\r\n		<br>\r\n</div>";
},"useData":true});
templates['editarobracapitulo'] = template({"1":function(depth0,helpers,partials,data) {
    var helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "			<option value = '"
    + alias3(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"id","hash":{},"data":data}) : helper)))
    + "'>"
    + alias3(((helper = (helper = helpers.nombre || (depth0 != null ? depth0.nombre : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"nombre","hash":{},"data":data}) : helper)))
    + "</option>\r\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1;

  return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n            <a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n                <span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n            </a>\r\n    </div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Crear Obra/Capítulo</h1>\r\n        </div>\r\n   \r\n    <div class='detalles scroller'>\r\n	<p><strong>Obra:</strong></p>\r\n       <select id = 'selectorObra' size = '1' class='full'>\r\n		<option value = '0'>Nueva Obra</option>\r\n"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.lista : depth0),{"name":"each","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "		</select>\r\n		<br><br>\r\n		<input type=\"button\" id ='borrarObra' value=\"Eliminar Obra\" class=\"topcoat-button--cta full\" disabled/>\r\n\r\n		<p><strong>Capitulo:</strong></p>\r\n		<select id = 'selectorCapitulo' size = '1' class='full'>\r\n		<option value = '0'>Nuevo Capitulo</option>\r\n		</select>\r\n		<br><br>\r\n	<input type=\"button\" id ='borrarCapitulo'  value=\"Eliminar Capítulo\" class=\"topcoat-button--cta full\" disabled/>\r\n\r\n		<h1>Obra</h1>\r\n		<center><img src=\"\" id = 'portadaObra' class=\"imagen-portada\" style='display:none'></center>\r\n			<p><strong><Titulo Obra:</strong></p>\r\n<input type=\"text\" id = 'tituloObra' maxlength='50' class=\"topcoat-text-input full\" placeholder=\"Titulo Obra\" value=\"\">\r\n<p><strong>Resumen:</strong></p>\r\n<textarea id='resumenObra' maxlength='512' class=\"topcoat-textarea full\" placeholder=\"Resumen Obra\" cols=\"6\" rows=\"16\"></textarea>\r\n<h1>Capitulo</h1>\r\n <center><img src=\"\" id = 'portadaCapi' class=\"imagen-portada\" style='display:none'></center>\r\n			<p><strong>Titulo Capitulo:</strong></p>\r\n<input type=\"text\" id = 'tituloCapitulo' maxlength='50' class=\"topcoat-text-input full\" placeholder=\"Titulo Capitulo\" value=\"\">\r\n			<p><strong>Contenido Capitulo:</strong></p>\r\n<textarea id='textoCapitulo' class=\"topcoat-textarea full\" placeholder=\"Texto del capítulo\" cols=\"6\" rows=\"16\"></textarea>\r\n	<p><strong>Comentarios Autor:</strong></p>\r\n<textarea id='comentarioAutor' class=\"topcoat-textarea full\" placeholder=\"Comentario del autor\" cols=\"6\" rows=\"6\"></textarea>\r\n<br><br>\r\n<button id ='fotoObra' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--camara\"></span> Foto Obra</button><br><br>\r\n<button id='fotoObraAlbum' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--image\"></span> Album Obra</button><br><br>\r\n\r\n<button id ='fotoCapitulo' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--camara\"></span> Foto Capítulo</button><br><br>\r\n<button id='fotocapituloAlbum' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--image\"></span> Album Capitulo</button><br><br>\r\n\r\n<input type=\"button\" id ='enviarCambios' value=\"Enviar Cambios\" class=\"topcoat-button--cta full\"/>\r\n\r\n\r\n    </div>";
},"useData":true});
templates['home'] = template({"1":function(depth0,helpers,partials,data) {
    return "disabled";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1;

  return " <div class=\"topcoat-navigation-bar\">\r\n           \r\n            <div class=\"topcoat-navigation-bar__item center full\">\r\n                <h1 class=\"topcoat-navigation-bar__title\">Escrito a la Vista</h1>\r\n	</div>\r\n</div>\r\n<div class=\"topcoat-list\">\r\n  <h3 class=\"topcoat-list__header\">Acciones</h3>\r\n  <ul class=\"topcoat-list__container center\">\r\n  <li class=\"topcoat-list__item\">\r\n  <a href='#login/'><button class=\"topcoat-button--cta full\" "
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.logged : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + " ><span class=\"topcoat-icon topcoat-icon--login\" ></span> LogIn</button></a>\r\n  </li>\r\n  <li class=\"topcoat-list__item\">\r\n  <a href='#listadoAPI/'><button class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--listar\"></span> Listar Obras</button></a>\r\n  </li>\r\n	<li class=\"topcoat-list__item\">\r\n     <a href='#cambiarObraApi/'><button class=\"topcoat-button--cta full\" "
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.logout : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + " ><span class=\"topcoat-icon topcoat-icon--obra\"></span> Modificar Obra</button></a>\r\n    </li>\r\n    <li class=\"topcoat-list__item\">\r\n      <a href='#cambiarDatos/'><button class=\"topcoat-button--cta full\" "
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.logout : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + " ><span class=\"topcoat-icon topcoat-icon--datos\"></span> Cambiar Datos</button></a>\r\n    </li>\r\n    <li class=\"topcoat-list__item\">\r\n      <a href='#registrarseAPI/'><button class=\"topcoat-button--cta full\" "
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.logged : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + " ><span class=\"topcoat-icon topcoat-icon--registrarse\"></span> Registrarse</button></a>\r\n    </li>\r\n	<li class=\"topcoat-list__item\">\r\n    <button id='buttonlogout' class=\"topcoat-button--cta full\" "
    + ((stack1 = helpers['if'].call(depth0,(depth0 != null ? depth0.logout : depth0),{"name":"if","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "><span class=\"topcoat-icon topcoat-icon--logout\"></span> LogOut</button>\r\n    </li>\r\n  </ul>\r\n</div>";
},"useData":true});
templates['listaobras'] = template({"1":function(depth0,helpers,partials,data) {
    var helper, alias1=helpers.helperMissing, alias2="function", alias3=this.escapeExpression;

  return "    <li class=\"topcoat-list__item\">\r\n      <a href=\"#obra/"
    + alias3(((helper = (helper = helpers.id || (depth0 != null ? depth0.id : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"id","hash":{},"data":data}) : helper)))
    + "\">\r\n		   <table class='tituloautor'>\r\n			<tr>\r\n				<td>"
    + alias3(((helper = (helper = helpers.titulo || (depth0 != null ? depth0.titulo : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"titulo","hash":{},"data":data}) : helper)))
    + "</td>			\r\n				<td>"
    + alias3(((helper = (helper = helpers.autor || (depth0 != null ? depth0.autor : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"autor","hash":{},"data":data}) : helper)))
    + "</td>\r\n			</tr>\r\n		   </table>\r\n            <p>"
    + alias3(((helper = (helper = helpers.resumen || (depth0 != null ? depth0.resumen : depth0)) != null ? helper : alias1),(typeof helper === alias2 ? helper.call(depth0,{"name":"resumen","hash":{},"data":data}) : helper)))
    + "</p>\r\n        </a>\r\n      </li>\r\n";
},"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    var stack1;

  return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n<a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n<span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n</a>\r\n</div>\r\n<div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Listado Obras</h1>\r\n        </div>\r\n     \r\n<!--<div class=\"main-content>-->\r\n\r\n<div class=\"topcoat-list\">\r\n <h3 class=\"topcoat-list__header\">Obras</h3>\r\n    <ul class=\"topcoat-list__container list scroller\" id=\"lstObras\">\r\n"
    + ((stack1 = helpers.each.call(depth0,(depth0 != null ? depth0.lista : depth0),{"name":"each","hash":{},"fn":this.program(1, data, 0),"inverse":this.noop,"data":data})) != null ? stack1 : "")
    + "	\r\n  </ul>\r\n</div>\r\n<!--</div>-->\r\n";
},"useData":true});
templates['login'] = template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n<a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n<span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n</a>\r\n</div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Login</h1>\r\n        </div>\r\n</div>\r\n<div id='loginForm'>\r\n<input type=\"text\" id='usuario' class=\"topcoat-text-input full\" placeholder=\"Nombre de usuario\" value=\"\"><br><br>\r\n<input type=\"password\" id='contrasena' class=\"topcoat-text-input full\" placeholder=\"Contraseña\" value=\"\"><br><br>\r\n<button id='loginbutton' class=\"topcoat-button--cta full\">Log In</button><br><br>\r\n<a href='#contrasenaOlvidada'><button class=\"topcoat-button--cta full\">He olvidado la contraseña</button></a>\r\n</div>";
},"useData":true});
templates['registrarse'] = template({"compiler":[6,">= 2.0.0-beta.1"],"main":function(depth0,helpers,partials,data) {
    return "<div class=\"topcoat-navigation-bar__item left quarter\">\r\n            <a class=\"topcoat-icon-button--quiet back-button\" href=\"#\">\r\n                <span class=\"topcoat-icon topcoat-icon--home\"></span>\r\n            </a>\r\n    </div>\r\n        <div class=\"topcoat-navigation-bar__item center half\">\r\n            <h1 class=\"topcoat-navigation-bar__title\">Registrarse</h1>\r\n        </div>\r\n   \r\n    <div class='detalles scroller'>\r\n	<center><img src=\"\" id='preview' style='display:none;' class=\"imagen-portada\"></center>\r\n		<p><strong>Nombre:</strong></p><input id='nombre' maxlength='20' type=\"text\" class=\"topcoat-text-input full\" placeholder=\"Nombre\" required = 'true'>	\r\n		<p><strong>Contraseña:</strong></p><input id='contrasena' type=\"password\" class=\"topcoat-text-input full\" placeholder=\"Contraseña\" required = 'true' pattern=\"[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]{6,}\">	\r\n		<p><strong>Email:</strong></p><input id='email' maxlength='45' type=\"email\" class=\"topcoat-text-input full\" placeholder=\"Email\" required = 'true'>	\r\n		<p><strong>Fecha nacimiento:</strong></p><input id='nacimiento' type=\"date\" required = 'true' class=\"topcoat-text-input full\" placeholder=\"yyyy-mm-dd\" pattern=\"[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])\">	\r\n        <p><strong>Pais:</strong></p><input id='pais' maxlength='20' type=\"text\" class=\"topcoat-text-input full\" placeholder=\"Pais\" required = 'true'>	\r\n		<p><strong>Sobre el autor:</strong></p><textarea id='about' class=\"topcoat-textarea full\" placeholder=\"Sobre mi\" cols=\"5\" rows=\"6\" required = 'true'></textarea>\r\n	<br>\r\n	<br>\r\n	<button id='SacarFotoCamara' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--camara\"></span> Sacar Foto</button>\r\n	<br><br>\r\n		<button id='SacarFotoAlbum' class=\"topcoat-button--cta full\"><span class=\"topcoat-icon topcoat-icon--image\"></span> Elegir Foto</button>\r\n<br>\r\n	<br>\r\n	<button id='EnviarRegistro' class=\"topcoat-button--cta full\">Enviar</button>\r\n    </div>";
},"useData":true});
})();