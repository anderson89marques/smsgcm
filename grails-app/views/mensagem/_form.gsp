<%@ page import="gcmserver.Mensagem" %>

<div class="fieldcontain ${hasErrors(bean: mensagemInstance, field: 'titulo', 'error')} ">
	<label for="titulo">
		<g:message code="mensagem.titulo.label" default="Titulo" />
		
	</label>
	<g:textField name="titulo" value="${mensagemInstance?.titulo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: mensagemInstance, field: 'conteudo', 'error')} ">
	<label for="conteudo">
		<g:message code="mensagem.conteudo.label" default="Conteudo" />
		
	</label>
	<g:textArea name="conteudo" value="${mensagemInstance?.conteudo}"/>

</div>

