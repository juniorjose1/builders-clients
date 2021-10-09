package br.com.builders.apiclients.util;

import java.time.LocalDate;
import java.time.Period;

public class ClientUtils {
	
	private ClientUtils() {
		throw new AssertionError();
	}
	
	public static Integer convertToAge(LocalDate birthDate) {
		Period period = Period.between(birthDate, LocalDate.now());
		return period.getYears();
	}

}
