<%@page import="com.rb.bouki.register.interest.model.BoukiRegisterInterest"%>
<%@page import="com.rb.bouki.register.interest.service.BoukiRegisterInterestLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@page import="java.util.List"%>
<%@ include file="init.jsp" %>


<%
List<BoukiRegisterInterest> list =  (List<BoukiRegisterInterest>) renderRequest.getAttribute("contactUsList");
%>

<liferay-portlet:renderURL  var="submitRYIFrom" />
 <aui:form name="submitRYIFrom" action="${submitRYIFrom}" method="post">
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
                        <input type="text" class="form-control" placeholder="Search" name="<portlet:namespace/>searchText"  value="${searchText}">
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
			<aui:row>
			    <aui:col width="80">
				    <aui:button name="export" type="submit" cssClass="pull-right" value="Export" />
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
		 <liferay-ui:search-container-results results="<%=ListUtil.subList(list, searchContainer.getStart(), searchContainer.getEnd())%>" />
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
   <div id="my-content-div hide">
		<div class="message">
			
		</div>
   </div>
</div>
