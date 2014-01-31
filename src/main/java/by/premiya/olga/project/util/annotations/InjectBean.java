package by.premiya.olga.project.util.annotations;

import java.lang.annotation.*;

/**
 * This annotation is used instead of {@code @Autowired} or {@code @Inject} annotations
 *
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see javax.inject.Inject
 *
 * @author vlad
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface InjectBean {
    /**
     * value - Name of bean that you want ot inject
     * */
    String value();
}
