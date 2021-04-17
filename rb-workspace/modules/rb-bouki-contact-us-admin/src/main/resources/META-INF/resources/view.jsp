<%@page import="com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil"%>
<%@ include file="/init.jsp" %>

		      <aui:form name="submitContactUsFrom" action="${submitContactUsFrom}" method="post">
				<aui:fieldset-group markupView="lexicon">
					<aui:fieldset>
						<aui:row>
						<aui:col width="20">
								<aui:input label="From Date" name="fromDate" type="date" />
							</aui:col>
							<aui:col width="20">
								<aui:input label="To Date" name="toDate" type="date" />
							</aui:col>
					        <aui:col width="20">
							    <aui:input label="Search" name="Search" type="text" />
							</aui:col>
							<aui:col width="20">
							    <button type="submit"><i class="fas fa-search"></i></button>
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
		 <liferay-ui:search-container-results results="<%=BoukiContactUsLocalServiceUtil.getBoukiContactUses(searchContainer.getStart(),searchContainer.getEnd())%>" />
		  <liferay-ui:search-container-row className="com.rb.bouki.contact.us.model.BoukiContactUs" modelVar="contactUsForm" keyProperty="id_" >
		   <liferay-ui:search-container-column-text name="Name" value="${contactUsForm.name}"/>
		    <liferay-ui:search-container-column-text name="EmailAddress"  value="${contactUsForm.emailAddress}"/>
		   <liferay-ui:search-container-column-text name="Message" value="${contactUsForm.message}"/>
		   <liferay-ui:search-container-column-text name="Subject"  value="${contactUsForm.subject}" />
		  </liferay-ui:search-container-row>
		 <liferay-ui:search-iterator />
		
		</liferay-ui:search-container>
		</div>
   </div>
</div>