package ca.mcgill.ecse321.projectgroup04.service;

import ca.mcgill.ecse321.projectgroup04.dao.*; 
import ca.mcgill.ecse321.projectgroup04.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Transactional
    public Owner createOwner(String userId, String password) {

        if (ownerExists()) {
            throw new IllegalArgumentException("Owner already exists");
        }
        if (userId == "") {
            throw new IllegalArgumentException("Username can't be empty");
        }
        if (password == "") {
            throw new IllegalArgumentException("Password can't be empty");
        }

        Owner owner = new Owner();
        owner.setUserId(userId);
        owner.setPassword(password);
        LogInLogOutService.currentUser = owner;
        ownerRepository.save(owner);
        return owner;
    }

    @Transactional
    public Owner getOwnerByUserId(Long userId) {
        return ownerRepository.findOwnerById(userId);
    }

    @Transactional
    public List<Owner> getOwner() {
        return (List<Owner>) ownerRepository.findAll();
    }

    @Transactional
    public Owner deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
        owner = null;
        return owner;
    }

    @Transactional
    public Owner editOwner(Owner owner, String userId, String password) {

        if (userId == owner.getUserId() && password == owner.getPassword()) {
            throw new IllegalArgumentException("You have to change the username or password or both");
        }

        owner.setUserId(userId);
        owner.setPassword(password);
        LogInLogOutService.currentUser = owner;
        ownerRepository.save(owner);

        return owner;
    }

    @Transactional
    public boolean ownerExists() { // does owner exist?
        long ownerCount = ownerRepository.count();
        if (ownerCount > 0) {
            return true; // owner exists
        } else {
            return false; // owner does not exist
        }
    }

    @Transactional
    public Owner getOwnerByUserId(String userId) {
        return ownerRepository.findOwnerByUserId(userId);
    }
}
