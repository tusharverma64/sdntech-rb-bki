package com.rb.bouki.register.interest.admin.application.list;

import com.rb.bouki.register.interest.admin.constants.RbBoukiRegisterInterestAdminPanelCategoryKeys;
import com.rb.bouki.register.interest.admin.constants.RbBoukiRegisterInterestAdminPortletKeys;

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
		"panel.category.key=" + RbBoukiRegisterInterestAdminPanelCategoryKeys.CONTROL_PANEL_CATEGORY
	},
	service = PanelApp.class
)
public class RbBoukiRegisterInterestAdminPanelApp extends BasePanelApp {

	@Override
	public String getPortletId() {
		return RbBoukiRegisterInterestAdminPortletKeys.RBBOUKIREGISTERINTERESTADMIN;
	}

	@Override
	@Reference(
		target = "(javax.portlet.name=" + RbBoukiRegisterInterestAdminPortletKeys.RBBOUKIREGISTERINTERESTADMIN + ")",
		unbind = "-"
	)
	public void setPortlet(Portlet portlet) {
		super.setPortlet(portlet);
	}

}