package postgcm
import grails.converters.JSON

class Post2Gcm {
	
	Post2Gcm(){
		
	}
	
	def post(apiKey, gcmclass){
		URL url = new URL("https://android.googleapis.com/gcm/send")
		def conn = url.openConnection()
		
		//conn.setRequestMethod("Post")
		conn.setRequestProperty("Content-Type", "application/json");  //"application/x-www-form-urlencoded;charset=UTF-8"
        conn.setRequestProperty("Authorization", "key="+apiKey);
		
		conn.setDoOutput(true)
		gcmclass.id = 1
		def map = [data: gcmclass.data, registration_ids: gcmclass.registration_ids]
		def j = map as JSON
		def writer = new OutputStreamWriter(conn.outputStream)
		println map
		println j
		writer.write(j.toString())
		writer.flush()
		writer.close()
		
		int codigoResposta = conn.getResponseCode()
		println "Msg enviada para $url"
		println "Código de resposta: $codigoResposta"
		
		conn.connect()
		def resposta = conn.content.text
		resposta
	}
}
