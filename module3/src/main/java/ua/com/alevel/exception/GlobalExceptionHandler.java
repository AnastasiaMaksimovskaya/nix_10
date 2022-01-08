package ua.com.alevel.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ModelAndView defaultErrorHandler(EntityNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = {NotEnoughMoneyException.class, InvalidInputException.class})
    public ModelAndView defaultErrorHandler(NotEnoughMoneyException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public ModelAndView defaultErrorHandler(NumberFormatException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", "cумма должна быть числом");
        mav.setViewName("error");
        return mav;
    }
}
