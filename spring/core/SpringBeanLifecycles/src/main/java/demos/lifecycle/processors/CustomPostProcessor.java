package demos.lifecycle.processors;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomPostProcessor implements DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        System.out.printf("CustomPostProcessor - postProcessBeforeDestruction called for %s\n", beanName);
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        System.out.printf("CustomPostProcessor - postProcessBeforeDestruction called for %s\n", bean.getClass().getSimpleName());
        return true;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("CustomPostProcessor - postProcessBeforeInitialization called for %s\n", beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.printf("CustomPostProcessor - postProcessAfterInitialization called for %s\n", beanName);
        return bean;
    }
}
