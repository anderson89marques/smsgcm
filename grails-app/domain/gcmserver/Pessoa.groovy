package gcmserver

class Pessoa {
	String nome
	//Endereco endereco
	String telefone //aqui vai provavelmente uma lista de telefones, de objetos telefone
	String grupo //
	
	static hasMany = [mensagens: Mensagem]
	 
    static constraints = {
		nome nullable:true 
		telefone nullable: true
		grupo nullable:true  
    }
}
