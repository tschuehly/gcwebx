package de.cschillingtschuehly.gcwebx.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class CRUDService<T,R extends JpaRepository<T,Long>> {

    @Autowired
    private R repository;

    public T save(T object){
        return repository.save(object);
    }

    public List<T> save(List<T> objects){
        return repository.saveAll(objects);
    }

    public Optional<T> getById(Long id){
        return repository.findById(id);
    }
    public List<T> getAll(){
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

}
