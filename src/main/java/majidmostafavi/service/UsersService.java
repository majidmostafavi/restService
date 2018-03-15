package majidmostafavi.service;

import majidmostafavi.dao.UsersDao;

import majidmostafavi.entity.Personnel;
import majidmostafavi.entity.Users;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UsersService implements Serializable{
    @EJB
    UsersDao usersDao;

    public List<Users> findAll(){
        return usersDao.findAll();
    }

    public Users create(Users user){
        return usersDao.create(user);
    }
    public boolean delete(Users user){
        return usersDao.delete(user);
    }
    public Users update(Users user){
        return usersDao.update(user);
    }

    public Users findUser(String userName,String password){
        return usersDao.find(userName,password);
    }
    public Personnel findPersonnel(String userName){
        return usersDao.findPersonnel(userName);
    }
}
