package com.betterachievements.domain.auth.repository;

import com.betterachievements.domain.auth.model.EmailVerificationToken;
import com.betterachievements.domain.auth.model.EmailVerificationToken_;
import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.data.Order;
import jakarta.data.exceptions.DataException;
import jakarta.data.exceptions.EmptyResultException;
import jakarta.data.exceptions.EntityExistsException;
import jakarta.data.exceptions.OptimisticLockingFailureException;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.page.impl.PageRecord;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceException;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import java.util.stream.Stream;
import org.hibernate.StaleStateException;
import org.hibernate.StatelessSession;
import org.hibernate.exception.ConstraintViolationException;
import static org.hibernate.query.Order.asc;
import static org.hibernate.query.SortDirection.*;
import org.hibernate.query.specification.SelectionSpecification;

/**
 * Implements Jakarta Data repository {@link com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository}
 **/
@Dependent
@Generated("org.hibernate.processor.HibernateProcessor")
public class EmailVerificationTokenRepository_ implements EmailVerificationTokenRepository {

	
	/**
	 * @see #findByTokenWithUserAuth(String)
	 **/
	static final String FIND_BY_TOKEN_WITH_USER_AUTH_String = "SELECT t FROM EmailVerificationToken t JOIN FETCH t.userAuth WHERE t.token = :token";
	
	/**
	 * @see #deleteByToken(String)
	 **/
	static final String DELETE_BY_TOKEN_String = "DELETE FROM EmailVerificationToken t WHERE t.token = :token";

	
	protected @Nonnull StatelessSession session;
	
	@Inject
	public EmailVerificationTokenRepository_(@Nonnull StatelessSession session) {
		this.session = session;
	}
	
	public @Nonnull StatelessSession session() {
		return session;
	}
	
