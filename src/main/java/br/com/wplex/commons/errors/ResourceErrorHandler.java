package br.com.wplex.commons.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The resource error handler for {@link MethodArgumentNotValidException}
 *
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
@ControllerAdvice
public class ResourceErrorHandler {

	private MessageSource messageSource;

	@Autowired
	public ResourceErrorHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ValidationError> processValidationInternal(RuntimeException e) {

		final ValidationError validationError = ValidationError.build(TypeError.INTERNAL_SERVER_ERROR, 1);
		validationError.addFieldError("", e.getCause().getLocalizedMessage());

		return new ResponseEntity<>(validationError, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> processValidation(MethodArgumentNotValidException e) {
		final BindingResult result = e.getBindingResult();
		final ValidationError validationError = this.processFieldErrors(result);

		return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
	}

	private ValidationError processFieldErrors(BindingResult binding) {
		final ValidationError validationError = ValidationError.build(TypeError.VALIDATION_ERROR,
				binding.getErrorCount());

		for (FieldError fieldError : binding.getFieldErrors()) {
			validationError.addFieldError(fieldError.getObjectName() + "." + fieldError.getField(),
					this.resolveLocalizedErrorMessage(fieldError));
		}

		return validationError;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		String errorMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

		/*
		 * if (errorMessage.equals(fieldError.getDefaultMessage())) { String[]
		 * fieldErrorCodes = fieldError.getCodes(); errorMessage =
		 * fieldErrorCodes[0]; }
		 */

		return errorMessage;
	}
}
