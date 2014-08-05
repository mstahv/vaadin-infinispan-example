package org.vaadin.infinispan;

import com.vaadin.cdi.UIScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.infinispan.qualifiers.Selected;
import org.vaadin.maddon.fields.MTable;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@UIScoped
public class MyEntityList extends MTable<MyEntity> {

	@Inject
	@Selected
	javax.enterprise.event.Event<MyEntity> event;

	public MyEntityList() {
		super(MyEntity.class);
		withFullWidth();
	}

	@PostConstruct
	protected void init() {
		setSelectable(true);
		addMValueChangeListener(e -> event.fire(e.getValue()));
	}

}
