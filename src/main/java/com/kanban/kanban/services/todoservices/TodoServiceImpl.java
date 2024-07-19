package com.kanban.kanban.services.todoservices;

import com.kanban.kanban.model.ToDoEntity;
import com.kanban.kanban.repository.KanBanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
     KanBanRepository kanBanRepository;

    @Override
    public ToDoEntity saveToDo(ToDoEntity toDoEntity) {
    return kanBanRepository.save(toDoEntity);
    }
    @Override
    public ToDoEntity updateToDo(ToDoEntity toDoEntity) {

        return toDoEntity;
    }
    @Override
    public ToDoEntity getReferenceById(Long id) {

        return kanBanRepository.findById(id).get();
    }

    @Override
    public List<ToDoEntity> findAll() {
        return kanBanRepository.findAll();

    }

    @Override
    public ToDoEntity findById(Long id) {
        return kanBanRepository.findById(id).get();

    }

    @Override
    public void deleteToDo(Long id) {
        kanBanRepository.deleteById(id);
            }


}

