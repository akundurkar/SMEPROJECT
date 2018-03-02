/**
 * 
 */
package in.adcast.common;

import java.io.Serializable;

/**
 * An interface shared by all business data access objects.
 * <p>
 * All CRUD (create, read, update, delete) basic data access operations are
 * isolated in this interface and shared across all DAO implementations.
 * The current design is for a state-management oriented persistence layer
 * (for example, there is no UPDATE statement function) which provides
 * automatic transactional dirty checking of business objects in persistent
 * state.
 */

public interface GenericDAO<T, PK extends Serializable> {

	   public T create(T t);
	   public T findById(PK id);
	   public T update(T t);
	   public void delete(T t);
    
}
