package de.cschillingtschuehly.gcwebx.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class RESTController<T,R extends JpaRepository<T,Long>,S extends CRUDService<T,R>> {

    @Autowired
    private S service;

    @PostMapping
    public T add(@RequestBody T object){
        return service.save(object);
    }

    @GetMapping
    public List<T> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        Optional<T> object = service.getById(id);
        if(object.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok(object.get());
        }
    }
    @PutMapping
    public T update(@RequestBody T object){
        return service.save(object);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
