package com.sa.clothingstore.service.user.factory;

import com.sa.clothingstore.dto.request.user.UserRequest;
import com.sa.clothingstore.exception.ObjectAlreadyExistsException;
import com.sa.clothingstore.exception.ObjectNotFoundException;
import com.sa.clothingstore.model.attribute.Image;
import com.sa.clothingstore.model.user.Role;
import com.sa.clothingstore.model.user.Status;
import com.sa.clothingstore.model.user.User;
import com.sa.clothingstore.repository.attribute.ImageRepository;
import com.sa.clothingstore.repository.user.UserRepository;
import com.sa.clothingstore.repository.user.customer.AddressRepository;
import com.sa.clothingstore.service.user.service.UserDetailService;
import com.sa.clothingstore.service.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class UserServiceFactory  {
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;
    private final UserDetailService userDetailService;
    private final UserRepository userRepository;
    protected abstract User createUser(User user, UserRequest userRequest);

    protected abstract User updateUser(User user, UserRequest userRequest);
    protected abstract List<User> getAllUsersByRole(Integer role);

    @Transactional
    public User create(UserRequest userRequest, Role role){
        userRepository.findByEmail(userRequest.getEmail()).ifPresent(user -> {
            throw new ObjectAlreadyExistsException("Email already existed");
        });
        userRepository.findByPhone(userRequest.getPhone()).ifPresent(user -> {
            throw new ObjectAlreadyExistsException("Phone already existed");
        });
        User user = User.builder()
                .fullName(userRequest.getFullname())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .dateOfBirth(userRequest.getDateOfBirth())
                .enabled(userRequest.getEnable() == Status.ACTIVE.ordinal())
                .role(role)
                .build();
        user.setCommonCreate(userDetailService.getIdLogin());
        String imagePath = userRequest.getImage();
        if(imagePath != null){
            Image image = Image.builder().url(userRequest.getImage()).build();
            imageRepository.save(image);
            user.setImage(image);
        }
        return createUser(user, userRequest);
    }

    public User update(UUID userId, UserRequest userRequest){
        User user = userRepository.findById(userId).orElseThrow(() ->{
                throw new ObjectNotFoundException("User not found");
            }
        );
        String oldEmail = user.getEmail();
        String newEmail = userRequest.getEmail();
        String oldPhone = user.getPhone();
        String newPhone = userRequest.getPhone();
        userRepository.findByEmail(newEmail).ifPresent(u -> {
            if(!oldEmail.equals(newEmail)) {
                throw new ObjectAlreadyExistsException("Email already existed");
            }
        });
        userRepository.findByPhone(newPhone).ifPresent(u -> {
            if(!oldPhone.equals(newPhone)) {
                throw new ObjectAlreadyExistsException("Phone already existed");
            }
        });
        user.setEmail(userRequest.getEmail());
        user.setFullName(userRequest.getFullname());
        user.setPhone(userRequest.getPhone());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setEnabled(userRequest.getEnable() == Status.ACTIVE.ordinal());
        user.getImage().setUrl(userRequest.getImage());
        user.setCommonUpdate(userDetailService.getIdLogin());

        return updateUser(user, userRequest);
    }

    public List<User> getAllUsers(Integer role){
        return userRepository.findByRole(Role.convertIntegerToRole(role))
                .orElseThrow(() -> new ObjectNotFoundException("No users were found with this role"));
    }
}
