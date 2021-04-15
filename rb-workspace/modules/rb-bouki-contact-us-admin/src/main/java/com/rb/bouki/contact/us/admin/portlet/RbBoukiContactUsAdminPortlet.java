package com.rb.bouki.contact.us.admin.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.rb.bouki.contact.us.admin.constants.RbBoukiContactUsAdminPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author tushar
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
    
    
//    public void exportContactUsResults_EXCEL(ActionRequest actionRequest, ActionResponse actionResponse)
//	    throws IOException {
//
//	String searchText = ParamUtil.getString(actionRequest, "search");
//	String fromDate = ParamUtil.getString(actionRequest, "fromDate");
//	String toDate = ParamUtil.getString(actionRequest, "toDate");
//	try {
//	    XSSFWorkbook workbook = new XSSFWorkbook();
//	    XSSFSheet sheet = workbook.createSheet("Report");
//
//	    DynamicQuery query = BoukiContactUsLocalServiceUtil.dynamicQuery();
//	    if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
//		query.add(PropertyFactoryUtil.forName("createDate").ge(fromDate));
//		query.add(PropertyFactoryUtil.forName("createDate").le(toDate));
//	    }
//	    if (Validator.isNotNull(searchText)) {
//		query.add(PropertyFactoryUtil.forName("searchText").eq(searchText));
//	    }
//
//	    List<BoukiContactUs> contactUsList = BoukiContactUsLocalServiceUtil.dynamicQuery(query);
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
//
//	    try (FileOutputStream outputStream = new FileOutputStream(
//		    "contactUsReports" + new Date().getTime() + ".xlsx")) {
//		workbook.write(outputStream);
//	    }
//	} catch (Exception e) {
//
//	}
//    }
}