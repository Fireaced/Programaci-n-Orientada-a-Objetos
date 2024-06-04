package upm.repositorios.MapasRep;

import upm.repositorios.RepGenerico;

import java.util.HashMap;
import java.util.*;

public abstract class RepGenericoMap<T> implements RepGenerico<T> {

    private int id;
    private final Map<Integer, T> map;

    RepGenericoMap() {
        this.map = new HashMap<>();
        this.id = 1;
    }

    @Override
    public T create(T entity) {
        this.setId(entity, this.id);
        this.map.put(this.id, entity);
        this.id++;
        return entity;
    }

    public T update(T entity) {
        if (this.getId(entity) == null) {
            throw new IllegalArgumentException("No se puede actualizar una entidad cuando su id es null: " + entity);
        }
        this.map.put(this.getId(entity), entity);
        return entity;
    }

    @Override
    public Optional<T> read(Integer id) {
        return Optional.ofNullable(this.map.get(id));
    }

    @Override
    public void deleteById(Integer id) {
        this.map.remove(id);
    }

    @Override
    public LinkedList<T> findAll() {
        return new LinkedList<>(map.values());
    }

    protected abstract Integer getId(T entity);

    protected abstract void setId(T entity, Integer id);

}
