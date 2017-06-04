package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;


@FacesValidator(value = "validators.birthday")
public class BirthdayValidator implements Validator {
	
	private static final String BIRTHDAY_PATTERN = "[0-9][0-9]";
	private Pattern pattern;
	private Matcher matcher;

	public BirthdayValidator() {
		pattern = Pattern.compile(BIRTHDAY_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if(!matcher.matches()){
			FacesMessage msg =new FacesMessage("Birthday validation failed.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg); 
		}
	}
}

