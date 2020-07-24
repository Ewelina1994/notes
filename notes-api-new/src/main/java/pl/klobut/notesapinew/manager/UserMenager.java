package pl.klobut.notesapinew.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.klobut.notesapinew.db.UserRepository;
import pl.klobut.notesapinew.model.User;

@Service
public class UserMenager {
    private UserRepository userRepository;

    @Autowired
    public UserMenager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
