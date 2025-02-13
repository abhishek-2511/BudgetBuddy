package fi.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class IncomeDTO {
	
	 private String id;

	    @NotBlank(message = "Title cannot be blank")
	    private String title;

	    @NotNull(message = "Amount cannot be null")
	    @Positive(message = "Amount must be positive")
	    private Double amount;

	    @NotNull(message = "Date cannot be null")
	    @PastOrPresent(message = "Date cannot be in the future")
	    private LocalDate date;

	    @NotBlank(message = "Category cannot be blank")
	    @Size(min = 3, message = "Category must be at least 3 characters long")
	    private String category;

	    @NotNull(message = "Description cannot be null")
	    @Size(max = 255, message = "Description cannot exceed 255 characters")
	    private String description;

	    @NotBlank(message = "User ID cannot be blank")
	    private String userId; // Reference to Users

	    // Getters and Setters
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

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }
}
