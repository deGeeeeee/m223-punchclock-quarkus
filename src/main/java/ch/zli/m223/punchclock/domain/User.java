package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    public static int PASSWORD_ERROR = -1;
    public static int GENDER_ERROR = -2;
    public static int SUCCESS = 1; 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private Gender gender;

    @Column(unique = true)
    private String genderString;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy =  "user", fetch = FetchType.LAZY)
    private List<Entry> entries;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getGenderString() {
        return genderString;
    }

    public void setGenderString(String genderString) {
        this.genderString = genderString;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createUser(String username, String password, String gender) {

        this.username = username;
        this.gender = new Gender(gender);
        this.password = password;
    }
}