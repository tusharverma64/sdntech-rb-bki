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

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.rb.bouki.contact.us.model.BoukiContactUs;
import com.rb.bouki.contact.us.service.base.BoukiContactUsLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

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


    private static final String CREATEDATE = "createDate";

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
    public BoukiContactUs createContactUs(String email, String subject, String name, String message, Long companyId,
	    Long groupId, String apiResponse) {
	final Long id = counterLocalService.increment(BoukiContactUs.class.getName());
	BoukiContactUs contactUsForm = boukiContactUsPersistence.create(id);
	contactUsForm.setEmailAddress(email);
	contactUsForm.setName(name);
	contactUsForm.setSubject(subject);
	contactUsForm.setMessage(message);
	contactUsForm.setCreateDate(new Date());
	contactUsForm.setGroupId(groupId);
	contactUsForm.setCompanyId(companyId);
	contactUsForm.setApiResponse(apiResponse);

	return boukiContactUsPersistence.update(contactUsForm);
    }

    
    /**
     * This method is used to get the sql result by Dynamic Query
     *
     * @param searchText
     * @param fromDate
     * @param toDate
     * @param locale
     * @return
     */
    public List<BoukiContactUs> getQueryResult(String searchText, String fromDate, String toDate, Locale locale) {
	DynamicQuery query = DynamicQueryFactoryUtil.forClass(BoukiContactUs.class, getClassLoader());
	if (Validator.isNotNull(fromDate) && Validator.isNotNull(toDate)) {
	    query.add(RestrictionsFactoryUtil.sqlRestriction(CREATEDATE + " >= '" + fromDate + "'"));
	    query.add(RestrictionsFactoryUtil.sqlRestriction(CREATEDATE + " <= '" + toDate + "'"));
	}
	if (Validator.isNotNull(searchText)) {
	    Criterion criterion = null;
	    criterion = RestrictionsFactoryUtil.like("emailAddress", "%" + searchText + "%");
	    criterion = RestrictionsFactoryUtil.or(criterion,
		    RestrictionsFactoryUtil.eq("name", "%" + searchText + "%"));
	    query.add(criterion);
	}
	Order order = OrderFactoryUtil.desc(CREATEDATE);
	query.addOrder(order);

	return boukiContactUsPersistence.findWithDynamicQuery(query);

    }

}