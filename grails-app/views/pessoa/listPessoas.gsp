<!Doctype html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Selecionar pessoas</title>
    <script>
        jQuery(jQuery).ready(function(e){
            jQuery("#enviar").click(function(e){
                enviarDados();
            });

            function enviarDados(){
                var i, arrayids = [];
                var idmsg = jQuery(".inp")[0].id;
                console.log(idmsg)
                var lis = jQuery(".check");
                var cont = 0;
                for(i = 0; i < lis.length; i++) {
                    if (lis[i].checked) {
                        arrayids[cont] = jQuery(".hiddenfield")[i].id;
                        console.log("log"+cont)
                        console.log(arrayids[cont]);
                        cont++;
                    }
                }
                <g:remoteFunction  controller="gcm" action="postmessagem" id="'+idmsg+'" params="'objs='+arrayids" update="list-pessoa"
                onSuccess="alert(data)"/>
            }
        });
    </script>
</head>
<body>
  <div class="body">
  		<a href="#list-pessoa" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-pessoa" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" default="Lista de pessoas" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            <input style="display: none;" id="${msgid}" class="inp"/>
			<button id="enviar" >Enviar</button>
			<table>
			<thead>
					<tr>
						<th>Enviar</th>
						
						<g:sortableColumn property="nome" title="${message(code: 'pessoa.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="telefone" title="${message(code: 'pessoa.telefone.label', default: 'Telefone')}" />
					
						<g:sortableColumn property="grupo" title="${message(code: 'pessoa.grupo.label', default: 'Grupo')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${pessoaInstanceList}" status="i" var="pessoaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td style="display: none" class="hiddenfield" id="${pessoaInstance.id}"></td>
						<td><g:checkBox name="chkEnviar" class="check"/></td>
						
						<td><g:link action="show" id="${pessoaInstance.id}" class="id">${fieldValue(bean: pessoaInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: pessoaInstance, field: "telefone")}</td>
					
						<td>${fieldValue(bean: pessoaInstance, field: "grupo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${pessoaInstanceCount ?: 0}" />
			</div>
		</div>

  </div>

</body>
</html>