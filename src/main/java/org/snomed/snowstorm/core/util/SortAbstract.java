package org.snomed.snowstorm.core.util;

import org.springframework.data.domain.Sort.Direction;

public class SortAbstract {

	/**
	 * From string.
	 *
	 * @param value the value
	 * @return the sort order
	 */
	public static Direction directionFromString(final String value) {
		if (value == null) {
			throw new IllegalArgumentException("Sort order cannot be null");
		}
		if ("asc".equalsIgnoreCase(value)) {
			return Direction.ASC;
		} else if ("desc".equalsIgnoreCase(value)) {
			return Direction.DESC;
		} else {
			throw new IllegalArgumentException("Sort order must be either 'asc' or 'desc'");
		}
	}

}
