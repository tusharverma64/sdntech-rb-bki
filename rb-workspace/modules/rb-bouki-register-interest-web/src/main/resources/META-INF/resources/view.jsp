<%@ include file="/init.jsp" %>

<portlet:actionURL var="submitRYIFrom" name="submitRYIFrom"></portlet:actionURL>

<aui:form name="submitRYIFrom" action="${submitRYIFrom}" method="post">
	<aui:fieldset-group markupView="lexicon">
	<h1><liferay-ui:message key="heading-message-key" /></h1>
	<p> <liferay-ui:message key="register-message-key" /></p>
		<aui:fieldset>
			<aui:row>
				<aui:col width="50">
					<aui:select name="countryCode">
					    <aui:option value="+91">+91</aui:option>
					    <aui:option value="+966">+966</aui:option>
					</aui:select>
				</aui:col>
				<aui:col width="50">
					<aui:input label="Phone number" name="phone_number" type="text" >
					   <aui:validator name="required"></aui:validator>
					   <aui:validator name="digits"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Email" name="email" type="email" >
					  <aui:validator name="required"></aui:validator>
					  <aui:validator name="email"/>
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset >
			<aui:input label="Keep me update about bauki news" name="keepInform" type="checkbox" />
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:button-row>
		<aui:button name="submitButton" type="submit" value="Submit" />
	</aui:button-row>
</aui:form>