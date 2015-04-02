package gcmserver

import grails.converters.JSON
import grails.transaction.Transactional
import postgcm.Post2Gcm

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = false)
class GcmController {
    def post2GcmService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Gcm.list(params), model:[gcmInstanceCount: Gcm.count()]
    }

	def postmessagem(){
		println "Enviando msg"

        println params.toString()
        def msg = post2GcmService.processarDados(params.id, params.objs)
		msg
	}

    def buscainformacao(){
        println "Buscando msg"
        def msgs = post2GcmService.getinformacao(params.objs)
        msgs
    }
	
	def criarGcm(){
        println params.toString()
		Gcm gcmclass = new Gcm()
		gcmclass.addRegId(params.RegId)
        gcmclass.save(flush: true)
		println "List: ${gcmclass.registration_ids}"
        render([ok:"ok"])
	}
	
    def show(Gcm gcmInstance) {
        respond gcmInstance
    }

    def create() {
        respond new Gcm(params)
    }

    @Transactional
    def save(Gcm gcmInstance) {
        if (gcmInstance == null) {
            notFound()
            return
        }

        if (gcmInstance.hasErrors()) {
            respond gcmInstance.errors, view:'create'
            return
        }

        gcmInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'gcm.label', default: 'Gcm'), gcmInstance.id])
                redirect gcmInstance
            }
            '*' { respond gcmInstance, [status: CREATED] }
        }
    }

    def edit(Gcm gcmInstance) {
        respond gcmInstance
    }

    @Transactional
    def update(Gcm gcmInstance) {
        if (gcmInstance == null) {
            notFound()
            return
        }

        if (gcmInstance.hasErrors()) {
            respond gcmInstance.errors, view:'edit'
            return
        }

        gcmInstance.save flush:true

        request.withFormat 
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Gcm.label', default: 'Gcm'), gcmInstance.id])
                redirect gcmInstance
            }
            '*'{ respond gcmInstance, [status: OK] }
    }

    @Transactional
    def delete(Gcm gcmInstance) {

        if (gcmInstance == null) {
            notFound()
            return
        }

        gcmInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Gcm.label', default: 'Gcm'), gcmInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'gcm.label', default: 'Gcm'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
