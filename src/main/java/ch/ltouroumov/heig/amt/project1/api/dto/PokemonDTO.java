package ch.ltouroumov.heig.amt.project1.api.dto;

/**
 * Created by Frederic on 17.10.16.
 */
public class PokemonDTO {

    private String name;
    private String type;

    public PokemonDTO(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
