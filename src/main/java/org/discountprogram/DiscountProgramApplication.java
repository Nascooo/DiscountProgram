package org.discountprogram;

import org.discountprogram.entity.User;
import org.discountprogram.repo.UserRepository;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.generators.Generators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static org.instancio.Select.field;

@SpringBootApplication
public class DiscountProgramApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DiscountProgramApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Model<User> userModel = Instancio.of(User.class).generate(field(User::getUserId), Generators::longSeq).toModel();
        List<User> users = Instancio.ofList(userModel).size(10).create();
        userRepository.saveAll(users);
    }
}
