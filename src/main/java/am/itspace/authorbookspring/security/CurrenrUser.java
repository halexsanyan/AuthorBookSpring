package am.itspace.authorbookspring.security;



import am.itspace.authorbookspring.model.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrenrUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrenrUser(User user) {
        super(user.getUsername(), user.getPassword(), user.isActive(), true,true,true,
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
