package cz.eg.hr.service;

import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.data.JavascriptFrameworkDTO;
import cz.eg.hr.repository.JavascriptFrameworkRepository;
import cz.eg.hr.rest.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JavascriptFrameworkService {

    private final JavascriptFrameworkRepository repository;

    @Autowired
    public JavascriptFrameworkService(JavascriptFrameworkRepository repository) {
        this.repository = repository;
    }

    public List<JavascriptFrameworkDTO> searchFrameworksByName(String keyword) {
        List<JavascriptFramework> frameworks = repository.searchByName(keyword);
        return frameworks.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public List<JavascriptFrameworkDTO> getAllFrameworks() {
        List<JavascriptFramework> frameworks = repository.findAll();
        return frameworks.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public JavascriptFrameworkDTO getFrameworkById(Long id) throws ResourceNotFoundException {
        JavascriptFramework framework = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Framework with ID " + id + " not found"));
        return convertToDTO(framework);
    }

    public JavascriptFrameworkDTO createFramework(JavascriptFrameworkDTO frameworkDTO) {
        JavascriptFramework framework = convertToEntity(frameworkDTO);
        return convertToDTO(repository.save(framework));
    }

    public JavascriptFrameworkDTO updateFramework(Long id, JavascriptFrameworkDTO updatedFrameworkDTO) throws ResourceNotFoundException {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Framework with ID " + id + " not found");
        }
        JavascriptFramework updatedFramework = convertToEntity(updatedFrameworkDTO);
        updatedFramework.setId(id);
        return convertToDTO(repository.save(updatedFramework));
    }

    public boolean deleteFramework(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     *
     * DTO converters
     */
    private JavascriptFrameworkDTO convertToDTO(JavascriptFramework framework) {
        JavascriptFrameworkDTO dto = new JavascriptFrameworkDTO();
        BeanUtils.copyProperties(framework, dto);
        System.out.println(dto);
        return dto;
    }

    private JavascriptFramework convertToEntity(JavascriptFrameworkDTO dto) {
        JavascriptFramework framework = new JavascriptFramework();
        BeanUtils.copyProperties(dto, framework);
        System.out.println(framework);
        return framework;
    }
}
