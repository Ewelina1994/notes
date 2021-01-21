package pl.klobut.notesapinew.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.klobut.notesapinew.api.viewmodel.UserPrincipal;
import pl.klobut.notesapinew.db.UserRepository;
import pl.klobut.notesapinew.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByName(s);
        if(user == null){
           throw new UsernameNotFoundException("User 404");
        }
        return new UserPrincipal(user);
    }
}
