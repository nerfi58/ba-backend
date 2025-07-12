package com.betterachievements.domain.auth.model;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.Instant;
import java.util.UUID;

/**
 * Static metamodel for {@link com.betterachievements.domain.auth.model.UserAuth}
 **/
@StaticMetamodel(UserAuth.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class UserAuth_ {

	
	/**
	 * @see #id
	 **/
	public static final String ID = "id";
	
	/**
	 * @see #email
	 **/
	public static final String EMAIL = "email";
	
	/**
	 * @see #passwordHash
	 **/
	public static final String PASSWORD_HASH = "passwordHash";
	
	/**
	 * @see #emailVerified
	 **/
	public static final String EMAIL_VERIFIED = "emailVerified";
	
	/**
	 * @see #createdAt
	 **/
	public static final String CREATED_AT = "createdAt";
	
	/**
	 * @see #updatedAt
	 **/
	public static final String UPDATED_AT = "updatedAt";

	
	/**
	 * Static metamodel type for {@link com.betterachievements.domain.auth.model.UserAuth}
	 **/
	public static volatile EntityType<UserAuth> class_;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.UserAuth#id}
	 **/
	public static volatile SingularAttribute<UserAuth, UUID> id;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.UserAuth#email}
	 **/
	public static volatile SingularAttribute<UserAuth, String> email;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.UserAuth#passwordHash}
	 **/
	public static volatile SingularAttribute<UserAuth, String> passwordHash;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.UserAuth#emailVerified}
	 **/
	public static volatile SingularAttribute<UserAuth, Boolean> emailVerified;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.UserAuth#createdAt}
	 **/
	public static volatile SingularAttribute<UserAuth, Instant> createdAt;
	
	/**
	 * Static metamodel for attribute {@link com.betterachievements.domain.auth.model.UserAuth#updatedAt}
	 **/
	public static volatile SingularAttribute<UserAuth, Instant> updatedAt;

}

