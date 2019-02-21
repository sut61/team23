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

	@Autowired private DocumentRepositoty           documentRepositoty;
	@Autowired private DiseaseRepository            diseaseRepository;
	@Autowired private OfficerRepository            officerRepository;
	@Autowired private EligibleDiseasesRepositoty   eligibleDiseasesRepositoty;
	@Autowired private CardRepository               cardRepository;
	@Autowired private ExpensesRepository           expensesRepository;
	@Autowired private AcceptToUserRepository       acceptToUserRepository;
	@Autowired private TestEntityManager            entityManager;
	@Autowired private ProvinceRepository           provinceRepository;
	@Autowired private HospitalRepository           hospitalRepository;
	@Autowired private RightsTypeRepository         rightsTypeRepository;
	@Autowired private RightRegistrationRepository  rightRegistrationRepository;
	@Autowired private StatusRepository             statusRepository;
	@Autowired private PeopleDiseaseRepository 	    peopleDiseaseRepository;
	@Autowired private TypeDiseaseRepository        typeDiseaseRepository;
	@Autowired private TreatmenthistoryRepository   treatmenthistoryRepository;
	@Autowired private DrugRepository               drugRepository;
	@Autowired private GoldcardRepository           goldcardRepository;
	@Autowired private MedicalSuppliesRepository    medicalSuppliesRepository;
	@Autowired private TrainingRepository		    trainingRepository;
	@Autowired private LecturerRepository           lecturerRepository;
	@Autowired private TypeTrainingRepository       typeTrainingRepository;
	@Autowired private AffiliationRepository        affiliationRepository;
	@Autowired private TypeHospitalRepository       typeHospitalRepository;
	@Autowired private TypesOfDrugsRepository 	    typesOfDrugsRepository;
	@Autowired private DrugRegistrationRepository   drugRegistrationRepository;
	@Autowired private TypesOfDosageFormsRepository typesOfDosageFormsRepository;

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

	//RightRegistrationRightRegistrationRightRegistrationRightRegistrationRightRegistrationRightRegistrationRightRegistrationRightRegistration
	@Test
	public void anyTrue() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername("User");
		rr.setPassword("Pass");
		rr.setFirstname("First");
		rr.setSurname("Sur");
		rr.setTel("0123456789");
		rr.setPersonalcard(1234567890123L);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(gdate2);
		rr.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr.setHospital(hospitalid);

		try {
			entityManager.persist(rr);
			entityManager.flush();

			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Success anyTrue -----------------------------------------------------------------------\n\n");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have anyTrue -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}
	}
	@Test
	public void anyFieldCannotBeNull() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername(null);
		rr.setPassword(null);
		rr.setFirstname(null);
		rr.setSurname(null);
		rr.setTel(null);
		rr.setPersonalcard(null);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(null);
		rr.setBirthday(null);

		provinceid = provinceRepository.findByProvinceName(null);
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName(null);
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName(null);
		rr.setHospital(hospitalid);
		try {
			entityManager.persist(rr);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 11);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have anyFieldCannotBeNull -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void userMoreTwenty() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername("123456789012345678901");
		rr.setPassword("Pass");
		rr.setFirstname("First");
		rr.setSurname("Sur");
		rr.setTel("0123456789");
		rr.setPersonalcard(1234567890123L);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(gdate2);
		rr.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr.setHospital(hospitalid);
		try {
			entityManager.persist(rr);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have userMoreTwenty -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void userLessThree() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername("12");
		rr.setPassword("Pass");
		rr.setFirstname("First");
		rr.setSurname("Sur");
		rr.setTel("0123456789");
		rr.setPersonalcard(1234567890123L);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(gdate2);
		rr.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr.setHospital(hospitalid);
		try {
			entityManager.persist(rr);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have userLessThree -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void firstTelNotZero() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername("User");
		rr.setPassword("Pass");
		rr.setFirstname("First");
		rr.setSurname("Sur");
		rr.setTel("11234567890");
		rr.setPersonalcard(1234567890123L);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(gdate2);
		rr.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr.setHospital(hospitalid);

		try {
			entityManager.persist(rr);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have firstTelNotZero -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void userUnique() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername("test");
		rr.setPassword("Pass");
		rr.setFirstname("First");
		rr.setSurname("Sur");
		rr.setTel("0123456789");
		rr.setPersonalcard(1234567890123L);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(gdate2);
		rr.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr.setHospital(hospitalid);

		entityManager.persist(rr);
		entityManager.flush();

		RightRegistration rr2 = new RightRegistration();

		rr2.setUsername("test");
		rr2.setPassword("Pass");
		rr2.setFirstname("First");
		rr2.setSurname("Sur");
		rr2.setTel("0123456789");
		rr2.setPersonalcard(1234567890124L);
		rr2.setDateregis(gdate2);
		rr2.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr2.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr2.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr2.setHospital(hospitalid);

		try {
			entityManager.persist(rr2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have userUnique -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}
	}
	@Test
	public void personalUnique() {

		RightRegistration rr = new RightRegistration();
		Hospital hospitalid = new Hospital();
		RightsType rightsTypeid = new RightsType();
		Province provinceid = new Province();

		rr.setUsername("test");
		rr.setPassword("Pass");
		rr.setFirstname("First");
		rr.setSurname("Sur");
		rr.setTel("0123456789");
		rr.setPersonalcard(1234567890123L);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

		String sDate2 = "25:05:1998";
		String biDate2 = "25:05:1990";
		LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
		LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
		rr.setDateregis(gdate2);
		rr.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr.setHospital(hospitalid);

		entityManager.persist(rr);
		entityManager.flush();

		RightRegistration rr2 = new RightRegistration();

		rr2.setUsername("test2");
		rr2.setPassword("Pass");
		rr2.setFirstname("First");
		rr2.setSurname("Sur");
		rr2.setTel("0123456789");
		rr2.setPersonalcard(1234567890123L);
		rr2.setDateregis(gdate2);
		rr2.setBirthday(bdate2);

		provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
		rr2.setProvince(provinceid);

		rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
		rr2.setRightsType(rightsTypeid);

		hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
		rr2.setHospital(hospitalid);

		try {
			entityManager.persist(rr2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have personalUnique -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	//RightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsTypeRightsType
	@Test
	public void rightsTypeAnyFieldTrue() {
		RightsType rightstypeid = new RightsType();
		rightstypeid.setRightsTypeName("รับราชการ");
		try {
			entityManager.persist(rightstypeid);
			entityManager.flush();

			System.out.println("\n\n---------------------------------------------------------------------------------- ๅ Success rightsTypeAnyFieldTrue -----------------------------------------------------------------------\n\n");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have rightsTypeAnyFieldTrue -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	@Test
	public void rightsTypeAnyFieldNotNull() {
		RightsType rightstypeid = new RightsType();
		rightstypeid.setRightsTypeName(null);
		try {
			entityManager.persist(rightstypeid);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have rightsTypeAnyFieldNotNull -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//ProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvinceProvince
	@Test
	public void provinceAnyFieldTrue() {
		Province provinceid = new Province();
		provinceid.setProvinceName("ต่างประเทศ");
		try {
			entityManager.persist(provinceid);
			entityManager.flush();

			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Success provinceAnyFieldTrue -----------------------------------------------------------------------\n\n");

		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have provinceAnyFieldTrue -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	@Test
	public void provinceAnyFieldNotNull() {
		Province provinceid = new Province();
		provinceid.setProvinceName(null);
		try {
			entityManager.persist(provinceid);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have provinceAnyFieldNotNull -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}

	//AccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUserAccecptToUser
	@Test
	public void AcceptAnyTure() {

		AcceptToUser acc = new AcceptToUser();

		acc.setCodeAccept("P10");
		acc.setComment("ครบ");
		acc.setAccId(1L);

		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("admin");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N3");
		try {
			entityManager.persist(acc);
//			entityManager.flush();

			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Success AcceptAnyTure -----------------------------------------------------------------------\n\n");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have AcceptAnyTure -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}

	@Test
	public void anyFieldNotNull() {

		AcceptToUser acc = new AcceptToUser();

		acc.setAccId(1L);
		acc.setCodeAccept(null);
		acc.setComment(null);

		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(null);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(null);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(null);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("admin");
		acc.setOfficer(null);

		try {
			entityManager.persist(acc);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 5);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have anyFieldNotNull -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void codeAcceptUnique() {

		AcceptToUser acc = new AcceptToUser();
		acc.setCodeAccept("F6");
		acc.setComment("มีผู้ใช้แล้ว");
		acc.setAccId(6L);
		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("admin");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N3");

		entityManager.persist(acc);
		entityManager.flush();

		AcceptToUser acc2 = new AcceptToUser();
		acc2.setCodeAccept("F6");
		acc2.setComment("มีผู้ใช้แล้ว");
		acc2.setAccId(7L);
		RightRegistration rightRegistrationId2 = new RightRegistration();
		rightRegistrationId2 = rightRegistrationRepository.findByRegId(2L);
		acc2.setRightRegistration(rightRegistrationId2);

		String Date2 = "25:02:2019";
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate2 = LocalDate.parse(Date2, formatter2);
		acc2.setDateAccept(adate2);

		Status statusid2 = new Status();
		statusid2 = statusRepository.findByStatusName("Fail");
		acc2.setStatus(statusid2);

		Officer officerid2 = new Officer();
		officerid2 = officerRepository.findByOfficerName("admin");
		acc.setOfficer(officerid2);

		acc2.setDocumentCode("N4");

		try {
			entityManager.persist(acc2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have codeAcceptUnique -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}

	@Test
	public void documentCodeUnique() {

		AcceptToUser acc = new AcceptToUser();
		acc.setAccId(8L);
		acc.setCodeAccept("F8");
		acc.setComment("ข้อมูลถูกต้อง");

		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("admin");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N8");

		entityManager.persist(acc);
		entityManager.flush();

		AcceptToUser acc2 = new AcceptToUser();
		acc2.setAccId(9L);
		acc2.setCodeAccept("F9");
		acc2.setComment("ข้อมูลถูกต้อง");

		RightRegistration rightRegistrationId2 = new RightRegistration();
		rightRegistrationId2 = rightRegistrationRepository.findByRegId(2L);
		acc2.setRightRegistration(rightRegistrationId2);

		acc2.setDateAccept(adate1);
		acc2.setStatus(statusid);
		acc2.setOfficer(officerid);

		acc2.setDocumentCode("N8");
		try {
			entityManager.persist(acc2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have documentCodeUnique -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}

	@Test
	public void acceptIDUnique() {

		AcceptToUser acc = new AcceptToUser();
		acc.setAccId(10L);
		acc.setCodeAccept("F10");
		acc.setComment("ข้อมูลถูกต้อง");

		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("admin");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N10");

		entityManager.persist(acc);
		entityManager.flush();

		AcceptToUser acc2 = new AcceptToUser();
		acc2.setAccId(10L);
		acc2.setCodeAccept("F11");
		acc2.setComment("ข้อมูลถูกต้อง");

		RightRegistration rightRegistrationId2 = new RightRegistration();
		rightRegistrationId2 = rightRegistrationRepository.findByRegId(2L);
		acc2.setRightRegistration(rightRegistrationId2);

		acc2.setDateAccept(adate1);
		acc2.setStatus(statusid);
		acc2.setOfficer(officerid);

		acc2.setDocumentCode("N11");
		try {
			entityManager.persist(acc2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have acceptIDUnique -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}

	@Test
	public void AcceptCodeNotTrue() {

		AcceptToUser acc = new AcceptToUser();
		acc.setAccId(3L);
		acc.setCodeAccept("B1");
		acc.setComment("ข้อมูลถูกต้อง");

		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("ไม่มีเจ้าหน้าที่");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N3");
		try {
			entityManager.persist(acc);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have AcceptCodeNotTrue -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void CommentNotShort() {

		AcceptToUser acc = new AcceptToUser();
		acc.setAccId(4L);
		acc.setCodeAccept("F1");
		acc.setComment("ไ");
		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("ไม่มีเจ้าหน้าที่");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N3");
		try {
			entityManager.persist(acc);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have CommentNotShort -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}
	@Test
	public void CommentNotLong() {

		AcceptToUser acc = new AcceptToUser();
		acc.setAccId(5L);
		acc.setCodeAccept("F1");
		acc.setComment("0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789");
		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Fail");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("ไม่มีเจ้าหน้าที่");
		acc.setOfficer(officerid);

		acc.setDocumentCode("N3");
		try {
			entityManager.persist(acc);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have CommentNotLong -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}

	@Test
	public void documentCodeNotTrue() {

		AcceptToUser acc = new AcceptToUser();
		acc.setAccId(12L);
		acc.setCodeAccept("P12");
		acc.setComment("ข้อมูลถูกต้อง");

		RightRegistration rightRegistrationId = new RightRegistration();
		rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
		acc.setRightRegistration(rightRegistrationId);

		String Date1 = "25:01:2019";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		LocalDate adate1 = LocalDate.parse(Date1, formatter);
		acc.setDateAccept(adate1);

		Status statusid = new Status();
		statusid = statusRepository.findByStatusName("Pass");
		acc.setStatus(statusid);

		Officer officerid = new Officer();
		officerid = officerRepository.findByOfficerName("ไม่มีเจ้าหน้าที่");
		acc.setOfficer(officerid);

		acc.setDocumentCode("A12");
		try {
			entityManager.persist(acc);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have documentCodeNotTrue -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}

	}


	//StatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatusStatus
	@Test
	public void statusAnyfieldNotnull() {
		Status statusid= new Status();
		statusid.setStatusName(null);
		try {
			entityManager.persist(statusid);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 2 Have statusAnyfieldNotnull -----------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");

		}
	}

	//Disease////Disease////Disease////Disease////Disease////Disease////Disease////Disease////Disease////Disease////Disease//
	// ทดสอบหข้อมูลปกติ
	@Test
	public void testDiseaseNameTrue() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคมะเร็ง");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ผมร่วง");
		dis.setCause("ดมควันบุหรี่");
		dis.setRemedy("กินยา");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			System.out.println("\n\n------------------------------------------------------------------Disease Success --------------------------------------------------------------------\n\n");
			System.out.println("Success");
			System.out.println("\n\n------------------------------------------------------------------Disease Success --------------------------------------------------------------------\n\n");


		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testDiseaseCannotBeNull() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName(null);
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("500-600 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ผมร่วง");
		dis.setCause("ดมควันบุหรี่");
		dis.setRemedy("กินยา");

		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------DiseaseName Null  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------DiseaseName Null  --------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบ Size ความยาวไม่ถึง
	@Test
	public void testDiseaseSizeShot() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคก");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคไม่ติดต่อหรือไม่ติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ไม่รู้จะใส่อะไร");
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy("ไม่รู้จะใส่อะไร");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------DiseaseName SizeShot  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------DiseaseName SizeShot  --------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบ Size ความยาวไม่ถึง
	@Test
	public void testDiseaseSizeShotSymptom() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคกระดูก");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคไม่ติดต่อหรือไม่ติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("สวย");
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy("ไม่รู้จะใส่อะไร");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Symptom DiseaseName SizeShot  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Symptom DiseaseName SizeShot  --------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบ Size ความยาวเกิน
	@Test
	public void testDiseaseSizeLong() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรค1234567890123456789012345678901234567890");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคไม่ติดต่อหรือไม่ติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("700-800 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ไม่รู้จะใส่อะไร");
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy("ไม่รู้จะใส่อะไร");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------DiseaseName SizeLong  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------DiseaseName SizeLong  --------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบ Pattern ไม่ตรง
	@Test
	public void testDiseaseNamePatternCannot() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("มะเร็งปากมดลูก");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ปวดท้อง");
		dis.setCause("ติดเชื้อที่มดลูก");
		dis.setRemedy("กินยาพักผ่อน");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------DiseaseName Pattern  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------DiseaseName Pattern  --------------------------------------------------------------------\n\n");

		}
	}
	@Test
	//(expected=javax.persistence.PersistenceException.class)
	public void testUniqueNameDisease() {
		Disease dis1 = new Disease();
		TypeDisease typeDiseaseid1 = new TypeDisease();
		PeopleDisease peopleDiseaseid1 = new PeopleDisease();

		dis1.setDiseaseName("โรคเบาเค็ม");
		typeDiseaseid1 = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis1.setTypedisease(typeDiseaseid1);
		peopleDiseaseid1 = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
		dis1.setPeopledisease(peopleDiseaseid1);
		dis1.setSymptom("ไม่รู้จะใส่อะไร");
		dis1.setCause("กินเค็มเยอะ");
		dis1.setRemedy("กินยาพักผ่อน");
		entityManager.persist(dis1);
		entityManager.flush();
		try{
			Disease dis2 = new Disease();
			TypeDisease typeDiseaseid2 = new TypeDisease();
			PeopleDisease peopleDiseaseid2 = new PeopleDisease();

			dis2.setDiseaseName("โรคเบาเค็ม");
			typeDiseaseid2 = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
			dis2.setTypedisease(typeDiseaseid2);
			peopleDiseaseid2 = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
			dis2.setPeopledisease(peopleDiseaseid2);
			dis2.setSymptom("ไม่รู้จะใส่อะไร");
			dis2.setCause("ไม่รู้จะใส่อะไร");
			dis2.setRemedy("นอนพักเยอะๆ");
			entityManager.persist(dis2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.persistence.PersistenceException e){
			System.out.println("\n\n------------------------------------------------------------------DiseaseName Unique  --------------------------------------------------------------------\n\n");
			System.out.println(e);

			System.out.println("\n\n------------------------------------------------------------------DiseaseName Unique  --------------------------------------------------------------------\n\n");
		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testSymptomCannotBeNull() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคเรื้อน");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("600-700 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom(null);
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy("ไม่รู้จะใส่อะไร");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Symptom Null  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Symptom Null  --------------------------------------------------------------------\n\n");

		}
	}
	// ทดสอบห้ามเป็น not null
	@Test
	public void testCauseCannotBeNull() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคไข้เลือดออก");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("500-600 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ไม่รู้จะใส่อะไร");
		dis.setCause(null);
		dis.setRemedy("ไม่รู้จะใส่อะไร");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Cause Null  --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Cause Null  --------------------------------------------------------------------\n\n");

		}
	}
	// ทดสอบห้ามเป็น not null
	@Test
	public void testRemedyCannotBeNull() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรควัณโรค");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("500-600 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ไม่รู้จะใส่อะไร");
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy(null);
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Remedy Null --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Remedy Null --------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testTypeDiseaseNameCannotBeNull() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคอาหารเป็นพิษ");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName(null);
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("500-600 คน");
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ไม่รู้จะใส่อะไร");
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy("กินอาหารครบ5หมู้");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------TypeDiseaseName Null --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------TypeDiseaseName Null --------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testPopulationRateCannotBeNull() {
		Disease dis = new Disease();
		TypeDisease typeDiseaseid = new TypeDisease();
		PeopleDisease peopleDiseaseid = new PeopleDisease();

		dis.setDiseaseName("โรคอาหารเป็นพิษ");
		typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคติดต่อหรือโรคติดเชื้อ");
		dis.setTypedisease(typeDiseaseid);
		peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate(null);
		dis.setPeopledisease(peopleDiseaseid);
		dis.setSymptom("ไม่รู้จะใส่อะไร");
		dis.setCause("ไม่รู้จะใส่อะไร");
		dis.setRemedy("กินอาหารครบ5หมู้");
		try {
			entityManager.persist(dis);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------PopulationRate Null --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------PopulationRate Null --------------------------------------------------------------------\n\n");

		}
	}
	//PeopleDisease////PeopleDisease////PeopleDisease////PeopleDisease////PeopleDisease////PeopleDisease////PeopleDisease////PeopleDisease//
	// ทดสอบห้ามเป็น not null
	@Test
	public void testPeopleCannotBeNull() {
		PeopleDisease pp = new PeopleDisease();

		pp.setPopulationRate(null);
		try {
			entityManager.persist(pp);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------People Name Null --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------People Name Null --------------------------------------------------------------------\n\n");

		}
	}

	//TypeDisease////TypeDisease////TypeDisease////TypeDisease////TypeDisease////TypeDisease////TypeDisease////TypeDisease//
	@Test
	public void testTypeDiseaseCannotBeNull() {
		TypeDisease td = new TypeDisease();

		td.setTypeDiseaseName(null);
		try {
			entityManager.persist(td);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------TypeDisease Name Null --------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------TypeDisease Name Null --------------------------------------------------------------------\n\n");

		}
	}

	//Treatmenthistory////Treatmenthistory////Treatmenthistory////Treatmenthistory////Treatmenthistory////Treatmenthistory//
	// ทดสอบหข้อมูลปกติ
	@Test
	public void testCodeTrueTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("12345678TH");
		goldcardid = goldcardRepository.findByGoldcardName("Graph");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "10:02:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Code Success Exception--------------------------------------------------------------------\n\n");
			System.out.println("Success");
			System.out.println("\n\n------------------------------------------------------------------Code Success Exception--------------------------------------------------------------------\n\n");

		}
	}
	// ทดสอบ Pattern ไม่ตรง
	@Test
	public void testPatternCodeCannotTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("1234567AA");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("------------------------------------------------------------------Pattern Code Have Exception--------------------------------------------------------------------");
			System.out.println(e.getMessage());
			System.out.println("------------------------------------------------------------------Pattern Code End Exception--------------------------------------------------------------------");

		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testCodeCannotBeNullTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode(null);
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Code Null Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Code Null End Exception--------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบ Size ความยาวไม่ถึง
	@Test
	public void testSizeCodeCannotTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("123456TH");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Code SizeShot Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Code SizeShot End Exception--------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testTreatDateCannotBeNullTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("123456789TH");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(null);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Date Null Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Date Null End Exception--------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testDrugCannotBeNullTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("123456789TH");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(null);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Drug Null Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Drug Null End Exception--------------------------------------------------------------------\n\n");

		}
	}
	// ทดสอบห้ามเป็น not null
	@Test
	public void testDiseaseCannotNullTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("123456789TH");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(null);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Disease Null Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Disease Null End Exception--------------------------------------------------------------------\n\n");

		}
	}

	// ทดสอบห้ามเป็น not null
	@Test
	public void testGoldcardCannotBeNullTH() {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
		Disease diseaseid = new Disease();

		th.setCode("123456789TH");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(null);
		drugid = drugRepository.findByDrugName("Ampicillin");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date1 = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date1);

		try {
			entityManager.persist(th);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n------------------------------------------------------------------Goldcard Null Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e.getMessage());
			System.out.println("\n\n------------------------------------------------------------------Goldcard Null End Exception--------------------------------------------------------------------\n\n");

		}
	}

	@Test
	//(expected=javax.persistence.PersistenceException.class)
	public void testUniqueTreatmenthistoryCodeTH () {
		Treatmenthistory th = new Treatmenthistory();
		Goldcard goldcardid = new Goldcard();
		Drug drugid = new Drug();
	
		Disease diseaseid = new Disease();
		drugid.setDrugName("paracetamal");
		
		th.setCode("123456789TH");
		goldcardid = goldcardRepository.findByGoldcardName("Sun");
		th.setGoldcards(goldcardid);
		drugid = drugRepository.findByDrugName("Enalapril");
		th.setDrugs(drugid);
		diseaseid = diseaseRepository.findByDiseaseName("โรคความดันโลหิตสูง");
		th.setDiseases(diseaseid);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
		String Date1 = "15:05:2019";
		LocalDate date = LocalDate.parse(Date1, formatter);
		th.setTreatDate(date);

		entityManager.persist(th);
		entityManager.flush();
		try{
			Treatmenthistory th1 = new Treatmenthistory();
			Goldcard goldcardid1 = new Goldcard();
			Drug drugid1 = new Drug();
			Disease diseaseid1 = new Disease();
			drugid1.setDrugName("paracetamal");
			th1.setCode("123456789TH");
			goldcardid1 = goldcardRepository.findByGoldcardName("Sun");
			th1.setGoldcards(goldcardid1);
			drugid1 = drugRepository.findByDrugName("Ampicillin");
			th1.setDrugs(drugid1);
			diseaseid1 = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
			th1.setDiseases(diseaseid1);
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
			String Date = "15:05:2019";
			LocalDate date1 = LocalDate.parse(Date, formatter1);
			th1.setTreatDate(date1);

			entityManager.persist(th1);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.persistence.PersistenceException e){
			System.out.println("\n\n------------------------------------------------------------------Code Unique Have Exception--------------------------------------------------------------------\n\n");
			System.out.println(e);
			System.out.println("\n\n------------------------------------------------------------------Code Unique End Exception--------------------------------------------------------------------\n\n");
		}
	}
	//1.33
	@Test
	public void Publicz_True_All() {
		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Suranaree");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			//fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------- 1 Have Publicz_True_All at Publicz ------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//2.34
	@Test
	public void Publicz_PubliczHead_isShortSize() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Sur");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PubliczHead_isShortSize at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	3.35
	@Test
	public void Publicz_PubliczHead_isLongSize() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("SuranareeUniversity Thailand");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PubliczHead_isLongSize at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	4.36
	@Test
	public void Publicz_PublicizeDetail_FristBlank() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Suranaree");
		publicz.setPublicizeDetail(" SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PublicizeDetail_FristBlank at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//5.37
	@Test
	public void Publicz_PubliczHead_isNull() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead(null);
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PubliczHead_isNull at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	6.38
	@Test
	public void Publicz_PubliczHead_Unique() {

		Publicz   publicz1    = new Publicz();

		publicz1.setPubliczHead("WEERAPAT");
		publicz1.setPublicizeDetail("SuranareeUniversity");
		publicz1.setCall("0883120905");
		publicz1.setEmail("oat@hotmail.com");

		entityManager.persist(publicz1);
		entityManager.flush();

		Publicz   publicz2    = new Publicz();

		publicz2.setPubliczHead("WEERAPAT");
		publicz2.setPublicizeDetail("SuranareeUniversity");
		publicz2.setCall("0883120905");
		publicz2.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PubliczHead_Unique at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(e);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//7.39
	@Test
	public void Publicz_Email_isTrue() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Suranaree");
		publicz.setPublicizeDetail(" SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_Email_isTrue at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	8.40
	@Test
	public void Publicz_Email_isNotTrue() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Suranaree");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("0883120905");
		publicz.setEmail("oat@hotmail.co");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_Email_isNotTrue at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//9.41
	@Test
	public void Publicz_PhoneNotTrue_pattern() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Surdfhssdfg");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("3283120905");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PhoneNotTrue_pattern at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	10.42
	@Test
	public void Publicz_PhoneNotTrue_sSize() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Suranaree");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("08831209");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PhoneNotTrue_sSize at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	11.43
	@Test
	public void Publicz_PhoneNotTrue__lSize() {

		Publicz   publicz    = new Publicz();

		publicz.setPubliczHead("Suranaree");
		publicz.setPublicizeDetail("SuranareeUniversity");
		publicz.setCall("0832564");
		publicz.setEmail("oat@hotmail.com");

		try {
			entityManager.persist(publicz);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PhoneNotTrue__lSize at Publicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	12.44
	@Test
	public void Merber_MemberName_NotTrue_isNull() {

		Member   member = new Member();

		member.setMemberName(null);

		try {
			entityManager.persist(member);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Merber_MemberName_NotTrue_isNull at Member -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	13.45
	@Test
	public void Member_True_All() {

		Member   member    = new Member();

		member.setMemberName("Suranaree");

		try {
			entityManager.persist(member);
			entityManager.flush();

			//fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------- 1 Have Member_True_All at Member ------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	14.46
	@Test
	public void Member_MemberName_Unique() {

		Member   member1    = new Member();

		member1.setMemberName("WEERAPAT11");

		entityManager.persist(member1);
		entityManager.flush();

		Member   member2    = new Member();

		member2.setMemberName("WEERAPAT11");

		try {
			entityManager.persist(member2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Member_MemberName_Unique at Member -----------------------------------------------------------------------\n\n");
			System.out.println(e);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	15.47
	@Test
	public void TypeOfPublicz_TypePubName_Unique() {

		TypeOfPublicz   typeOfPublicz1    = new TypeOfPublicz();

		typeOfPublicz1.setTypePubName("TypeA");

		entityManager.persist(typeOfPublicz1);
		entityManager.flush();

		TypeOfPublicz   typeOfPublicz2    = new TypeOfPublicz();

		typeOfPublicz2.setTypePubName("TypeA");

		try {
			entityManager.persist(typeOfPublicz2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch(javax.persistence.PersistenceException e) {
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Member_MemberName_Unique at TypeOfPublicz -----------------------------------------------------------------------\n\n");
			System.out.println(e);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	16.48
	@Test
	public void TypeOfPublicz_True_All() {

		TypeOfPublicz   typeOfPublicz1    = new TypeOfPublicz();

		typeOfPublicz1.setTypePubName("TypeA");

		try {
			entityManager.persist(typeOfPublicz1);
			entityManager.flush();

			//fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------- 1 Have Member_True_All at TypeOfPublicz ------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	17.49
	@Test
	public void TypeOfPublicz_TypePubName_NotTrue_isNull() {

		TypeOfPublicz   typeOfPublicz1    = new TypeOfPublicz();

		typeOfPublicz1.setTypePubName(null);

		try {
			entityManager.persist(typeOfPublicz1);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Merber_MemberName_NotTrue_isNull at TypeOfPublicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	18.50
	@Test
	public void TypeOfPublicz_TypePubName_NotTrue_isLsize() {

		TypeOfPublicz   typeOfPublicz1    = new TypeOfPublicz();

		typeOfPublicz1.setTypePubName("123456789012345678901234567890");

		try {
			entityManager.persist(typeOfPublicz1);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have Publicz_PhoneNotTrue__lSize at TypeOfPublicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
	//	19.51
	@Test
	public void TypeOfPublicz_TypePubName_NotTrue_isSsize() {

		TypeOfPublicz   typeOfPublicz1    = new TypeOfPublicz();

		typeOfPublicz1.setTypePubName("1");

		try {
			entityManager.persist(typeOfPublicz1);
			entityManager.flush();

			fail("Should not pass to this line");
		}
		catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
			System.out.println("\n\n---------------------------------------------------------------------------------- 1 Have TypeOfPublicz_TypePubName_NotTrue_isSSize at TypeOfPublicz -----------------------------------------------------------------------\n\n");
			System.out.println(violations);
			System.out.println("\n\n----------------------------------------------------------------------------------- End Exception -----------------------------------------------------------------------\n\n");
		}
	}
//------------------------------------------------------------------Unit Test Sprit 2 B5913114----------------------------------------------------------------------
//-------------------------------------------------------testMedicalSuppliesPass------------------------------------------------------
@Test
public void testMedicalSuppliesPass() {
	MedicalSupplies   medicalSupplies    = new MedicalSupplies();
	medicalSupplies.setCodeNumber("C59741");
	medicalSupplies.setMedicalsuppliesName("ABCDE");
	medicalSupplies.setBrandName("STD");
	medicalSupplies.setModelNumber("ADED");
	try {
		entityManager.persist(medicalSupplies);
		entityManager.flush();
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------testMedicalSuppliesPass------------------------------------------------------");
		System.out.println("testMedicalSuppliesPass");
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n");
		
	} catch(javax.validation.ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		assertEquals(violations.isEmpty(), false);
		assertEquals(violations.size(), 1);
		
		
	}
	
}

//-------------------------------------------------------testMedicalSuppliesNotNull------------------------------------------------------
@Test
public void testMedicalSuppliesNotNull() {
	MedicalSupplies   medicalSupplies    = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName(null);

	try {
		entityManager.persist(medicalSupplies);
		entityManager.flush();

		fail("Should not pass to this line");
	} catch(javax.validation.ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		assertEquals(violations.isEmpty(), false);
		assertEquals(violations.size(), 1);
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------testMedicalSuppliesNotNull------------------------------------------------------");
		System.out.println( e.getConstraintViolations());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n");
	}
}

//-------------------------------------------------------testMedicalSuppliesless2----------------------------------------------------	
@Test
public void testMedicalSuppliesless2() {
	MedicalSupplies   medicalSupplies    = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("a");
	try {
		entityManager.persist(medicalSupplies);
		entityManager.flush();

		fail("Should not pass to this line");
	} catch(javax.validation.ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		assertEquals(violations.isEmpty(), false);
		assertEquals(violations.size(), 1);
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------testMedicalSuppliesless2------------------------------------------------------");
		System.out.println( e.getConstraintViolations());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n");
	}
}

//-------------------------------------------------------testMedicalSuppliesthan30------------------------------------------------------
   @Test
	public void testMedicalSuppliesthan30() {
	MedicalSupplies   medicalSupplies    = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("siohfoihasoidhfoihsoidhfoihsdoihfoiihsdoifhoihsdfoihdsf");
	try {
		entityManager.persist(medicalSupplies);
		entityManager.flush();

		fail("Should not pass to this line");
	} catch(javax.validation.ConstraintViolationException e) {
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		assertEquals(violations.isEmpty(), false);
		assertEquals(violations.size(), 1);
		System.out.println("\n\n\n\n\n");
		System.out.println("-------------------------------------------------------testMedicalSuppliesthan30------------------------------------------------------");
		System.out.println( e.getConstraintViolations());
		System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n");
	}

}

//-------------------------------------------------------MedicalSuppliesShouldbeEnglish------------------------------------------------------
@Test
public void testMedicalSuppliesShouldbeEnglish() {
MedicalSupplies   medicalSupplies    = new MedicalSupplies();
medicalSupplies.setMedicalsuppliesName("๓๒๔&หน้ากากอนามัย");
try {
	entityManager.persist(medicalSupplies);
	entityManager.flush();

	fail("Should not pass to this line");
} catch(javax.validation.ConstraintViolationException e) {
	Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	assertEquals(violations.isEmpty(), false);
	assertEquals(violations.size(), 1);
	System.out.println("\n\n\n\n\n");
	System.out.println("-------------------------------------------------------MedicalSuppliesShouldbeEnglish------------------------------------------------------");
	System.out.println( e.getConstraintViolations());
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n\n");	
}

}
//-------------------------------------------------------testMedicalSuppliesunique------------------------------------------------------
@Test
public void testMedicalSuppliesunique() {
MedicalSupplies   medicalSupplies    = new MedicalSupplies();
MedicalSupplies   medicalSuppliesunique    = new MedicalSupplies();
medicalSupplies.setMedicalsuppliesName("days");
medicalSuppliesunique.setMedicalsuppliesName("days");
try {
	entityManager.persist(medicalSupplies);
	entityManager.flush();
	entityManager.persist(medicalSuppliesunique);
	entityManager.flush();


	fail("Should not pass to this line");
} catch(javax.persistence.PersistenceException e) {
	// Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	// assertEquals(violations.isEmpty(), false);
	// assertEquals(violations.size(), 1);
	System.out.println("\n\n\n\n\n");
	System.out.println("-------------------------------------------------------testMedicalSuppliesunique------------------------------------------------------");
	System.out.println( e+"testMedicalSuppliesunique");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n\n");	
}

}
//-------------------------------------------------------testcodeNumberunique------------------------------------------------------
@Test
public void testcodeNumberunique(){
	MedicalSupplies medicalsupplies  = new MedicalSupplies();
	MedicalSupplies medicalSuppliesunique = new MedicalSupplies();
	medicalsupplies.setMedicalsuppliesName("ABCDE"); //Message MedicalSupplies should not null มีข้อความนี้มาจึงต้องเพิ่ม setMedicalSuppliesName
	medicalSuppliesunique.setMedicalsuppliesName("ABCDED");

	medicalsupplies.setCodeNumber("C123456");
	medicalSuppliesunique.setCodeNumber("C123456");
try{

	entityManager.persist(medicalsupplies);
	entityManager.flush();
	entityManager.persist(medicalSuppliesunique);
	entityManager.flush();
	fail("Should not pass to this line");
}catch(javax.validation.ConstraintViolationException e){
	Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	assertEquals(violations.isEmpty(), false);
	assertEquals(violations.size(), 1);
	System.out.println("\n\n\n\n\n");
	System.out.println("-------------------------------------------------------testcodeNumberunique------------------------------------------------------");
	System.out.println(e+"testcodeNumberunique");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n\n\n\n");
}

}
@Test
public void testcodeNumberpattern(){
	MedicalSupplies medicalsupplies  = new MedicalSupplies();
	medicalsupplies.setMedicalsuppliesName("ABCDE"); //Message MedicalSupplies should not null มีข้อความนี้มาจึงต้องเพิ่ม setMedicalSuppliesName
	medicalsupplies.setCodeNumber("C123456");
try{
	entityManager.persist(medicalsupplies);
	entityManager.flush();
	fail("Should not pass to this line");
}catch(javax.validation.ConstraintViolationException e){
	Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	assertEquals(violations.isEmpty(), false);
	assertEquals(violations.size(), 1);
	System.out.println("\n\n\n\n\n");
	System.out.println("-------------------------------------------------------testcodeNumberpattern------------------------------------------------------");
	System.out.println(e+"testcodeNumberpattern");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n\n\n\n");
}

}

//-------------------------------------------------------modelNumberuque------------------------------------------------------
@Test
public void testmodelNumberuque(){
	MedicalSupplies medicalSupplies  = new MedicalSupplies();
	MedicalSupplies medicalSupplies1 = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("Mask");
	medicalSupplies1.setMedicalsuppliesName("Masks");
	medicalSupplies.setModelNumber("MODEL");
	medicalSupplies1.setModelNumber("MODEL");
try{
	entityManager.persist(medicalSupplies);
	entityManager.flush();
	entityManager.persist(medicalSupplies1);
	entityManager.flush();

}catch(javax.persistence.PersistenceException e){
	System.out.println("\n\n\n\n");
	System.out.println("-------------------------------------------------------testmodelNumberuque------------------------------------------------------");
	System.out.println(e+"testmodelNumberuque");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n");
}

}
@Test
public void testmodelNumberless1(){
	MedicalSupplies medicalSupplies  = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("Mask");
	medicalSupplies.setModelNumber("");

try{
	entityManager.persist(medicalSupplies);
	entityManager.flush();
	fail("Should not pass to this line");
}catch(javax.validation.ConstraintViolationException  e){
	Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	assertEquals(violations.isEmpty(), false);
	assertEquals(violations.size(), 1);
	System.out.println("\n\n\n\n");
	System.out.println("-------------------------------------------------------testmodelNumberless1------------------------------------------------------");
	System.out.println(e+"testmodelNumberless1");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n");
}

}
@Test
public void testmodelNumberthan5(){
	MedicalSupplies medicalSupplies  = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("Mask");
	medicalSupplies.setModelNumber("GHUJ58");

try{
	entityManager.persist(medicalSupplies);
	entityManager.flush();
	fail("Should not pass to this line");
}catch(javax.validation.ConstraintViolationException e){
	Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
	assertEquals(violations.isEmpty(), false);
	assertEquals(violations.size(), 1);
	System.out.println("\n\n\n\n");
	System.out.println("-------------------------------------------------------testmodelNumberthan5------------------------------------------------------");
	System.out.println(e+"testmodelNumberthan5");
	System.out.println("----------------------------------------------------------------------------------------------------------------------------");
	System.out.println("\n\n\n\n");
}

}
//--------------------------------------------------------------------testbrandNameshort-------------------------------------------------------------
@Test
public void testbrandNameshort(){
	MedicalSupplies medicalsupplies = new MedicalSupplies();
	medicalsupplies.setMedicalsuppliesName("ABCD");
	medicalsupplies.setBrandName("a");
	try{
		entityManager.persist(medicalsupplies);
		entityManager.flush();

	}catch(javax.validation.ConstraintViolationException e){
		Set<ConstraintViolation<?>> violations =e.getConstraintViolations();
		assertEquals(violations.isEmpty(),false);
		assertEquals(violations.size(),1);
		System.out.println("\n\n\n\n\n");
		System.out.println("---------------------------------------------------testbrandNameshort-------------------------------------------");
		System.out.println(e.getConstraintViolations());
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n\n\n");			
	}

}
//--------------------------------------------------------------------testbrandNamelong-------------------------------------------------------------
@Test
public void testbrandNamelong(){
	MedicalSupplies medicalsupplies = new MedicalSupplies();
	medicalsupplies.setMedicalsuppliesName("Mask");
	medicalsupplies.setBrandName("askdhifoihidohfiwhioekdhfpowsiehfkdkofwiheofihosdfieidkfhowiehfodkfhoekidowfoijdfieik");
	try{
		entityManager.persist(medicalsupplies);
		entityManager.flush();
	}catch(javax.validation.ConstraintViolationException e){
		Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
		assertEquals(violation.isEmpty(),false);
		assertEquals(violation.size(),1);

		System.out.println("\n\n\n\n\n");
		System.out.println("---------------------------------------------------testbrandNamelong-------------------------------------------");
		System.out.println(e.getConstraintViolations());
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n\n\n");	
		
	}
}
//--------------------------------------------------------------------testpropertiesshort-------------------------------------------------------------
@Test
public void testpropertiesshort(){
	MedicalSupplies medicalSupplies =new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("Mask");
	medicalSupplies.setProperties("d");
	try{
		entityManager.persist(medicalSupplies);
		entityManager.flush();
	}catch(javax.validation.ConstraintViolationException e){
		Set<ConstraintViolation<?>> validation =e.getConstraintViolations();
		assertEquals(validation.isEmpty(),false);
		assertEquals(validation.size(),1);
		System.out.println("\n\n\n\n\n");
		System.out.println("---------------------------------------------------testpropertiesshort-------------------------------------------");
		System.out.println(e.getConstraintViolations());
		System.out.println("-----------------------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n\n\n");	
	}
}
//--------------------------------------------------------------------testpropertieslong-------------------------------------------------------------

@Test
public void testpropertieslong(){
	MedicalSupplies medicalSupplies = new MedicalSupplies();
	medicalSupplies.setMedicalsuppliesName("Mask");
	medicalSupplies.setProperties("ihsoadfihsoidhfoihwkdofjowihdofihwihodhsifhowoiheifhoiwheifoihididifowhiefhooihef");
	
	try{
		entityManager.persist(medicalSupplies);
		entityManager.flush();

	}catch(javax.validation.ConstraintViolationException e){
		Set<ConstraintViolation<?>> validation = e.getConstraintViolations();
		assertEquals(validation.isEmpty(),false);
		assertEquals(validation.size(),1);
		System.out.println("\n\n\n\n\n\n\n\n");
		System.out.println("-----------------------------------------testpropertieslong-----------------------------------");
		System.out.println(e.getConstraintViolations());
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("\n\n\n\n\n\n\n\n");

	}

}


	/////////////    Training    /////////////
	@Test
	public void traningaddtrue(){
		Training ta = new Training();
		ta.setTopicTraining("Topic 2");
		ta.setObjectiveTraining("Objective2");
		ta.setImportantTopicTraining("important2");
		ta.setAttendess(120L);
		ta.setExpenditure(15000l);
		System.out.println("\n\n\n\n");

		System.out.println("-----------------------------------------Training Pattern passed -----------------------------------");
		System.out.println("\n\n\n\n");


	}

	@Test
	public void topictrainingisnull(){
		Training ta = new Training();
		ta.setTopicTraining(null);
		ta.setObjectiveTraining("Objective3");
		ta.setImportantTopicTraining("important3");
		ta.setAttendess(123L);
		ta.setExpenditure(15003l);
		try{
			entityManager.persist(ta);
			entityManager.flush();
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
	
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------Training Topic is Null-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");	

	}
}
	@Test
	public void topictrainingmorethansixty(){
		Training ta = new Training();
		ta.setTopicTraining("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		ta.setObjectiveTraining("Objectives");
		ta.setImportantTopicTraining("importants");
		ta.setAttendess(125L);
		ta.setExpenditure(15005l);
		try{
			entityManager.persist(ta);
			entityManager.flush();
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
	
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------Training Topic more than sixty-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");	

	}




}
@Test
	public void topictraininglessthanfive(){
		Training ta = new Training();
		ta.setTopicTraining("a");
		ta.setObjectiveTraining("Objectives");
		ta.setImportantTopicTraining("importants");
		ta.setAttendess(126L);
		ta.setExpenditure(15006l);
		try{
			entityManager.persist(ta);
			entityManager.flush();
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
	
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------Training Topic less than five-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-----------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
	}




}


//--------------------------------------------------------------------Hospital Test-------------------------------------------------------------
@Test 
public void hospitalAllPass(){

				Hospital ho = new Hospital();
				ho.setHospitalName("St.Marry Hospital");
				ho.setBranceFive(52341L);
				ho.setBranceNine(523456289L);
				ho.setHospitalAddress("hospitalAddress66");
				ho.setHospitalPhone("0440005061");
				ho.setHospitalPostcode(30607L);
		System.out.println("\n\n\n\n");
		System.out.println("-----------------------------------------Hospital Pattern passed -----------------------------------");
		
		System.out.println("\n\n\n\n");
}

@Test
public void hospitalphonenotzeroinfirst(){
				Hospital ho = new Hospital();
				ho.setHospitalName("St.Marry HospitalA");
				ho.setBranceFive(52342L);
				ho.setBranceNine(523456287L);
				ho.setHospitalAddress("hospitalAddress67");
				ho.setHospitalPhone("1144000506");
				ho.setHospitalPostcode(31107L);
				try{
					entityManager.persist(ho);
					entityManager.flush();
				}catch(javax.validation.ConstraintViolationException e){
					Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
					assertEquals(violation.isEmpty(),false);
					assertEquals(violation.size(),1);
			
					System.out.println("\n\n\n\n\n");
					System.out.println("---------------------------------------------------Hospital First digit phone number is not ZERO-------------------------------------------");
					System.out.println(e.getConstraintViolations());
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println("\n\n\n\n\n\n\n");
			}
}

@Test
public void hospitalNameIsNull(){
				Hospital ho = new Hospital();
				ho.setHospitalName(null);
				ho.setBranceFive(82342L);
				ho.setBranceNine(823456287L);
				ho.setHospitalAddress("hospitalAddress87");
				ho.setHospitalPhone("0440008506");
				ho.setHospitalPostcode(31187L);
				try{
					entityManager.persist(ho);
					entityManager.flush();
				}catch(javax.validation.ConstraintViolationException e){
					Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
					assertEquals(violation.isEmpty(),false);
					assertEquals(violation.size(),1);
			
					System.out.println("\n\n\n\n\n");
					System.out.println("---------------------------------------------------Hospital Name is Null-------------------------------------------");
					System.out.println(e.getConstraintViolations());
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println("\n\n\n\n\n\n\n");
			}
}

@Test
public void hospitalNameIslessThanThree(){
				Hospital ho = new Hospital();
				ho.setHospitalName("H");
				ho.setBranceFive(82442L);
				ho.setBranceNine(823444287L);
				ho.setHospitalAddress("hospitalAddress44");
				ho.setHospitalPhone("0444485006");
				ho.setHospitalPostcode(31447L);
				try{
					entityManager.persist(ho);
					entityManager.flush();
				}catch(javax.validation.ConstraintViolationException e){
					Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
					assertEquals(violation.isEmpty(),false);
					assertEquals(violation.size(),1);
			
					System.out.println("\n\n\n\n\n");
					System.out.println("---------------------------------------------------Hospital Name less than Three-------------------------------------------");
					System.out.println(e.getConstraintViolations());
					System.out.println("-----------------------------------------------------------------------------------------------------------");
					System.out.println("\n\n\n\n\n\n\n");
			}
}

@Test
public void hospitalbranceisNULL(){
				Hospital ho = new Hospital();
				ho.setHospitalName("Rama 9 Hospital");
				ho.setBranceFive(null);
				ho.setBranceNine(null);
				ho.setHospitalAddress("hospitalAddress111");
				ho.setHospitalPhone("0444481106");
				ho.setHospitalPostcode(31417L);
				try{
					entityManager.persist(ho);
					entityManager.flush();
				}catch(javax.validation.ConstraintViolationException e){
					Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
					assertEquals(violation.isEmpty(),false);
					assertEquals(violation.size(),2);
			
					System.out.println("\n\n\n\n\n");
					System.out.println("---------------------------------------------------Hospital brance is NULL -------------------------------------------");
					System.out.println(e.getConstraintViolations());
					System.out.println("------------------------------------------(branceNine and branceFive)--------------------------------------------------");
					System.out.println("\n\n\n\n\n\n\n");
			}
}
@Test
public void branceNineUnique(){
	Province pro = new Province();
	Affiliation aff = new Affiliation();
	TypeHospital ty = new TypeHospital();

	Hospital hoo = new Hospital();
	hoo.setHospitalName("PoaloHospital");
	hoo.setBranceFive(55555L);
	hoo.setBranceNine(555555555L);
	hoo.setHospitalAddress("hospitalAddress1");
	hoo.setHospitalPhone("0440000000");
	hoo.setHospitalPostcode(30000L);

	aff = affiliationRepository.findByAffiliationName("โรงพยาบาล");
	hoo.setAffiliationName(aff);
	pro = provinceRepository.findByProvinceName("นครราชสีมา");
	hoo.setProvinceName(pro);
	ty = typeHospitalRepository.findByTypeName("เอกชน");
	hoo.setTypeName(ty);

		entityManager.persist(hoo);
		entityManager.flush();

		Hospital hoo2 = new Hospital();
		hoo2.setHospitalName("PoaloHospitalTwo");
		hoo2.setBranceFive(99999L);
		hoo2.setBranceNine(555555555L);
		hoo2.setHospitalAddress("hospitalAddress1Two");
		hoo2.setHospitalPhone("0440000002");
		hoo2.setHospitalPostcode(30002L);

	aff = affiliationRepository.findByAffiliationName("โรงพยาบาล");
	hoo2.setAffiliationName(aff);
	pro = provinceRepository.findByProvinceName("นครราชสีมา");
	hoo2.setProvinceName(pro);
	ty = typeHospitalRepository.findByTypeName("เอกชน");
	hoo2.setTypeName(ty);

		try {
			entityManager.persist(hoo2);
			entityManager.flush();

			fail("Should not pass to this line");
		} catch (javax.persistence.PersistenceException e) {
			
			System.out.println("\n\n\n\n\n");
					System.out.println("---------------------------------------------------Have ฺBranceNine of Hospital Unique -------------------------------------------");
					System.out.println(e.getMessage());
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("\n\n\n\n\n\n\n");
		}
	}


	//--------------------------------------------------Unit Test Sprint 1 B5913114-----------------------------------------------------------------------------------------
	@Test
	public void TestDrugNamePass(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("Tilennall");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

	

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),2);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------DrugName isn't passed-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
			fail("Should not pass to this line");
		}

	}

	@Test
	public void TestDrugNameEnglishOnly(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("พารา");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugNameEnglishOnly-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void TestDrugNameminSize(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("s");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugNameminSize-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}
	@Test
	public void TestDrugNamemaxSize(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("guyguyguyguuygyguygugyguygyuguyguyguyguygyguyguyguyguyguyguyguygyuguygyugyuguuyguguygyuguyguyguyguyguyg");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugNamemaxSize-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}
	@Test
	public void TestDrugNamenotNull(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName(null);
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugNamenotNull-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}
	@Test
	public void TestDrugNameUnique(){
		Drug               drug 			  = new Drug();
		Drug               drug1 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("abcd");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		drug1.setDrugName("abcd");
		drug1.setTypesOfDrugs(typesOfDrugs);
		drug1.setDrugRegistration(drugRegistration);
		drug1.setTypesOfDosageForms(typesOfDosageForms);
		drug1.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			entityManager.persist(drug1);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.persistence.PersistenceException e){
	
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugNameUnique-------------------------------------------");
			System.out.println(e +"TestDrugNameUnique");
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}


	@Test
	public void TestTypesOfDrugsnotNull(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName(null);
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("abcd");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestTypesOfDrugsnotNull-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}


	@Test
	public void TestDrugRegistrationnotNull(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName(null);
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("abcd");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugRegistrationnotNull-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}
	@Test
	public void TestTypesOfDosageFormsnotNull(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName(null);
		disease 				= 	diseaseRepository.findByDiseaseName("โรคเบาหวาน");

		drug.setDrugName("abcd");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestTypesOfDosageFormsnotNull-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}

	@Test
	public void TestDiseasenotNull(){
		Drug               drug 			  = new Drug();
		TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
		DrugRegistration   drugRegistration   = new DrugRegistration();
		TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
		Disease            disease            = new Disease();
		

		typesOfDrugs 			= 	typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
		drugRegistration 		=	drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
		typesOfDosageForms 		= 	typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
		disease 				= 	diseaseRepository.findByDiseaseName(null);

		drug.setDrugName("abcd");
		drug.setTypesOfDrugs(typesOfDrugs);
		drug.setDrugRegistration(drugRegistration);
		drug.setTypesOfDosageForms(typesOfDosageForms);
		drug.setDisease(disease);

		try{
			entityManager.persist(drug);
			entityManager.flush();
			System.out.println("---------------------------------------------------TestDrugNamePass is passed------------------------------------");
			fail("Should not pass to this line");
		}catch(javax.validation.ConstraintViolationException e){
			Set<ConstraintViolation<?>> violation=e.getConstraintViolations();
			assertEquals(violation.isEmpty(),false);
			assertEquals(violation.size(),1);
			System.out.println("\n\n\n\n\n");
			System.out.println("---------------------------------------------------TestDrugNamenotNull-------------------------------------------");
			System.out.println(e.getConstraintViolations());
			System.out.println("-------------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\n\n\n\n\n\n");
		}
	}




}

