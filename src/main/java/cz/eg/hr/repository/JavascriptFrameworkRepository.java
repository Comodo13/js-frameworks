package cz.eg.hr.repository;

import cz.eg.hr.data.JavascriptFramework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JavascriptFrameworkRepository extends JpaRepository<JavascriptFramework, Long> {

    @Query("SELECT j FROM JavascriptFramework j WHERE j.name LIKE %?1%")
    List<JavascriptFramework> searchByName(String keyword);
}
