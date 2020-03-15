package br.com.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SwapiResultDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String count;
	private String next;
	private String previous;
	private List<SwapiPlanetDto> results = new ArrayList<>();
	
	private SwapiResultDto() {
		
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public List<SwapiPlanetDto> getResults() {
		return results;
	}

	public void setResults(List<SwapiPlanetDto> results) {
		this.results = results;
	}
	
}
