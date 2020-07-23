package pl.klobut.notesapinew.mail;

public interface FeedbackSender {
    boolean sendFeedback(String from, String name, String feedback);
}
