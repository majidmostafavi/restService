package majidmostafavi.service;

import majidmostafavi.dao.PersonnelDao;
import majidmostafavi.entity.Personnel;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless
public class PersonnelService implements Serializable {

    @EJB
    PersonnelDao personnelDao;

    public List<Personnel> findAll(){
        return personnelDao.findAll();
    }

    public Personnel create(Personnel personnel) {
         return personnelDao.create(personnel);
    }
    public boolean delete(Personnel personnel){
        return personnelDao.delete(personnel);
    }
    public Personnel update(Personnel personnel){
        return personnelDao.update(personnel);
    }

}
