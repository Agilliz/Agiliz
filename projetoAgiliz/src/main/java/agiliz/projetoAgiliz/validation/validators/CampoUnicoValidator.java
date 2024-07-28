package agiliz.projetoAgiliz.validation.validators;

import agiliz.projetoAgiliz.configs.security.Exception.ResponseEntityException;
import agiliz.projetoAgiliz.validation.annotations.CampoUnico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

@Component
public class CampoUnicoValidator implements ConstraintValidator<CampoUnico, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private String fieldName;
    private Class<?> entityClass;
    private String message;


    @Override
    public void initialize(CampoUnico campoUnico) {
        this.fieldName = campoUnico.fieldName();
        this.entityClass = campoUnico.entityClass();
        this.message = campoUnico.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) return true;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> query = cb.createQuery(Long.class);

        Root<?> root = query.from(entityClass);

        query.select(cb.count(root))
                .where(cb.equal(root.get(fieldName), value));

        // deve ter um jeito melhor de fazer isso aqui eu vejo depois
        if(entityManager.createQuery(query).getSingleResult() > 0)
            throw new ResponseEntityException(
                    HttpStatusCode.valueOf(409),
                    context.getDefaultConstraintMessageTemplate(),
                    409
            );

        return true;
    }
}
