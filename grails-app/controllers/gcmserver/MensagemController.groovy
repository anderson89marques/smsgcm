package gcmserver

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class MensagemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Mensagem.list(params), model:[mensagemInstanceCount: Mensagem.count()]
    }

    def show(Mensagem mensagemInstance) {
        respond mensagemInstance
    }

    def getInformacao(){

    }

    def create() {
        respond new Mensagem(params)
    }

    @Transactional
    def save(Mensagem mensagemInstance) {
        if (mensagemInstance == null) {
            notFound()
            return
        }

        if (mensagemInstance.hasErrors()) {
            respond mensagemInstance.errors, view:'create'
            return
        }

        mensagemInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'mensagem.label', default: 'Mensagem'), mensagemInstance.id])
                redirect controller:'pessoa', action:'listPessoas', params: [mensagemInstanceId: mensagemInstance.id]
            }
            '*' { respond mensagemInstance, [status: CREATED] }
        }
    }

    def edit(Mensagem mensagemInstance) {
        respond mensagemInstance
    }

    @Transactional
    def update(Mensagem mensagemInstance) {
        if (mensagemInstance == null) {
            notFound()
            return
        }

        if (mensagemInstance.hasErrors()) {
            respond mensagemInstance.errors, view:'edit'
            return
        }

        mensagemInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Mensagem.label', default: 'Mensagem'), mensagemInstance.id])
                redirect mensagemInstance
            }
            '*'{ respond mensagemInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Mensagem mensagemInstance) {

        if (mensagemInstance == null) {
            notFound()
            return
        }

        mensagemInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Mensagem.label', default: 'Mensagem'), mensagemInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'mensagem.label', default: 'Mensagem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
