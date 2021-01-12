package pl.klobut.notesapinew.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.klobut.notesapinew.mail.FeedbackSender;
import pl.klobut.notesapinew.manager.FeedbackMenager;
import pl.klobut.notesapinew.model.Feedback;
import javax.xml.bind.ValidationException;
import java.util.Date;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedbackController {
    private FeedbackSender feedbackSender;
    private FeedbackMenager feedbackMenager;
    public FeedbackController(FeedbackSender feedbackSender, FeedbackMenager feedbackMenager) {
        this.feedbackSender = feedbackSender;
        this.feedbackMenager=feedbackMenager;
    }

    @PostMapping
    public boolean sendFeedback(@RequestBody Feedback feedback,
                                BindingResult bindingResult) throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException("Feedback has errors; Can not send feedback;");
        } else {
            this.feedbackSender.sendFeedback(
                    feedback.getEmail(),
                    feedback.getName(),
                    feedback.getFeedback());
          //  feedbackMenager.saveFeedback(feedback);
            return true;
        }
    }

    @PostMapping("/add")
    public boolean addSendMessageToDatabase(@RequestBody Feedback feedback,
                                            BindingResult bindingResult) throws ValidationException{
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback has errors; Can save send feedback;");
        }else {
            feedback.setDate(new Date());
            feedbackMenager.saveFeedback(feedback);
            return true;
        }
    }

    @GetMapping("/all")
    public Iterable<Feedback> getAll() {
        return feedbackMenager.getAll();
    }
}
