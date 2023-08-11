package cz.eg.hr.data;

import org.springframework.lang.NonNull;

import java.time.LocalDate;

public class JavascriptFrameworkDTO {
    @NonNull
    private String name;
    @NonNull
    private String version;
    @NonNull
    private Long rating;
    @NonNull
    private LocalDate actualUntil;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public LocalDate getActualUntil() {
        return actualUntil;
    }

    public void setActualUntil(LocalDate actualUntil) {
        this.actualUntil = actualUntil;
    }
}


