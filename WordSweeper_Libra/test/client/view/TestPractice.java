package client.view;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.Test;

import client.model.Model;

public class TestPractice {
	Model model = new Model();
	Application app = new Application(model);
	
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
