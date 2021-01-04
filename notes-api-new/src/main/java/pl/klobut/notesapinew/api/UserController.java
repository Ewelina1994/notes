package pl.klobut.notesapinew.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.klobut.notesapinew.db.UserRepository;
import pl.klobut.notesapinew.manager.UserMenager;
import pl.klobut.notesapinew.model.User;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private UserMenager userMenager;

    public UserController(UserMenager userMenager) {

        this.userMenager = userMenager;
    }

    @GetMapping("/byId/{id}")
    public User getById(@PathVariable Long id) {
        User user = this.userMenager.findById(id);

        if (user == null) {
            throw new EntityNotFoundException();
        }
        return user;
    }

    @PostMapping
    public User save(@RequestBody User user, BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Bład dodania usera");
        }
        if (user.getId() != null) {
            this.userMenager.save(user);
        }
        return user;
    }

    @DeleteMapping("/{id")
    public void delete(Long id) {
        this.userMenager.deleteById(id);
    }

    @PostMapping("/check")
    public void check(@RequestBody User user) {
        System.out.println("Imię: " + user.getName() + ", hasło: " + user.getPassword());
    }

}
