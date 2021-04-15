<%@page import="com.rb.bouki.register.interest.service.BoukiRegisterInterestLocalServiceUtil"%>
<%@ include file="/init.jsp" %>

<portlet:actionURL var="submitContactUsFrom" name="submitContactUsFrom"></portlet:actionURL>
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
							    <button type="submit"><i class="fa fa-search"></i></button>
							</aui:col>
							<aui:col width="20">
							    <aui:button name="Reset" type="submit" value="Reset" />
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
		<liferay-ui:search-container total="<%=BoukiRegisterInterestLocalServiceUtil.getBoukiRegisterInterestsCount()%>" var="searchContainer"  delta="10" deltaConfigurable="true" 
		   emptyResultsMessage="Oops. There Are No record To Display">
		 <liferay-ui:search-container-results results="<%=BoukiRegisterInterestLocalServiceUtil.getBoukiRegisterInterests(searchContainer.getStart(),searchContainer.getEnd())%>" />
		  <liferay-ui:search-container-row className="com.rb.bouki.register.interest.model.BoukiRegisterInterest" modelVar="registerInterest" keyProperty="id_" >
		   <liferay-ui:search-container-column-text name="Country Code" value="${registerInterest.countryCode}"/>
		   <liferay-ui:search-container-column-text name="Phone No"  value="${registerInterest.phoneNo}" />
		   <liferay-ui:search-container-column-text name="Keep Inform"  value="${registerInterest.keepInform}"/>
		   <liferay-ui:search-container-column-text name="EmailAddress" value="${registerInterest.emailAddress}"/>
		  </liferay-ui:search-container-row>
		 <liferay-ui:search-iterator />
		
		</liferay-ui:search-container>
		</div>
   </div>
</div>