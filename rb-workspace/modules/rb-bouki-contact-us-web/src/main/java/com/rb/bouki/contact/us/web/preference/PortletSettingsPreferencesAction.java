package com.rb.bouki.contact.us.web.preference;

import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.rb.bouki.contact.us.web.constants.RbBoukiContactUsWebPortletKeys;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import aQute.bnd.annotation.metatype.Configurable;


/**
 * The purpose of this class is setting up the configuration 
 *
 *
 *
 *@author Liferay
 * 
 */
@Component(
	configurationPid = RbBoukiContactUsWebPortletKeys.CONFIGURATION_ID,
	configurationPolicy = ConfigurationPolicy.OPTIONAL, 
	immediate = true,
	property = "javax.portlet.name=" + RbBoukiContactUsWebPortletKeys.RBBOUKICONTACTUSWEB,
	service = ConfigurationAction.class
)

public class PortletSettingsPreferencesAction extends DefaultConfigurationAction {

    private volatile ContactUsConfiguration contactConfiguration;
    
    /*
     * This method is used to set the portlet preference
     *
     * @param portletConfig
     * @param actionRequest
     * @param actionResponse
     * @throws Exception
     */
    @Override
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse)
	    throws Exception {
	String toEmail = ParamUtil.getString(actionRequest, "toEmail");
	setPreference(actionRequest, "toEmail", toEmail);
	actionRequest.setAttribute("toEmail", toEmail);
	super.processAction(portletConfig, actionRequest, actionResponse);
    }

    /*
     * This method is used to include the configuration
     *
     * @param portletConfig
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception :
     */
    @Override
    public void include(
        PortletConfig portletConfig, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse) throws Exception {

        httpServletRequest.setAttribute(
        	ContactUsConfiguration.class.getName(),
        	contactConfiguration);

        super.include(portletConfig, httpServletRequest, httpServletResponse);
    }

    
    /**
     * This method is used to activate the proeprties
     *
     * @param properties : objects
     */
    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
	contactConfiguration = Configurable.createConfigurable(ContactUsConfiguration.class, properties);
    }

}
