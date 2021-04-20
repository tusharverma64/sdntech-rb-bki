<%@page import="com.rb.bouki.contact.us.model.BoukiContactUs"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>
<%@page import="com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil"%>
<%@ include file="init.jsp" %>

<%
List<BoukiContactUs> list =  (List<BoukiContactUs>) renderRequest.getAttribute("contactUsList");
%>
<liferay-portlet:resourceURL var="rsourceURL" ></liferay-portlet:resourceURL>
<liferay-portlet:renderURL  var="submitContactUsFrom" />
<div id="submitContactFrom">
<aui:form name="submitContactUsFrom" id="submitContactUsFrom" action="${submitContactUsFrom}" method="post">
<aui:fieldset-group markupView="lexicon">
	<aui:fieldset>
		<aui:row>
		    <aui:col width="25">
				<aui:input  inlineLabel="true" label="From Date" name="fromDate" type="date" value="${fromDate}" >
				  <aui:validator name="date"/>
				</aui:input>
			</aui:col>
			<aui:col width="25">
				<aui:input  inlineLabel="true" label="To Date" name="toDate" type="date" value="${toDate}"  >
				  <aui:validator name="date"/>
				</aui:input>
			</aui:col>
	        <aui:col width="30">
			    <div class="input-group">
                               <input type="text" class="form-control" placeholder="Search" id="<portlet:namespace />searchText" name="<portlet:namespace/>searchText" value="${searchText}">
                                  <div class="input-group-btn">
                                      <button class="btn btn-default btn-primary" type="submit">
                                           <i class="fa fa-search"></i>
                                      </button>
                                  </div>
                             </div>
			</aui:col>
			<aui:col width="20">
			    <aui:button name="Reset" type="reset" value="Reset" />
			</aui:col>
		</aui:row>
	</aui:fieldset>
</aui:fieldset-group>
</aui:form>
</div>
<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
               <aui:row>
					<aui:col width="80">
					    <aui:button name="export" type="submit" cssClass="pull-right" value="Export" onClick="exportreport()" />
					</aui:col>
				</aui:row>
            </aui:fieldset>
</aui:fieldset-group>
<div class="container-fluid">
  <div class="row">
    <div class="col-11">
		<liferay-ui:search-container total="<%=BoukiContactUsLocalServiceUtil.getBoukiContactUsesCount()%>" var="searchContainer" delta="10" deltaConfigurable="true" 
		   emptyResultsMessage="Oops. There Are No record To Display">
		 <liferay-ui:search-container-results results="<%=ListUtil.subList(list, searchContainer.getStart(), searchContainer.getEnd())%>" />
		  <liferay-ui:search-container-row className="com.rb.bouki.contact.us.model.BoukiContactUs" modelVar="contactUsForm" keyProperty="id_" >
		   <liferay-ui:search-container-column-text name="Name" value="${contactUsForm.name}"/>
		   <liferay-ui:search-container-column-text name="EmailAddress" value="${contactUsForm.emailAddress}"/>
		   <liferay-ui:search-container-column-text  name="Message" cssClass="contact-message width-11" >
		   <div class="contact-message" >
		      ${contactUsForm.message}
		   </div>
		   </liferay-ui:search-container-column-text>
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
    <div id="hiddentext" class="hidden">
         
    </div>
    <div id="my-content-div">
		<div class="message">
			
		</div>
   </div>
</div>
<aui:script  position="inline" use="aui-base,aui-io-request">
Liferay.on('allPortletsReady', function() {
  AUI().use('aui-base','aui-io-plugin-deprecated','node', function(A) {
   
      
      A.one('body').delegate('click', function(a) {
        A.one("#hiddentext").html(a.currentTarget._node.parentNode.getElementsByClassName('contact-message')[0].innerText);
      },".last");

      A.one('body').delegate('click', function(a) {
        if(a.currentTarget._node.attributes.href.value = "#1") {
        A.one("#my-content-div").removeClass('hidden');
        AUI().use('aui-dialog', 'aui-io','aui-modal', function(b) {
			var dialog = new b.Modal({
				title: "Message Description",
				bodyContent: A.one("#hiddentext").html(),
				headerContent: 'Message Description',
				centered: true,
				modal: true,
				height: 500,
				width:  600,
				render: '#my-content-div',
				id: '<portlet:namespace/>dialog',
				close: true
			});
			dialog.render();
        
        });
        }
      } , ".dropdown-item");
 });
});

</aui:script>
<script type="text/javascript">
	function exportreport() {
	    AUI().use('aui-base','aui-io-request', function(A){
	        var fromDate = A.one("#<portlet:namespace />fromDate").val();
	        var toDate = A.one(" #<portlet:namespace />toDate").val();
	        var searchText = A.one("#<portlet:namespace />searchText").val();
	        A.io.request('${rsourceURL}', {
	               method: 'POST',
	               dataType: 'json',
	               data: {
	                   <portlet:namespace />fromDate: fromDate,
	                   <portlet:namespace />toDate: toDate,
	                   <portlet:namespace />searchText: searchText
	               },
	               on: {
	                   success: function() {
	                	   var data=this.get('responseData');
	                       if(data.message) {
		                       A.one("#submitContactFrom").hide();
	                       }
	                   }
	              }
	        });
	    });
	}
 
</script >