package com.phl.namespace;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Created by panhongliang on 15/12/18.
 */
public class PhlBeanDefinitionParser implements BeanDefinitionParser{
    private final Class<?> beanClass;
    public PhlBeanDefinitionParser(Class<?> beanClass){
        this.beanClass=beanClass;
    }
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition beanDefinition=new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);

        String id=element.getAttribute("id");
        if(id==null || id.length()==0){
            throw new IllegalStateException("spring bean id is null");
        }
        parserContext.getRegistry().registerBeanDefinition(id,beanDefinition);
        if(Book.class.equals(beanClass)){
            NamedNodeMap attrs=element.getAttributes();
            parseBookDefinition(attrs, beanDefinition);
        }
        if(People.class.equals(beanClass)){
            NamedNodeMap attrs=element.getAttributes();
            parsePeopleDefinition(attrs, beanDefinition);
        }
        return null;
    }
    private void parseBookDefinition(NamedNodeMap attrs,BeanDefinition beanDefinition){
        int len=attrs.getLength();
        for(int i=0;i<len;i++){
            Node n=attrs.item(i);
            String name=n.getLocalName();
            String value=n.getNodeValue();
            beanDefinition.getPropertyValues().addPropertyValue(name,value);
        }
    }
    private void parsePeopleDefinition(NamedNodeMap attrs,BeanDefinition beanDefinition){
        int len=attrs.getLength();
        for(int i=0;i<len;i++){
            Node n=attrs.item(i);
            String name=n.getLocalName();
            String value=n.getNodeValue();
            beanDefinition.getPropertyValues().addPropertyValue(name,value);
        }
    }
}
