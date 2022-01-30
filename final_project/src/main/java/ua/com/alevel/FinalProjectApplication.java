package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.store.BrandRepository;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.tmp.BrandTmp;

import java.util.Date;
import java.util.List;

@SpringBootApplication
        (exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class })
public class FinalProjectApplication {
    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final BrandRepository brandRepository;

    public FinalProjectApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository, BrandRepository brandRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.brandRepository = brandRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        Admin admin = adminRepository.findByEmail("admin@mail.com");
        if (admin == null) {
            admin = new Admin();
            admin.setEmail("admin@mail.com");
            admin.setCreated(new Date(System.currentTimeMillis()));
            admin.setUpdated(new Date(System.currentTimeMillis()));
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
        List<BrandTmp> list = brandRepository.findAllBrandTmp();
        for (BrandTmp brandTmp : list) {
            System.out.println("brandTmp id = " + brandTmp.id());
            System.out.println("brandTmp name = " + brandTmp.name());
        }
    }

}
