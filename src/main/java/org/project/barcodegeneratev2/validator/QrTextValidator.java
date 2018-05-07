package org.project.barcodegeneratev2.validator;

import org.project.barcodegeneratev2.model.QrTextInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class QrTextValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == QrTextInfo.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
//		QrTextInfo qrTextInfo = (QrTextInfo) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qrtext", "NotEmpty.qrTextForm.qrtext");
	}

}
