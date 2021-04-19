package com.rb.bouki.register.interest.admin.portlet;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.rb.bouki.register.interest.admin.constants.RbBoukiRegisterInterestAdminPortletKeys;
import com.rb.bouki.register.interest.model.BoukiRegisterInterest;
import com.rb.bouki.register.interest.service.BoukiRegisterInterestLocalServiceUtil;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
/**
 * The purpose of this class is to create admin component for Register Your Interest forms Entry
 *
 *
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.use-default-template=true",
		"javax.portlet.display-name=" + RbBoukiRegisterInterestAdminPortletKeys.RBBOUKIREGISTERINTERESTADMINNAME,
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RbBoukiRegisterInterestAdminPortletKeys.RBBOUKIREGISTERINTERESTADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",

	},
	service = Portlet.class
)
public class RbBoukiRegisterInterestAdminPortlet extends MVCPortlet {

    Log _log = LogFactoryUtil.getLog(RbBoukiRegisterInterestAdminPortlet.class.getName());

    private static final String CONTACTUSLIST = "contactUsList";
    private static final String FROMDATE = "fromDate";
    private static final String TODATE = "toDate";
    private static final String SEARCH_TEXT = "searchText";

    /*
     * This method is used to render the portlet
     *
     * @param renderRequest
     * 
     * @param renderResponse
     * 
     * @throws IOException
     * 
     * @throws PortletException
     */
    @Override
    public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
	    throws IOException, PortletException {
	ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
	String searchText = ParamUtil.getString(renderRequest, SEARCH_TEXT);
	String fromDate = ParamUtil.getString(renderRequest, FROMDATE);
	String toDate = ParamUtil.getString(renderRequest, TODATE);
	if (_log.isDebugEnabled()) {
	    _log.debug("SearchText :" + searchText);
	    _log.debug("fromDate :" + fromDate);
	    _log.debug("toDate :" + toDate);
	}
	List<BoukiRegisterInterest> contactUsList = Collections.emptyList();
	try {
	    if (Validator.isNotNull(searchText) || (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate))) {
		contactUsList = BoukiRegisterInterestLocalServiceUtil.getQueryResult(searchText, fromDate, toDate,
			themeDisplay.getLocale());
	    } else {
		contactUsList = BoukiRegisterInterestLocalServiceUtil.getBoukiRegisterInterests(QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	    }
	} catch (Exception e) {
	    _log.error(e.getMessage());
	}

	renderRequest.setAttribute(CONTACTUSLIST, contactUsList);
	renderRequest.setAttribute(FROMDATE, fromDate);
	renderRequest.setAttribute(TODATE, toDate);
	renderRequest.setAttribute(SEARCH_TEXT, searchText);


	super.doView(renderRequest, renderResponse);
    }
}