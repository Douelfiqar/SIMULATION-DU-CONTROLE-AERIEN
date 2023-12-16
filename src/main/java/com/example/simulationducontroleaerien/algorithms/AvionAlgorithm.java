package com.example.simulationducontroleaerien.algorithms;

import java.util.Date;

import com.example.simulationducontroleaerien.entities.Avion;
import com.example.simulationducontroleaerien.entities.Vol;

public class AvionAlgorithm {
	private Avion avion;
	
	
	
	 public AvionAlgorithm(Avion avion) {
		super();
		this.avion = avion;
	}

	 
	public Avion getAvion() {
		return avion;
	}


	public void setAvion(Avion avion) {
		this.avion = avion;
	}
	
	public boolean isFree(Vol vol) {
		Date volStarteDate = vol.getHeurDepart();
		Date volArriveDate = vol.getHeurArriver();
	  if(volStarteDate.after(volArriveDate)) 
		return false;
	  for (Vol v : avion.getVol()) {
	        if (!(volArriveDate.before(v.getHeurDepart()) || volStarteDate.after(v.getHeurArriver()))) {
	            return false;
	        }
		}
	  return true;
	}
}
