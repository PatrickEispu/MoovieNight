package br.com.trainee.MoovieNight.domain.exception.handler;

import br.com.trainee.MoovieNight.domain.constante.ErrorMessage;
import br.com.trainee.MoovieNight.domain.exception.*;
import br.com.trainee.MoovieNight.domain.exception.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MoovieNightExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handleNegocioException(NegocioException ex) {
        logger.error("NegocioException: {}");
        String message = ErrorMessage.NEGOCIO_EXCEPTION + ex.getMessage();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
