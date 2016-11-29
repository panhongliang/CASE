package com.phl.namespace;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by panhongliang on 15/12/18.
 */
public class PhlNamespaceHandleer extends NamespaceHandlerSupport{
    public void init() {
        registerBeanDefinitionParser("book",new PhlBeanDefinitionParser(Book.class));
        registerBeanDefinitionParser("people",new PhlBeanDefinitionParser(People.class));
    }
}
