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
	ApplicationRunner init(RightsTypeRepository       rightsTypeRepository
			, RightRegistrationRepository  rightRegistrationRepository
			, ProvinceRepository         provinceRepository
			, HospitalRepository           hospitalRepository
			, DiseaseRepository          diseaseRepository
			, DocumentRepositoty           documentRepositoty
			, EligibleDiseasesRepositoty eligibleDiseasesRepositoty
			, OfficerRepository            officerRepository
			, GoldcardRepository         goldcardRepository
			, TypesOfDrugsRepository       typesOfDrugsRepository
			, DrugRegistrationRepository drugRegistrationRepository
			, TypesOfDosageFormsRepository typesOfDosageFormsRepository
			, DrugRepository             drugRepository
			, AffiliationRepository		affiliationRepository
			, TypeHospitalRepository	  typeHospitalRepository
			, MemberRepository memberRepository
			, StatusRepository statusRepository
			, AcceptToUserRepository acceptToUserRepository
			, TypeDiseaseRepository typeDiseaseRepository
			, PeopleDiseaseRepository	peopleDiseaseRepository
			, ExpensesRepository expensesRepository 
			,TypeTrainingRepository typeTrainingRepository
			,LecturerRepository lecturerRepository
			, TrainingRepository trainingRepository
			, PubliczRepository publiczRepository
			, TypeOfPubliczRepository typeOfPubliczRepository
			, MedicalInstrumentRepository medicalInstrumentRepository
			, UseabilityRepository useabilityRepository
			, MedicalSuppliesRepository medicalSuppliesRepository
	) throws Exception {

		return args -> {

			Stream.of("Post1111","Post1112").forEach(publiczHead -> {

				Publicz publicz = new Publicz();
				Hospital hospital = new Hospital();
				TypeOfPublicz typeOfPublicz = new TypeOfPublicz();
				Officer officer = new Officer();

				publicz.setPubliczHead(publiczHead);
				publiczRepository.save(publicz);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

				String DateA = "25:05:1998";
				String DateB = "25:05:1990";

				LocalDate datea = LocalDate.parse(DateA, formatter);
				LocalDate dateb = LocalDate.parse(DateB, formatter);

				if (publiczHead == "Post1111") {

					publicz.setPublicizeDetail("Suranaree University of Technology");
					publicz.setDate_reg(datea);
					publicz.setEmail("oat@hotmail.com");
					publicz.setCall("0883120905");

					officer = officerRepository.findByOfficerName("OAT");
					publicz.setOfficer(officer);

					typeOfPublicz = typeOfPubliczRepository.findByTypePubName("กิจกรรม");
					publicz.setTypeOfPublicz(typeOfPublicz);

					hospital = hospitalRepository.findByHospitalName("รพ.กรุงเทพ");
					publicz.setHospital(hospital);

					publiczRepository.save(publicz);
				}
				if (publiczHead == "Post1112") {

					publicz.setPublicizeDetail("Suranaree University of Technology 2");
					publicz.setDate_reg(datea);
					publicz.setEmail("beem@hotmail.com");
					publicz.setCall("0923120905");

					officer = officerRepository.findByOfficerName("BEEM");
					publicz.setOfficer(officer);

					typeOfPublicz = typeOfPubliczRepository.findByTypePubName("ประชาสัมพันธ์");
					publicz.setTypeOfPublicz(typeOfPublicz);

					hospital = hospitalRepository.findByHospitalName("รพ.กรุงเทพ");
					publicz.setHospital(hospital);

					publiczRepository.save(publicz);
				}
			});
			Stream.of("ประชาสัมพันธ์", "กิจกรรม").forEach(typePubName -> {
				TypeOfPublicz typeOfPublicz = new TypeOfPublicz();
				typeOfPublicz.setTypePubName(typePubName);
				typeOfPubliczRepository.save(typeOfPublicz);
			});
			Stream.of("OATTT", "BEEMM", "JANJOW","ANGGG","admin","Kanathip Poungtham", "Pichakorn Lohanut","Pantamit Sombaddee").forEach(officerName -> {
				Officer officer = new Officer();
				officer.setOfficerName(officerName);



				if(officerName == "OATTT"){
					officer.setCallNumber("0883120905");
					officer.setUserName("OAT1234");
					officer.setPassWord("admin");
					officerRepository.save(officer);
				}
				else if(officerName == "BEEMM"){
					officer.setCallNumber("0883146847");
					officer.setUserName("BEEM1234");
					officer.setPassWord("admin");
					officerRepository.save(officer);
				}
				else if(officerName == "ANGGG"){
					officer.setCallNumber("0880303038");
					officer.setUserName("ANG1234");
					officer.setPassWord("admin");
					officerRepository.save(officer);
				}
				else if(officerName == "JANJOW"){
					officer.setCallNumber("0981546782");
					officer.setUserName("JANJOW1");
					officer.setPassWord("admin");
					officerRepository.save(officer);
				}
				else if(officerName == "admin"){
					officer.setCallNumber("0999999999");
					officer.setUserName("admin");
					officer.setPassWord("admin");
					officerRepository.save(officer);
				}else if(officerName == "Kanathip Poungtham"){
					officer.setCallNumber("0888888888");
					officer.setUserName("user1");
					officer.setPassWord("0000");
					officerRepository.save(officer);
				} else if(officerName == "Pichakorn Lohanut"){
					officer.setCallNumber("0812345678");
					officer.setUserName("user2");
					officer.setPassWord("0000");
					officerRepository.save(officer);
				}else if(officerName == "Pantamit Sombaddee"){
					officer.setCallNumber("0912345687");
					officer.setUserName("user3");
					officer.setPassWord("0000");
					officerRepository.save(officer);
				}
				officerRepository.save(officer);

			});

			Stream.of("10001", "10002","10003","10004", "10005","10006","10007", "10008","10009","100010").forEach(numberDocument -> {
				DocumentWork documentWork = new DocumentWork();

				documentWork.setNumberDocument(numberDocument);
				if(numberDocument == "10001"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-ปอด#1");
					documentWork.setUrl("https://www.bangkokhospital.com/index.php/th/diseases-treatment/chest_05");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "08:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				} else if(numberDocument == "10002"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-หัวใจ#1");
					documentWork.setUrl("https://www.honestdocs.co/signs-to-watch-out-for-heart-disease");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "08:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}else if(numberDocument == "10003"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-ประสาท#1");
					documentWork.setUrl("https://www.honestdocs.co/psychosis-and-neurosis-difference");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "09:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				} else if(numberDocument == "10004"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-มะเร็งตับอ่อน#1");
					documentWork.setUrl("https://www.honestdocs.co/what-is-pancreatic-cancer");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "10:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}else if(numberDocument == "10005"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-โรคถุงลมปอดโป่งพอง#1");
					documentWork.setUrl("https://www.honestdocs.co/emphysema-diagnose");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "11:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}else if(numberDocument == "10006"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-กระดูกพรุน#1");
					documentWork.setUrl("https://www.honestdocs.co/pneumonia");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "12:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}else if(numberDocument == "10007"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-ข้อเสื่อม#1");
					documentWork.setUrl("https://www.honestdocs.co/drugs");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "13:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				} else if(numberDocument == "10008"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-กล้ามเนื้ออ่อนแรง#1");
					documentWork.setUrl("https://www.honestdocs.co/what-is-myasthenia-gravis");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "08:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}else if(numberDocument == "10009"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-ต้อกระจก#1");
					documentWork.setUrl("https://www.honestdocs.co/cataracts");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "14:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}else if(numberDocument == "100010"){
					documentWork.setTitle("รับรอง-เพิ่ม-โรค-อาหารเป็นพิษ#1");
					documentWork.setUrl("https://www.honestdocs.co/food-poisoning");
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
					String Date1 = "15:02:2019";
					LocalDate adate1 = LocalDate.parse(Date1, formatter);
					documentWork.setDate(adate1);
					documentRepositoty.save(documentWork);
				}
			});

			Stream.of("บัตรทอง","รับราชการ").forEach(rightsTypeName -> {
				RightsType rightstype = new RightsType();
				rightstype.setRightsTypeName(rightsTypeName);
				rightsTypeRepository.save(rightstype);
			});

			Stream.of("Pass","Fail").forEach(statusName -> {
				Status st = new Status();
				st.setStatusName(statusName);
				statusRepository.save(st);
			});

			Stream.of("เอกชน","รัฐวิสาหกิจ","ไม่มีในฐานข้อมูล").forEach(typeName -> {
				TypeHospital typehos = new TypeHospital();
				typehos.setTypeName(typeName);
				typeHospitalRepository.save(typehos);

			});
			Stream.of("โรงพยาบาล","คลินิค","ไม่มีในฐานข้อมูล").forEach(affiliationName -> {
				Affiliation affi = new Affiliation();
				affi.setAffiliationName(affiliationName);
				affiliationRepository.save(affi);
			});
			Stream.of("กระบี่","กรุงเทพมหานคร","กาญจนบุรี","กาฬสินธุ์","กำแพงเพชร","ขอนแก่น","จันทบุรี","ฉะเชิงเทรา" ,"ชลบุรี","ชัยนาท","ชัยภูมิ","ชุมพร","เชียงราย","เชียงใหม่","ตรัง","ตราด","ตาก","นครนายก","นครปฐม","นครพนม","นครราชสีมา" ,"นครศรีธรรมราช","นครสวรรค์","นนทบุรี","นราธิวาส","น่าน","บุรีรัมย์","บึงกาฬ","ปทุมธานี","ประจวบคีรีขันธ์","ปราจีนบุรี","ปัตตานี" ,"พะเยา","พังงา","พัทลุง","พิจิตร","พิษณุโลก","เพชรบุรี","เพชรบูรณ์","แพร่","ภูเก็ต","มหาสารคาม","มุกดาหาร","แม่ฮ่องสอน" ,"ยโสธร","ยะลา","ร้อยเอ็ด","ระนอง","ระยอง","ราชบุรี","ลพบุรี","ลำปาง","ลำพูน","เลย","ศรีสะเกษ","สกลนคร","สงขลา" ,"สตูล","สมุทรปราการ","สมุทรสงคราม","สมุทรสาคร","สระแก้ว","สระบุรี","สิงห์บุรี","สุโขทัย","สุพรรณบุรี","สุราษฎร์ธานี" ,"สุรินทร์","หนองคาย","หนองบัวลำภู","อยุธยา","อ่างทอง","อำนาจเจริญ","อุดรธานี","อุตรดิตถ์","อุทัยธานี","อุบลราชธานี","ไม่มีในฐานข้อมูล").forEach(provinceName -> {
				Province province = new Province();
				province.setProvinceName(provinceName);
				provinceRepository.save(province);
			});
			Stream.of("อบรม", "อบรมเชิงปฏิบัติ", "สัมนา").forEach(typeTrainingName -> {
				TypeTraining tt = new TypeTraining();
				tt.setTypeTrainingName(typeTrainingName);
				typeTrainingRepository.save(tt);

			});
			Stream.of("Profressor A", "Profressor B", "Profressor C").forEach(lecturerName -> {
				Lecturer ll = new Lecturer();
				ll.setLecturerName(lecturerName);
				lecturerRepository.save(ll);

			});
			Stream.of("RatchasrimaHospital").forEach(hospitalName -> {
				Province pro = new Province();
				Affiliation aff = new Affiliation();
				TypeHospital ty = new TypeHospital();
				Hospital hospital = new Hospital();
				hospital.setHospitalName(hospitalName);
				hospital.setBranceFive(12345L);
				hospital.setBranceNine(123456789L);
				hospital.setHospitalAddress("hospitalAddress1");
				hospital.setHospitalPhone("0440000000");
				hospital.setHospitalPostcode(30000L);

				aff = affiliationRepository.findByAffiliationName("โรงพยาบาล");
				hospital.setAffiliationName(aff);
				pro = provinceRepository.findByProvinceName("นครราชสีมา");
				hospital.setProvinceName(pro);
				ty = typeHospitalRepository.findByTypeName("เอกชน");
				hospital.setTypeName(ty);
				hospitalRepository.save(hospital);
			});
			Stream.of("BangkokHospitalRatchasrima").forEach(hospitalName -> {
				Province pro = new Province();
				Affiliation aff = new Affiliation();
				TypeHospital ty = new TypeHospital();
				Hospital hospital = new Hospital();
				hospital.setHospitalName(hospitalName);
				hospital.setBranceFive(12341L);
				hospital.setBranceNine(123456289L);
				hospital.setHospitalAddress("hospitalAddress5");
				hospital.setHospitalPhone("0440005000");
				hospital.setHospitalPostcode(30007L);

				aff = affiliationRepository.findByAffiliationName("โรงพยาบาล");
				hospital.setAffiliationName(aff);
				pro = provinceRepository.findByProvinceName("นครราชสีมา");
				hospital.setProvinceName(pro);
				ty = typeHospitalRepository.findByTypeName("เอกชน");
				hospital.setTypeName(ty);
				hospitalRepository.save(hospital);
			});

			Stream.of("การอบรมการจ่ายยาที่เกี่ยวกับบัตรสุขภาพ").forEach(topicTraining -> {
				Training ta = new Training();
				ta.setTopicTraining(topicTraining);
				ta.setImportantTopicTraining("importantTopicTraining");
				ta.setObjectiveTraining("objectiveTraining");
				ta.setAttendess(1000L);
				ta.setExpenditure(10000L);
				
				Hospital hh = hospitalRepository.findByHospitalName("RatchasrimaHospital");
				ta.setHospitalName(hh);

				Lecturer lll = lecturerRepository.findByLecturerName("Profressor A");
				ta.setLecturerName(lll);

				TypeTraining ty = typeTrainingRepository.findByTypeTrainingName("อบรมเชิงปฏิบัติ");
				ta.setTypeTrainingName(ty);

				trainingRepository.save(ta);

			});
			Stream.of("ยาสามัญ","ยาสามัญประจำบ้าน","ยาอันตราย","ยาควบคุมพิเศษ","ผลิตภัณฑ์เสริมอาหาร").forEach(typesOfDrugsName -> {
		    TypesOfDrugs typesOfDrugs = new TypesOfDrugs();
			typesOfDrugs.setTypesOfDrugsName(typesOfDrugsName);
				typesOfDrugsRepository.save(typesOfDrugs);
				});

			Stream.of("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดแคปซูล","ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด").forEach(drugRegistrationName -> {
			DrugRegistration drugRegistration = new DrugRegistration();
			drugRegistration.setDrugRegistrationName(drugRegistrationName);
			drugRegistrationRepository.save(drugRegistration);
			});


		    Stream.of("เม็ดสี่เหลี่ยม","เม็ดสามเหลี่ยม","เม็ดวงกลม","เม็ดวงรี","เม็ดแคปซูล","น้ำ").forEach(typesOfDosageFormsName -> {
			TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
			typesOfDosageForms.setTypesOfDosageFormsName(typesOfDosageFormsName);
			typesOfDosageFormsRepository.save(typesOfDosageForms);
			});

			Stream.of("Graph", "Sun","Bas").forEach(userName -> {

				RightRegistration rightRegistration = new RightRegistration();
				Hospital hospitalid = new Hospital();
				RightsType rightsTypeid = new RightsType();
				Province provinceid = new Province();

				rightRegistration.setUsername(userName);

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
					rightRegistration.setTel("0123456789");
					rightRegistration.setPersonalcard(1309902591596L);
					rightRegistration.setDateregis(gdate2);
					rightRegistration.setBirthday(bdate2);

					provinceid = provinceRepository.findByProvinceName("กรุงเทพมหานคร");
					rightRegistration.setProvince(provinceid);

					rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
					rightRegistration.setRightsType(rightsTypeid);

					hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
					rightRegistration.setHospital(hospitalid);

					rightRegistrationRepository.save(rightRegistration);
				} else if (userName == "Sun") {

					rightRegistration.setPassword("Pass" + userName);
					rightRegistration.setFirstname("First" + userName);
					rightRegistration.setSurname("Sur" + userName);
					rightRegistration.setTel("0123456789");
					rightRegistration.setPersonalcard(1309902582554L);
					rightRegistration.setDateregis(gdate3);
					rightRegistration.setBirthday(bdate3);

					provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
					rightRegistration.setProvince(provinceid);

					rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
					rightRegistration.setRightsType(rightsTypeid);

					hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
					rightRegistration.setHospital(hospitalid);

					rightRegistrationRepository.save(rightRegistration);
				}else if (userName == "Bas") {

					rightRegistration.setPassword("Pass" + userName);
					rightRegistration.setFirstname("First" + userName);
					rightRegistration.setSurname("Sur" + userName);
					rightRegistration.setTel("0123456789");
					rightRegistration.setPersonalcard(1309902555555L);
					rightRegistration.setDateregis(gdate3);
					rightRegistration.setBirthday(bdate3);

					provinceid = provinceRepository.findByProvinceName("นครราชสีมา");
					rightRegistration.setProvince(provinceid);

					rightsTypeid = rightsTypeRepository.findByRightsTypeName("บัตรทอง");
					rightRegistration.setRightsType(rightsTypeid);

					hospitalid = hospitalRepository.findByHospitalName("RatchasrimaHospital");
					rightRegistration.setHospital(hospitalid);

					rightRegistrationRepository.save(rightRegistration);
				}
			});

			Stream.of("โรคติดต่อหรือโรคติดเชื้อ", "โรคไม่ติดต่อหรือไม่ติดเชื้อ").forEach(typeDiseaseName -> {
				TypeDisease typeDisease = new TypeDisease();
				typeDisease.setTypeDiseaseName(typeDiseaseName);
				typeDiseaseRepository.save(typeDisease);
			});

			Stream.of("200-300 คน", "300-400 คน", "400-500 คน", "500-600 คน", "600-700 คน", "700-800 คน", "800-900 คน", "900-1000 คน").forEach(populationRate -> {
				PeopleDisease peopleDisease = new PeopleDisease();
				peopleDisease.setPopulationRate(populationRate);
				peopleDiseaseRepository.save(peopleDisease);
			});


			Stream.of("โรคเบาหวาน", "โรคความดันโลหิตสูง").forEach(diseaseName -> {
				Disease disease = new Disease();
				TypeDisease typeDiseaseid = new TypeDisease();
				PeopleDisease peopleDiseaseid = new PeopleDisease();

				disease.setDiseaseName(diseaseName);
				if(diseaseName == "โรคเบาหวาน"){
					typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคไม่ติดต่อหรือไม่ติดเชื้อ");
					disease.setTypedisease(typeDiseaseid);
					peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("300-400 คน");
					disease.setPeopledisease(peopleDiseaseid);
					disease.setSymptom("หิวน้ำบ่อย");
					disease.setCause("อ้วนหรือน้ำหนักเกินมาตรฐาน");
					disease.setRemedy("ลดน้ำตาล");
					diseaseRepository.save(disease);
				} else if(diseaseName == "โรคความดันโลหิตสูง"){
					typeDiseaseid = typeDiseaseRepository.findByTypeDiseaseName("โรคไม่ติดต่อหรือไม่ติดเชื้อ");
					disease.setTypedisease(typeDiseaseid);
					peopleDiseaseid = peopleDiseaseRepository.findByPopulationRate("500-600 คน");
					disease.setPeopledisease(peopleDiseaseid);
					disease.setSymptom("เหนื่อยง่าย");
					disease.setCause("นอนน้อย พักผ่อนไม่เพียงพอ ");
					disease.setRemedy("คงน้ำหนักร่างกายให้เหมาะสม");
					diseaseRepository.save(disease);
				}
			});


			Stream.of("Ampicillin","Enalapril").forEach(drugName -> {
				Drug               grug               = new Drug();
				TypesOfDrugs       typesOfDrugs       = new TypesOfDrugs();
				DrugRegistration   drugRegistration   = new DrugRegistration();
				TypesOfDosageForms typesOfDosageForms = new TypesOfDosageForms();
				Disease            disease            = new Disease();
				
				if(drugName == "Ampicillin"){
					grug.setDrugName("Ampicillin");
					typesOfDrugs = typesOfDrugsRepository.findByTypesOfDrugsName("ยาสามัญ");
					grug.setTypesOfDrugs(typesOfDrugs);

					drugRegistration =drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
					grug.setDrugRegistration(drugRegistration);
					
					typesOfDosageForms = typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
					grug.setTypesOfDosageForms(typesOfDosageForms);

					disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
					grug.setDisease(disease);

					drugRepository.save(grug);

				}else if(drugName == "Enalapril"){

					grug.setDrugName("Enalapril");
					typesOfDrugs = typesOfDrugsRepository.findByTypesOfDrugsName("ยาควบคุมพิเศษ");
					grug.setTypesOfDrugs(typesOfDrugs);

					drugRegistration =drugRegistrationRepository.findByDrugRegistrationName("ทะเบียนยาแผนปัจจุบันสำหรับมนุษย์ชนิดเม็ด");
					grug.setDrugRegistration(drugRegistration);
					
					typesOfDosageForms = typesOfDosageFormsRepository.findByTypesOfDosageFormsName("เม็ดวงรี");
					grug.setTypesOfDosageForms(typesOfDosageForms);

					disease = diseaseRepository.findByDiseaseName("โรคเบาหวาน");
					grug.setDisease(disease);
					drugRepository.save(grug);
				}


			});
			Stream.of("อุปกรณ์การแพทย์","บริภัณฑ์การแพทย์","วัสดุการแพทย์และวัสดุฝังในทางศัลยกรรม","เครื่องมือแพทย์เฉพาะทาง").forEach(medicalInstrumentName ->{
				MedicalInstrument medicalInstrument = new MedicalInstrument();
				medicalInstrument.setMedicalInstrumentName(medicalInstrumentName);
				medicalInstrumentRepository.save(medicalInstrument);
			});
			medicalInstrumentRepository.findAll().forEach(System.out::println);

		    Stream.of("ใช้ทั่วไป","ใช้เฉพาะทาง").forEach(ueabilityName ->{
				Useability ueability = new Useability();
				ueability.setUseabilityName(ueabilityName);
				useabilityRepository.save(ueability);
			});
			useabilityRepository.findAll().forEach(System.out::println);

			Stream.of("Graph", "Sun").forEach(goldcardName -> {
				Goldcard goldcard = new Goldcard();
				goldcard.setGoldcardName(goldcardName);
				goldcardRepository.save(goldcard);
			});


			Stream.of("Weerapat", "Nantika", "Nuntawut","Porramate").forEach(memberName -> {
				Member member = new Member();
				member.setMemberName(memberName);
				memberRepository.save(member);
			});

//Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2 Sprint2

			Stream.of("P1", "F2","F3").forEach(acceptToUser -> {

				AcceptToUser acceptToUserId = new AcceptToUser();
				Officer officerId = new Officer();
				RightRegistration rightRegistrationId = new RightRegistration();
				Status statusid = new Status();

				acceptToUserId.setCodeAccept(acceptToUser);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");

				String Date1 = "25:01:2019";
				String Date2 = "15:01:2019";
				LocalDate adate1 = LocalDate.parse(Date1, formatter);
				LocalDate adate2 = LocalDate.parse(Date2, formatter);

				if (acceptToUser == "P1") {
					acceptToUserId.setAccId(1L);
					acceptToUserId.setComment("ครบถ้วน");
					acceptToUserId.setDateAccept(adate1);
					acceptToUserId.setDocumentCode("N1");
					statusid = statusRepository.findByStatusName("Pass");
					acceptToUserId.setStatus(statusid);

					rightRegistrationId = rightRegistrationRepository.findByRegId(1L);
					acceptToUserId.setRightRegistration(rightRegistrationId);

					officerId = officerRepository.findByOfficerName("Pichakorn Lohanut");
					acceptToUserId.setOfficer(officerId);


				} else if (acceptToUser == "F2") {
					acceptToUserId.setAccId(2L);
					acceptToUserId.setComment("เอกสารไม่ตรงกับข้อมูล");
					acceptToUserId.setDateAccept(adate2);
					acceptToUserId.setDocumentCode("N2");
					statusid = statusRepository.findByStatusName("Fail");
					acceptToUserId.setStatus(statusid);

					rightRegistrationId = rightRegistrationRepository.findByRegId(2L);
					acceptToUserId.setRightRegistration(rightRegistrationId);

					officerId = officerRepository.findByOfficerName("Kanathip Poungtham");
					acceptToUserId.setOfficer(officerId);

				}else if (acceptToUser == "F3") {
					acceptToUserId.setAccId(3L);
					acceptToUserId.setComment("เอกสารไม่ตรงกับข้อมูล");
					acceptToUserId.setDateAccept(adate2);
					acceptToUserId.setDocumentCode("C3");
					statusid = statusRepository.findByStatusName("Fail");
					acceptToUserId.setStatus(statusid);

					rightRegistrationId = rightRegistrationRepository.findByRegId(3L);
					acceptToUserId.setRightRegistration(rightRegistrationId);

					officerId = officerRepository.findByOfficerName("Pantamit Sombaddee");
					acceptToUserId.setOfficer(officerId);

				}
				acceptToUserRepository.save(acceptToUserId);
			});


			Stream.of("บัตรทอง", "บัตรทองพรีเมียม", "บัตรข้าราชการ").forEach(ExpensesName -> {
				Expenses expenses = new Expenses();
				expenses.setExpensesName(ExpensesName);

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
				String Date0 = "08:02:2019";
				String Date1 = "09:02:2019";
				String Date2 = "10:02:2019";
				LocalDate date0 = LocalDate.parse(Date0, formatter);
				LocalDate date1 = LocalDate.parse(Date1, formatter);
				LocalDate date2 = LocalDate.parse(Date2, formatter);
				if(ExpensesName == "บัตรทอง"){

					expenses.setNumber("100");
					expenses.setComment("this is comment1");
					expenses.setDate(date0);
					expensesRepository.save(expenses);
				}
				else if(ExpensesName == "บัตรทองพรีเมียม"){
					expenses.setNumber("150");
					expenses.setComment("this is comment2");
					expenses.setDate(date1);
					expensesRepository.save(expenses);
				}
				else if(ExpensesName == "บัตรข้าราชการ"){
					expenses.setNumber("200");
					expenses.setComment("this is comment3");
					expenses.setDate(date2);
					expensesRepository.save(expenses);
				}
				expensesRepository.save(expenses);

			});


			Stream.of("Cogblunt","HotSale","sterile").forEach(medicalSuppliesName ->{

				MedicalSupplies      medicalSupplies   = new MedicalSupplies();
				MedicalInstrument    medicalInstrument = new MedicalInstrument();
				Useability           useability        = new Useability();

				if(medicalSuppliesName == "Cogblunt"){
					medicalSupplies.setCodeNumber("C12345");
					medicalSupplies.setModelNumber("Mono");
					medicalSupplies.setMedicalsuppliesName("Cogblunt");
					medicalSupplies.setBrandName("HIPREETY");
					medicalSupplies.setProperties("Medical Adhesive");

					medicalInstrument = medicalInstrumentRepository.findByMedicalInstrumentName("วัสดุการแพทย์และวัสดุฝังในทางศัลยกรรม");
					medicalSupplies.setMedicalInstrument(medicalInstrument);

					useability = useabilityRepository.findByuseabilityName("ใช้เฉพาะทาง");
					medicalSupplies.setUseability(useability);

					medicalSuppliesRepository.save(medicalSupplies);
					System.out.println(medicalInstrument);
					System.out.println(useability);
					
				}
				else if(medicalSuppliesName == "HotSale"){
					medicalSupplies.setCodeNumber("C12346");
					medicalSupplies.setModelNumber("SU001");
					medicalSupplies.setMedicalsuppliesName("HotSale");
					medicalSupplies.setBrandName("suerful");
					medicalSupplies.setProperties("Suture Material");

					medicalInstrument = medicalInstrumentRepository.findByMedicalInstrumentName("อุปกรณ์การแพทย์");
					medicalSupplies.setMedicalInstrument(medicalInstrument);

					useability = useabilityRepository.findByuseabilityName("ใช้ทั่วไป");
					medicalSupplies.setUseability(useability);

					medicalSuppliesRepository.save(medicalSupplies);

					System.out.println(medicalInstrument);
					System.out.println(useability);

				}
				else if(medicalSuppliesName == "sterile"){
					medicalSupplies.setCodeNumber("C12347");
					medicalSupplies.setModelNumber("Base");
					medicalSupplies.setMedicalsuppliesName("sterile");
					medicalSupplies.setBrandName("KLF");
					medicalSupplies.setProperties("vaginal speculum");

					medicalInstrument = medicalInstrumentRepository.findByMedicalInstrumentName("วัสดุการแพทย์และวัสดุฝังในทางศัลยกรรม");
					medicalSupplies.setMedicalInstrument(medicalInstrument);

					
					useability = useabilityRepository.findByuseabilityName("ใช้เฉพาะทาง");
					medicalSupplies.setUseability(useability);

					medicalSuppliesRepository.save(medicalSupplies);

					System.out.println(medicalInstrument);
					System.out.println(useability);
				}
			});


			System.out.println("Spring Boot Pass");
			String colorwhite = "\u001b[37m";
			String Success = "\u001b[32m";
			System.out.println(Success+"Mvnw Spring-boot:Run ::  Success !!"+colorwhite);
		};
		};

	}


