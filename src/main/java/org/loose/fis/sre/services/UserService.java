package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("agentie_imobiliara.db").toFile())
                .openOrCreate("agent_imb", "agent_imb");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String fullName,String phoneNumber,String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(fullName,phoneNumber,username, encodePassword(username, password), role));
    }

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }

    static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }
    public static void checkAgentDoesExist(String agent_book) throws AgentDoesNotExistException {
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getRole(), "Agent") && Objects.equals(user.getFullName(), agent_book))
                return;
        }
        throw new AgentDoesNotExistException(agent_book);
    }
    public static void checkUsername(String username) throws IncorrectNameException
    {
        for(User user : userRepository.find())
        {
            if(Objects.equals(username,user.getFullName()))
            {
                return;
            }
        }
        throw new IncorrectNameException(username);
    }
    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

    public static int CheckUserCredentials(String username, String password, String role) throws IncorrectCredentials
    {
        String pass=encodePassword(username,password);
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()) && Objects.equals(role,user.getRole()) && pass.equals(user.getPassword()))
            {
                if(role.equals("Buyer"))
                {
                    return 1;
                }
                else
                {
                    return 2;
                }
            }
        }
        throw new IncorrectCredentials(username);
    }


    public static String agents_lsit() {
        String s = "";
        for (User user : userRepository.find()) {
            if (Objects.equals(user.getRole(), "Agent")) {
                s += user.toString();
            }
        }
        return s;
    }

    public static void CheckNameCredentials (String Name) throws NoSchedulingsException
    {

        for (User user : userRepository.find()) {
            if (Objects.equals(Name, user.getFullName()))
            {
                return;
            }
        }
        throw new NoSchedulingsException(Name);

    }
}
