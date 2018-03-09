package paperSubmission.model.DAO;

import java.util.List;

public interface DAO<E,PK> {
    E getById(PK id);
    List<E> getAll();
    PK insert(E entity);
}
