package $package$;

import $dao_interface_package$.$entity$DAO;
import $entity_package$.$entity$;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * User: Nguyen Tuan Anh
 * Company: QSoftVietNam
 * Dev: JAVA
 */

@Component("$entity_lowwerCase$DAO")
@Transactional
public class $entity$DaoImpl implements $entity$DAO
{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public $entity$ findById($type_of_key$ id)
    {
        return entityManager.find($entity$.class, id);
    }

    @Override
    public void insert($entity$ entity)
    {
        entityManager.persist(entity);
    }

    @Override
    public void update($entity$ entity)
    {
        entityManager.merge(entity);
    }

    @Override
    public void delete($entity$ entity)
    {
       entityManager.remove(entity);
    }

    @Override
    public List<$entity$> getAll()
    {
        Query query = entityManager.createQuery("select e from $entity$ e");
        return query.getResultList();
    }
}
