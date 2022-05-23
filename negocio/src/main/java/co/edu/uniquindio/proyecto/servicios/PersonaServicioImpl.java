package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Persona;
import co.edu.uniquindio.proyecto.exception.FitnesscampException;
import co.edu.uniquindio.proyecto.repositorios.PersonaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PersonaServicioImpl implements PersonaServicio{

    @Autowired
    private PersonaRepo personaRepo;

    @Override
    public Persona login(String email, String password) throws FitnesscampException {

        Optional<Persona> persona = personaRepo.findByEmailAndPassword(email,password);

        if (persona.isEmpty()){
            throw new FitnesscampException("No se encontraron coincidencias en el sistema");
        }

        return persona.get();
    }


    @Override
    public Persona obtenerPersonaEmail(String email) throws FitnesscampException {

        Optional<Persona> personaEncontrada = personaRepo.findByEmail(email);

        if(personaEncontrada.isEmpty()){

            throw new FitnesscampException("No se encontraron coincidencias en el sistema");
        }
        return personaEncontrada.get();
    }


    @Override
    public void cambiarPassword(String email,String passwordN) throws FitnesscampException {

        Persona personaEncontrada = obtenerPersonaEmail(email);

        if (personaEncontrada!=null){
            personaEncontrada.setPassword(passwordN);
            personaRepo.save(personaEncontrada);
        }else{
            throw new FitnesscampException("No se encontraron coincidencias en el sistema");
        }

    }

}
