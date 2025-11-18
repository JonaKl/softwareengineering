package de.rh.softwareengineering;

import de.rh.softwareengineering.application.Application;
import de.rh.softwareengineering.application.ApplicationItem;
import de.rh.softwareengineering.country.Country;
import de.rh.softwareengineering.hostuniversity.HostUniversity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
	private Application application;

	@BeforeEach
	public void setUp() {
		Country usa = new Country("USA");
		Country slovenia = new Country("Slovenia");
		HostUniversity wyoming = new HostUniversity(usa, "University of Wyoming", "Laramie");
		HostUniversity ljubljana = new HostUniversity(slovenia, "University of Ljubljana", "LJubljana");
		application = new Application("WS 2019/2020", "WS 2019/2020", "C1", "Strengthen intercultural skills");
		application.createApplicationItem(wyoming, 1);
		application.createApplicationItem(ljubljana, 2);
	}

	@Test
	public void testGetApprovedApplicationItems() {
		application.getApplicationItems().get(0).setAdmitted(true);
		int expResult= 1;
		ArrayList<ApplicationItem> result = application.getApprovedApplicationItems();
		assertEquals(expResult, result.size());
	}
		
	@Test
	public void testNoApprovedApplicationItems() {
		int expResult = 0;
		ArrayList<ApplicationItem> result = application.getApprovedApplicationItems();
		assertEquals(expResult, result.size());
	}
}