<%@ include file="/init.jsp" %>

<portlet:actionURL var="submitContactUsPreference" name="submitContactUsPreference"></portlet:actionURL>

<aui:form name="submitContactUsPreference" action="${submitContactUsPreference}" method="post">
<h1><liferay-ui:message key="contactusheading-message-key" /></h1>
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:row>
				<aui:col width="100">
					<aui:input label="To Email" name="toEmail" type="text" >
					  <aui:validator name="required"></aui:validator>
					  <aui:validator name="email"/>
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
	
	<aui:button-row>
		<aui:button name="submitButton" type="submit" value="Submit" />
	</aui:button-row>
</aui:form>