package com.example.javaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class JavaProjectApplication {
    public static   Controller controller = new Controller();
    public static void main(String[] args) throws ClassNotFoundException {
        SpringApplication.run(JavaProjectApplication.class, args);
        controller.intilazingLists();
    }


    @GetMapping("/getallTeamMember")
    public List<TeamMember> getTeammember() throws ClassNotFoundException {

        List<TeamMember> TeamList = new ArrayList<TeamMember>();
        TeamList = controller.getallTeamMember();
        return TeamList;
    }
    @GetMapping(value = "/getallTaskByMemberCode/{code}")
    public List<Tasks> getallTaskByMemberCode(@PathVariable int code) throws ClassNotFoundException {
     return  controller.getallTaskByMemberCode(code);
    }
    @GetMapping(value = "/existTeam/{name}/{mail}")
    public String existTeam(@PathVariable String name,@PathVariable String mail) throws ClassNotFoundException {
        return  controller.existTeam(name,mail);
    }
    @GetMapping(value = "/invokeThreads/{taskCode}/{taskStatus}")
    public void invokeTheareds(@PathVariable int taskCode,@PathVariable status taskStatus) throws ClassNotFoundException {
       controller.invokeThreads(taskCode,taskStatus);
    }
    @GetMapping("/getAllTask")
    public List<Tasks> getAllTask() throws ClassNotFoundException {
DBAccess b=new DBAccess();
        List<Tasks> TasksList = new ArrayList<Tasks>();
        TasksList = b.getAllTask();
        return TasksList;
    }
}
