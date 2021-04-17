package com.rb.bouki.contact.us.web.preference;

import com.rb.bouki.contact.us.web.constants.RbBoukiContactUsWebPortletKeys;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = RbBoukiContactUsWebPortletKeys.CONFIGURATION_ID)
public interface ContactUsConfiguration {

	@Meta.AD(description = "Enter the 'to' email configuration", name = "toEmail", required = true)
	public String toEmail();
}
