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

package com.rb.bouki.register.interest.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.rb.bouki.register.interest.model.BoukiRegisterInterest;
import com.rb.bouki.register.interest.service.base.BoukiRegisterInterestLocalServiceBaseImpl;

import java.util.Date;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the bouki register interest local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.rb.bouki.register.interest.service.BoukiRegisterInterestLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BoukiRegisterInterestLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.rb.bouki.register.interest.model.BoukiRegisterInterest",
	service = AopService.class
)
public class BoukiRegisterInterestLocalServiceImpl
	extends BoukiRegisterInterestLocalServiceBaseImpl {


    /**
     * This method is used to create regiter Interest
     *
     * @param email
     * @param phoneNumber
     * @param keepInform
     * @param countryCode
     * @param companyId
     * @param groupId
     * @return :  BoukiRegisterInterest
     */
    public BoukiRegisterInterest createRegisterInterest(String email, String phoneNumber, Boolean keepInform,
	    String countryCode, Long companyId, Long groupId) {
	final Long id = CounterLocalServiceUtil.increment(BoukiRegisterInterest.class.getName());
	BoukiRegisterInterest registerInterest = boukiRegisterInterestLocalService.createBoukiRegisterInterest(id);
	registerInterest.setEmailAddress(email);
	registerInterest.setPhoneNo(phoneNumber);
	registerInterest.setCreateDate(new Date());
	registerInterest.setKeepInform(keepInform);
	registerInterest.setCountryCode(countryCode);
	registerInterest.setCompanyId(companyId);
	registerInterest.setGroupId(groupId);

	return boukiRegisterInterestLocalService.addBoukiRegisterInterest(registerInterest);
    }
    
    
    
}