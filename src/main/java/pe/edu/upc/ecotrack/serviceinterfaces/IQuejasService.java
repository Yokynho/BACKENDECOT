package pe.edu.upc.ecotrack.serviceinterfaces;

import pe.edu.upc.ecotrack.entities.Lotes;
import pe.edu.upc.ecotrack.entities.Quejas;

import java.util.List;

public interface IQuejasService {
    public List<Quejas> list();
    public void insert(Quejas quejas);
    public Quejas listId(int id);
    public void update(Quejas quejas);
    public void delete(int id);
}