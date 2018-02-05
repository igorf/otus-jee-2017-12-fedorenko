package ru.otus.hw5.server.services;

import ru.otus.hw5.db.model.Employee;

import javax.servlet.http.HttpSession;

public class LoginService {
    private static LoginService ourInstance = new LoginService();

    public static LoginService getInstance() {
        return ourInstance;
    }

    private LoginService() {
        super();
    }

    private final static String LOGGED_USER_ATTRIBUTE = "LOGGED_USER";
    private HttpSession session;

    public boolean isLogged() {
        return getUser() != null;
    }

    public Employee getUser() {
        return (Employee) session.getAttribute(LOGGED_USER_ATTRIBUTE);
    }

    public void login(String login, String password) {
        Employee emp = EmployeeService.getInstance().findByLoginAndPassword(login, password);
        if (emp != null) {
            setUserLogged(emp);
        }
    }

    public void setUserLogged(Employee user) {
        if (user != null) {
            session.setAttribute(LOGGED_USER_ATTRIBUTE, user);
        }
    }

    public void logoff() {
        session.removeAttribute(LOGGED_USER_ATTRIBUTE);
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
}
