package vttp.batch5.ssf.noticeboard.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Notice {


    @NotEmpty(message = "Mandatory")
    @Size(min = 3, max = 128, message = "The notice title's length must be between 3 and 128 characters")
    private String title;
    
    
    @Email
    @NotBlank(message = "Mandatory")
    private String poster;
    
    @NotNull(message = "Mandatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "This must be a date in the future.")
    private LocalDate postDate;

    @NotEmpty(message = "Mandatory")
    private String[] categories;

    @NotEmpty(message = "Mandatory")
    private String text;

    @Override
    public String toString() {
        return "Notice [title=" + title + ", poster=" + poster + ", postDate=" + postDate + ", categories=" + categories
                + ", text=" + text + "]";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    
    
}
