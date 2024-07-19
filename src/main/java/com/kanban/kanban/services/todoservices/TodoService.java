package com.kanban.kanban.services.todoservices;

import com.kanban.kanban.model.ToDoEntity;

import java.util.List;

public interface TodoService {
    ToDoEntity saveToDo(ToDoEntity toDoEntity);
    List<ToDoEntity> findAll();

    ToDoEntity findById(Long id);

    void deleteToDo(Long id);
    public ToDoEntity getReferenceById(Long id) ;

    public ToDoEntity updateToDo(ToDoEntity toDoEntity);

    }
