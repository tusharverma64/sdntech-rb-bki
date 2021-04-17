<%@ include file="/init.jsp" %>

<liferay-portlet:resourceURL var="RYIResourceURL" ></liferay-portlet:resourceURL>
<h1><liferay-ui:message key="heading-message-key" /></h1>
<div id="messageRYI" class="hide">
	<input checked="true" type="checkbox" />
	<p>
	   <liferay-ui:message key="thankYou-message-key" />
	</p>
	<p>
	   <liferay-ui:message key="contactDescription-message-key" />
	</p>
	<p>
	   <liferay-ui:message key="touch-message-key" />
	</p>
	<p>
	   <liferay-ui:message key="contactDescription-message-key" />
	</p>
</div>
<aui:form id="submitRYIFrom">
	<aui:fieldset-group markupView="lexicon">
	<p> <liferay-ui:message key="register-message-key" /></p>
		<aui:fieldset>
			<aui:row>
				<aui:col width="50">
					<aui:select name="countryCode" label="Contry Code" cssClass="countryCode">
					    <aui:option value="+91">+91</aui:option>
					    <aui:option value="+966">+966</aui:option>
					</aui:select>
				</aui:col>
				<aui:col width="50">
					<aui:input label="Phone number" name="phone_number" type="text" cssClass="phone_number">
					   <aui:validator name="required"></aui:validator>
					   <aui:validator name="digits"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Email" name="email" type="email"  cssClass="email">
					  <aui:validator name="required"></aui:validator>
					  <aui:validator name="email"/>
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset >
			<aui:input label="Keep me update about bauki news" name="keepInform" type="checkbox" cssClass="keepInform" />
		</aui:fieldset>
	</aui:fieldset-group>
	<aui:button-row>
		<aui:button name="submitButton" type="submit" value="Submit" onclick="callServeResource()" />
	</aui:button-row>
</aui:form>

<script type="text/javascript">
	function callServeResource(){
	    AUI().use('aui-base','aui-io-request', function(A){
	        var countryCode = A.one("#submitRYIFrom .countryCode").val();
	        var phone_number = A.one("#submitRYIFrom .phone_number").val();
	        var email = A.one("#submitRYIFrom .email").val();
	        var keepInform = A.one("#submitRYIFrom .keepInform").val();
	        A.io.request('${RYIResourceURL}', {
	               method: 'POST',
	               dataType: 'json',
	               data: {
	                   <portlet:namespace />countryCode: countryCode,
	                   <portlet:namespace />phone_number: phone_number,
	                   <portlet:namespace />keepInform: keepInform,
	                   <portlet:namespace />email: email
	               },
	               on: {
	                   success: function() {
	                	   var data=this.get('responseData');
	                       if(data.message) {
		                       A.one("#messageRYI").show();
		                       A.one("#submitRYIFrom").hide();
	                       }
	                   }
	              }
	        });
	    });
	}
</script >