package com.abdallahsproject.Customer.services;

import com.abdallahsproject.Customer.models.CarSpecs;
import com.abdallahsproject.Customer.models.Factory;
import com.abdallahsproject.Customer.repositories.FactoryRepository;
import com.abdallahsproject.exception.DuplicateResourceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryService {

    private final FactoryRepository factoryRepository;

    public FactoryService(FactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }


    public List<Factory> selectAllFactories() {
        return factoryRepository.findAll();
    }

    public void addFactory(Factory request){
        if (factoryRepository.existsFactoryByName(request.getName())) {
            throw new DuplicateResourceException("Factory name already registered");
        }
        factoryRepository.save(request);
    }

    public void deleteFactory(Long id){
        factoryRepository.deleteById(id);
    }
}
