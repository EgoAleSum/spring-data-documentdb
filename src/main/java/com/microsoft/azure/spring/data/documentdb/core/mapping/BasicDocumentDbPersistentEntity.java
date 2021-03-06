/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for
 * license information.
 */

package com.microsoft.azure.spring.data.documentdb.core.mapping;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.TypeInformation;
import org.springframework.expression.spel.support.StandardEvaluationContext;


public class BasicDocumentDbPersistentEntity<T> extends BasicPersistentEntity<T, DocumentDbPersistentProperty>
        implements DocumentDbPersistentEntity<T>, ApplicationContextAware {

    private final StandardEvaluationContext context;

    public BasicDocumentDbPersistentEntity(TypeInformation<T> typeInformation) {
        super(typeInformation);
        this.context = new StandardEvaluationContext();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context.addPropertyAccessor(new BeanFactoryAccessor());
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        context.setRootObject(applicationContext);
    }

    public String getCollection() {
        return "";
    }

    @Override
    public String getLanguage() {
        return "";
    }
}
