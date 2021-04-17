package com.rb.bouki.contact.us.admin.application.list;

import com.rb.bouki.contact.us.admin.constants.RbBoukiContactUsAdminPanelCategoryKeys;
import com.rb.bouki.contact.us.admin.constants.RbBoukiContactUsAdminPortletKeys;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author tushar
 */
@Component(
	immediate = true,
	property = {
		"panel.app.order:Integer=100",
		"panel.category.key=" + RbBoukiContactUsAdminPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class RbBoukiContactUsAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return RbBoukiContactUsAdminPortletKeys.RBBOUKICONTACTUSADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + RbBoukiContactUsAdminPortletKeys.RBBOUKICONTACTUSADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}