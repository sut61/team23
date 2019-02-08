package com.Team23.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

	@Autowired
	private DocumentRepositoty documentRepositoty;
	private DiseaseRepository diseaseRepository;
	private OfficerRepository officerRepository;
	private EligibleDiseasesRepositoty eligibleDiseasesRepositoty;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testEligibleDiseasesAllPass() {


		EligibleDiseases eligibleDiseases = new EligibleDiseases();
		eligibleDiseases.setEligibleDiseasesCode("El0123456789");

		try {
			entityManager.persist(eligibleDiseases);
			entityManager.flush();
			String color = "\u001b[33m";
			String colorwhite = "\u001b[37m";

			System.out.println(color+"--------------------------------------------------" +
					"------------------------------------------------ test All Pass it Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

			fail("Should not pass to this line");

		}
	}

	@Test
	//(expected=javax.persistence.PersistenceException.class)
	public void testEligibleDiseasesUnique() {


		EligibleDiseases eligibleDiseases1 = new EligibleDiseases();


		eligibleDiseases1.setEligibleDiseasesCode("El012345");
		entityManager.persist(eligibleDiseases1);
		entityManager.flush();



		try {
			EligibleDiseases eligibleDiseases2 = new EligibleDiseases();
			eligibleDiseases2.setEligibleDiseasesCode("El012345");
			entityManager.persist(eligibleDiseases2);
			entityManager.flush();
			fail("Should not pass to this line");


		}catch(javax.persistence.PersistenceException e) {

			String color1 = "\u001b[35m";
			String colorwhite = "\u001b[37m";

			System.out.println(color1+e);
			System.out.println("-----------------------------------------------------" +
					"--------------------------------------------- test  Unique Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}
	}

	@Test
	public void testEligibleDiseasesCannotBeNull() {
//		Disease disease = new Disease("1");
//		Officer officer = new Officer("1","1","1","1");
//		DocumentWork documentWork = new DocumentWork("1","1","1");
//		EligibleDiseases eligibleDiseases = new EligibleDiseases(disease,documentWork,officer,"1");
//			eligibleDiseases.setEligibleDiseasesCode(null);
//			eligibleDiseases.setDocumentWork(documentWork);
//			eligibleDiseases.setOfficer(officer);
//			eligibleDiseases.setDisease(disease);
		EligibleDiseases eligibleDiseases = new EligibleDiseases();
		eligibleDiseases.setEligibleDiseasesCode(null);
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
					"------------------------------------------------ test Null Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);


		}
	}

	@Test
	public void testEligibleDiseasesPattern() {


		EligibleDiseases eligibleDiseases = new EligibleDiseases();
		eligibleDiseases.setEligibleDiseasesCode("Pattern Must not pass !!");

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
					"------------------------------------------------ test Pattern Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);


		}
	}

	@Test
	public void testEligibleDiseasesMinSize() {


		EligibleDiseases eligibleDiseases = new EligibleDiseases();
		eligibleDiseases.setEligibleDiseasesCode("El3");


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
			System.out.println(color1+"-----------------------------------------------------" +
					"--------------------------------------------- test Min Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}

	}

	@Test
	public void testEligibleDiseasesMaxSize() {


		EligibleDiseases eligibleDiseases = new EligibleDiseases();
		eligibleDiseases.setEligibleDiseasesCode("Elig123456789012345678901234567890");


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
					"------------------------------------------ test Max Size Passed !! " +
					"--------------------------------------------------------------------------------------------------"+colorwhite);
		}

	}

/*
	@Test
	public void testDocNumberCannotBeNull() {
		DocumentWork d = new DocumentWork("null","0123456789","B5901609");


		try {
			entityManager.persist(d);
			entityManager.flush();

			fail(" Number of Document must not be null to be valid");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}



	@Test
	public void testTitleSize() {
		DocumentWork d1 = new DocumentWork("00000","1","B5901609");


		try {
			entityManager.persist(d1);
			entityManager.flush();

			fail(" Title of Document value must not exceed 50 and not less than 10");
		} catch(javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
		}
	}
*/
}

