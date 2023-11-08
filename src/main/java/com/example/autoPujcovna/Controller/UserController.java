package com.example.autoPujcovna.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.autoPujcovna.Model.User;
import com.example.autoPujcovna.Service.UserService;

@Controller
@RequestMapping(path = "autoPujcovna")
public class UserController {
    
    private final UserService userService;
   
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
	public String getUsers(Model model) {
		List<User> userList = userService.getUsers();
        model.addAttribute("users", userList);
        return "index";
	}





    @GetMapping("/users/add") 
    public String addUserForm(Model model) {

        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }
    @PostMapping("/users/add")
    public String addUser(@ModelAttribute("User") User user,BindingResult result) {
        if (result.hasErrors()) {
            return "addUser";
        }
            userService.addUser(user);
            return "redirect:http://localhost:8080/autoPujcovna/users";
    }






    @GetMapping("/users/edit/{userId}")
    public String editUserForm(Model model,@PathVariable("userId") long userId) {
        
        User user = userService.getUserById(userId); 
        model.addAttribute("user", user);
        
        return "editUser";
    }
    @PostMapping(path = "/users/edit/{userId}")
    public String updateUser(  
        @RequestParam("id") Long userId,
        @RequestParam(required = false) String username, 
        @RequestParam(required = false) String email){
        userService.updateUser(userId, username, email);
        return "redirect:http://localhost:8080/autoPujcovna/users";
    }



    

    @GetMapping(path = "/users/{userId}")
    public String deleteUser(
        @PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:http://localhost:8080/autoPujcovna/users";
    }
    
}
