package ch.zli.m223.punchclock.service;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.punchclock.domain.Gender;
import ch.zli.m223.punchclock.domain.User;
import io.quarkus.vertx.web.Body;

@ApplicationScoped
public class UserService {
    
    @Inject
    private EntityManager entityManager;

    public UserService() {
    }

    
    /** 
     * @param user
     * @return int
     */
    @Transactional 
    public int signupUser(User user) {

        if(user.getPassword().length() <= 5){
            return User.PASSWORD_ERROR;
        } else if(!isGenderValid(user.getGender())){
            return User.GENDER_ERROR;
        } else {
            user.setGender(new Gender(user.getGenderString()));;
            entityManager.persist(user);
            return User.SUCCESS;
        }
        
        
    }

    
    /** 
     * @param myToken
     * @return int
     */
    @Transactional 
    public int checkIfLoggedIn(String myToken) {
        System.out.println(myToken);
        System.out.println("---");
        AuthenticationService authenticationService = new AuthenticationService();
        List<User> users = findAll();
        for (User user : users) {
            String token = authenticationService.GenerateValidJwtToken(user.getUsername());
            System.out.println(token);
            System.out.println("---");
            if(token.equals(myToken)){
                return 1;
            }
        }

        return -1;
    }

    
    /** 
     * @param u
     * @return Response
     */
    @Transactional
    public Response loginUser(User u) {
        List<User> users = findAll();
        for (User user : users) {
            if(user.getUsername().equals(u.getUsername())){
                if (u.getPassword() == user.getPassword()){
                }
                else{
                    return Response
                    .ok(-1)
                    .header("token", "")
                    .build();
                }
                entityManager.persist(user);
                AuthenticationService authenticationService = new AuthenticationService();
                String token = authenticationService.GenerateValidJwtToken(u.getUsername());

                
                return Response
                .ok(1)
                .header("token", token)
                .build();
            }
        }
        return Response
        .ok(-2)
        .header("token", "")
        .build();
    }

    
    /** 
     * @return List<User>
     */
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }

    
    /** 
     * @param g
     * @return boolean
     */
    public boolean isGenderValid(Gender g) {
        String string = g.getGender();
        for (int i = 0; i < string.length(); i++) {
            if(Character.isDigit(string.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
