package com.academy.cic.ibm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.TypedQuery;

import com.academy.cic.model.Course;
import com.academy.cic.model.Module;
import com.academy.cic.util.DateUtil;

public class Main {

	public static void main(String[] args) {

		Dao dao = new Dao();
		
		// 1
		Course ibmWeek1 = new Course("Java Programming", DateUtil.stringToDate("30-01-2023"), DateUtil.stringToDate("03-02-2023"));
		Module basiDiJava = new Module("Percorso di java ", "Luca");
		Module git = new Module("SQL", "Francesco");
		Module xmlJson = new Module("c++", "Antonio");
		Module maven = new Module("Eclipse", "Serena");
		Module docker = new Module("JavaSE&EE", "Davide");
		Set<Module> modulesFirstWeek = new HashSet<Module>();
		modulesFirstWeek.add(basiDiJava);
		modulesFirstWeek.add(git);
		modulesFirstWeek.add(xmlJson);
		modulesFirstWeek.add(maven);
		modulesFirstWeek.add(docker);
		ibmWeek1.setSetModule(modulesFirstWeek);
		
		// 2
		Course ibmWeek2 = new Course("Web Tier", DateUtil.stringToDate("06-02-2023"), DateUtil.stringToDate("10-02-2023"));
		Module html = new Module("HTML5", "Domenico");
		Module css = new Module("CSS3", "Francesco");
		Module javaScript = new Module("JS", "Luigi");
		Module rest = new Module("REST", "Luciano");
		Set<Module> modulesSecondWeek = new HashSet<Module>();
		modulesSecondWeek.add(html);
		modulesSecondWeek.add(css);
		modulesSecondWeek.add(javaScript);
		modulesSecondWeek.add(rest);
		ibmWeek2.setSetModule(modulesSecondWeek);
		
		// Terzo corso
		Course ibmWeek3 = new Course("Data Tier", DateUtil.stringToDate("13-02-2023"), DateUtil.stringToDate("17-02-2023"));
		Module rdbms = new Module("jdbc", "Giovanni");
		Module sql = new Module("SQL", "Gianfrancesco");
		Module jdbc = new Module("MySql", "Alfonso");
		Module transactions = new Module("SQirrel", "Domenico");
		Module noSql = new Module("NoSQL", "Riccardo");
		Set<Module> moduleThirdWeek = new HashSet<Module>();
		moduleThirdWeek.add(rdbms);
		moduleThirdWeek.add(sql);
		moduleThirdWeek.add(jdbc);
		moduleThirdWeek.add(transactions);
		moduleThirdWeek.add(noSql);
		ibmWeek3.setSetModule(moduleThirdWeek);
		
		// Quarto corso
		Course ibmWeek4 = new Course("Integration Tier", DateUtil.stringToDate("20-02-2023"), DateUtil.stringToDate("24-02-2023"));
		Module jdbc2 = new Module("JDBC2", "Antonio");
		Module orm = new Module("ORM", "Gianfranco");
		Module hibernate = new Module("Hibernate", "Daniele");
		Set<Module> moduleFourthWeek = new HashSet<Module>();
		moduleFourthWeek.add(jdbc2);
		moduleFourthWeek.add(orm);
		moduleFourthWeek.add(hibernate);
		ibmWeek4.setSetModule(moduleFourthWeek);
			
			// Quinto corso
			Course ibmWeek5 = new Course("Tier", DateUtil.stringToDate("27-02-2023"), DateUtil.stringToDate("03-03-2023"));
			Module spring = new Module("Spring", "Gianfranco Improta");
			Module springBoot = new Module("SpringBoot", "Enrico testa ");
			Set<Module> moduleFifthWeek = new HashSet<Module>();
			moduleFifthWeek.add(spring);
			moduleFifthWeek.add(springBoot);
			ibmWeek5.setSetModule(moduleFifthWeek);
		
		
		//  metodo insertCourse
		System.out.println(dao.insertCourse(ibmWeek1));
		System.out.println(dao.insertCourse(ibmWeek2));
		System.out.println(dao.insertCourse(ibmWeek3));
		System.out.println(dao.insertCourse(ibmWeek4));
		System.out.println(dao.insertCourse(ibmWeek5)); 
		
		// metodo findCourseByName
		System.out.println(dao.findCourseByName("Integration Tier"));
		
		//  metodo addModuleToCourse
		
		Module jpa = new Module("JPA", "Luciano");
		dao.addModuleToCourse(jpa, 21);
		
		// metodo findCourseModules
		for (Module m: dao.findCourseModules(21)) {
			System.out.println(m);
		}
		
		//metodo findStartingCourseInRange
		for (Course c: dao.findStartingCourseInRange(new Date(2023, 02, 13), new Date(2023, 02, 20))){
			System.out.println(c);
		}
		
		// metodo updateStartDate
		dao.updateStartDate(DateUtil.stringToDateDB("2023-02-17"), 22);
		
		// metodo void deleteCourse
		dao.deleteCourse(22);
		
	}
}