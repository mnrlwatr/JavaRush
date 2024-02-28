package model;

import bean.User;

import java.util.List;


public class ModelData {
    private List<User> users;
    private User activeUser;
    private boolean displayDeletedUserList;

    private User deletedUser;

    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getDeletedUser() {
        return deletedUser;
    }

    public void setDeletedUser(User deletedUser) {
        this.deletedUser = deletedUser;
    }
}
