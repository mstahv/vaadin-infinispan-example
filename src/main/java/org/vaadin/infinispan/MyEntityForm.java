package org.vaadin.infinispan;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.infinispan.qualifiers.Saved;
import org.vaadin.maddon.fields.MTextField;
import org.vaadin.maddon.form.AbstractForm;
import org.vaadin.maddon.layouts.MVerticalLayout;

import com.vaadin.cdi.UIScoped;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

@UIScoped
public class MyEntityForm extends AbstractForm<MyEntity> {

    @Inject
    @Saved
    javax.enterprise.event.Event<MyEntity> savedEvent;

    TextField name = new MTextField("Name");

    TextField foo = new MTextField("foo");

    TextField bar = new MTextField("bar");

    @PostConstruct
    void init() {
        setSavedHandler(myData -> {
            savedEvent.fire(myData);
        });
        setEagarValidation(true);
    }

    @Override
    protected Component createContent() {
        return new MVerticalLayout(
                new FormLayout(
                        name,
                        foo,
                        bar
                ),
                getToolbar()
        );
    }

}
