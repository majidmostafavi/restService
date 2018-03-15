package majidmostafavi.managedBean;

import majidmostafavi.entity.Personnel;
import majidmostafavi.entity.Users;
import majidmostafavi.service.PersonnelService;
import majidmostafavi.service.UsersService;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UsersManagedBean implements Serializable {

    @Inject
    UsersService usersService;
    @Inject
    PersonnelService personnelService;


    private Users users;
    private List<Users> usersList;
    private Personnel personnel;


    private String username;
    private String password;

    public void init(){

    }


    public String login(){
        users = usersService.findUser(username,password);
        return users!=null &&users.getId() != null ? "home" : null;


    }

    public void signup(){
        personnel = new Personnel();
    }

    public void save(){
        personnel=personnelService.create(personnel);
        Users users = new Users();
        users.setPersonnel(personnel);
        users.setUsername(personnel.getEmail());
        users.setPassword(personnel.getFirstName());
        usersService.create(users);
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Personnel getPersonnel() {
        return personnel;
    }
    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }
}
