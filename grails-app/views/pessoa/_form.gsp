<%@ page import="gcmserver.Pessoa" %>



<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'nome', 'error')} ">
	<label for="nome">
		<g:message code="pessoa.nome.label" default="Nome" />
		
	</label>
	<g:textField name="nome" value="${pessoaInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'telefone', 'error')} ">
	<label for="telefone">
		<g:message code="pessoa.telefone.label" default="Telefone" />
		
	</label>
	<g:textField name="telefone" value="${pessoaInstance?.telefone}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: pessoaInstance, field: 'grupo', 'error')} ">
	<label for="grupo">
		<g:message code="pessoa.grupo.label" default="Grupo" />
		
	</label>
	<g:textField name="grupo" value="${pessoaInstance?.grupo}"/>

</div>
