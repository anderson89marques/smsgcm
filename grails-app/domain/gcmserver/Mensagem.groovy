package gcmserver

class Mensagem {
	String titulo
	String conteudo
	
	static hasMany = [pessoas: Pessoa]
	static belongsTo = Pessoa
	
    static constraints = {
		titulo nullable: true 
		conteudo nullable: true 
		pessoas nullable: true
    }
}
