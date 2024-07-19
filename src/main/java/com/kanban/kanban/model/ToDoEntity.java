package com.kanban.kanban.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ToDos")
public class ToDoEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="Subject")
    private String todoName;

    @Column(name = "Description")
    private String todo;

    @Column(name = "Deadline")
    private String deadline;

    @Column(name = "Status")
    private String status;

    @Column(name = "currentdate")
    private String currentdate;

    @Column(name = "inhaber")
    private String inhaber;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
