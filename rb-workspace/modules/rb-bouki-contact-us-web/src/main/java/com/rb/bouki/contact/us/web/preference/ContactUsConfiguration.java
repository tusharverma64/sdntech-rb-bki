package com.rb.bouki.contact.us.web.preference;

import com.rb.bouki.contact.us.web.constants.RbBoukiContactUsWebPortletKeys;

import aQute.bnd.annotation.metatype.Meta;

/**
 * The purpose of this class is for Configuration variable
 *
 *
 *@author Liferay
 * 
 */
@Meta.OCD(id = RbBoukiContactUsWebPortletKeys.CONFIGURATION_ID)
public interface ContactUsConfiguration {

    @Meta.AD(description = "Enter the 'to' email configuration", name = "toEmail", required = false)
    public String toEmail();
}
