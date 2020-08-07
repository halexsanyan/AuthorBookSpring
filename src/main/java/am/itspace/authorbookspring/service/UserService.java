package am.itspace.authorbookspring.service;

import am.itspace.authorbookspring.model.User;
import am.itspace.authorbookspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveUser(User user, MultipartFile file) {

        try {
            String name = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
            File picUrl = new File(uploadDir, name);

            file.transferTo(picUrl);

            user.setProfilePic(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(user);
    }

    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteAuthor(int id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
}
