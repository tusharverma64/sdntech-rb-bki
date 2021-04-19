package com.rb.bouki.contact.us.web.portlet;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.rb.bouki.contact.us.model.BoukiContactUs;
import com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil;
import com.rb.bouki.contact.us.web.constants.RbBoukiContactUsWebPortletKeys;
import com.rb.bouki.contact.us.web.preference.ContactUsConfiguration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;


/**
 * The purpose of this class is to load the portlet and perform actions
 *
 * Accessibility : This class will get access from the portlet level
 *
 *
 *@author Liferay
 * 
 */
@Component(
	configurationPid =
		 RbBoukiContactUsWebPortletKeys.CONFIGURATION_ID,
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=Bouki",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=RbBoukiContactUsWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RbBoukiContactUsWebPortletKeys.RBBOUKICONTACTUSWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RbBoukiContactUsWebPortlet extends MVCPortlet {
    
    
    Log _log = LogFactoryUtil.getLog(RbBoukiContactUsWebPortlet.class.getName());
    
    private volatile ContactUsConfiguration contactUsConfiguration;

    private static final Configuration _configuration = ConfigurationFactoryUtil
	    .getConfiguration(RbBoukiContactUsWebPortlet.class.getClassLoader(), "portlet");
    /*
     * This method is used to render the form
     *
     * @param renderRequest
     * @param renderResponse
     * @throws IOException
     * @throws PortletException :
     */

    @Override
    public void render(RenderRequest renderRequest, RenderResponse renderResponse)
	    throws IOException, PortletException {
	renderRequest.setAttribute(ContactUsConfiguration.class.getName(), contactUsConfiguration);
	super.render(renderRequest, renderResponse);
    }
    
    /**
     * This method is used to activate the preferences
     *
     * @param properties : 
     */
    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
	contactUsConfiguration = ConfigurableUtil.createConfigurable(
		ContactUsConfiguration.class, properties);
    }

    /*
     * This method is used to handle ajax calling
     *
     * @param resourceRequest
     * @param resourceResponse
     * @throws IOException
     * @throws PortletException 
     */
    @Override
    public void serveResource(ResourceRequest request, ResourceResponse resourceResponse)
	    throws IOException, PortletException {
	
	_log.debug("This is serve resource method....");

	final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	final String name = ParamUtil.getString(request, "name");
	final String subject = ParamUtil.getString(request, "subject");
	final String fromEmail = ParamUtil.getString(request, "email");
	final String toEmail = ParamUtil.getString(request, "toEmail");
	final String message = ParamUtil.getString(request, "message");
	try {

	    String apiResponse = getResponseFromApi(toEmail, name, subject, fromEmail, message);

	    if (Validator.isNotNull(name) && Validator.isNotNull(subject) && Validator.isNotNull(fromEmail)
		    && Validator.isNotNull(toEmail) && Validator.isNotNull(message)
		    && Validator.isNotNull(apiResponse)) {
		BoukiContactUs boukiContactUs = BoukiContactUsLocalServiceUtil.createContactUs(toEmail, subject, name,
			message, themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), apiResponse);

		if (Validator.isNotNull(boukiContactUs)) {
		    JSONObject obj = JSONFactoryUtil.createJSONObject();
		    obj.put("message", "Data Received Successfully");
		    PrintWriter out = resourceResponse.getWriter();
		    out.print(obj.toString());
		    include("/jsp/loadResult.jsp", request, resourceResponse);

		}
	    }

	} catch (SystemException e) {
	    _log.error(e.getMessage());
	}

	super.serveResource(request, resourceResponse);
    }
	/**
	 * This method is used to {Please explain the usability of this method}
	 *
	 * @param toEmail
	 * @param name
	 * @param subject
	 * @param email
	 * @param message
	 * @return : {Please explain the usage of all the arguments}
	 */
	private String getResponseFromApi(String toEmail, String name, String subject, String email, String message) {
		String apiReponse = "true";
//		final HttpClient client = HttpClientBuilder.create().build();
//		try {
//			HttpUriRequest post = new HttpGet(_configuration.get("api.url"));
//
//			Base64 b = new Base64();
//			String encoding = b.encode(new String("test@liferay.com:test").getBytes());
//			post.setHeader("Authorization", "Basic " + encoding);
//
//			HttpParams httpClientParams = new BasicHttpParams();
//			httpClientParams.setParameter("name", name);
//			httpClientParams.setParameter("subject", subject);
//			httpClientParams.setParameter("from", email);
//			httpClientParams.setParameter("message", message);
//			httpClientParams.setParameter("to", toEmail);
//
//			post.setParams(httpClientParams);
//
//			HttpResponse resp = client.execute(post);
//			if (resp.getStatusLine().getStatusCode() == 200) {
//				resp.getEntity().writeTo(System.out);
//			}
//
//		} catch (Exception e) {
//			_log.error(e.getMessage());
//		}
		return apiReponse;
	}



}