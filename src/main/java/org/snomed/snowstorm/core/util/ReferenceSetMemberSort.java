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

	/** The Constant DEFAULT_SORT_FIELD. */
	public static final ReferenceSetMemberSortField DEFAULT_SORT_FIELD = ReferenceSetMemberSortField.MEMBER_ID;

	private final static Logger logger = LoggerFactory.getLogger(ReferenceSetMemberSort.class);
	
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
		MAP_TARGET("mapTarget"),

		/** The referenced component pt term. */
		REFERENCED_COMPONENT_PT_TERM("referencedComponent.pt.term");

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
	public static Sort sort(final ReferenceSetMemberSortField field, final Direction direction) {
		
		logger.debug("B. Sorting by field {} in direction {}", field, direction);
		
		switch (field) {
		case REFERENCED_COMPONENT_ID:
			return Sort.sort(ReferenceSetMember.class).by(ReferenceSetMember::getReferencedComponentId).by(direction);
		case EFFECTIVE_TIME:
			return Sort.sort(ReferenceSetMember.class).by(ReferenceSetMember::getEffectiveTime).by(direction);
		case MAP_TARGET:
			return Sort.sort(ReferenceSetMember.class).by(ReferenceSetMember::getMapTargetCoding).by(direction);
		default:
			return Sort.sort(ReferenceSetMember.class).by(ReferenceSetMember::getMemberId).by(direction);
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
		validateSortField(field, direction);

		ReferenceSetMemberSortField sortField = null;

		switch (ReferenceSetMemberSortField.valueOf(field)) {
		case REFERENCED_COMPONENT_ID:
			sortField = ReferenceSetMemberSortField.REFERENCED_COMPONENT_ID;
			break;
		case EFFECTIVE_TIME:
			sortField = ReferenceSetMemberSortField.EFFECTIVE_TIME;
			break;
		case MAP_TARGET:
			sortField = ReferenceSetMemberSortField.MAP_TARGET;
			break;
		case REFERENCED_COMPONENT_PT_TERM:
			sortField = ReferenceSetMemberSortField.REFERENCED_COMPONENT_PT_TERM;
			break;
		default:
			sortField = ReferenceSetMemberSortField.MEMBER_ID;
			break;
		}
		
		logger.debug("Sorting by field {} in direction {}", field, direction);

		return sort(sortField, fromString(direction));
	}

	/**
	 * Validate sort field.
	 *
	 * @param field the field
	 */
	public static void validateSortField(final String field, final String direction) {

		try {
			Direction.valueOf(direction.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid sort direction: " + direction);
		}

		try {
			ReferenceSetMemberSortField.valueOf(field);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid sort field: " + field);
		}
	}
}
