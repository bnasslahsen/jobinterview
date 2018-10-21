package com.badrconsulting.jobinterview.controller.util;

public enum SupportedLanguages {

	FRENCH("fr"), ENGLISH("en");

	private String abbreviation;

	public String getAbbreviation() {
		return this.abbreviation;
	}

	SupportedLanguages(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public static boolean contains(String test) {
		for (SupportedLanguages c : SupportedLanguages.values()) {
			if (c.abbreviation.equals(test)) {
				return true;
			}
		}
		return false;
	}

}
