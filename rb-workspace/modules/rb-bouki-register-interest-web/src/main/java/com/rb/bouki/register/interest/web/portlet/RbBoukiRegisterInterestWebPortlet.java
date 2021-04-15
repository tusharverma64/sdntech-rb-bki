package com.rb.bouki.register.interest.web.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.rb.bouki.register.interest.model.BoukiRegisterInterest;
import com.rb.bouki.register.interest.service.BoukiRegisterInterestLocalServiceUtil;
import com.rb.bouki.register.interest.web.constants.RbBoukiRegisterInterestWebPortletKeys;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author tushar
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

    public void submitRYIFrom(ActionRequest request, ActionResponse response) {
	final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	final String phoneNumber = ParamUtil.getString(request, "phone_number");
	final String countryCode = ParamUtil.getString(request, "countryCode");
	final String email = ParamUtil.getString(request, "email");
	final Boolean keepInform = ParamUtil.getBoolean(request, "keepInform");
	try {
	    final Long id = CounterLocalServiceUtil.increment(BoukiRegisterInterest.class.getName());
	    BoukiRegisterInterest registerInterest = BoukiRegisterInterestLocalServiceUtil
		    .createBoukiRegisterInterest(id);
	    registerInterest.setEmailAddress(email);
	    registerInterest.setPhoneNo(phoneNumber);
	    registerInterest.setCreateDate(new Date());
	    registerInterest.setKeepInform(keepInform);
	    registerInterest.setCountryCode(countryCode);
	    registerInterest.setCompanyId(themeDisplay.getCompanyId());
	    registerInterest.setGroupId(themeDisplay.getScopeGroupId());

	    BoukiRegisterInterestLocalServiceUtil.addBoukiRegisterInterest(registerInterest);
	    response.getRenderParameters().setValue("mvcPath", "/success.jsp");
	    SessionMessages.add(request,
		    PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
	} catch (SystemException e) {
	    _log.error(e.getMessage());
	    SessionMessages.add(request,
		    PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

    }
}