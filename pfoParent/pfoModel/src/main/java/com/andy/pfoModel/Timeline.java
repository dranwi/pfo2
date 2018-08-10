package com.andy.pfoModel;



import java.util.Map;
import java.util.Set;

//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;

//@Entity
//@Table(name="TIMELINE")
public class Timeline {
	
	//@Id
	//@GeneratedValue
	//@Column(name="TIMELINE_ID")	
	long id;
	
	
	Map<String,Double> map;
	
	
	
	public void TimeLine() {		
		//map = Collections.synchronizedMap(new HashMap<Date,Double>());
	}
	
	public void put(String date, Double value) {
		map.put(date, value);
	}
	
	public Double get (String date) {
		return map.get(date);
	}
	
	public Set<String> keySet() {
		return map.keySet();
	}
	

}
