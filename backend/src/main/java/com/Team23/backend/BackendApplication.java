package com.Team23.backend;

import com.Team23.backend.Entity.*;
import com.Team23.backend.Repository.*;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.util.*;
import java.util.stream.Stream;
import java.text.*;
import java.text.ParseException;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.time.*;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication

public class BackendApplication  {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}

	@Bean
	ApplicationRunner init(RightsTypeRepository rightsTypeRepository, RightRegistrationRepository rightRegistrationRepository,
						   ProvinceRepository provinceRepository,HospitalRepository hospitalRepository, DiseaseRepository diseaseRepository,DocumentRepositoty documentRepositoty,
						   EligibleDiseasesRepositoty eligibleDiseasesRepositoty , OfficerRepositoty officerRepositoty) throws Exception {

		return args -> {

			Officer off1 = new Officer("kanathip", "user1","0");
			Officer off2 = new Officer("pack", "user2","0");
			Officer off3 = new Officer("se", "user3","0");
			officerRepositoty.save(off1);
			officerRepositoty.save(off2);
			officerRepositoty.save(off3);

			DocumentWork doc1 = new DocumentWork("10001","รับรอง-เพิ่ม-โรค-ปอด#1","https://www.bangkokhospital.com/index.php/th/diseases-treatment/chest_05");
			DocumentWork doc2 = new DocumentWork("10002","รับรอง-เพิ่ม-โรค-หัวใจ#1","https://www.honestdocs.co/signs-to-watch-out-for-heart-disease");
			DocumentWork doc3 = new DocumentWork("10003","รับรอง-เพิ่ม-โรค-ประสาท#1","https://www.honestdocs.co/psychosis-and-neurosis-difference");
			DocumentWork doc4 = new DocumentWork("10004","รับรอง-เพิ่ม-โรค-มะเร็งตับอ่อน#1","https://www.honestdocs.co/what-is-pancreatic-cancer");
			DocumentWork doc5 = new DocumentWork("10005","รับรอง-เพิ่ม-โรค-โรคถุงลมปอดโป่งพอง#1","https://www.honestdocs.co/emphysema-diagnose");
			DocumentWork doc6 = new DocumentWork("10006","รับรอง-เพิ่ม-โรค-กระดูกพรุน#1","https://www.honestdocs.co/pneumonia");
			DocumentWork doc7 = new DocumentWork("10007","รับรอง-เพิ่ม-โรค-ข้อเสื่อม#1","https://www.honestdocs.co/drugs");
			DocumentWork doc8 = new DocumentWork("10008","รับรอง-เพิ่ม-โรค-กล้ามเนื้ออ่อนแรง#1","https://www.honestdocs.co/what-is-myasthenia-gravis");
			DocumentWork doc9 = new DocumentWork("10009","รับรอง-เพิ่ม-โรค-ต้อกระจก#1","https://www.honestdocs.co/cataracts");
			DocumentWork doc10 = new DocumentWork("10010","รับรอง-เพิ่ม-โรค-อาหารเป็นพิษ#1","https://www.honestdocs.co/food-poisoning");
			documentRepositoty.save(doc1);
			documentRepositoty.save(doc2);
			documentRepositoty.save(doc3);
			documentRepositoty.save(doc4);
			documentRepositoty.save(doc5);
			documentRepositoty.save(doc6);
			documentRepositoty.save(doc7);
			documentRepositoty.save(doc8);
			documentRepositoty.save(doc9);
			documentRepositoty.save(doc10);
			Disease dis1 = new Disease("ปอด");
			Disease dis2 = new Disease("หัวใจ");
			Disease dis3 = new Disease("ประสาท");
			Disease dis4 = new Disease("มะเร็งตับอ่อน");
			Disease dis5 = new Disease("โรคถุงลมปอดโป่งพอง");
			Disease dis6 = new Disease("กระดูกพรุน");
			Disease dis7 = new Disease("ข้อเสื่อม");
			Disease dis8 = new Disease("กล้ามเนื้ออ่อนแรง");
			Disease dis9 = new Disease("ต้อกระจก");
			Disease dis10 = new Disease("อาหารเป็นพิษ");
			diseaseRepository.save(dis1);
			diseaseRepository.save(dis2);
			diseaseRepository.save(dis3);
			diseaseRepository.save(dis4);
			diseaseRepository.save(dis5);
			diseaseRepository.save(dis6);
			diseaseRepository.save(dis7);
			diseaseRepository.save(dis8);
			diseaseRepository.save(dis9);
			diseaseRepository.save(dis10);


			EligibleDiseases elig2 = new EligibleDiseases(dis4,doc4,off1);
			EligibleDiseases elig3 = new EligibleDiseases(dis5,doc5,off3);
			EligibleDiseases elig4 = new EligibleDiseases(dis6,doc6,off2);
			EligibleDiseases elig5 = new EligibleDiseases(dis7,doc7,off2);
			EligibleDiseases elig6 = new EligibleDiseases(dis8,doc8,off1);
			EligibleDiseases elig7 = new EligibleDiseases(dis9,doc9,off3);
			EligibleDiseases elig1 = new EligibleDiseases(dis10,doc10,off3);
			eligibleDiseasesRepositoty.save(elig1);
			eligibleDiseasesRepositoty.save(elig2);
			eligibleDiseasesRepositoty.save(elig3);
			eligibleDiseasesRepositoty.save(elig4);
			eligibleDiseasesRepositoty.save(elig5);
			eligibleDiseasesRepositoty.save(elig6);
			eligibleDiseasesRepositoty.save(elig7);


			Stream.of("บัตรทอง","รับราชการ","testrights").forEach(rightsTypeName -> {
				RightsType rightstype = new RightsType();
				rightstype.setRightsTypeName(rightsTypeName);
				rightsTypeRepository.save(rightstype);
			});

			Stream.of("รพ.นครราชสีมา","รพ.กรุงเทพ","testhostpital").forEach(hospitalName -> {
				Hospital hospital = new Hospital();
				hospital.setHospitalName(hospitalName);
				hospitalRepository.save(hospital);
			});

			Stream.of("นครราชสีมา","กรุงเทพ","testprovince").forEach(provinceName -> {
				Province province = new Province();
				province.setProvinceName(provinceName);
				provinceRepository.save(province);
			});

			Stream.of("Graph", "Sun").forEach(userName -> {

				RightRegistration rightRegistration = new RightRegistration();
				Hospital hospitalid = new Hospital();
				RightsType rightsTypeid = new RightsType();
				Province provinceid = new Province();

				rightRegistration.setUsername(userName);
				rightRegistrationRepository.save(rightRegistration);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

				String sDate2 = "25:05:1998";
				String biDate2 = "25:05:1990";
				String sDate3 = "15:09:1998";
				String biDate3 = "15:09:1990";
				LocalDate gdate2 = LocalDate.parse(sDate2, formatter);
				LocalDate bdate2 = LocalDate.parse(biDate2, formatter);
				LocalDate gdate3 = LocalDate.parse(sDate3, formatter);
				LocalDate bdate3 = LocalDate.parse(biDate3, formatter);

				if (userName == "Graph") {

					rightRegistration.setPassword("Pass" + userName);
					rightRegistration.setFirstname("First" + userName);
					rightRegistration.setSurname("Sur" + userName);
					rightRegistration.setTel("0639085430");
					rightRegistration.setPersonalcard(1309902591596L);
					rightRegistration.setDateregis(gdate2);
					rightRegistration.setBirthday(bdate2);

					provinceid = provinceRepository.findByProvinceName("กรุงเทพ");
					rightRegistration.setProvince(provinceid);

					rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
					rightRegistration.setRightsType(rightsTypeid);

					hospitalid = hospitalRepository.findByHospitalName("รพ.กรุงเทพ");
					rightRegistration.setHospital(hospitalid);

					rightRegistrationRepository.save(rightRegistration);
				} else if (userName == "Sun") {

					rightRegistration.setPassword("Pass" + userName);
					rightRegistration.setFirstname("First" + userName);
					rightRegistration.setSurname("Sur" + userName);
					rightRegistration.setTel("0902737190");
					rightRegistration.setPersonalcard(1309902582554L);
					rightRegistration.setDateregis(gdate3);
					rightRegistration.setBirthday(bdate3);

					provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
					rightRegistration.setProvince(provinceid);

					rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
					rightRegistration.setRightsType(rightsTypeid);

					hospitalid = hospitalRepository.findByHospitalName("รพ.นครราชสีมา");
					rightRegistration.setHospital(hospitalid);

					rightRegistrationRepository.save(rightRegistration);
				}
			});

			rightsTypeRepository.findAll().forEach(System.out::println);
			rightRegistrationRepository.findAll().forEach(System.out::println);
			provinceRepository.findAll().forEach(System.out::println);
		};

	}
}

