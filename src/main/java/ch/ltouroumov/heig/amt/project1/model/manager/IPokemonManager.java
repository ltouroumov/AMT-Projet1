package ch.ltouroumov.heig.amt.project1.model.manager;

import ch.ltouroumov.heig.amt.project1.model.entities.Pokemon;

import java.util.List;

/**
 * Created by Frederic on 17.10.16.
 */
public interface IPokemonManager {

    List<Pokemon> findAll();

    Pokemon findOne();

    Pokemon findOne(int id);

    void create(Pokemon pokemon);

    void update(Pokemon pokemon);

    void delete(Pokemon pokemon);

    void delete(int id);
}
