package model;

import bean.User;
import model.service.UserService;
import model.service.UserServiceImpl;

import java.util.List;


public class MainModel implements Model {
    private ModelData modelData = new ModelData(); // объект, который будет хранить необходимые данные для отображения.
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(getAllUsers());
    }

    @Override
    public void loadDeletedUsers() {
        modelData.setUsers(userService.getAllDeletedUsers());
        modelData.setDisplayDeletedUserList(true);
    }

    @Override
    public void loadUserById(long userId) {
        modelData.setActiveUser(userService.getUsersById(userId));
    }

    @Override
    public void deleteUserById(long id) {
       User deleted = userService.deleteUser(id);
       modelData.setDeletedUser(deleted);

    }

    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(getAllUsers());
    }

    private List<User> getAllUsers(){
        modelData.setDisplayDeletedUserList(false);
        return userService.filterOnlyActiveUsers(userService.getUsersBetweenLevels(1,100));
    }
}
