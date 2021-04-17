<%@ include file="init.jsp" %>

<liferay-portlet:resourceURL var="myResourceURL" ></liferay-portlet:resourceURL>
<h1><liferay-ui:message key="contactusheading-message-key" /></h1>
<div id="messagec" class="hide">
		<p>
		   <liferay-ui:message key="contactDescription-message-key" />
		</p>
		
		<p>
		 <liferay-ui:message key="talk-to-you-soon" />
		</p>
		<p>
		   <liferay-ui:message key="baukiTeam-message-key" />
		</p>

		<button name="Send another message" class="btn"/>
</div>
test  <%= toEmail %>
<div id="submitContactUsFrom">
<aui:form name="submitContactUsFrom" >
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:row>
				<aui:col width="100">
					<aui:input label="name" name="name" type="text" cssClass="name">
					 <aui:validator name="required"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Email" name="email" type="text" cssClass="email" >
					  <aui:validator name="required"></aui:validator>
					  <aui:validator name="email"/>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Subject" name="subject" type="text" cssClass="subject">
					  <aui:validator name="required"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
			<aui:input label="toEmail" name="toEmail" type="hidden" value="<%= toEmail %>" cssClass="toEmail"/>
			<aui:row>
				<aui:col width="100">
					<aui:input label="Message" name="message" type="textarea" cssClass="message" >
					 <aui:validator name="required"></aui:validator>
					</aui:input>
				</aui:col>
			</aui:row>
		</aui:fieldset>
	</aui:fieldset-group>
	
	<aui:button-row>
		<aui:button name="submitButton" type="submit" value="Submit"  onclick="callcontactUsServeResource()"/>
	</aui:button-row>
</aui:form>
</div>
<script type="text/javascript">
	function callcontactUsServeResource(){
	    AUI().use('aui-base','aui-io-request', function(A){
	        var name = A.one("#submitContactUsFrom .name").val();
	        var subject = A.one("#submitContactUsFrom .subject").val();
	        var message = A.one("#submitContactUsFrom .message").val();
	        var toEmail = A.one("#submitContactUsFrom .toEmail").val();
	        var email = A.one("#submitContactUsFrom .email").val();
	        A.io.request('${myResourceURL}', {
	               method: 'POST',
	               dataType: 'json',
	               data: {
	                   <portlet:namespace />name: name,
	                   <portlet:namespace />subject: subject,
	                   <portlet:namespace />message: message,
	                   <portlet:namespace />toEmail: toEmail,
	                   <portlet:namespace />email: email
	               },
	               on: {
	                   success: function() {
	                	   var data=this.get('responseData');
	                       if(data.message) {
		                       A.one("#messagec").show();
		                       A.one("#submitContactUsFrom").hide();
	                       }
	                   }
	              }
	        });
	    });
	}
</script >