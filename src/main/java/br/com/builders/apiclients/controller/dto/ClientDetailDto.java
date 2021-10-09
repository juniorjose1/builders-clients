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

}
