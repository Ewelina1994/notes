package pl.klobut.notesapinew.api;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.klobut.notesapinew.api.viewmodel.FeedbackViewModel;
import pl.klobut.notesapinew.mail.FeedbackSender;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin
public class FeedbackController {
    private FeedbackSender feedbackSender;

    public FeedbackController(FeedbackSender feedbackSender) {
        this.feedbackSender = feedbackSender;
    }

    @PostMapping
    public boolean sendFeedback(@RequestBody FeedbackViewModel feedbackViewModel,
                             BindingResult bindingResult) throws ValidationException {
        if(bindingResult.hasErrors()){
            throw new ValidationException("Feedback has errors; Can not send feedback;");
        }
        else{
            this.feedbackSender.sendFeedback(
                    feedbackViewModel.getEmail(),
                    feedbackViewModel.getName(),
                    feedbackViewModel.getFeedback());
            return true;
        }


    }
}
