package com.peaqock.aml.api;

import com.peaqock.aml.dao.FeedbackRepository;
import com.peaqock.aml.dao.ScriptRepository;
import com.peaqock.aml.dao.UserRepository;
import com.peaqock.aml.domain.Feedback;
import com.peaqock.aml.domain.Script;
import com.peaqock.aml.domain.UserEntity;
import com.peaqock.aml.dto.FeedbackRequest;
import com.peaqock.aml.dto.enums.FeedbackStatus;
import com.peaqock.aml.dto.payload.UpdateStatusDTO;
import com.peaqock.aml.exception.errors.NoRecordFoundException;
import com.peaqock.aml.exception.errors.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {

    private final ScriptRepository scriptRepository;
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody FeedbackRequest feedbackrequest) {
        Optional<Script> script = this.scriptRepository.findById(feedbackrequest.getScriptId());
        if (script.isEmpty()) {
            throw new ResourceNotFoundException("script with id " + feedbackrequest.getScriptId() + " not found");
        }
        Optional<UserEntity> user = this.userRepository.findById(feedbackrequest.getProspectId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("user with id " + feedbackrequest.getProspectId() + " not found");
        }

        Feedback feedback = Feedback.builder()
                .script(script.get())
                .prospect(user.get())
                .status(FeedbackStatus.INITIAL)
                .build();
        return ResponseEntity.ok(this.feedbackRepository.save(feedback));
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable UUID id, @Valid @RequestBody FeedbackRequest feedbackrequest) {

        /*Optional<UserEntity> user = this.userRepository.findById(feedbackrequest.getProspectId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("user with id " + feedbackrequest.getProspectId() + " not found");
        }*/
        Feedback result = feedbackRepository.findById(id).map(feedback -> {
            feedback.setFeedback(feedbackrequest.getFeedback());
            feedback.setStatus(FeedbackStatus.PENDING);
            feedback.setScore(feedbackrequest.getScore());
            return feedbackRepository.save(feedback);
        }).orElseThrow(() -> new NoRecordFoundException("feedback with id " + id + " not found"));
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> get(@PathVariable("id") UUID feedbacktId) {
        return ok(this.feedbackRepository.findById(feedbacktId).orElseThrow(() -> new ResourceNotFoundException("feedback with id " + feedbacktId + " not found")));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> all() {
        return ok(feedbackRepository.findAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Feedback> changeFeedbackStatus(@PathVariable UUID id, @RequestBody UpdateStatusDTO status) {
        Optional<Feedback> fb = this.feedbackRepository.findById(id);
        if (fb.isEmpty()) {
            throw new ResourceNotFoundException("The Feedback doesn't exist");
        }
        fb.get().setStatus(status.getStatus());
        return ResponseEntity.ok(this.feedbackRepository.save(fb.get()));
    }

}
