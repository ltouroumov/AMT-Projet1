package ch.ltouroumov.heig.amt.project1.model.entities;

/**
 * Created by Frederic on 17.10.16.
 */
public class Pokemon {

    private int id;
    private String name;
    private String type;

    public Pokemon(){ }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
}
