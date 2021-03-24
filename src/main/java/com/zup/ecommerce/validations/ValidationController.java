package com.zup.ecommerce.validations;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationController {

	private MessageSource messages;

	public ValidationController(MessageSource messages) {
		this.messages = messages;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse errosValidacao(MethodArgumentNotValidException exception) {
		
		List<FieldError> errors = exception.getBindingResult().getFieldErrors();
		
		return criarErrorResponse(errors);
	}

	private ErrorResponse criarErrorResponse(List<FieldError> errors) {
		ErrorResponse response = new ErrorResponse();
		errors.forEach(erro -> {
			String message = messages.getMessage(erro, LocaleContextHolder.getLocale());
			response.addError(erro.getField(), message);
		});
		return response;
	}
	
}
