package com.dasol.domain;

public class SearchCriteria extends Criteria {
	private String city;
	private String category;
	private String sort;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "SearchCriteria [city=" + city + ", category=" + category + ", sort=" + sort + "]";
	}

}
