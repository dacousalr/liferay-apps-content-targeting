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

package com.liferay.contenttargeting.trackingactions.form;

import com.liferay.contenttargeting.api.model.BaseTrackingAction;
import com.liferay.contenttargeting.api.model.TrackingAction;
import com.liferay.contenttargeting.model.TrackingActionInstance;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Eduardo Garcia
 */
@Component(immediate = true, service = TrackingAction.class)
public class FormTrackingAction extends BaseTrackingAction {

	@Override
	public List<String> getEventTypes() {
		return ListUtil.fromArray(_EVENT_TYPES);
	}

	@Override
	public String getIcon() {
		return "icon-list-alt";
	}

	@Override
	public String getSummary(
		TrackingActionInstance trackingActionInstance, Locale locale) {

		String summary = LanguageUtil.format(
			locale, "tracking-action-x-in-form-x",
			new Object[] {
				trackingActionInstance.getEventType(),
				trackingActionInstance.getElementId()
			});

		return summary;
	}

	@Override
	protected void populateContext(
		TrackingActionInstance trackingActionInstance,
		Map<String, Object> context) {

		String elementId = StringPool.BLANK;
		String eventType = StringPool.BLANK;

		if (trackingActionInstance != null) {
			elementId = trackingActionInstance.getElementId();
			eventType = trackingActionInstance.getEventType();
		}

		context.put("elementId", elementId);
		context.put("eventType", eventType);
		context.put("eventTypes", getEventTypes());
	}

	private static final String[] _EVENT_TYPES = {"view", "interact", "submit"};

}