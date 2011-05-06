package com.xinlv.domain;

import java.util.Date;

import org.springframework.data.graph.annotation.EndNode;
import org.springframework.data.graph.annotation.RelationshipEntity;
import org.springframework.data.graph.annotation.StartNode;

@RelationshipEntity
public class ConcernTo {
	@StartNode
	User user;
	@EndNode
	User concerned;
	
	Date requestDate;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getConcerned() {
		return concerned;
	}

	public void setConcerned(User concerned) {
		this.concerned = concerned;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	
}
