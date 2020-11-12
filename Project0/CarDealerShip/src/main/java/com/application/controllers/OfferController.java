package com.application.controllers;

import java.util.List;

import com.application.models.Offer;
import com.application.service.OfferService;

public class OfferController {
	private OfferService os = new OfferService();
	
	public Offer create(Offer t) {
		return os.create(t);
	}
	
	public Offer findById(Integer i) {
		return os.findById(i);
	}
	
	public List<Offer> findAll(){
		return os.findAll();
	}
	
	public List<Offer> findByUsername(String s){
		return os.findByUsername(s);
	}
	
	public Offer update(Offer t) {
		return os.update(t);
	}
	
	public int deleteAllAccepted() {
		return os.deleteAllAccepted();
	}
	
	public int deleteAllRejected(Integer i) {
		return os.deleteAllRejected(i);
	}
}
