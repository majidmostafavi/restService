package majidmostafavi.dao;

import majidmostafavi.entity.Personnel;
import majidmostafavi.entity.Users;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;


@Stateless
public class UsersDao extends AbstractDao<Users> {


    public UsersDao() {
        super(Users.class);
    }

    public Users find(String userName,String password){
        try {
            Query query = em.createNamedQuery("findUserByUserName");
            query.setParameter("username",userName);
            query.setParameter("password",password);
            return (Users) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

    public Personnel findPersonnel(String userName){
        Query query = em.createNamedQuery("findPersonnelByUserName");
        query.setParameter("username",userName);
        return (Personnel) query.getSingleResult();
    }

}
