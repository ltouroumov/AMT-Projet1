package ch.ltouroumov.heig.amt.project1.model.manager.impl;

import ch.ltouroumov.heig.amt.project1.model.entities.Pokemon;
import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.model.manager.IPokemonManager;
import ch.ltouroumov.heig.amt.project1.model.manager.ManagerException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages pokemons with JDBC connection
 * @author Frederic
 * Created: 17.10.16
 */
@Stateless
public class JdbcPokemonManager implements IPokemonManager {

    @Resource(lookup = "java:/app/db")
    private DataSource dataSource;

    @Override
    public List<Pokemon> findAll() {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("SELECT * FROM pokemons");
            ResultSet result = query.executeQuery();

            List<Pokemon> pokemons = new ArrayList<>();
            while (result.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(result.getInt("id"));
                pokemon.setName(result.getString("name"));
                pokemon.setType(result.getString("type"));

                pokemons.add(pokemon);
            }

            return pokemons;
        } catch (SQLException ex) {
            throw new ManagerException("Error querying pokemons", ex);
        }
    }

    @Override
    public Pokemon findOne(int id) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("SELECT * FROM pokemons WHERE id = ?");
            query.setInt(1, id);
            ResultSet result = query.executeQuery();

            if (result.next()) {
                Pokemon pokemon = new Pokemon();
                pokemon.setId(result.getInt("id"));
                pokemon.setName(result.getString("name"));
                pokemon.setType(result.getString("type"));

                return pokemon;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new ManagerException("Error querying pokemons", ex);
        }
    }

    @Override
    public void create(Pokemon pokemon) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("INSERT INTO pokemons(name, type) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            query.setString(1, pokemon.getName());
            query.setString(2, pokemon.getType());
            int id = query.executeUpdate();
            pokemon.setId(id);
        } catch (SQLException ex) {
            throw new ManagerException("Failed to create pokemon", ex);
        }
    }

    @Override
    public void update(Pokemon pokemon) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("UPDATE pokemons SET name = ?, type = ? WHERE id = ?");
            query.setString(1, pokemon.getName());
            query.setString(2, pokemon.getType());
            query.setInt(3, pokemon.getId());

            query.executeUpdate();
        } catch (SQLException ex) {
            throw new ManagerException("Failed to update pokemon", ex);
        }
    }

    @Override
    public void delete(Pokemon pokemon) {
        delete(pokemon.getId());
    }

    @Override
    public void delete(int id) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("DELETE FROM pokemons WHERE id = ?");
            query.setInt(1, id);

            query.executeUpdate();
        } catch (SQLException ex) {
            throw new ManagerException("Failed to delete pokemon", ex);
        }
    }

}
