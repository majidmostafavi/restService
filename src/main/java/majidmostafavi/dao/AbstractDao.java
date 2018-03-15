package majidmostafavi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

public class AbstractDao<T> {
    private final static String UNIT_NAME = "DATABASE";

    @PersistenceContext(unitName = UNIT_NAME)
    EntityManager em;

    private Class<T> entityClass;

    public AbstractDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        try {
            em.persist(entity);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public boolean delete(T entity) {
        try {
            T entityToBeRemoved = em.merge(entity);
            em.remove(entityToBeRemoved);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public T update(T entity) {
        try {
            return em.merge(entity);
        }catch (Exception e){
            return null;
        }

    }

    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }

    // Using the unchecked because JPA does not have a
    // em.getCriteriaBuilder().createQuery()<T> method

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {

        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();

    }

    // Using the unchecked because JPA does not have a
    // ery.getSingleResult()<T> method

    @SuppressWarnings("unchecked")
    protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
        T result = null;
        try {
            Query query = em.createNamedQuery(namedQuery);
            // Method that will populate parameters if they are passed not null and empty
            if (parameters != null && !parameters.isEmpty()) {
                populateQueryParameters(query, parameters);
            }
            result = (T) query.getSingleResult();
        } catch (Exception e) {

            System.out.println("Error while running query: " + e.getMessage());

            e.printStackTrace();

        }
        return result;

    }

    private void populateQueryParameters(Query query, Map<String, Object> parameters) {

        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());

        }

    }
}
