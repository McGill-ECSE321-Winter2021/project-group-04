package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*;
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AdministrativeAssistantService {

    @Autowired
    private AdministrativeAssistantRepository administrativeAssistantRepository;

    @Transactional
    public AdministrativeAssistant getAdministrativeAssistantByUserId(String userId) {
        return administrativeAssistantRepository.findAdministrativeAssistantByUserId(userId);
    }

    @Transactional
    public AdministrativeAssistant createAdministrativeAssistant(String userId, String password) {

        AdministrativeAssistant existingAssistant = getAdministrativeAssistantByUserId(userId);

        if (existingAssistant != null) {
            throw new IllegalArgumentException("Administrative Assistant already exists");
        }
        // if (userId == null ) {
        // throw new IllegalArgumentException("Username can't be null");
        // }
        if (userId == "") {
            throw new IllegalArgumentException("Username can't be empty");
        }
        // if (password == null) {
        // throw new IllegalArgumentException("Password can't be null");
        // }
        if (password == "") {
            throw new IllegalArgumentException("Password can't be empty");
        }
        AdministrativeAssistant administrativeAssistant = new AdministrativeAssistant();
        administrativeAssistant.setUserId(userId);
        administrativeAssistant.setPassword(password);
        LogInLogOutService.currentUser = administrativeAssistant;
        administrativeAssistantRepository.save(administrativeAssistant);
        return administrativeAssistant;

    }

    @Transactional
    public AdministrativeAssistant getAdministrativeAssistantById(Long userId) {
        return administrativeAssistantRepository.findAdministrativeAssistantById(userId);
    }

    @Transactional
    public List<AdministrativeAssistant> getAllAdministrativeAssistants() {
        return (List<AdministrativeAssistant>) administrativeAssistantRepository.findAll();
    }

    public AdministrativeAssistant deleteAdministrativeAssistant(AdministrativeAssistant administrativeAssistant) {
        administrativeAssistantRepository.delete(administrativeAssistant);
        administrativeAssistant = null;
        return administrativeAssistant;
    }

    public AdministrativeAssistant editAdministrativeAssistant(AdministrativeAssistant administrativeAssistant,
            String userId, String password) {
        if (userId == administrativeAssistant.getUserId() && password == administrativeAssistant.getPassword()) {
            throw new IllegalArgumentException("You have to change the username or password or both");
        }
        administrativeAssistant.setUserId(userId);
        administrativeAssistant.setPassword(password);
        LogInLogOutService.currentUser = administrativeAssistant;
        administrativeAssistantRepository.save(administrativeAssistant);
        return administrativeAssistant;
    }

}
