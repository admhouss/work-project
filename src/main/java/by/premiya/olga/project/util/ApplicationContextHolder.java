package by.premiya.olga.project.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * This class is used for holding {@code ApplicationContext} and for getting beans if
 * {@code @Autowired} or {@code @Inject} annotations doesn't work.
 *
 * @see org.springframework.context.ApplicationContextAware
 * @see org.springframework.beans.factory.Aware
 * @see org.springframework.beans.factory.annotation.Autowired
 * @see javax.inject.Inject
 *
 * @author vlad
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * Method is works similar to {@code @Autowired} or {@code @Inject} annotations,
     * but if this annotations doesn't work - this method will help you.
     *
     * @param beanName bean name that you want to take.
     * @return specified bean with name {@code beanName}
     * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException if bean with name {@code beanName} doesn't exists in application context
     *
     * @see org.springframework.beans.factory.annotation.Autowired
     * */
    public static Object getBean(String beanName) throws NoSuchBeanDefinitionException {
        Object bean = context.getBean(beanName);
        if (bean == null) {
            throw new NoSuchBeanDefinitionException(beanName);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}