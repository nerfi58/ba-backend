package com.betterachievements.domain.auth.model;

import jakarta.annotation.Generated;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.TextAttribute;
import jakarta.data.metamodel.impl.SortableAttributeRecord;
import jakarta.data.metamodel.impl.TextAttributeRecord;

/**
 * Jakarta Data static metamodel for {@link com.betterachievements.domain.auth.model.UserAuth}
 **/
@StaticMetamodel(UserAuth.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public interface _UserAuth {

	
	/**
	 * @see #id
	 **/
	String ID = "id";
	
	/**
	 * @see #email
	 **/
	String EMAIL = "email";
	
	/**
	 * @see #passwordHash
	 **/
	String PASSWORD_HASH = "passwordHash";
	
	/**
	 * @see #emailVerified
	 **/
	String EMAIL_VERIFIED = "emailVerified";
	
	/**
	 * @see #createdAt
	 **/
	String CREATED_AT = "createdAt";
	
	/**
	 * @see #updatedAt
	 **/
	String UPDATED_AT = "updatedAt";

	
	/**
	 * Static metamodel for attribute {@link UserAuth#id}
	 **/
	SortableAttribute<UserAuth> id = new SortableAttributeRecord<>(ID);
	
	/**
	 * Static metamodel for attribute {@link UserAuth#email}
	 **/
	TextAttribute<UserAuth> email = new TextAttributeRecord<>(EMAIL);
	
	/**
	 * Static metamodel for attribute {@link UserAuth#passwordHash}
	 **/
	TextAttribute<UserAuth> passwordHash = new TextAttributeRecord<>(PASSWORD_HASH);
	
	/**
	 * Static metamodel for attribute {@link UserAuth#emailVerified}
	 **/
	SortableAttribute<UserAuth> emailVerified = new SortableAttributeRecord<>(EMAIL_VERIFIED);
	
	/**
	 * Static metamodel for attribute {@link UserAuth#createdAt}
	 **/
	SortableAttribute<UserAuth> createdAt = new SortableAttributeRecord<>(CREATED_AT);
	
	/**
	 * Static metamodel for attribute {@link UserAuth#updatedAt}
	 **/
	SortableAttribute<UserAuth> updatedAt = new SortableAttributeRecord<>(UPDATED_AT);

}

