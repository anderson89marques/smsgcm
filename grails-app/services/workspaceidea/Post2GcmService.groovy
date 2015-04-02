package workspaceidea

import gcmserver.Gcm
import gcmserver.Mensagem
import gcmserver.Pessoa
import grails.converters.JSON

class Post2GcmService {

    def getMensagem(def idmensagem){
        return Mensagem.get(idmensagem.toInteger())
    }

    def getPessoas(def ids){
        def listids = []
        ids.each{id ->
            listids += Pessoa.get(id)
        }
        return listids
    }

    def givemeGcm(){
        def gcm = Gcm.findByNome("GCMKYES")
        println "Entrei no givegcm $gcm"
        if(!gcm){
            println "Entrei no não giveme"
            Gcm.withTransaction{ status ->
                gcm = new Gcm()
                gcm.save()
            }

        }
        return gcm
    }

    def construirListeMapPessoas(def pessoas){
        def listpessoas = []
        pessoas.each{p ->
            listpessoas += [nome: p.nome, telefone: p.telefone]
        }
        listpessoas
    }

    def processarDados(def idmensagem, String idspessoas) {
        def mensagem = getMensagem(idmensagem)
        println mensagem

        def ids = idspessoas.split(',')
        println ids

        def pessoas = getPessoas(ids)
        def listpessoas = construirListeMapPessoas(pessoas)
        println listpessoas

        def gcm = givemeGcm()
        //Importante: data e registrations_ids devem ter esses nomes!!
        def dados = [data : [mensagem: [titulo:mensagem.titulo, conteudo:mensagem.conteudo],
                             pessoas : listpessoas], registration_ids: gcm.registration_ids]

        //post(gcm, dados)
        def d = [data: [id_projeto:"7363000269c85acdce6873270f6898852f5f7684570626f8167e929369bd2b450ad549884367470e2371076c423c25552c1b79260c65f3a41613a07920baccc4bb73ef1c34371040e0c4462e102909b8cffa11d245b0b5b942727fa2e12e23a238bacb2e",
                        id_mensagem: mensagem.id, mensagem: [titulo:mensagem.titulo, conteudo:mensagem.conteudo],
                        pessoas : listpessoas] ]

        send_data_khipu(d)
    }


    def send_data_khipu(dados=null){
        def msg=""
        withHttp(uri: "http://0.0.0.0:6543"){
            msg = get(path: '/receiver', query:[q:dados as JSON])
            println msg
        }
        return msg
    }


    def post(gcm, dados){
        URL url = new URL("https://android.googleapis.com/gcm/send")
        def conn = url.openConnection()

        //conn.setRequestMethod("Post")
        conn.setRequestProperty("Content-Type", "application/json");  //"application/x-www-form-urlencoded;charset=UTF-8"
        conn.setRequestProperty("Authorization", "key=" + gcm.apiKey);

        conn.setDoOutput(true)
        //gcmclass.id = 1
        //def map = [data: gcmclass.data, registration_ids: gcmclass.registration_ids]
        def j = dados as JSON
        def writer = new OutputStreamWriter(conn.outputStream)
        println dados
        println j
        writer.write(j.toString())
        writer.flush()
        writer.close()

        int codigoResposta = conn.getResponseCode()
        println "Msg enviada para $url"
        println "Código de resposta: $codigoResposta"

        conn.connect()
        def resposta = conn.content.text
        println resposta
    }

    def getinformacao(String idspessoas){
        println("Get informção")
        def ids = idspessoas.split(',')
        println ids
        def dados = [mensagem_ids: ids, id_projeto:"7363000269c85acdce6873270f6898852f5f7684570626f8167e929369bd2b450ad549884367470e2371076c423c25552c1b79260c65f3a41613a07920baccc4bb73ef1c34371040e0c4462e102909b8cffa11d245b0b5b942727fa2e12e23a238bacb2e"]
        def msg=""
        withHttp(uri: "http://0.0.0.0:6543"){
            msg = get(path: '/informacao', query:[q:dados as JSON])
            println msg
        }
        return msg
    }

}
