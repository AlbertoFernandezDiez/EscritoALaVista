var LocalStorageAdapter = function () {

    this.inicializar = function() {
        var deferred = $.Deferred();
        // Store sample data in Local Storage
        
        deferred.resolve();
        return deferred.promise();
    }

    this.comprobarsesion = function () {
		var id= window.localStorage.getItem('id');
		
		loggedID = id;
		
		return id != null;
    }

    this.eliminarsesion = function () {
        window.localStorage.removeItem('id');
		loggedID ='';
    }
	
	this.iniciarsesion = function(id){
	window.localStorage.setItem('id',id);
	}

}
