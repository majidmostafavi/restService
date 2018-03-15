package majidmostafavi.dao;

import majidmostafavi.entity.Personnel;

import javax.ejb.Stateless;

@Stateless
public class PersonnelDao extends AbstractDao<Personnel> {

    public PersonnelDao() {
        super(Personnel.class);
    }
}
