package br.com.builders.apiclients.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClientUtilsTest {

	@Test
	@DisplayName("Should calculate age according to date of birth")
	void testAgeDateBirth() {
		LocalDate birthDate = LocalDate.of(1995, 05, 16);
		Integer age = ClientUtils.convertToAge(birthDate);
		assertEquals(26, age);
	}

}
