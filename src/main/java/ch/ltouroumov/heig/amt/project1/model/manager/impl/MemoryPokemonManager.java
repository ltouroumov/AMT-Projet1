package ch.ltouroumov.heig.amt.project1.model.manager.impl;

import ch.ltouroumov.heig.amt.project1.model.entities.Pokemon;
import ch.ltouroumov.heig.amt.project1.model.manager.IPokemonManager;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages pokemons in memory
 *
 * @author ldavid
 */
@Singleton
public class MemoryPokemonManager implements IPokemonManager {

    private static int counter = 0;
    private Map<Integer, Pokemon> pokemons = new HashMap<>();

    public MemoryPokemonManager() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("Pikachu");
        pokemon.setType("Electric");
        create(pokemon);
    }

    @Override
    public List<Pokemon> findAll() {
        return pokemons.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Pokemon findOne(int id) {
        return pokemons.get(id);
    }

    @Override
    public void create(Pokemon pokemon) {
        pokemon.setId(++counter);
        pokemons.put(pokemon.getId(), pokemon);
    }

    @Override
    public void update(Pokemon pokemon) {
        //Do nothing
    }

    @Override
    public void delete(Pokemon pokemon){
        pokemons.remove(pokemon.getId());
    }

    @Override
    public void delete(int id){
        pokemons.remove(id);
    }
}
