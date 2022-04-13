package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

//This is the controller method for the entry point of the user registration page.
//It does not do anything really other than provide a route to the register.jsp page.

@Slf4j
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
    public class UserController {

        @Autowired
        private UserDAO UserDAO;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @RequestMapping(value = "/user/register", method = RequestMethod.GET)
        public ModelAndView register() throws Exception {
            ModelAndView response = new ModelAndView();
            response.setViewName("user/register");

            //All these 2 lines of code are doing is seeding the model with an
            //empty form bean so that the JSP page substitutions will not error out
            //in this case spring is being nice enough not to throw errors but these
            //2 lines are safety.  They are "pre-seeding the model."
            RegisterFormBean form = new RegisterFormBean();
            response.addObject("form",form);

            return response;
        }

            //    When the user submits the form it will call into this method
            //    1) the action on the form itself must match the value here in the request
            //    2) method on the form must match the method here
            //    otherwise Spring MVC will not be able to respond to the request

            //This method now becomes a create and an edit based on if the id is populated in
            //the RegisterFormBean

        @RequestMapping(value = "/user/registerSubmit", method={ RequestMethod.POST, RequestMethod.GET })
        public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
            ModelAndView response = new ModelAndView();

//            int i = 10 / 0;

            if ( bindingResult.hasErrors() ) {

                for ( ObjectError error : bindingResult.getAllErrors() ) {
                    log.info( ((FieldError) error).getField() + " " + error.getDefaultMessage());
                }

                response.addObject("form", form);
//                response.addObject("errorMessages", errorMessages);
                response.addObject("bindingResult", bindingResult);

                response.setViewName("user/register");
                return response;
            }//if bindingResult.hasErrors()

            //the first thing we want to do is create a new user object
//            User user = new User();

            //Now check if the id in the form bean is not null, then query it
//            if ( form.getId() != null ) {
//                user = UserDAO.findById(form.getId());
//
//                if ( user == null ) {
//                    user = new User();
//                }
//            }

            // All of the above can be optimized with this code:
            //we first assume that we are going to do an edit by loadng the user from the db
            // using the incoming id on the form
            User user = UserDAO.findById(form.getId());
            //if the user is not null then we know it is an edit.
            if ( user == null ) {
            //now, if the user from the database is null then it means we did not find this user.
            //Therefore, it's a create.
            user = new User();
            }

            user.setEmail(form.getEmail());
            user.setFirstName(form.getFirstName());
            user.setLastName(form.getLastName());
//            user.setPassword(form.getPassword());
            //04122022 added bCrypt dependency injection with @Autowired and encrypting password into db.
            String password = passwordEncoder.encode(form.getPassword());
            user.setPassword(password);
            user.setCreateDate((new Date()));

            UserDAO.save(user);

            log.info(form.toString());

            response.setViewName("redirect:/user/edit/" + user.getId());

            return response;
        }

            //    Note the path variable below, using the url to pass info (the {id})
        @GetMapping("/user/edit/{userId}")
        public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
            ModelAndView response = new ModelAndView();
            response.setViewName("user/register");

            User user = UserDAO.findById(userId);

            RegisterFormBean form = new RegisterFormBean();

            form.setId(user.getId());
            form.setEmail(user.getEmail());
            form.setFirstName(user.getFirstName());
            form.setLastName(user.getLastName());
            form.setPassword(user.getPassword());
            form.setConfirmPassword(user.getPassword());

            response.addObject("form", form);

            return response;
    }

    // create a form on the user search page that submits to this route using a get method
    // make an input box for the user to enter a search term for first name
    // add a @RequestParam to take in a search value from the input box - use required = false in the annotation
    // use the search value in the query
    // add the search value to the model and make it display in the input box when the page reloads
    // add error checking to make sure that the incoming search value is not null and is not empty.
    // find apache string utils on maven central and add it to your pom file - very high recommendation
    // research the StringUtils.isEmpty function and use for error checking
    //if you wanna get all users, use the findAll() method
    //String has a method called isBlank() for checking only containing spaces

    @GetMapping("/user/search")
    public ModelAndView search(@RequestParam(name = "firstName", required = false, defaultValue = "") String firstName) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        List<User> users;

        //very basic example of error checking

        if (!StringUtils.isBlank(firstName)) {
            users = UserDAO.findByFirstNameIgnoreCaseContaining(firstName);
            response.addObject("users", users);
        } else {
            firstName = "Enter First Name";
        }

        response.addObject("searchValue", firstName);
        log.info(String.valueOf(response));
//            List<User> users = UserDAO.findByFirstNameIgnoreCaseContaining("e");
//            response.addObject("users", users);

        return response;
    }

}

