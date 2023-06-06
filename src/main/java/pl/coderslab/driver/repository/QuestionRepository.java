package pl.coderslab.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.driver.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
