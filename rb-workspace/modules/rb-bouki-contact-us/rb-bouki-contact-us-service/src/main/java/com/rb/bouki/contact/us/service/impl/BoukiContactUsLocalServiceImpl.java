/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.rb.bouki.contact.us.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.rb.bouki.contact.us.model.BoukiContactUs;
import com.rb.bouki.contact.us.service.BoukiContactUsLocalServiceUtil;
import com.rb.bouki.contact.us.service.base.BoukiContactUsLocalServiceBaseImpl;

import java.util.Date;

import org.osgi.service.component.annotations.Component;


/**
 * The implementation of the bouki contact us local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.rb.bouki.contact.us.service.BoukiContactUsLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BoukiContactUsLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.rb.bouki.contact.us.model.BoukiContactUs",
	service = AopService.class
)
public class BoukiContactUsLocalServiceImpl
	extends BoukiContactUsLocalServiceBaseImpl {


 
    /**
     * This method is used to Create the contact us
     *
     * @param email
     * @param subject
     * @param name
     * @param message
     * @param companyId
     * @param groupId
     * @param apiResponse
     * @return : BoukiContactUs Object
     */
    public BoukiContactUs createContactUs(String email, String subject, String name, String message,
	    Long companyId, Long groupId, String apiResponse) {
	final Long id = CounterLocalServiceUtil.increment(BoukiContactUs.class.getName());
	BoukiContactUs contactUsForm = BoukiContactUsLocalServiceUtil.createBoukiContactUs(id);
	contactUsForm.setEmailAddress(email);
	contactUsForm.setName(name);
	contactUsForm.setSubject(subject);
	contactUsForm.setMessage(message);
	contactUsForm.setCreateDate(new Date());
	contactUsForm.setGroupId(groupId);
	contactUsForm.setCompanyId(companyId);
	contactUsForm.setApiResponse(apiResponse);

	return BoukiContactUsLocalServiceUtil.addBoukiContactUs(contactUsForm);
    }

    
}