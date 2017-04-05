package br.com.wplex.commons.errors;

import java.io.Serializable;

/**
 * The field error handler.
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public class FieldError implements Serializable {

	private static final long serialVersionUID = 3617874727874972534L;

	private String field;
	private String message;

	public FieldError(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FieldError [field=" + field + ", message=" + message + "]";
	}

}
