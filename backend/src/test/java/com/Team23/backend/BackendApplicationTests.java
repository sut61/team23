package com.Team23.backend;

import javafx.scene.paint.Color;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.Team23.backend.Entity.*;
import com.Team23.backend.Repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BackendApplicationTests {

	@Autowired private DocumentRepositoty documentRepositoty;
	@Autowired private DiseaseRepository diseaseRepository;
	@Autowired private OfficerRepository officerRepository;
	@Autowired private EligibleDiseasesRepositoty eligibleDiseasesRepositoty;
	@Autowired private CardRepository cardRepository;
	@Autowired private ExpensesRepository expensesRepository;
	@Autowired private AcceptToUserRepository acceptToUserRepository;
	@Autowired private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/////////////    EligibleDiseases    /////////////
	@Test
	public void test_EligibleDiseases_All_Pass() {
		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases = new EligibleDiseases(disease,documentWork,officer,"El0123456789");


		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();
			String color = "\u001b[33m";
			String colorwhite = "\u001b[37m";

			System.out.println(color+"--------------------------------------------------" +
					"------------------------------------------------ test EligibleDiseases All Pass it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

			fail("Should not pass to this line");

		}
	}
	@Test
	public void test_EligibleDiseases_Unique() {
//(expected=javax.persistence.PersistenceException.class)
		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases1 = new EligibleDiseases(disease,documentWork,officer,"El0123456789");

		entityManager.persist(eligibleDiseases1);
		entityManager.flush();

		try {
			EligibleDiseases eligibleDiseases2 = new EligibleDiseases(disease,documentWork,officer,"El0123456789");
					entityManager.persist(eligibleDiseases2);
			entityManager.flush();
			fail("Should not pass to this line");


		}catch(javax.persistence.PersistenceException e) {

			String color1 = "\u001b[35m";
			String colorwhite = "\u001b[37m";

			System.out.println(color1+e);
			System.out.println("-----------------------------------------------------" +
					"--------------------------------------------- test EligibleDiseases Unique it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_EligibleDiseases_Disease_Null() {
		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases = new EligibleDiseases(null,documentWork,officer,"El0123456789");
		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[36m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println("--------------------------------------------------" +
					"------------------------------------------------ test EligibleDiseases Disease Null it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);


		}
	}
	@Test
	public void test_EligibleDiseases_Document_Null() {

		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases = new EligibleDiseases(disease,null,officer,"El0123456789");

		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";

			System.out.println(color1+e.getConstraintViolations());

			System.out.println("--------------------------------------------------" +
					"------------------------------------------------ test EligibleDiseases Document Null it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);


		}
	}
	@Test
	public void test_EligibleDiseases_Officer_Null() {


		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases = new EligibleDiseases(disease,documentWork,null,"El0123456789");


		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test EligibleDiseases Officer Null it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}

	}
	@Test
	public void test_EligibleDiseases_Code_Max_Size() {

		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases = new EligibleDiseases(disease,documentWork,officer,"El01234567892222222222222222222222222222222222222222222");


		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[34m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"--------------------------------------------------------" +
					"------------------------------------------ test EligibleDiseases Code Max Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}

	}
	@Test
	public void test_EligibleDiseases_Code_Min_Size() {

		Disease disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		Officer officer = officerRepository.findByOfficerName("Kanathip Poungtham");
		DocumentWork documentWork = documentRepositoty.findBynumberDocument("10001");
		EligibleDiseases eligibleDiseases = new EligibleDiseases(disease,documentWork,officer,"El1");


		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[32m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"--------------------------------------------------------" +
					"------------------------------------------ test EligibleDiseases Code Min Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}

	}

	/////////////    DocumentWork    /////////////
	@Test
	public void test_DocumentWork_Number_Pattern() {
		DocumentWork d = new DocumentWork("ThisisnotNumber","0123456789","httpB5901609");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		d.setDate(adate1);


		try {
			entityManager.persist(d);
			entityManager.flush();

			fail("Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println("--------------------------------------------------" +
					"------------------------------------------------ test DocumentWork_Number Pattern Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_DocumentWork_Title_Max_Size() {
		DocumentWork d1 = new DocumentWork("00000","1sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss","httpsB5901609");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		d1.setDate(adate1);

		try {
			entityManager.persist(d1);
			entityManager.flush();

			fail("Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[34m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test DocumentWork_Title Max Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_DocumentWork_Title_Min_Size() {
		DocumentWork d1 = new DocumentWork("00000","1","httpsB5901609");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		d1.setDate(adate1);

		try {
			entityManager.persist(d1);
			entityManager.flush();

			fail("Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[32m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test DocumentWork_Title Min Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_DocumentWork_Url_Pattern() {
		DocumentWork d1 = new DocumentWork("00000","012345","imnotpatternifusaw");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		d1.setDate(adate1);

		try {
			entityManager.persist(d1);
			entityManager.flush();

			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test DocumentWork_Url Pattern Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_DocumentWork_Date_Null() {
		DocumentWork d = new DocumentWork("102345","0123456789","httpB5901609");
		d.setDate(null);


		try {
			entityManager.persist(d);
			entityManager.flush();

			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[36m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println("--------------------------------------------------" +
					"------------------------------------------------ test DocumentWork_Date Null Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_DocumentWork_All_Pass() {
		DocumentWork d = new DocumentWork("102345","0123456789","httpB5901609");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		d.setDate(adate1);


		try {
			entityManager.persist(d);
			entityManager.flush();
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test DocumentWork All Pass is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			fail(" Should Fail");

		}
	}

	/////////////    Officer    /////////////
	@Test
	public void test_Officer_CallNumber_Pattern() {
		Officer officer = new Officer("test","test1","1234","this is not Number");

		try {
			entityManager.persist(officer);
			entityManager.flush();

			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e.getConstraintViolations());
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer CallNumber Pattern Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Officer_All_Pass() {
		Officer officer = new Officer("test","test1","1234","044852461");

		try {
			entityManager.persist(officer);
			entityManager.flush();

			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer All pass it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			fail(" Should Fail");
		}
	}
	@Test
	public void test_Officer_Name_Null() {
		Officer officer = new Officer(null,"test1","1234","044852461");

		try {
			entityManager.persist(officer);
			entityManager.flush();


			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer Name Null it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Officer_Username_Min_Size() {
		Officer officer = new Officer("test","min","1234","044852461");

		try {
			entityManager.persist(officer);
			entityManager.flush();


			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[32m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer Username Min size it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Officer_Username_Max_Size() {
		Officer officer = new Officer("test","Maxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","1234","044852461");

		try {
			entityManager.persist(officer);
			entityManager.flush();


			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[34m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer Username Max size it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Officer_Password_Min_Size() {
		Officer officer = new Officer("test","username","min","044852461");

		try {
			entityManager.persist(officer);
			entityManager.flush();


			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[32m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer Password Min size it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Officer_Password_Max_Size() {
		Officer officer = new Officer("test","test1","Maxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","044852461");

		try {
			entityManager.persist(officer);
			entityManager.flush();


			fail(" Should Fail");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[34m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"-----------------------------------------------------" +
					"--------------------------------------------- test Officer Password Max size it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}

	/////////////    Expenses    /////////////
	@Test
	public void test_Expenses_All_Pass() {
		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);


		try {
			entityManager.persist(expenses);
			entityManager.flush();
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Expenses All Pass is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			fail(" Should Fail");

		}
	}
	@Test
	public void test_Expenses_Number_Pattern() {
		Expenses expenses = new Expenses("Expenses","this is not Number(int)","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);


		try {
			entityManager.persist(expenses);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Expenses Number Pattern is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		}
	}
	@Test
	public void test_Expenses_Comment_Max_Size() {
		Expenses expenses = new Expenses("Expenses","220","this is comment Maxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);


		try {
			entityManager.persist(expenses);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[34m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Expenses Comment Max Size is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		}
	}
	@Test
	public void test_Expenses_Comment_Min_Size() {
		Expenses expenses = new Expenses("Expenses","220","min");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);


		try {
			entityManager.persist(expenses);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[32m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Expenses Comment Min Size is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		}
	}
	@Test
	public void test_Expenses_Date_Null() {
		Expenses expenses = new Expenses("Expenses","220","comment");
		expenses.setDate(null);


		try {
			entityManager.persist(expenses);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Expenses Date Null is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		}
	}
	@Test
	public void test_Expenses_Name_Null() {
		Expenses expenses = new Expenses(null,"220","comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);


		try {
			entityManager.persist(expenses);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Expenses Name Null is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		}
	}

	/////////////    Card    /////////////
	@Test
	public void test_Card_All_Pass() {
		AcceptToUser acceptToUser = acceptToUserRepository.findByAccId('1');

		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);
		expensesRepository.save(expenses);
		Card card = new Card("card12345",acceptToUser,expenses,"commenttt");
		card.setDate(date);


		try {
			entityManager.persist(card);
			entityManager.flush();
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+"--------------------------------------------------" +
					"------------------------------------------------ test Card All Pass is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+colorwhite);
			fail(" Should Fail");

		}
	}
	@Test
	public void test_Card_Cardcord_Pattern() {
		AcceptToUser acceptToUser = acceptToUserRepository.findByAccId('1');
		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);
		expensesRepository.save(expenses);
		Card card = new Card("This is not Card pattern",acceptToUser,expenses,"commenttt");
		card.setDate(date);


		try {
			entityManager.persist(card);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"--------------------------------------------------" +
					"------------------------------------------------ test Card Cardcord Pattern is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Card_Date_Null() {
		AcceptToUser acceptToUser = acceptToUserRepository.findByAccId('1');
		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);
		expensesRepository.save(expenses);
		Card card = new Card("card code",acceptToUser,expenses,"commenttt");
		card.setDate(null);


		try {
			entityManager.persist(card);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"--------------------------------------------------" +
					"------------------------------------------------ test Card Date Null is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Card_Comment_Min_Size() {
		AcceptToUser acceptToUser = acceptToUserRepository.findByAccId('1');
		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);
		expensesRepository.save(expenses);
		Card card = new Card("card code",acceptToUser,expenses,"min");
		card.setDate(date);


		try {
			entityManager.persist(card);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[32m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"--------------------------------------------------" +
					"------------------------------------------------ test Card Comment min size is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Card_Comment_Max_Size() {
		AcceptToUser acceptToUser = acceptToUserRepository.findByAccId('1');
		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);
		expensesRepository.save(expenses);
		Card card = new Card("card code",acceptToUser,expenses,"Maxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		card.setDate(date);


		try {
			entityManager.persist(card);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[34m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"--------------------------------------------------" +
					"------------------------------------------------ test Card Comment max size is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}
	@Test
	public void test_Card_Expensese_Null() {
		AcceptToUser acceptToUser = acceptToUserRepository.findByAccId('1');
		Expenses expenses = new Expenses("Expenses","120","this is comment");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "08:02:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		expenses.setDate(date);
		expensesRepository.save(expenses);
		Card card = new Card("card code",acceptToUser,null,"This is comment true");
		card.setDate(date);


		try {
			entityManager.persist(card);
			entityManager.flush();
			fail(" Should Fail");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			String color1 = "\u001b[33m";
			String colorwhite = "\u001b[37m";
			System.out.println(color1+e+"--------------------------------------------------" +
					"------------------------------------------------ test Card Expenses Null is Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}



}

