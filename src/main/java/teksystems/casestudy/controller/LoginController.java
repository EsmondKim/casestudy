package teksystems.casestudy.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.UserRoleDAO;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserRoleDAO UserRoleDAO;

    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("login/loginForm");
        return response;
    }
}
