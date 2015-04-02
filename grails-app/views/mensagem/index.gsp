
<%@ page import="gcmserver.Mensagem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'mensagem.label', default: 'Mensagem')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
        <script>
            jQuery(document).ready(function(){
                jQuery("#botao").click(function(e){
                    var i, arrayids = [];
                    var checks = jQuery("input[type=checkbox]:checked");
                    console.log(checks.length);
                    for(i =0; i < checks.length; i++){
                        arrayids[i] = checks[i].id;
                        console.log(arrayids);
                    }
                    <g:remoteFunction controller="gcm" action="postmessagem" params="'objs='+arrayids" onSuccess="alert(data)"></g:remoteFunction>
                });
            });
        </script>
	</head>
	<body>
		<a href="#list-mensagem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="list-mensagem" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            <button id="botao">Enviar</button>
			<table>
			<thead>
					<tr>
					    <td><g:message code="" default="escolha as mensagens"/></td>

						<g:sortableColumn property="titulo" title="${message(code: 'mensagem.titulo.label', default: 'Titulo')}" />
					
						<g:sortableColumn property="conteudo" title="${message(code: 'mensagem.conteudo.label', default: 'Conteudo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${mensagemInstanceList}" status="i" var="mensagemInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><g:checkBox name="chkEnviar" class="check" id="${mensagemInstance.id}"></g:checkBox></td>

						<td><g:link action="show" id="${mensagemInstance.id}">${fieldValue(bean: mensagemInstance, field: "titulo")}</g:link></td>
					
						<td>${fieldValue(bean: mensagemInstance, field: "conteudo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${mensagemInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
