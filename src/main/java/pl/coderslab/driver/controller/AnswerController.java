package pl.coderslab.driver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.driver.entity.Answer;
import pl.coderslab.driver.service.AnswerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/answers")
@RequiredArgsConstructor
public class AnswerController {

  private final AnswerService answerService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Answer> getAllAnswers() {
    return answerService.findAll();
  }

  @GetMapping("/{answerId}")
  @ResponseStatus(HttpStatus.OK)
  public Answer getAnswerById(@PathVariable long answerId) {
    return Optional.ofNullable(answerService.findById(answerId))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createAnswer(@RequestBody Answer answer) {
    answerService.save(answer);
  }

  @PutMapping("/{answerId}")
  @ResponseStatus(HttpStatus.OK)
  public void updateAnswer(@PathVariable long answerId, @RequestBody Answer answer) {
    Answer answerToUpdate = answerService.findById(answerId);
    answerToUpdate.setContent(answer.getContent());
    answerService.save(answer);
  }

  @DeleteMapping("/{answerId}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteAnswer(@PathVariable long answerId) {
    answerService.deleteById(answerId);
  }
}