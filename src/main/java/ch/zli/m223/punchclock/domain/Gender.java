package ch.zli.m223.punchclock.domain;

import javax.persistence.*;

@Entity
public class Gender {

    public Gender(String g) {
        this.gender = g;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String gender;

    @ManyToOne()
    private User user;

    
    /** 
     * @return Long
     */
    public Long getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    
    /** 
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    
    /** 
     * @return String
     */
    public String getGender() {
        return gender;
    }
}