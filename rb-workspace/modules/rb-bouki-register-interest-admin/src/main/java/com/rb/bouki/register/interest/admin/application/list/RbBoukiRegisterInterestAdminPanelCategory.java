package com.rb.bouki.register.interest.admin.application.list;

import com.rb.bouki.register.interest.admin.constants.RbBoukiRegisterInterestAdminPanelCategoryKeys;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author tushar
 */
@Component(
	immediate = true,
	property = {
		"panel.category.key=" + PanelCategoryKeys.HIDDEN,
		"panel.category.order:Integer=100"
	},
	service = PanelCategory.class
)
public class RbBoukiRegisterInterestAdminPanelCategory extends BasePanelCategory {

	@Override
	public String getKey() {
		return RbBoukiRegisterInterestAdminPanelCategoryKeys.CONTROL_PANEL_CATEGORY;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "category.custom.label");
	}

}