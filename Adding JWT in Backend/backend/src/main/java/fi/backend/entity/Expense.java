package fi.backend.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Document(collection="expenses")
public class Expense {
	
	@Id
	private String id;
	
	@NotBlank(message = "Title cannot be blank")
    @NotNull(message = "Title cannot be null")
    private String title;
    
    @NotBlank
    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private Double amount;
    
    @NotBlank
    @NotNull(message = "Date cannot be null")
    @PastOrPresent(message = "Date cannot be in the future")
    private LocalDate date;
    
    @NotNull
    @NotBlank(message = "Category cannot be blank")
    @Size(min = 3, message = "Category must be at least 3 characters long")
    private String category;
    
    @NotNull(message = "Description cannot be null")
    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;
    
    @NotBlank(message = "User ID cannot be blank")
    private String userId; // Reference to Users

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
