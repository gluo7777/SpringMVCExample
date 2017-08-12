package org.app.entity;

public class Account {

    private int userId;
    private String userName;
    private String password;

    public Account(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public Account(){

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return 31 * userId + (userName != null ? userName.hashCode() : 0 );
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true; // same instance
        else if (obj == null) return false; // null comparison
        else if (getClass() != obj.getClass()) return false; // different classes
        else if (userId != ((Account) obj).getUserId()) return false; // different id
        return true;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Username: " + userName +
                "\t" +
                "Password: "+ password +
                "}";
    }
}
