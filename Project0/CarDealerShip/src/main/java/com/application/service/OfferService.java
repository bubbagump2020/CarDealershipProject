package com.application.service;

import java.util.List;

import com.application.connections.OfferConn;
import com.application.models.Offer;

public class OfferService {
	private OfferConn oc = new OfferConn();
	
	public Offer create(Offer t) {
		return oc.create(t);
	}
	
	public Offer findById(Integer i) {
		return oc.findById(i);
	}
	
	public List<Offer> findAll(){
		return oc.findAll();
	}
	
	public List<Offer> findByUsername(String s){
		return oc.findByUsername(s);
	}
	
	public Offer update(Offer t) {
		return oc.update(t);
	}
	
	public int deleteAllAccepted() {
		return oc.deleteAllAccepted();
	}
	
	public int deleteAllRejected(Integer i) {
		return oc.deleteAllRejected(i);
	}
}
