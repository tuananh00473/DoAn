package $package$;

import $service_interface_package$.$entity$Service;
import $dao_interface_package$.$entity$DAO;
import $entity_package$.$entity$;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: Nguyen Tuan Anh
 * Company: QSoftVietNam
 * Dev: JAVA
 */

@Component("$entity_lowwerCase$Service")
public class $entity$ServiceImpl implements $entity$Service
{
    @Autowired
    $entity$DAO $entity_lowwerCase$DAO;

    @Override
    public $entity$ findById($type_of_key$ id)
    {
        return $entity_lowwerCase$DAO.findById(id);
    }

    @Override
    public void save($entity$ entity)
    {
        if(entity.getId()!=null)
        {
            $entity_lowwerCase$DAO.insert(entity);
        }
        $entity_lowwerCase$DAO.update(entity);
    }

    @Override
    public void remove($entity$ entity)
    {
        $entity_lowwerCase$DAO.delete(entity);
    }

    @Override
    public List<$entity$> getAll()
    {
        return $entity_lowwerCase$DAO.getAll();
    }
}