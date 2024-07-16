package org.snomed.snowstorm.core.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * The Class ReferenceSetMemberSort.
 */
public class ReferenceSetMemberSort extends SortAbstract {

	/** The Constant DEFAULT_SORT_FIELD. */
	public static final ReferenceSetMemberSortField DEFAULT_SORT_FIELD = ReferenceSetMemberSortField.MEMBER_ID;

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
	 */
	public static Sort sort(final String field, final Direction direction) {

		ReferenceSetMemberSortField sortField;

		try {
			sortField = ReferenceSetMemberSortField.valueOf(field);
		} catch (IllegalArgumentException e) {
			sortField = DEFAULT_SORT_FIELD;
		}

		return Sort.by(direction, sortField.getFieldName());

	}

	/**
	 * Sort.
	 *
	 * @param field     the field
	 * @param direction the direction
	 * @return the sort
	 */
	public static Sort sort(final String field, final String direction) {
		return sort(field, fromString(direction));
	}
}
