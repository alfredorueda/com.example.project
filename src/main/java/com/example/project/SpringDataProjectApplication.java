package com.example.project;

import com.example.project.Model.*;
import com.example.project.Repositories.DeveloperRepository;
import com.example.project.Repositories.ManagerRepository;
import com.example.project.Repositories.ProjectRepository;
import com.example.project.Repositories.SpecialityRepository;
import com.example.project.Service.DeveloperService;
import com.example.project.Service.ProjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Calendar;

@SpringBootApplication
@EnableJpaRepositories
public class SpringDataProjectApplication {

    private static DeveloperRepository developerRepository;
    private static SpecialityRepository specialityRepository;
    private static ProjectRepository projectRepository;
    private static ManagerRepository managerRepository;
    private static ProjectService projectService;
    private static DeveloperService developerService;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringDataProjectApplication.class, args);

        developerRepository = context.getBean(DeveloperRepository.class);
        specialityRepository = context.getBean(SpecialityRepository.class);
        projectRepository = context.getBean(ProjectRepository.class);
        managerRepository = context.getBean(ManagerRepository.class);
        projectService = context.getBean(ProjectService.class);
        developerService = context.getBean(DeveloperService.class);

        initializeDemoData();
    }

    private static void initializeDemoData() {

        initializeManagers();

        initializeDevelopers();

        initializeSpecialties();

        initializeProjects();

        addSpecialtiesToDevelopers();

        addSpecialtiesToProjects();

        addDevelopersToProject();


    }

    private static void addSpecialtiesToProjects() {

        projectService.addSpecialtiesToProjects();
    }

    private static void addSpecialtiesToDevelopers() {

        developerService.addSpecialtiesToDevelopers();

    }

    private static void addDevelopersToProject() {

        Developer golum = developerRepository.findByNameContains("Golum").get(0);

        Developer frodo = developerRepository.findByNameContains("Frodo").get(0);

        projectService.addDeveloper(1L,golum.getId());
        projectService.addDeveloper(1L,frodo.getId());


    }



    private static void initializeProjects() {
        Project project = new Project();
        project.setDescription("Create the next Android killer app");
        Calendar calendar = Calendar.getInstance();

        calendar.set(2014, Calendar.OCTOBER, 1, 12, 28, 0);
        project.setStartDate(calendar.getTime());

        calendar.set(2015, Calendar.MARCH, 23, 12, 28, 0);
        project.setEndDate(calendar.getTime());

        projectRepository.save(project);
    }

    private static void initializeManagers() {
        Manager manager1 = new Manager();
        manager1.setName("Jane Doe");
        manager1.setSalary(1500);
        manager1.setIdCard("39453381Y");
        manager1.setStatus(EmployeeStatus.FULL_TIME);
        manager1.setBonusSuccess(5000.00);

        managerRepository.save(manager1);
    }

    private static void initializeSpecialties() {
        Specialty android = new Specialty();
        android.setName("Android");

        Specialty spring = new Specialty();
        spring.setName("Spring");

        Specialty hibernate = new Specialty();
        hibernate.setName("Hibernate");

        specialityRepository.save(android);
        specialityRepository.save(spring);
        specialityRepository.save(hibernate);
    }

    private static void initializeDevelopers() {
        Developer developer1 = new Developer();
        developer1.setName("John Doe");
        developer1.setSalary(1000);
        developer1.setIdCard("39453382J");
        developer1.setStatus(EmployeeStatus.PART_TIME);
        developer1.setCategory(Category.SENIOR);

        Developer developer2 = new Developer();
        developer2.setName("Golum");
        developer2.setSalary(2000);
        developer2.setIdCard("65432178Y");
        developer2.setStatus(EmployeeStatus.PART_TIME);
        developer2.setCategory(Category.SENIOR);

        Developer developer3 = new Developer();
        developer3.setName("Dumbledore");
        developer3.setSalary(2500);
        developer3.setIdCard("46135582W");
        developer3.setStatus(EmployeeStatus.FULL_TIME);
        developer3.setCategory(Category.JUNIOR);

        Developer developer4 = new Developer();
        developer4.setName("Harry Potter");
        developer4.setSalary(800);
        developer4.setIdCard("49135554W");
        developer4.setStatus(EmployeeStatus.PART_TIME);
        developer4.setCategory(Category.JUNIOR);

        Developer developer5 = new Developer();
        developer5.setName("Voldemort");
        developer5.setSalary(4000);
        developer5.setIdCard("41278932O");
        developer5.setStatus(EmployeeStatus.FULL_TIME);
        developer5.setCategory(Category.ARCHITECT);

        Developer developer6 = new Developer();
        developer6.setName("Frodo");
        developer6.setSalary(1000);
        developer6.setIdCard("54321878K");
        developer6.setStatus(EmployeeStatus.FULL_TIME);
        developer6.setCategory(Category.SENIOR);



        developerRepository.save(developer1);
        developerRepository.save(developer2);
        developerRepository.save(developer3);
        developerRepository.save(developer4);
        developerRepository.save(developer5);
        developerRepository.save(developer6);
    }
}
