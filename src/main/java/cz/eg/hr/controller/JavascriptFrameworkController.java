package cz.eg.hr.controller;

import cz.eg.hr.data.JavascriptFrameworkDTO;
import cz.eg.hr.rest.exception.RequestFormatException;
import cz.eg.hr.rest.exception.ResourceNotFoundException;
import cz.eg.hr.service.JavascriptFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RequestMapping("api/v1/frameworks")
@RestController
public class JavascriptFrameworkController {

    private final JavascriptFrameworkService frameworkService;

    @Autowired
    public JavascriptFrameworkController(JavascriptFrameworkService frameworkService) {
        this.frameworkService = frameworkService;
    }

    @GetMapping("/")
    public ResponseEntity<List<JavascriptFrameworkDTO>> getAllFrameworks() {
        List<JavascriptFrameworkDTO> frameworks = frameworkService.getAllFrameworks();
        return ResponseEntity.ok(frameworks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JavascriptFrameworkDTO> getFrameworkById(@PathVariable Long id) throws ResourceNotFoundException {
        return  ResponseEntity.ok(frameworkService.getFrameworkById(id));
    }

    @PostMapping("/")
    public ResponseEntity<JavascriptFrameworkDTO> createFramework(@RequestBody JavascriptFrameworkDTO frameworkDTO) throws RequestFormatException {
        if (frameworkDTO.getRating() < 1 || frameworkDTO.getRating() > 5) {
            throw new RequestFormatException("Rating must be between 1 and 5");
        }
        JavascriptFrameworkDTO createdFramework = frameworkService.createFramework(frameworkDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFramework);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JavascriptFrameworkDTO> updateFramework(@PathVariable Long id, @RequestBody JavascriptFrameworkDTO updatedFrameworkDTO) throws RequestFormatException, ResourceNotFoundException {
        if (updatedFrameworkDTO.getRating() < 1 || updatedFrameworkDTO.getRating() > 5) {
            throw new RequestFormatException("Rating must be between 1 and 5");
        }

        JavascriptFrameworkDTO updatedFramework = frameworkService.updateFramework(id, updatedFrameworkDTO);
        return ResponseEntity.ok(updatedFramework);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFramework(@PathVariable Long id) throws ResourceNotFoundException {
        boolean deleted = frameworkService.deleteFramework(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            throw new ResourceNotFoundException("Framework with id: " + id + " was not found");
        }
    }

    @GetMapping("/search/")
    public ResponseEntity<List<JavascriptFrameworkDTO>> searchFrameworksByName(@RequestParam String keyword) {
        List<JavascriptFrameworkDTO> searchResults = frameworkService.searchFrameworksByName(keyword);
        return ResponseEntity.ok(searchResults);
    }
}


