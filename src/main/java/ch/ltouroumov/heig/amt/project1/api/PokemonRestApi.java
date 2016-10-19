package ch.ltouroumov.heig.amt.project1.api;

import ch.ltouroumov.heig.amt.project1.api.dto.*;
import ch.ltouroumov.heig.amt.project1.model.entities.Pokemon;
import ch.ltouroumov.heig.amt.project1.model.manager.IPokemonManager;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
@Path("/pokemons")
public class PokemonRestApi {


    @EJB(beanName = "JdbcPokemonManager")
    private IPokemonManager pokemonManager;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPokemons(@QueryParam(value = "byName") String byName) {
        try {
            List<Pokemon> users = pokemonManager.findAll();
            return Response.ok(users.stream().filter(p -> byName == null || p.getName().equalsIgnoreCase(byName))
                    .map(this::toPokemonDTO)
                    .collect(Collectors.toList())).build();
        } catch(Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPokemon(@PathParam(value = "id") int id) {
        try {
            Pokemon pokemon = pokemonManager.findOne(id);
            if (pokemon != null) {
                return Response.ok(toPokemonDTO(pokemon)).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception ex){
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPokemon(PokemonDTO pokemonDTO) {
        Pokemon pokemon = fromPokemonDTO(pokemonDTO);
        try {
            pokemonManager.create(pokemon);
            int id = pokemon.getId();

            URI href = uriInfo
                    .getBaseUriBuilder()
                    .path(UserRestApi.class)
                    .path(UserRestApi.class, "getPokemon")
                    .build(id);

            return Response
                    .created(href)
                    .build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePokemon(@PathParam(value = "id") int id, PokemonDTO pokemonDTO){
        try {
            Pokemon pokemon = pokemonManager.findOne(id);
            pokemon.setName(pokemonDTO.getName());
            pokemon.setType(pokemonDTO.getType());
            pokemonManager.update(pokemon);
            return Response.accepted().build();
        }catch(Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePokemon(@PathParam(value = "id") int id) {
        try {
            pokemonManager.delete(id);
            return Response.accepted().build();
        } catch(Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    public Pokemon fromPokemonDTO(PokemonDTO pokemonDTO){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.getName());
        pokemon.setType(pokemonDTO.getType());
        return pokemon;
    }


    public GetPokemonDTO toPokemonDTO(Pokemon pokemon) {
        GetPokemonDTO pokemonDTO = new GetPokemonDTO();
        pokemonDTO.setId(pokemon.getId());
        pokemonDTO.setName(pokemon.getName());
        pokemonDTO.setType(pokemon.getType());
        return pokemonDTO;
    }
}
