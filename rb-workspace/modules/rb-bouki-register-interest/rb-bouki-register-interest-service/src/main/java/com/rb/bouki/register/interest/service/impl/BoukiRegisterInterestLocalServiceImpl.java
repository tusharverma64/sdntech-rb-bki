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
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.rb.bouki.register.interest.model.BoukiRegisterInterest;
import com.rb.bouki.register.interest.service.base.BoukiRegisterInterestLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    private static final String CREATEDATE = "createDate";

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
    
    
    /**
     * This method is used to get the sql dynamic query
     *
     * @param searchText
     * @param fromDate
     * @param toDate
     * @param locale
     * @return
     */
    public List<BoukiRegisterInterest> getQueryResult(String searchText, String fromDate, String toDate,
	    Locale locale) {
	DynamicQuery query = boukiRegisterInterestLocalService.dynamicQuery();
	if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
	    query.add(RestrictionsFactoryUtil.sqlRestriction(CREATEDATE + " >= '" + fromDate + "'"));
	    query.add(RestrictionsFactoryUtil.sqlRestriction(CREATEDATE + " <= '" + toDate + "'"));
	}
	if (Validator.isNotNull(searchText)) {
	    Criterion criterion = null;
	    criterion = RestrictionsFactoryUtil.like("emailAddress", "%" + searchText + "%");
	    query.add(criterion);
	}
	Order order = OrderFactoryUtil.desc(CREATEDATE);
	query.addOrder(order);

	return boukiRegisterInterestLocalService.dynamicQuery(query);

    }
    
    
    
}