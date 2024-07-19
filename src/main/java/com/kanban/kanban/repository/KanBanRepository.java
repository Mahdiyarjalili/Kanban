package com.kanban.kanban.repository;

import com.kanban.kanban.model.ToDoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KanBanRepository extends JpaRepository<ToDoEntity, Long> {
}
