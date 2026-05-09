package com.madhes.EmployeeManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Employee is the core entity of this application.
 *
 * Relationship:
 *   Many employees → One sector (ManyToOne, owning side)
 *   The FK column "sector_id" lives in the employee table.
 *
 * empId vs id:
 *   - id: database surrogate PK (Long, auto-generated)
 *   - empId: business key ("EMP001") — unique, human-readable, used in domain logic
 *
 * @CreationTimestamp: Hibernate sets this automatically on INSERT, never on UPDATE
 * @UpdateTimestamp: Hibernate sets this on every INSERT and UPDATE
 *
 * Typo fixed: updatetAt → updatedAt
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String empId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String domain;
    private String grade;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at") // Fixed: was "updatetAt"
    private LocalDateTime updatedAt;
}