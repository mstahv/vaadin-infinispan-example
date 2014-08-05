package org.vaadin.infinispan;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
public class MyEntity implements Serializable {

	@NotNull
	private String foo;

	private String bar;

	@NotNull
	private String name;
	
	public MyEntity() {
	}

	public MyEntity(String newName, String newValue) {
		name = newName;
		bar = newValue;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of name
	 *
	 * @param name
	 *            new value of name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the value of bar
	 *
	 * @return the value of bar
	 */
	public String getBar() {
		return bar;
	}

	/**
	 * Set the value of bar
	 *
	 * @param bar
	 *            new value of bar
	 */
	public void setBar(String bar) {
		this.bar = bar;
	}

	/**
	 * Get the value of foo
	 *
	 * @return the value of foo
	 */
	public String getFoo() {
		return foo;
	}

	/**
	 * Set the value of foo
	 *
	 * @param foo
	 *            new value of foo
	 */
	public void setFoo(String foo) {
		this.foo = foo;
	}

}
