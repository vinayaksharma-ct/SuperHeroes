package com.superheroes.training_superheroes.services;

import com.superheroes.training_superheroes.Repository.SuperheroRepository;
import com.superheroes.training_superheroes.Services.SuperheroService;
import com.superheroes.training_superheroes.model.Superhero;
import com.superheroes.training_superheroes.model.SuperheroRequestBody;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
public class SuperheroService2 {
    @Mock
    private SuperheroRepository superheroRepository;

    @InjectMocks
    private SuperheroService superheroService;

    @Test
    void testPersistSuperhero_whenUniverseIsDc(){
        SuperheroRequestBody requestBody = getSuperheroRequestBody("Batman");

        doAnswer(invocation -> invocation.getArgument(0)).when(superheroRepository).save(any());

        Superhero superhero = superheroService.persistSuperhero(requestBody, "DC");
        assertEquals("DC", superhero.getUniverse(), "Universe is wrong");
    }

    private static Superhero getSuperhero() {
        Superhero superhero = new Superhero();
        superhero.setId("test-id");
        superhero.setUniverse("universe");
        superhero.setName("name");
        return superhero;
    }

    private static SuperheroRequestBody getSuperheroRequestBody(String name) {
        return SuperheroRequestBody.builder().superheroName(name).power("Testpower").universe("Bla").build();
    }
}
