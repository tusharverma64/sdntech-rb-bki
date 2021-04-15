package com.rb.bouki.contact.us.web.portlet;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.rb.bouki.contact.us.model.BoukiContactUs;
import com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil;
import com.rb.bouki.contact.us.web.constants.RbBoukiContactUsWebPortletKeys;

import java.io.IOException;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
		"javax.portlet.display-name=RbBoukiContactUsWeb",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.init-param.config-template=/configuration.jsp",
		"javax.portlet.init-pa=/configuration.jsp",
		"javax.portlet.name=" + RbBoukiContactUsWebPortletKeys.RBBOUKICONTACTUSWEB,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class RbBoukiContactUsWebPortlet extends MVCPortlet {
    
    Log _log = LogFactoryUtil.getLog(RbBoukiContactUsWebPortlet.class.getName());

    private static final Configuration _configuration = ConfigurationFactoryUtil
	    .getConfiguration(RbBoukiContactUsWebPortlet.class.getClassLoader(), "portlet");
    
    
    @Override
    public void doEdit(RenderRequest renderRequest, RenderResponse renderResponse)
	    throws IOException, PortletException {
	
	
	super.doEdit(renderRequest, renderResponse);
    }

    public void submitContactUsFrom(ActionRequest request, ActionResponse response) {
	final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	final String name = ParamUtil.getString(request, "name");
	final String subject = ParamUtil.getString(request, "subject");
	final String email = ParamUtil.getString(request, "email");
	final String message = ParamUtil.getString(request, "message");
	try {
	    final Long id = CounterLocalServiceUtil.increment(BoukiContactUs.class.getName());
	    BoukiContactUs contactUsForm = BoukiContactUsLocalServiceUtil.createBoukiContactUs(id);
	    contactUsForm.setEmailAddress(email);
	    contactUsForm.setName(name);
	    contactUsForm.setSubject(subject);
	    contactUsForm.setMessage(message);
	    contactUsForm.setCreateDate(new Date());
	    contactUsForm.setGroupId(themeDisplay.getScopeGroupId());
	    contactUsForm.setCompanyId(themeDisplay.getCompanyId());
	    // Calling External APi with configured toEmail
	    String toEmail = StringPool.BLANK;
	    if (Validator.isNotNull(toEmail)) {
		// toEmail = conf.toEmail();
	    }
	    String apiResponse = getResponseFromApi(toEmail, name, subject, email, message);
	    contactUsForm.setApiResponse(apiResponse);

	    BoukiContactUsLocalServiceUtil.addBoukiContactUs(contactUsForm);
	    response.getRenderParameters().setValue("mvcPath", "/success.jsp");
	    SessionMessages.add(request,
		    PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
	} catch (SystemException e) {
	    _log.error(e.getMessage());
	    SessionMessages.add(request,
		    PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
	}

    }
    
    
    public void submitContactUsPreference(ActionRequest request, ActionResponse response) {
  	final String toEmail = ParamUtil.getString(request, "toEmail");
  	try {
  	   
  	    response.getRenderParameters().setValue("mvcPath", "/configuration.jsp");
  	    SessionMessages.add(request,
  		    PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_SUCCESS_MESSAGE);
  	} catch (SystemException e) {
  	    _log.error(e.getMessage());
  	    SessionMessages.add(request,
  		    PortalUtil.getPortletId(request) + SessionMessages.KEY_SUFFIX_HIDE_DEFAULT_ERROR_MESSAGE);
  	}

      }

	private String getResponseFromApi(String toEmail, String name, String subject, String email, String message) {
		String apiReponse = StringPool.BLANK;
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