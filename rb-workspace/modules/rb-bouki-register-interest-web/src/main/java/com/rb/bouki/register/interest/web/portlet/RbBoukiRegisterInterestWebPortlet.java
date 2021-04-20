package com.rb.bouki.register.interest.web.portlet;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.rb.bouki.register.interest.model.BoukiRegisterInterest;
import com.rb.bouki.register.interest.service.BoukiRegisterInterestLocalServiceUtil;
import com.rb.bouki.register.interest.web.constants.RbBoukiRegisterInterestWebPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Bouki",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RbBoukiRegisterInterestWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RbBoukiRegisterInterestWebPortletKeys.RBBOUKIREGISTERINTERESTWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RbBoukiRegisterInterestWebPortlet extends MVCPortlet {
    
    Log _log = LogFactoryUtil.getLog(RbBoukiRegisterInterestWebPortlet.class.getName());

    /*
     * This method is used to handle ajax calling
     *
     * @param resourceRequest
     * 
     * @param resourceResponse
     * 
     * @throws IOException
     * 
     * @throws PortletException
     */
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse resourceResponse)
	    throws IOException, PortletException {

	_log.debug("This is serve resource method....");

	final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	final String phoneNumber = ParamUtil.getString(request, RbBoukiRegisterInterestWebPortletKeys.PHONE_NO);
	final String countryCode = ParamUtil.getString(request, RbBoukiRegisterInterestWebPortletKeys.COUNTRY_CODE);
	final String email = ParamUtil.getString(request, RbBoukiRegisterInterestWebPortletKeys.KEEP_INFORM);
	final Boolean keepInform = ParamUtil.getBoolean(request, RbBoukiRegisterInterestWebPortletKeys.KEEP_INFORM);
	if (_log.isDebugEnabled()) {
	    _log.debug("email :" + email);
	    _log.debug("phoneNumber :" + phoneNumber);
	    _log.debug("countryCode :" + countryCode);
	    _log.debug("keepInform :" + keepInform);
	}
	try {
	    if (Validator.isNotNull(phoneNumber) && Validator.isNotNull(countryCode) && Validator.isNotNull(keepInform)
		    && Validator.isNotNull(email)) {
		BoukiRegisterInterest boukiRegisterInterest = BoukiRegisterInterestLocalServiceUtil
			.createRegisterInterest(email, phoneNumber, keepInform, countryCode,
				themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId());

		if (Validator.isNotNull(boukiRegisterInterest)) {
		    JSONObject obj = JSONFactoryUtil.createJSONObject();
		    obj.put(RbBoukiRegisterInterestWebPortletKeys.MESSAGE, LanguageUtil.get(themeDisplay.getLocale(),
			    RbBoukiRegisterInterestWebPortletKeys.DATA_SAVED_SUCCESSFULLY));
		    PrintWriter out = resourceResponse.getWriter();
		    out.print(obj.toString());
		}
	    } else {
		JSONObject obj = JSONFactoryUtil.createJSONObject();
		obj.put(RbBoukiRegisterInterestWebPortletKeys.MESSAGE,
			RbBoukiRegisterInterestWebPortletKeys.DATA_SAVED_FAILED);
		PrintWriter out = resourceResponse.getWriter();
		out.print(obj.toString());
	    }
	} catch (SystemException e) {
	    _log.error(e.getMessage());
	}

	super.serveResource(request, resourceResponse);
    }
}