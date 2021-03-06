package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;

// Validateur pour fistname & lastname

@FacesValidator(value = "validators.lastname")
public class LastnameValidator implements Validator {
	
	private static final String LASTNAME_PATTERN = "^[a-zA-Z0-9]+";
	private Pattern pattern;
	private Matcher matcher;

	public LastnameValidator() {
		pattern = Pattern.compile(LASTNAME_PATTERN);
	}

	@Override
	public void validate(FacesContext context, UIComponent component,
		Object value) throws ValidatorException {
		matcher = pattern.matcher(value.toString());
		if(!matcher.find()){
			FacesMessage msg;
			if(component.getId().equals("lastname")){
				msg =new FacesMessage("Lastname validation failed.");
			} else {
				 msg =new FacesMessage("FirstName validation failed.");
			}
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg); 
		}
	}
}
