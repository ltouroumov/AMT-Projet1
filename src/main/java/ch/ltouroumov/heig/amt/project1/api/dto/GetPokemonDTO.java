package ch.ltouroumov.heig.amt.project1.api.dto;


public class GetPokemonDTO extends PokemonDTO {

    private int id;

    public GetPokemonDTO(){ super(); }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }
}
