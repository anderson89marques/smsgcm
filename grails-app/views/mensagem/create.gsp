<%@ page import="gcmserver.Mensagem" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">	
		<title><g:message code="default.create.labe" args="[entityName]" default="Criar Mensagem"/></title>
	</head>
	<body>
		<a href="#create-mensagem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="create-mensagem" class="content scaffold-create" role="main" >
			<h1><g:message code="default.create.labe" args="[entityName]" default="Criar Mensagem"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${mensagemInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${mensagemInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:mensagemInstance, action:'save']" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
					<button>Cancelar</button>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
