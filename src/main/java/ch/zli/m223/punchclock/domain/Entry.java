package ch.zli.m223.punchclock.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime checkIn;

    @Column(nullable = false)
    private LocalDateTime checkOut;

    @ManyToOne()
    private Category category;

    @ManyToOne()
    private User user;

    
    /** 
     * @return Category
     */
    public Category getCategory(){
        return category;
    }

    
    /** 
     * @param category
     */
    public void setCategory(Category category){
        this.category = category;
    }


    
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
     * @return LocalDateTime
     */
    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    
    /** 
     * @param checkIn
     */
    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    
    /** 
     * @return LocalDateTime
     */
    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    
    /** 
     * @param checkOut
     */
    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}