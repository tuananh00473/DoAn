package $package$;

import java.util.List;
import $entity_package$.$entity$;

/**
 * User: Nguyen Tuan Anh
 * Company: QSoftVietNam
 * Dev: JAVA
 */

public interface $entity$DAO {

	public $entity$ findById($type_of_key$ id);

	public void insert ($entity$ entity);

	public void update ($entity$ entity);

	public void delete($entity$ entity);

	public List<$entity$> getAll();
}