	@Override
	public EmailVerificationToken insert(@Nonnull EmailVerificationToken entity) {
		requireNonNull(entity, "Null entity");
		try {
			session.insert(entity);
		}
		catch (ConstraintViolationException _ex) {
			throw new EntityExistsException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
		return entity;
	}
	
	@Override
	public List insertAll(@Nonnull List entities) {
		requireNonNull(entities, "Null entities");
		try {
			session.insertMultiple(entities);
		}
		catch (ConstraintViolationException _ex) {
			throw new EntityExistsException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
		return entities;
	}
	
	@Override
	public EmailVerificationToken update(@Nonnull EmailVerificationToken entity) {
		requireNonNull(entity, "Null entity");
		try {
			session.update(entity);
		}
		catch (StaleStateException _ex) {
			throw new OptimisticLockingFailureException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
		return entity;
	}
	
	@Override
	public List updateAll(@Nonnull List entities) {
		requireNonNull(entities, "Null entities");
		try {
			session.updateMultiple(entities);
		}
		catch (StaleStateException _ex) {
			throw new OptimisticLockingFailureException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
		return entities;
	}
	
	@Override
	public EmailVerificationToken save(@Nonnull EmailVerificationToken entity) {
		requireNonNull(entity, "Null entity");
		try {
			if (session.getIdentifier(entity) == null)
				session.insert(entity);
			else
				session.upsert(entity);
		}
		catch (StaleStateException _ex) {
			throw new OptimisticLockingFailureException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
		return entity;
	}
	
	@Override
	public List saveAll(@Nonnull List entities) {
		requireNonNull(entities, "Null entities");
		try {
			session.upsertMultiple(entities);
		}
		catch (StaleStateException _ex) {
			throw new OptimisticLockingFailureException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
		return entities;
	}
	
	@Override
	public void delete(@Nonnull EmailVerificationToken entity) {
		requireNonNull(entity, "Null entity");
		try {
			session.delete(entity);
		}
		catch (StaleStateException _ex) {
			throw new OptimisticLockingFailureException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	@Override
	public void deleteAll(@Nonnull List<? extends EmailVerificationToken> entities) {
		requireNonNull(entities, "Null entities");
		try {
			session.deleteMultiple(entities);
		}
		catch (StaleStateException _ex) {
			throw new OptimisticLockingFailureException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	/**
	 * Execute the query {@value #FIND_BY_TOKEN_WITH_USER_AUTH_String}.
	 *
	 * @see com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository#findByTokenWithUserAuth(String)
	 **/
	@Override
	public Optional<EmailVerificationToken> findByTokenWithUserAuth(String token) {
		try {
			return session.createSelectionQuery(FIND_BY_TOKEN_WITH_USER_AUTH_String, EmailVerificationToken.class)
				.setParameter("token", token)
				.uniqueResultOptional();
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	/**
	 * Execute the query {@value #DELETE_BY_TOKEN_String}.
	 *
	 * @see com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository#deleteByToken(String)
	 **/
	@Override
	public void deleteByToken(String token) {
		try {
			session.createMutationQuery(DELETE_BY_TOKEN_String)
				.setParameter("token", token)
				.executeUpdate();
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	/**
	 * Find {@link EmailVerificationToken} by {@link EmailVerificationToken#id id}.
	 *
	 * @see com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository#findById(Long)
	 **/
	@Override
	public Optional<EmailVerificationToken> findById(@Nonnull Long id) {
		requireNonNull(id, "Null id");
		try {
			return ofNullable(session.get(EmailVerificationToken.class, id));
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	/**
	 * Find {@link EmailVerificationToken}.
	 *
	 * @see com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository#findAll()
	 **/
	@Override
	public Stream<EmailVerificationToken> findAll() {
		var _builder = session.getCriteriaBuilder();
		var _query = _builder.createQuery(EmailVerificationToken.class);
		var _entity = _query.from(EmailVerificationToken.class);
		_query.where(
		);
		try {
			return session.createSelectionQuery(_query)
				.getResultStream();
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	/**
	 * Find {@link EmailVerificationToken}.
	 *
	 * @see com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository#findAll(PageRequest,Order)
	 **/
	@Override
	public Page<EmailVerificationToken> findAll(PageRequest pageRequest, Order<EmailVerificationToken> sortBy) {
		var _builder = session.getCriteriaBuilder();
		var _query = _builder.createQuery(EmailVerificationToken.class);
		var _entity = _query.from(EmailVerificationToken.class);
		_query.where(
		);
		var _spec = SelectionSpecification.create(_query);
		for (var _sort : sortBy.sorts()) {
			_spec.sort(asc(EmailVerificationToken.class, _sort.property())
						.reversedIf(_sort.isDescending())
						.ignoringCaseIf(_sort.ignoreCase()));
		}
		try {
			long _totalResults = 
					pageRequest.requestTotal()
							? _spec.createQuery(session)
									.getResultCount()
							: -1;
			var _results = _spec.createQuery(session)
				.setFirstResult((int) (pageRequest.page()-1) * pageRequest.size())
				.setMaxResults(pageRequest.size())
				.getResultList();
			return new PageRecord<>(pageRequest, _results, _totalResults);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}
	
	/**
	 * Delete {@link EmailVerificationToken} by {@link EmailVerificationToken#id id}.
	 *
	 * @see com.betterachievements.domain.auth.repository.EmailVerificationTokenRepository#deleteById(Long)
	 **/
	@Override
	public void deleteById(@Nonnull Long id) {
		requireNonNull(id, "Null id");
		var _builder = session.getCriteriaBuilder();
		var _query = _builder.createCriteriaDelete(EmailVerificationToken.class);
		var _entity = _query.from(EmailVerificationToken.class);
		_query.where(
				_builder.equal(_entity.get(EmailVerificationToken_.id), id)
		);
		try {
			session.createMutationQuery(_query)
				.executeUpdate();
		}
		catch (NoResultException _ex) {
			throw new EmptyResultException(_ex.getMessage(), _ex);
		}
		catch (NonUniqueResultException _ex) {
			throw new jakarta.data.exceptions.NonUniqueResultException(_ex.getMessage(), _ex);
		}
		catch (PersistenceException _ex) {
			throw new DataException(_ex.getMessage(), _ex);
		}
	}

}

