package br.com.builders.apiclients.controller.dto;

import br.com.builders.apiclients.model.Address;

public class AddressDetailDto {

	private String city;
	private String street;
	private Integer number;
	private String district;
	private String country;
	private String cep;

	public AddressDetailDto(Address address) {
		this.city = address.getCity();
		this.street = address.getStreet();
		this.number = address.getNumber();
		this.district = address.getDistrict();
		this.country = address.getCountry();
		this.cep = address.getCep();
	}

	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public Integer getNumber() {
		return number;
	}

	public String getDistrict() {
		return district;
	}

	public String getCountry() {
		return country;
	}

	public String getCep() {
		return cep;
	}

}
