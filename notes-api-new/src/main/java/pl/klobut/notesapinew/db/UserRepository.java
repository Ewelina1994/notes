package pl.klobut.notesapinew.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.klobut.notesapinew.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
