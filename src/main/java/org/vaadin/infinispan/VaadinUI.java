package org.vaadin.infinispan;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.apache.commons.beanutils.BeanUtils;
import org.vaadin.infinispan.qualifiers.Saved;
import org.vaadin.infinispan.qualifiers.Selected;
import org.vaadin.maddon.label.Header;
import org.vaadin.maddon.label.RichText;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

/**
 *
 * @author Matti Tahvonen <matti@vaadin.com>
 */
@CDIUI
@Theme("dawn")
public class VaadinUI extends UI {

	@Inject
	Repository repo;

	@Inject
	MyEntityList entries;

	@Inject
	MyEntityForm form;

	/**
	 * Listens CDI events fired by MyEntityForm
	 * 
	 * @param data
	 */
	void saved(@Observes @Saved MyEntity data) {
		repo.save(data);
		refreshList();
	}

	/**
	 * Listens CDI events fired by MyEntityList
	 * 
	 * @param data
	 */
	void selected(@Observes @Selected MyEntity entity) {
		try {
			// Edit the clone instead of the original, I'm not an Infinispan
			// expert, but I guess the entity might be shared with other users
			// (using @Stateless Repository).
			form.setEntity((MyEntity) BeanUtils.cloneBean(entity));
		} catch (Exception e) {
			throw (new RuntimeException(e));
		}
	}

	protected void refreshList() {
		entries.setBeans(repo.getAll());
	}

	@Override
	protected void init(VaadinRequest request) {
		
		if(repo.size() == 0) {
			MyEntity myEntity = new MyEntity("example", "Entity");
			repo.save(myEntity);
		}
		
		refreshList();

		Button refresh = new Button("Refresh list", e -> refreshList());
		Button newEntry = new Button("Add data",
				e -> form.setEntity(new MyEntity("new entity " + repo.size(),
						"newValue")));

		setContent(new MVerticalLayout(
				new RichText().withMarkDownResource("/intro.md"),
				new MHorizontalLayout(refresh, newEntry),
				new MHorizontalLayout(entries, form).withFullWidth()));

	}

}
