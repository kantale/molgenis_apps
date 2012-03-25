
package org.molgenis.feedback.ui;

import org.molgenis.framework.ui.FreemarkerView;
import org.molgenis.framework.ui.ScreenController;

/**
 * Chd7ContactController takes care of all user requests and application logic.
 *
 * <li>Each user request is handled by its own method based action=methodName. 
 * <li> MOLGENIS takes care of db.commits and catches exceptions to show to the user
 * <li>Chd7ContactModel holds application state and business logic on top of domain model. Get it via this.getModel()/setModel(..)
 * <li>Chd7ContactView holds the template to show the layout. Get/set it via this.getView()/setView(..).
 */
public class Chd7Contact extends Contact
{
	private static final long serialVersionUID = 1L;

	public Chd7Contact(String name, ScreenController<?> parent)
	{
		super(name, parent);
		this.setModel(new ContactModel(this)); //the default model
		this.setView(new FreemarkerView("Contact.ftl", getModel())); //<plugin flavor="freemarker"
		this.getModel().setEmailTo("n.janssen01@umcg.nl");
		this.getModel().setText("If you have any comments, questions or suggestions to improve the CHD7 mutation database, please do not hesitate to contact us. Please enter your name, a valid email address and your message and press \"submit\". We will reply shortly.");
	}
}