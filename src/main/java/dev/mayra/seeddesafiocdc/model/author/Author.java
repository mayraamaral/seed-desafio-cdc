package dev.mayra.seeddesafiocdc.model.author;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Author {

    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String description;

    @Column
    private LocalDate createdAt;

    @Deprecated
    public Author() {}

    public Author(String name, String email, String description) {
        this.name = name;
        this.email = email;
        this.description = description;
        this.createdAt = LocalDate.now();
    }

    public Author(AuthorRequestDTO dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.description = dto.getDescription();
        this.createdAt = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public AuthorResponseDTO toResponseDTO() {
        return new AuthorResponseDTO(id, name, email, description, createdAt);
    }
}