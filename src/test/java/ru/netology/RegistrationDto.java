package ru.netology;

public class RegistrationDto {
private String login;
private String password;
private String status;
    
    public RegistrationDto(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPassword() {
        return password;
    }
    
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getAccountStatus() {
//        return accountStatus;
//    }
//
//    public void setAccountStatus(String accountStatus) {
//        this.accountStatus = accountStatus;
//    }
}
