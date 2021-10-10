package br.com.builders.apiclients.specification;

import java.lang.reflect.Field;

import org.springframework.data.jpa.domain.Specification;

import br.com.builders.apiclients.model.Address;
import br.com.builders.apiclients.model.Client;
import br.com.builders.apiclients.model.Gender;

public class ClientSpecification {

	public static Specification<Client> findByAttribute(String attribute, String value) {

		String attributeLower = attribute.toLowerCase();
		String valueLower = value.toLowerCase();
		Field fieldList[] = Address.class.getDeclaredFields();

		if (attributeLower.equals("gender")) {
			switch (valueLower) {
			case "male":
				return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(attributeLower),
						Gender.MALE);
			case "female":
				return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(attributeLower),
						Gender.FEMALE);
			case "other":
				return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(attributeLower),
						Gender.OTHER);
			default:
				break;
			}
		}

		for (int i = 0; i < fieldList.length; i++) {
			Field fld = fieldList[i];
			if (fld.getName().equals(attributeLower)) {
				return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
						.like(criteriaBuilder.lower(root.get("address").get(attributeLower)), "%" + valueLower + "%");
			}
		}

		return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
				.like(criteriaBuilder.lower(root.get(attributeLower)), "%" + valueLower + "%");
	}

}
