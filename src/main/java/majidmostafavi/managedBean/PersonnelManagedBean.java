package majidmostafavi.managedBean;

import majidmostafavi.entity.Personnel;
import majidmostafavi.service.PersonnelService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class PersonnelManagedBean implements Serializable {

    @Inject
    PersonnelService personnelService;


    private Personnel personnel;
    private List<Personnel> personnelList;

    @PostConstruct
    public void init(){

    }

    public void createPersonnel(){

    }

    public void editPersonnel(){

    }

    public void deletePersonnel(){

    }




}
