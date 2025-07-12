package com.betterachievements.domain.auth.model;

import jakarta.annotation.Generated;
import jakarta.data.metamodel.SortableAttribute;
import jakarta.data.metamodel.StaticMetamodel;
import jakarta.data.metamodel.TextAttribute;
import jakarta.data.metamodel.impl.SortableAttributeRecord;
import jakarta.data.metamodel.impl.TextAttributeRecord;

/**
 * Jakarta Data static metamodel for {@link com.betterachievements.domain.auth.model.EmailVerificationToken}
 **/
@StaticMetamodel(EmailVerificationToken.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public interface _EmailVerificationToken {

	
	/**
	 * @see #id
	 **/
	String ID = "id";
	
	/**
	 * @see #token
	 **/
	String TOKEN = "token";
	
	/**
	 * @see #userAuth
	 **/
	String USER_AUTH = "userAuth";
	
	/**
	 * @see #expiresAt
	 **/
	String EXPIRES_AT = "expiresAt";

	
	/**
	 * Static metamodel for attribute {@link EmailVerificationToken#id}
	 **/
	SortableAttribute<EmailVerificationToken> id = new SortableAttributeRecord<>(ID);
	
	/**
	 * Static metamodel for attribute {@link EmailVerificationToken#token}
	 **/
	TextAttribute<EmailVerificationToken> token = new TextAttributeRecord<>(TOKEN);
	
	/**
	 * Static metamodel for attribute {@link EmailVerificationToken#userAuth}
	 **/
	SortableAttribute<EmailVerificationToken> userAuth = new SortableAttributeRecord<>(USER_AUTH);
	
	/**
	 * Static metamodel for attribute {@link EmailVerificationToken#expiresAt}
	 **/
	SortableAttribute<EmailVerificationToken> expiresAt = new SortableAttributeRecord<>(EXPIRES_AT);

}

