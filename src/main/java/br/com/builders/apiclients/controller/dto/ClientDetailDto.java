package br.com.builders.apiclients.controller.dto;

import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;
import br.com.builders.apiclients.util.ClientUtils;

public class ClientDetailDto {

	private Long id;
	private String name;
	private String cpf;
	private Integer age;
	private Gender gender;
	private AddressDetailDto address;

	public ClientDetailDto(Client client) {
		this.id = client.getId();
		this.name = client.getName();
		this.cpf = client.getCpf();
		this.age = ClientUtils.convertToAge(client.getBirthDate());
		this.gender = client.getGender();
		this.address = new AddressDetailDto(client.getAddress());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Integer getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public AddressDetailDto getAddress() {
		return address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDetailDto other = (ClientDetailDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}
