package org.snomed.snowstorm.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snomed.snowstorm.core.data.domain.ReferenceSetMember;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * The Class ReferenceSetMemberSort.
 */
public class ReferenceSetMemberSort extends SortAbstract {

	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(ReferenceSetMemberSort.class);

	/** The Constant DEFAULT_SORT. */
	private static final Sort DEFAULT_SORT = Sort.sort(ReferenceSetMember.class).by(ReferenceSetMember::getMemberId)
			.ascending();

	/**
	 * The Enum of attributes for sorting.
	 */
	public enum ReferenceSetMemberSortField {

		/** The member id. */
		MEMBER_ID("memberId"),

		/** The referenced component id. */
		REFERENCED_COMPONENT_ID("referencedComponentId"),

		/** The effective time. */
		EFFECTIVE_TIME("effectiveTime"),

		/** The map target. */
		MAP_TARGET("mapTarget");

		/** The field name. */
		private final String fieldName;

		/**
		 * Instantiates a new reference set member sort field.
		 *
		 * @param fieldName the field name
		 */
		ReferenceSetMemberSortField(final String fieldName) {
			this.fieldName = fieldName;
		}

		/**
		 * Gets the field name.
		 *
		 * @return the field name
		 */
		public String getFieldName() {
			return this.fieldName;
		}
	}

	/**
	 * Sort.
	 *
	 * @param field     the field
	 * @param direction the direction
	 * @return the sort
	 * @throws Exception the exception
	 */
	public static Sort sort(final ReferenceSetMemberSortField field, final Direction direction) throws Exception {

		try {
			return Sort.by(direction, field.getFieldName());
		} catch (Exception e) {
			logger.error("Error: {}", e.getMessage(), e);
			throw e;
		}

	}

	/**
	 * Sort.
	 *
	 * @param field     the field
	 * @param direction the direction
	 * @return the sort
	 */
	public static Sort sort(final String field, final String direction) {

		validateSortParameters(field, direction);

		try {
			return sort(fromString(field), directionFromString(direction));
		} catch (final Exception e) {
			logger.error("Sorting by field {} in direction {}", ReferenceSetMemberSortField.MEMBER_ID, direction, e);
			return DEFAULT_SORT;
		}

	}

	/**
	 * From string.
	 *
	 * @param field the field
	 * @return the reference set member sort field
	 */
	private static ReferenceSetMemberSortField fromString(final String field) {

		for (final ReferenceSetMemberSortField sortField : ReferenceSetMemberSortField.values()) {
			if (sortField.getFieldName().equals(field)) {
				return sortField;
			}
		}
		throw new IllegalArgumentException(String
				.format("Invalid sort field: " + field + " Must be one of " + ReferenceSetMemberSortField.values()));
	}

	/**
	 * Validate sort field.
	 *
	 * @param field     the field
	 * @param direction the direction
	 */
	public static void validateSortParameters(final String field, final String direction) {

		try {
			Direction.valueOf(direction.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid sort direction: " + direction);
		}

		boolean isFieldValid = false;
		for (final ReferenceSetMemberSortField sortField : ReferenceSetMemberSortField.values()) {
			if (!isFieldValid && sortField.getFieldName().equals(field)) {
				isFieldValid = true;
				continue;
			}
		}
		if (!isFieldValid) {
			throw new IllegalArgumentException(
					"Invalid sort field: " + field + " Must be one of " + ReferenceSetMemberSortField.values());
		}

	}
}
