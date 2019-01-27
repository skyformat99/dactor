/*
 * @(#)PlaceholderActor.java	1.0 2014年5月13日 下午3:43:18
 *
 * Copyright 2004-2010 Client Server International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package cn.ymotel.dactor.action;

import cn.ymotel.dactor.message.Message;
import cn.ymotel.dactor.workflow.ActorProcessStructure;
import cn.ymotel.dactor.workflow.WorkFlowProcess;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Deque;

/**
 * {type specification, must edit}
 *
 * @author  Administrator {must edit, use true name}
 * <p>
 *   Created on 2014年5月13日
 *   Modification history	
 *   {add your history}
 * </p>
 * <p>
 *
 *
 * </p>
 * @version 1.0
 * @since 1.0
 */
public class PlaceholderActor implements Actor, ApplicationContextAware {

	/* (non-Javadoc)
	 * @see com.ymotel.util.actor.Actor#HandleMessage(com.ymotel.util.actor.Message)
	 */
	@Override
	public Object HandleMessage(Message message) throws Exception {
		Deque<ActorProcessStructure>  deque=message.getControlMessage().getActorsStack();
		Deque<ActorProcessStructure>  downque=message.getControlMessage().getDownStack();
		WorkFlowProcess.processGetToBeanId(message.getControlMessage(), message, appcontext);

		try {
			downque.push(deque.pop());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	private ApplicationContext appcontext=null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.appcontext=applicationContext;
	}
}