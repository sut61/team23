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
	ApplicationRunner init(RightsTypeRepository       rightsTypeRepository,             RightRegistrationRepository  rightRegistrationRepository,
						   ProvinceRepository         provinceRepository,               HospitalRepository           hospitalRepository, 
						   DiseaseRepository          diseaseRepository,                DocumentRepositoty           documentRepositoty,
						   EligibleDiseasesRepositoty eligibleDiseasesRepositoty ,      OfficerRepositoty            officerRepositoty, 
						   GoldcardRepository         goldcardRepository,               TypesOfDrugsRepository       typesOfDrugsRepository,
						   DrugRegistrationRepository drugRegistrationRepository,       TypesOfDosageFormsRepository typesOfDosageFormsRepository,
						   DrugRepository             drugRepository

						   ) throws Exception {

		return args -> {
			Stream.of("kanathip","pack","se").forEach(officerName -> {
				Officer officer = new Officer();
				officer.setName(officerName);
				officerRepositoty.save(officer);
				if (officerName == "kanathip") {
					officer.setUserName("user1");
					officer.setPassWord("0");
					officerRepositoty.save(officer);
				}else if (officerName == "pack"){
					officer.setUserName("user2");
					officer.setPassWord("0");
					officerRepositoty.save(officer);
				}else if (officerName == "se"){
					officer.setUserName("user3");
					officer.setPassWord("0");
					officerRepositoty.save(officer);
				}
			});

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

			Disease diseaseid = new Disease();
			diseaseid = diseaseRepository.findByDiseaseName("ธาลัสซีเมีย");
			Officer officerid = new Officer();
			officerid = officerRepositoty.findByUserName("pack");
			EligibleDiseases elig2 = new EligibleDiseases(diseaseid,doc4,officerid);
			eligibleDiseasesRepositoty.save(elig2);

			Disease diseaseid1 = new Disease();
			diseaseid1 = diseaseRepository.findByDiseaseName("ปอดอักเสบ (ปอดบวม)");
			Officer officerid1 = new Officer();
			officerid1 = officerRepositoty.findByUserName("kanathip");
			EligibleDiseases elig3 = new EligibleDiseases(diseaseid1,doc5,officerid1);
			eligibleDiseasesRepositoty.save(elig3);

			Disease diseaseid2 = new Disease();
			diseaseid2 = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
			Officer officerid2 = new Officer();
			officerid2 = officerRepositoty.findByUserName("se");
			EligibleDiseases elig4 = new EligibleDiseases(diseaseid2,doc6,officerid2);
			eligibleDiseasesRepositoty.save(elig4);

			Disease diseaseid3 = new Disease();
			diseaseid3 = diseaseRepository.findByDiseaseName("ธาลัสซีเมีย");
			Officer officerid3 = new Officer();
			officerid3 = officerRepositoty.findByUserName("kanathip");
			EligibleDiseases elig1 = new EligibleDiseases(diseaseid3,doc10,officerid3);
			eligibleDiseasesRepositoty.save(elig1);

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
			Stream.of("ยาสามัญ","ยาสามัญประจำบ้าน","ยาอันตราย","ยาควบคุมพิเศษ","ผลิตภัณฑ์เสริมอาหาร").forEach(typesOfDrugsName -> {
		    TypesOfDrugs typesOfDrugs = new TypesOfDrugs();
			typesOfDrugs.setTypesOfDrugsName(typesOfDrugsName);
				typesOfDrugsRepository.save(typesOfDrugs);
				});
				typesOfDrugsRepository.findAll().forEach(System.out::println);

			Stream.of("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดแคปซูล","ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด").forEach(drugRegistrationName -> {
			DrugRegistration drugRegistration = new DrugRegistration();
			drugRegistration.setDrugRegistrationName(drugRegistrationName);
			drugRegistrationRepository.save(drugRegistration);
			});
			drugRegistrationRepository.findAll().forEach(System.out::println);
			
			
		    Stream.of("เม็ดสี่เหลี่ยม","เม็ดสามเหลี่ยม","เม็ดวงกลม","เม็ดวงรี","เม็ดแคปซูล","น้ำ").forEach(typesOfDosageFormsName -> {
			TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
			typesOfDosageForms.setTypesOfDosageFormsName(typesOfDosageFormsName);
			typesOfDosageFormsRepository.save(typesOfDosageForms);
			});
			typesOfDosageFormsRepository.findAll().forEach(System.out::println);

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

			Stream.of("กรดไหลย้อน (เกิร์ด)", "โรคเบาหวาน", "โรคความดันโลหิตสูง", "ไขมันในเลือดสูง", "โรคหลอดเลือดหัวใจ", "ถุงลมโป่งพอง", "ธาลัสซีเมีย", "กรวยไตอักเสบ", "ไข้เลือดออก", "ปอดอักเสบ (ปอดบวม)").forEach(diseaseName -> {
				Disease disease = new Disease();
				disease.setDiseaseName(diseaseName);
				diseaseRepository.save(disease);
			});

			Stream.of("Ampicillin (แอมพิซิลลิน)", "Enalapril (อีนาลาพริล)", "Mannitol (แมนนิทอล)", "Flavoxate (ฟลาโวเซท)").forEach(drugName -> {
				Drug grug = new Drug();
				grug.setDrugName(drugName);
				drugRepository.save(grug);
			});

			Stream.of("Graph", "Sun").forEach(goldcardName -> {
				Goldcard goldcard = new Goldcard();
				goldcard.setGoldcardName(goldcardName);
				goldcardRepository.save(goldcard);
			});

			goldcardRepository.findAll().forEach(System.out::println);
			drugRepository.findAll().forEach(System.out::println);
			diseaseRepository.findAll().forEach(System.out::println);
			rightsTypeRepository.findAll().forEach(System.out::println);
			rightRegistrationRepository.findAll().forEach(System.out::println);
			provinceRepository.findAll().forEach(System.out::println);
		};

	}
}

