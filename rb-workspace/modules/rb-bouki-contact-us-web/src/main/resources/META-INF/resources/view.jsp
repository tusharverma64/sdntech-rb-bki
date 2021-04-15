<%@ include file="/init.jsp" %>

<portlet:actionURL var="submitContactUsFrom" name="submitContactUsFrom"></portlet:actionURL>

<aui:form name="submitContactUsFrom" action="${submitContactUsFrom}" method="post">
<h1><liferay-ui:message key="contactusheading-message-key" /></h1>
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:row>
				<aui:col width="100">
					<aui:input label="name" name="name" type="text" >
					 <aui:validator name="required"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Email" name="email" type="text" >
					  <aui:validator name="required"></aui:validator>
					  <aui:validator name="email"/>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Subject" name="subject" type="text" >
					  <aui:validator name="required"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Message" name="message" type="textarea" >
					 <aui:validator name="required"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
	
	<aui:button-row>
		<aui:button name="submitButton" type="submit" value="Submit" />
	</aui:button-row>
</aui:form>