package pl.klobut.notesapinew.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.klobut.notesapinew.api.viewmodel.FeedbackViewModel;
import pl.klobut.notesapinew.db.FeedbackRepository;
import pl.klobut.notesapinew.model.Feedback;

import java.util.List;

@Service
public class FeedbackMenager {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Iterable<Feedback> getAll() {
        return feedbackRepository.findAll();
    }
}
