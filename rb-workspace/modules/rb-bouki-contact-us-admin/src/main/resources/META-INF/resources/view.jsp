<%@page import="com.rb.bouki.contact.us.model.BoukiContactUs"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil"%>
<%@ include file="init.jsp" %>

<%
List<BoukiContactUs> list =  (List<BoukiContactUs>) renderRequest.getAttribute("contactUsList");
%>
<liferay-portlet:renderURL  var="submitContactUsFrom" />
		      <aui:form name="submitContactUsFrom" action="${submitContactUsFrom}" method="post">
				<aui:fieldset-group markupView="lexicon">
					<aui:fieldset>
						<aui:row>
						    <aui:col width="20">
								<aui:input label="From Date" name="fromDate" type="date" value="${fromDate}" >
								  <aui:validator name="date"/>
								</aui:input>
							</aui:col>
							<aui:col width="20">
								<aui:input label="To Date" name="toDate" type="date" value="${toDate}"  >
								  <aui:validator name="date"/>
								</aui:input>
							</aui:col>
					        <aui:col width="20">
							    <aui:input label="Search" name="searchText" type="text" value="${searchText}"/>
							</aui:col>
							<aui:col width="20">
							    <button type="submit"><i class="fa fa-search"></i></button>
							</aui:col>
							<aui:col width="20">
							    <aui:button name="Reset" type="reset" value="Reset" />
							</aui:col>
						</aui:row>
						<aui:row>
						    <aui:col width="100">
							    <aui:button name="export" type="submit" value="Export" />
							</aui:col>
						</aui:row>
					</aui:fieldset>
				</aui:fieldset-group>
			</aui:form>

<div class="container-fluid">
  <div class="row">
    <div class="col-11">
		<liferay-ui:search-container total="<%=BoukiContactUsLocalServiceUtil.getBoukiContactUsesCount()%>" var="searchContainer" delta="10" deltaConfigurable="true" 
		   emptyResultsMessage="Oops. There Are No record To Display">
		 <liferay-ui:search-container-results results="<%=ListUtil.subList(list, searchContainer.getStart(), searchContainer.getEnd())%>" />
		  <liferay-ui:search-container-row className="com.rb.bouki.contact.us.model.BoukiContactUs" modelVar="contactUsForm" keyProperty="id_" >
		   <liferay-ui:search-container-column-text name="Name" value="${contactUsForm.name}"/>
		    <liferay-ui:search-container-column-text name="EmailAddress" value="${contactUsForm.emailAddress}"/>
		   <liferay-ui:search-container-column-text  name="Message" cssClass="contact-message width-11" value="${contactUsForm.message}"/>
		   <liferay-ui:search-container-column-text name="Subject"  value="${contactUsForm.subject}" />
		   <liferay-ui:search-container-column-text name="Action">
		      <clay:dropdown-actions
					dropdownItems="${dropDownItems}"
				/>
			</liferay-ui:search-container-column-text>
		  </liferay-ui:search-container-row>
		 <liferay-ui:search-iterator />
		
		</liferay-ui:search-container>
		</div>
   </div>
    <div id="my-content-div" class="hidden">
		<div class="message">
			
		</div>
   </div>
</div>
<aui:script  position="inline" use="aui-base,aui-io-request">
Liferay.on('allPortletsReady', function() {
 AUI().use('aui-base', function(A) {
      
       A.all('.last').on('click', function(a) {
         console.log("asdasd");
         A.one("#my-content-div .message").html(a.currentTarget._node.parentNode.getElementsByClassName('contact-message')[0].innerText);
       });
 
       
      A.all('.dropdown-item').on('click', function(a) {
        if(a.currentTarget._node.attributes.href.value = "#1") {
         AUI().use('aui-dialog', 'aui-io','aui-modal', function(b) {
			var dialog = new b.Modal({
				title: "Message Description",
				bodyContent: A.one("#my-content-div").html(),
				headerContent: 'Message Description',
				centered: true,
				modal: true,
				height: 400,
				width:  400,
				render: '#my-content-div',
				close: true
			});
			dialog.render();
        
        });
        }
      });
 });
  });

</aui:script>