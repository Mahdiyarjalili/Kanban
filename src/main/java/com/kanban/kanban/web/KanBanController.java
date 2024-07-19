package com.kanban.kanban.web;

import com.kanban.kanban.UserService.UserService;
import com.kanban.kanban.model.ToDoEntity;
import com.kanban.kanban.services.todoservices.TodoService;
import com.kanban.kanban.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class KanBanController {
    @Autowired
    private TodoService todoService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index() {
        return "index/index";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        List<ToDoEntity> finalTodos = new ArrayList<>();
        List<ToDoEntity> todos = todoService.findAll();
        for (ToDoEntity todo : todos) {
            if (todo.getInhaber().equals(principal.getName())) {
                finalTodos.add(todo);
            }
        }


        model.addAttribute("todos", finalTodos);

        return "home";
    }

    public List<ToDoEntity> getTodos(Principal principal) {
        List<ToDoEntity> finaltodos = new ArrayList<>();
        UserEntity userEntity = userService.findUserByEmail(principal.getName());
        List<ToDoEntity> todos = todoService.findAll();
        for (ToDoEntity todo : todos) {
            if (todo.getInhaber().equals(principal.getName())) {
                finaltodos.add(todo);
            }
        }

        if(userEntity.getRole().equals("ADMIN"))
        {
            return todos;

        }
        else
        {
            return finaltodos;

        }
    }

    @GetMapping("/newtodoform")
    public String neeToDo(Model model) {
        ToDoEntity toDoEntity = new ToDoEntity();
        model.addAttribute("toDoEntity", toDoEntity);
        return "todo/newToDo";
    }

    @PostMapping("/saveToDo")
    public String saveToDo(@ModelAttribute("toDoEntity") ToDoEntity toDoEntity, Principal principal) {
        String username = principal.getName();
        UserEntity userEntity = userService.findUserByEmail(username);
        toDoEntity.setInhaber(userEntity.getEmail());
        todoService.saveToDo(toDoEntity);
        return "redirect:myprofile";
    }

    @GetMapping("todo/edit/{id}")
    public String editToDo(@PathVariable(value = "id") Long id, Model model) {
        ToDoEntity todo = todoService.findById(id);
        model.addAttribute("toDoEntity", todo);
        return "todo/edit_todo";
    }

    @PostMapping("/todo/{id}")
    public String updateTodo(@PathVariable Long id, @ModelAttribute("todoEntity") ToDoEntity todo, Model model) {
        ToDoEntity existingToDo = todoService.findById(id);
        existingToDo.setId(id);
        existingToDo.setTodo(todo.getTodo());
        existingToDo.setTodoName(todo.getTodoName());
        existingToDo.setStatus(todo.getStatus());
        existingToDo.setDeadline(todo.getDeadline());
        todoService.saveToDo(existingToDo);

        return "redirect:/myprofile";
    }

    @GetMapping("/todo/{id}")
    public String deleteToDo(@PathVariable Long id) {
        todoService.deleteToDo(id);
        return "redirect:/myprofile";
    }
}
