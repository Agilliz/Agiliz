package agiliz.projetoAgiliz.validation.annotations;

import agiliz.projetoAgiliz.validation.validators.CampoUnicoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CampoUnicoValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CampoUnico {
    String message() default "Valor já existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String fieldName();
    Class<?> entityClass();
}
