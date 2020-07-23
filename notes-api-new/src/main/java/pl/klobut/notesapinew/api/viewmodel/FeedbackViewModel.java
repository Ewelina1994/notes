package pl.klobut.notesapinew.api.viewmodel;

import com.jayway.jsonpath.internal.function.numeric.Min;

public class FeedbackViewModel {

    private String name;


    private String email;


    private String feedback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
