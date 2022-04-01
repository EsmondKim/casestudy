package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;

import java.util.Date;

//This is the controller method for the entry point of the user registration page.
//It does not do anything really other than provide a rout to the register.jsp page.

@Slf4j
@Controller
    public class UserController {

        @Autowired
        private UserDAO UserDAO;

        @RequestMapping(value = "/user/register", method = RequestMethod.GET)
        public ModelAndView register() throws Exception {
            ModelAndView response = new ModelAndView();
            response.setViewName("user/register");

            return response;
        }

//    When the user submits the form it will call into this method
//    1) the action on the form itself must match the value here in the request
//    2) method on the form must match the method here
//    otherwise Sprinb MVC will not be able to responde to the request

    @RequestMapping(value = "/user/registerSubmit", method = RequestMethod.POST)
    public ModelAndView registerSubmit(RegisterFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        User user = new User();

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setPassword(form.getPassword());
        user.setCreateDate((new Date()));

        UserDAO.save(user);

        log.info(form.toString());

        return response;
    }

}

