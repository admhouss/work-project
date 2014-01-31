package by.premiya.olga.project.util;

import by.premiya.olga.project.util.annotations.InjectBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author vlad
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static Object getBean(String name) {
        Object bean = context.getBean(name);
        if (bean == null) {
            throw new NoSuchBeanDefinitionException(name);
        }
        return bean;
    }
}