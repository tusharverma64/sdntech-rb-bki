<%@page import="com.liferay.portal.kernel.util.Constants"%>
<%@page import="com.rb.bouki.contact.us.web.preference.ContactUsConfiguration"%>
<%@page import="com.liferay.petra.string.StringPool"%>
<%@page import="com.liferay.portal.kernel.util.GetterUtil"%>
<%@ include file="init.jsp" %>
<liferay-portlet:actionURL portletConfiguration="true" var="configurationActionURL" />
 
<liferay-portlet:renderURL portletConfiguration="true" var="configurationRenderURL" />
 
<div class="container-fluid">
	<aui:form action="<%=configurationActionURL%>" method="post" name="fm">
    <aui:input name="<%=Constants.CMD%>" type="hidden" value="<%=Constants.UPDATE%>" />

    <aui:input name="redirect" type="hidden"    value="<%=configurationRenderURL%>" />
	 	<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				    <aui:row>
						<aui:col width="50">
				             <aui:input label="toEmail" name="toEmail" value="<%=toEmail%>">
				             </aui:input>
				         </aui:col>
					</aui:row>
	        </aui:fieldset>
	    </aui:fieldset-group>
	 
	    <aui:button-row>
	        <aui:button type="submit"></aui:button>
	    </aui:button-row>
	</aui:form>
</div>