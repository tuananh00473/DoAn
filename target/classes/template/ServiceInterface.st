package $package$;

import java.util.List;
import $entity_package$.$entity$;

/**
 * User: Nguyen Tuan Anh
 * Company: QSoftVietNam
 * Dev: JAVA
 */

public interface $entity$Service {

	public $entity$ findById($type_of_key$ id);

	public void save($entity$ entity);

	public void remove($entity$ entity);

	public List<$entity$> getAll();
}
