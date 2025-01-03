package controller;

import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.PersonaRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class PersonaController {

   @Autowired
   private PersonaRepository personaRepository;

   @GetMapping
   public List<Persona> getAllPersonas(){
       return personaRepository.findAll();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Persona> getPersonaById(@PathVariable Long id) {
       Optional<Persona> persona = personaRepository.findById(id);
       return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
   }

   @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
       Persona savedPersona = personaRepository.save(persona);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedPersona);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaRequest) {
       if( !personaRepository.existsById(id) )
              return ResponseEntity.notFound().build();

       personaRequest.setId(id);
       personaRepository.save(personaRequest);
       return ResponseEntity.status(HttpStatus.CREATED).body(personaRequest);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
       if(!personaRepository.existsById(id))
           return ResponseEntity.notFound().build();

       personaRepository.deleteById(id);
       return ResponseEntity.noContent().build();
   }

}
