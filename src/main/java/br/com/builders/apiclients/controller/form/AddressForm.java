package br.com.builders.apiclients.controller.form;

import br.com.builders.apiclients.model.Address;

public class AddressForm {

	private String city;
	private String street;
	private Integer number;
	private String district;
	private String country;
	private String cep;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public Address toAddress() {
		return new Address(this.city, this.street, this.number, this.district, this.cep, this.country);
	}

}
