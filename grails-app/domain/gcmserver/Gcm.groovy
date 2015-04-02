package gcmserver

class Gcm {
    String nome = "GCMKYES"
	//List registration_ids
    static hasMany = [registration_ids: String]
	String apiKey

    Gcm(){
       if(!apiKey) this.apiKey = "AIzaSyBCyTjgOMZncxzTgPxVN9yxEbaZ0Y2SvQo"
       /*if(!registration_ids) {
           registration_ids = new LinkedList<String>()
           registration_ids.add("APA91bFLPuYydec9qaPkF2poz0nrYzd2mbdPEL5jzJLKsaBccsrzIVgVI2LqqkMrIOuMHf0RJxfoEa-cko2M8-XKkcQX-C8Yu6xriyZ95uI2FEKL_iU0wUKShyOh9iJMivOdnau_XjSws6fshpw8jssCUP1p8L6jyA")
       }*/
    }
	
    static constraints = {
		registration_ids nullable:true
		apiKey nullable:true
    }
	
	def addRegId(rid){
		if(!registration_ids)
			registration_ids = new LinkedList<String>()
		println "ID: $rid"
		registration_ids.add(rid) 
	}

    String toString(){
        "Nome: $nome, Registration Ids: ${registration_ids}"
    }
}
