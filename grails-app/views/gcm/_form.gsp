<%@ page import="gcmserver.Gcm" %>

<div class="fieldcontain ${hasErrors(bean: gcmInstance, field: 'regId', 'error')} ">
	<label for="regId">
		<g:message code="gcm.regId.label" default="Reg Id" />
		
	</label>
	<g:textField name="regId" value="${gcmInstance?.regId}"/>

</div>

