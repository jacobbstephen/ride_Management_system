package ride_booking_system.repositories;

import java.io.IOException;
import java.util.List;

public interface RepositoryInterface <T> {

    void save() throws IOException; // Used to store the entities in the CSV
    void add(T entity);// Used to add the entity to the list
    T findById(int id);// Used to find the entity using the id
    void load(); // Load the entities from CSV
    List<T> getAll();// Get the list of the entities
    int size();// Return the size of the list containing the entities
} 
