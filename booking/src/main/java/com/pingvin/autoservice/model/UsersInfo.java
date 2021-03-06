package com.pingvin.autoservice.model;

import com.pingvin.autoservice.entity.User;

public class UsersInfo {
    private int idUser;
    private String login;
    private String password;
    private String email;
    private String role;

    public UsersInfo() {
    }

    public UsersInfo(int idUser, String login, String password, String email, String role) {
        this.setIdUser(idUser);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setRole(role);
    }

    public UsersInfo(int idUser, String login, String password, String email) {
        this.setIdUser(idUser);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setRole("USER");
    }

    public UsersInfo(User user) {
        this.idUser = user.getIdUser();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        if (this.role.equals("ADMIN"))
            return true;
        return false;
    }

    public boolean isBlocked() {
        if (this.role.equals("BLOCKED"))
            return true;
        return false;
    }

    public boolean isUser() {

        if (this.role.equals("USER")) {
            return true;
        }
        return false;
    }
}