package controller;

import exception.DataBaseException;
import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import repository.PersonaRepository;

import java.util.List;


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

   @PostMapping
    public Persona createPersona(@RequestBody Persona persona){
       return personaRepository.save(persona);
   }

   @GetMapping("/{id}")
    public Persona getPersonaById(@PathVariable Long id)
   throws DataBaseException {
       return personaRepository.findById(id).orElseThrow(() -> new DataBaseException("No se pudo encontrar la Persona"));
   }

   @PutMapping("/{id}")
    public Persona updatePersona(@PathVariable Long id, @RequestBody Persona personaRequest)
   throws DataBaseException {
       Persona p = personaRepository.findById(id).orElseThrow(() -> new DataBaseException("No se pudo encontrar la Persona"));

       personaRequest.setNombre(p.getNombre());
       personaRequest.setApellido(p.getApellido());
       return personaRepository.save(personaRequest);
   }

   @DeleteMapping("/{id}")
    public void deletePersona(@PathVariable Long id)
   throws DataBaseException {
       Persona p = personaRepository.findById(id).orElseThrow(() -> new DataBaseException("No se pudo encontrar la Persona"));
       personaRepository.delete(p);
   }

}
