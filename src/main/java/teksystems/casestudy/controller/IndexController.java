package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.validation.EmailUniqueImpl;


import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        log.debug(currentPrincipalName);

        if (authentication.isAuthenticated() ) {
            String currentPrincipalName = authentication.getName();
            User loggedInUser = userDAO.findByEmail(currentPrincipalName);
            log.debug("Logged in user record = " + loggedInUser);
        }

        log.info("lombok logging at info level");
        log.warn("lombok logging at info level");

        List<User> firstName = userDAO.findByFirstName("Eric");
        List<User> firstLetter = userDAO.findByFirstNameIgnoreCaseContaining("E");

        for( User user : firstLetter ) {
            log.debug(user.toString());
        }

        for( User name : firstName ) {
            log.info(name.toString());
        }

        for( User name : firstLetter ) {
            log.info(name.toString());
        }

        response.setViewName("index");

        return response;
    }//ModelAndView index()

}//public class IndexController
