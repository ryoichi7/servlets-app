package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.ReadUserDto;
import entity.User;
import exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import mapper.CreateUserMapper;
import mapper.ReadUserMapper;
import validator.CreateUserValidator;
import validator.ValidationResult;

import java.io.IOException;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();

    private static final ReadUserMapper readUserMapper = ReadUserMapper.getInstance();

    public static UserService getInstance() {
        return INSTANCE;
    }


    public Optional<ReadUserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email, password)
                .map(readUserMapper::mapFrom);
    }
    @SneakyThrows
    public Integer  create(CreateUserDto userDto) {
        var validationResult = createUserValidator.isValid(userDto); //Validate - step 1
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var userEntity = createUserMapper.mapFrom(userDto); //Map - step 2
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        var user = userDao.save(userEntity); //Save - step 3
        return user.getId(); //Return - step 4
    }
}
