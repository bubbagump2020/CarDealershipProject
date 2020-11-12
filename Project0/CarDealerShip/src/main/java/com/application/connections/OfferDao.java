package com.application.connections;

import java.util.List;

import com.application.models.Offer;

public interface OfferDao<T, I> extends MasterDao<T, I> {
	public List<Offer> findByUsername(String s);
	public Offer findByUsernameAndVehicleId(String s, Integer i);
	public int deleteAllAccepted();
	public int deleteAllRejected(Integer i);
}
