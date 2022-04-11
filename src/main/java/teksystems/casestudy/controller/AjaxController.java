package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class AjaxController {
    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public ModelAndView ajaxView() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("ajax");

        return response;
    }

    @RequestMapping(value = "/ajaxRequest", method = RequestMethod.GET)
    public ResponseEntity<String> ajaxRequest(@RequestParam Integer sliderValue) throws Exception {
        log.debug("slider value = " + sliderValue);

        // 1) payload response spring that will show up in the ajax javascript as the "data" arguement
        // 2) return status code.
        return new ResponseEntity(sliderValue * 10, HttpStatus.OK);
    }
}

