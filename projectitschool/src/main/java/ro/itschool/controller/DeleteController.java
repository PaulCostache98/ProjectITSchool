package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.entity.MyUser;
import ro.itschool.exception.UserNotFoundException;
import ro.itschool.repository.UserRepository;
import ro.itschool.service.UserService;

import java.util.Optional;

@Controller
public class DeleteController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/delete-account")
    public String deleteAccount() throws UserNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser user = userRepository.findById(userService.findUserByUserName(authentication.getName()).getId()).orElseThrow(UserNotFoundException::new);
        userService.deleteById(user.getId());
        return "redirect:/logout";
    }
}
