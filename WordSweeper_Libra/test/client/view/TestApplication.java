package client.view;

import static org.junit.Assert.*;

import javax.swing.JButton;

import org.junit.Test;

import client.model.Model;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Application" Boundary class*/
public class TestApplication {

	Model model = new Model();
	
	/**the test for application construction*/
	@Test
	public void testApplication() {
		Application app = new Application(model);
	}
}
