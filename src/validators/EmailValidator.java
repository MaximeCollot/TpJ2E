package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

@FacesValidator(value = "validators.email")
public class EmailValidator implements Validator {
	
	private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
	private Pattern pattern;
	private Matcher matcher;

	public EmailValidator() {
		System.err.println("LOUIS");
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
			FacesMessage msg =new FacesMessage("Email validation failed.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg); 
		}
	}
}

