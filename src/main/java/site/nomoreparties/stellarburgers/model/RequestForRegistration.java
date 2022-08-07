package site.nomoreparties.stellarburgers.model;

import org.apache.commons.lang3.RandomStringUtils;

public class RequestForRegistration {
    private String email;
    private String password;
    private String name;

    public RequestForRegistration(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static RequestForRegistration getRandomUser () {
        String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(8);
        return new RequestForRegistration(email, password, name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
