package vn.edu.nlu.fit.web.chat.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Data
@Document(collection = "users")
public class User extends AbstractDocument implements UserDetails {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Status status;
    private boolean isActive;
    private boolean isLocked;
    private String roles;


    public User() {
        this.roles = Role.ROLE_USER.name();
        this.isLocked = false;
        this.isActive = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(roles.split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 3, 3, 3};
        int[] b = {5,2,3, 6};
        List<Integer> tem = Arrays.stream(b).boxed().collect(Collectors.toList());
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.stream(a).boxed().collect(Collectors.toList()));
        for(Integer i : tem){
            if(list.contains(i)){
                list.removeIf(l -> l.equals(i));
            }
            else{
                System.out.println("HEHEH");
            }
        }
        System.out.println(list);

        HashMap<String,String> h = new HashMap<String,String>();
        h.

    }
}