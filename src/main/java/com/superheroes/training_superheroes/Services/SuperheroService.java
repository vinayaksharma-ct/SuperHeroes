package com.superheroes.training_superheroes.Services;
import com.superheroes.training_superheroes.Repository.SuperheroRepository;
import com.superheroes.training_superheroes.model.Superhero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class SuperheroService {
    private final SuperheroRepository superheroRepository;
    @Autowired
    public SuperheroService(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }
    public Superhero getSuperheroByName(String name) {
        return superheroRepository.findByName(name);
    }
    public List<Superhero> getSuperheroesByUniverse(String universe) {
        return superheroRepository.findByUniverse(universe);
    }
    public Superhero addSuperhero(Superhero superhero) {
        return superheroRepository.save(superhero);
    }
    public List<Superhero> addManySuperheroes(List<Superhero> superheroes) {
        return superheroRepository.saveAll(superheroes);
    }
    public Superhero updateSuperhero(String name, Superhero superhero) {
        // optional is used to handle Nullpointerexception to check absence or presence of a value
        Optional<Superhero> existingSuperhero = Optional.ofNullable(superheroRepository.findByName(name));
        if (existingSuperhero.isPresent()) {
            superhero.setName(name);
            return superheroRepository.save(superhero);
        }
        return null;
    }
    public void deleteSuperhero(String name) {
        superheroRepository.deleteById(name);
    }
}