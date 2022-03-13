package com.example.controller;

import com.example.model.Person;
import com.example.model.Task;
import com.example.repository.PersonRepository;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class TasksController {

    private final TaskRepository taskRepository;
    private final TaskService taskService;
    private final PersonRepository personRepository;


    public TasksController(TaskRepository taskRepository, TaskService taskService, com.example.repository.PersonRepository personRepository) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
        this.personRepository = personRepository;
    }



    @RequestMapping(value = {"/tasks"}, method = RequestMethod.GET)
    public String viewTasks(Model model){
        List<Task> taskList = taskService.getTasks();
        model.addAttribute("task",taskList);
        return "tasks/tasks";
    }

    @RequestMapping(value = {"/addTask"}, method = RequestMethod.GET)
    public String viewAddTask(Model model){
        List<Person> list = personRepository.findAll();
        model.addAttribute("person", list);
        return "tasks/addTask";
    }

    @RequestMapping(value = "/addNewTask", method = RequestMethod.POST)
    public RedirectView saveTask(@ModelAttribute Task newTask){
        taskRepository.save(newTask);
        return new RedirectView("/tasks");
    }

    @RequestMapping(value = {"/editTask/{id}"}, method = RequestMethod.GET)
    public String editTask(Model model, @PathVariable("id") Long id){
        List<Person> list = personRepository.findAll();
        Task task = taskRepository.findById(id).orElse(null);
        model.addAttribute("person", list);
        model.addAttribute("task", task);
        return "tasks/editTask";
    }

    @RequestMapping(value = {"/editTaskSave/{id}"}, method = RequestMethod.POST)
    public RedirectView saveEditTask(@ModelAttribute Task newTask, @PathVariable("id") Long id){
        taskRepository.save(newTask);
        return new RedirectView("/tasks");
    }

    @RequestMapping(value = {"/editTask/{id}"}, method = RequestMethod.POST)
    public RedirectView deleteTask(@PathVariable("id") Long id){
        taskRepository.deleteById(id);
        return new RedirectView("/tasks");
    }


}
