package com.rb.bouki.contact.us.admin.portlet;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.rb.bouki.contact.us.admin.constants.RbBoukiContactUsAdminPortletKeys;
import com.rb.bouki.contact.us.model.BoukiContactUs;
import com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Liferay
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
		"javax.portlet.display-name=" + RbBoukiContactUsAdminPortletKeys.RBBOUKICONTACTUSADMINNAME,
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + RbBoukiContactUsAdminPortletKeys.RBBOUKICONTACTUSADMIN,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",

	},
	service = Portlet.class
)
public class RbBoukiContactUsAdminPortlet extends MVCPortlet {

    Log logger = LogFactoryUtil.getLog(RbBoukiContactUsAdminPortlet.class.getName());

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
	if (logger.isDebugEnabled()) {
	    logger.debug("SearchText :" + searchText);
	    logger.debug("fromDate :" + fromDate);
	    logger.debug("toDate :" + toDate);
	}
	List<BoukiContactUs> contactUsList = Collections.emptyList();
	try {
	    if (Validator.isNotNull(searchText) || (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate))) {
		contactUsList = BoukiContactUsLocalServiceUtil.getQueryResult(searchText, fromDate, toDate,
			themeDisplay.getLocale());
	    } else {
		contactUsList = BoukiContactUsLocalServiceUtil.getBoukiContactUses(QueryUtil.ALL_POS,
			QueryUtil.ALL_POS);
	    }
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	renderRequest.setAttribute(CONTACTUSLIST, contactUsList);
	renderRequest.setAttribute(FROMDATE, fromDate);
	renderRequest.setAttribute(TODATE, toDate);
	renderRequest.setAttribute(SEARCH_TEXT, searchText);
	renderRequest.setAttribute("dropDownItems", getActionDropdownItems());

	super.doView(renderRequest, renderResponse);
    }
    
    /*
     * This method is used to serve resource
     *
     * @param resourceRequest
     * @param resourceResponse
     * @throws IOException
     * @throws PortletException 
     */
    @Override
    public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
            throws IOException, PortletException {
	ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
	String searchText = ParamUtil.getString(resourceRequest, SEARCH_TEXT);
	String fromDate = ParamUtil.getString(resourceRequest, FROMDATE);
	String toDate = ParamUtil.getString(resourceRequest, TODATE);

	List<BoukiContactUs> contactUsList = Collections.emptyList();
	try {
	    contactUsList = BoukiContactUsLocalServiceUtil.getQueryResult(searchText, fromDate, toDate,
		    themeDisplay.getLocale());
	} catch (Exception e) {
	    logger.error(e.getMessage());
	}

	resourceRequest.setAttribute(CONTACTUSLIST, contactUsList);
	resourceRequest.setAttribute(FROMDATE, fromDate);
	resourceRequest.setAttribute(TODATE, toDate);
	resourceRequest.setAttribute(SEARCH_TEXT, searchText);
	
	
	

	try {

	    File file = File.createTempFile("contactUsReports" + new Date().getTime(), ".xlsx");

	    if (logger.isDebugEnabled()) {
		logger.debug("Temporary File absolute Path::" + file.getAbsoluteFile());
	    }
//	    XSSFWorkbook workbook = new XSSFWorkbook();
//	    XSSFSheet sheet = workbook.createSheet("Report");
//
//	    int rowCount = 0;
//	    int columnCount = 0;
//	    for (BoukiContactUs contactUs : contactUsList) {
//		Row row = sheet.createRow(++rowCount);
//		Cell cell = row.createCell(++columnCount);
//		cell.setCellValue(contactUs.getName());
//		cell.setCellValue(contactUs.getSubject());
//		cell.setCellValue(contactUs.getEmailAddress());
//		cell.setCellValue(contactUs.getMessage());
//		cell.setCellValue(contactUs.getApiResponse());
//		cell.setCellValue(contactUs.getEmailAddress());
//
//	    }

//	    try (FileOutputStream outputStream = new FileOutputStream(
//		    "contactUsReports" + new Date().getTime() + ".xlsx")) {
//		workbook.write(outputStream);
//	    }
	} catch (Exception e) {
	    logger.error("Error generating the excel File" + e.getMessage());

	}
	
	// generating out put
	JSONObject obj = JSONFactoryUtil.createJSONObject();
	obj.put(RbBoukiContactUsAdminPortletKeys.MESSAGE,
		LanguageUtil.get(themeDisplay.getLocale(), RbBoukiContactUsAdminPortletKeys.DATA_SAVED_SUCCESSFULLY));
	PrintWriter out = resourceResponse.getWriter();
	out.print(obj.toString());

	super.serveResource(resourceRequest, resourceResponse);
    }

    /**
     * This method is used to 
     *
     * @return 
     */
    @SuppressWarnings("unchecked")
    public List<DropdownItem> getActionDropdownItems() {
	return DropdownItemList.of(() -> {
	    DropdownItem dropdownItem = new DropdownItem();

	    dropdownItem.putData("action", "viewMessagePopup");
	    dropdownItem.setIcon("times-circle");
	    dropdownItem.setLabel("view");
	    dropdownItem.setQuickAction(true);
	    dropdownItem.setHref("#1");
	    return dropdownItem;
	});
    }
}