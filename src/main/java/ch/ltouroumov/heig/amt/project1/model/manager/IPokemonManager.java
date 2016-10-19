package ch.ltouroumov.heig.amt.project1.model.manager;

import ch.ltouroumov.heig.amt.project1.model.entities.Pokemon;

import java.util.List;

/**
 * Interface to manage pokemons
 *
 * @author Frederic
 * Created: 17.10.16
 */
public interface IPokemonManager {

    /**
     * Finds all managed pokemons
     * @return List of pokemons
     */
    List<Pokemon> findAll();

    /**
     * Find a pokemon by id
     * @param id id of the pokemon
     * @return Pokemon object or null if not found
     */
    Pokemon findOne(int id);

    /**
     * Create a pokemon in the backing store
     * @param pokemon Pokemon object
     */
    void create(Pokemon pokemon);

    /**
     * Update a pokemon in the backing store
     * @param pokemon Pokemon object
     */
    void update(Pokemon pokemon);

    /**
     * Delete a pokemon from the backing store
     * @param pokemon Pokemon object
     */
    void delete(Pokemon pokemon);

    /**
     * Delete a pokemon from the backing store
     * @param id Pokemon id
     */
    void delete(int id);

}
