package ua.com.alevel.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ModelAndView entityNotFoundErrorHandler(EntityNotFoundException exception) {
        return generateModelAndView("что-то пошло не так");
    }

    @ExceptionHandler(value = {EntityExistException.class})
    public ModelAndView entityExistErrorHandler(EntityExistException exception) {
        return generateModelAndView("что-то пошло не так");
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ModelAndView constraintViolationErrorHandler(ConstraintViolationException exception) {
        return generateModelAndView("что-то пошло не так");
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentNotValidException exception) {
        return generateModelAndView("что-то пошло не так");
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    public ModelAndView methodArgumentNotValidErrorHandler(MethodArgumentTypeMismatchException exception) {
        return generateModelAndView("incorrect value");
    }

    @ExceptionHandler(Throwable.class)
    public String serverError(final Throwable throwable, final Model model) {
        throwable.printStackTrace();
        model.addAttribute("errorMessage", "что-то пошло не так");
        model.addAttribute("showMessage", true);
        return "error";
    }

    private ModelAndView generateModelAndView(String msg) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("showMessage", true);
        mav.addObject("errorMessage", msg);
        mav.setViewName("error");
        return mav;
    }
}
