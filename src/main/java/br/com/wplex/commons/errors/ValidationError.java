package br.com.wplex.commons.errors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The validation class errors handler.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public class ValidationError implements Serializable {

	private static final long serialVersionUID = 6453556268616126940L;

	private int code;
	private String description;
	private Date timestamp;
	private int errors;
	private List<FieldError> fieldErrors;

	public ValidationError() {

	}

	public static ValidationError build(TypeError errorType, int counter) {
		final ValidationError wsError = new ValidationError();
		wsError.setCode(errorType.getCode());
		wsError.setDescription(errorType.getMessage());
		wsError.setTimestamp(new Date());
		wsError.setErrors(counter);
		return wsError;
	}

	public void addFieldError(String field, String message) {
		if (this.fieldErrors == null) {
			this.fieldErrors = new ArrayList<>();
		}

		this.fieldErrors.add(new FieldError(field, message));
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldError> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

	@Override
	public String toString() {
		return "ValidationError [code=" + code + ", description=" + description + ", timestamp=" + timestamp
				+ ", errors=" + errors + ", fieldErrors=" + fieldErrors + "]";
	}

}
