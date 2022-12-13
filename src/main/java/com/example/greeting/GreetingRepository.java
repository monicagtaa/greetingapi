package com.example.greeting;
import com.example.greeting.model.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

//      Repositiry
//          |
//      CrudRepository
//          |
//      JpaRepository
// we have access to all of the CRUD operations inherited from CrudRepository

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, String> {
//we have access to built in methods like save() for POST, find all for GET
    //we can also write our own custom methods (later)
        //Jpa will parse the names of these custom methods to look for fin "by" keywords for example
Greeting findByid(int id);

}
