package br.com.wplex.commons.errors;

/**
 * The Type Error enumeration used on {@link ValidationError}
 * 
 * @author Ryan Padilha <ryan.padilha@wplex.com.br>
 * @since 0.1
 *
 */
public enum TypeError {

	BAD_REQUEST_ERROR(4002, "Bad request error"), INTERNAL_SERVER_ERROR(5001,
			"Unexpected server error"), VALIDATION_ERROR(4001, "Found validation issues");

	private int code;
	private String message;

	TypeError(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
