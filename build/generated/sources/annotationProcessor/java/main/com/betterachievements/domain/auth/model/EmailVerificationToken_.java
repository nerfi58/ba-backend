package com.betterachievements.domain.auth.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;

/**
 * Static metamodel for {@link com.betterachievements.domain.auth.model.EmailVerificationToken}
 **/
@StaticMetamodel(EmailVerificationToken.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class EmailVerificationToken_ {

	
	/**
	 * @see #id
	 **/
	public static final String ID = "id";
	
	/**
	 * @see #token
	 **/
	public static final String TOKEN = "token";
	
	/**
	 * @see #userAuth
	 **/
	public static final String USER_AUTH = "userAuth";
	
	/**
	 * @see #expiresAt
	 **/
	public static final String EXPIRES_AT = "expiresAt";

	
	/**
	 * Static metamodel type for {@link com.betterachievements.domain.auth.model.EmailVerificationToken}
	 **/
	public static volatile EntityType<EmailVerificationToken> class_;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.EmailVerificationToken#id}
	 **/
	public static volatile SingularAttribute<EmailVerificationToken, Long> id;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.EmailVerificationToken#token}
	 **/
	public static volatile SingularAttribute<EmailVerificationToken, String> token;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.EmailVerificationToken#userAuth}
	 **/
	public static volatile SingularAttribute<EmailVerificationToken, UserAuth> userAuth;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.EmailVerificationToken#expiresAt}
	 **/
	public static volatile SingularAttribute<EmailVerificationToken, Instant> expiresAt;

}

