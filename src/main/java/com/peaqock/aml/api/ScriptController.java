package com.peaqock.aml.api;

import com.peaqock.aml.dao.ScriptRepository;
import com.peaqock.aml.domain.Script;
import com.peaqock.aml.exception.errors.NoRecordFoundException;
import com.peaqock.aml.exception.errors.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequiredArgsConstructor
@RequestMapping("/script")
public class ScriptController {

    private final ScriptRepository scriptRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Script>> all(HttpServletRequest req) {
        return ok(scriptRepository.findAll());
    }

    @PostMapping("")
    public Script createScript(@Valid @RequestBody Script script) {
        script = scriptRepository.save(script);
        return script;
    }

    @PutMapping("/{id}")
    public Script updateScript(@PathVariable UUID id, @Valid @RequestBody Script scriptRequest) {
        Script result = scriptRepository.findById(id).map(script -> {
            script.setNom(scriptRequest.getNom());
            script.setLangue(scriptRequest.getLangue());
            script.setIs_configured(scriptRequest.getIs_configured());
            script.setData(scriptRequest.getData());
            return scriptRepository.save(script);
        }).orElseThrow(() -> new NoRecordFoundException("script with id " + id + " not found"));
        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScript(@PathVariable UUID id) {
        return scriptRepository.findById(id).map(script -> {
            scriptRepository.delete(script);
            return ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("scriptId " + id + " not found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Script> get(@PathVariable("id") UUID scriptId) {
        return ok(this.scriptRepository.findById(scriptId).orElseThrow(() -> new ResourceNotFoundException("scriptId " + scriptId + " not found")));
    }
}
