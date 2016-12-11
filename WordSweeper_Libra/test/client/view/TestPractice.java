package client.view;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.Test;

import client.model.Model;

/**@author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * This is responsible for testing "Practice" Boundary class*/
public class TestPractice {
	Model model = new Model();
	Application app = new Application(model);
	
	/** test the fundamental functions in practice as user interface*/
	@Test
	public void testPractice() {
		Practice p = new Practice(model, app);
		for(JButton btn : p.allCells){
			btn.doClick();
		}
		p.submit.doClick();
		p.clear.doClick();
		p.btnRefresh.doClick();	
		p.quit.doClick();
	}
	
}
