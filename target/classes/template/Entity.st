package $package$;

import org.apache.log4j.Logger;
import javax.persistence.*;

/**
 * User: Nguyen Tuan Anh
 * Company: QSoftVietNam
 * Dev: JAVA
 */

@Entity
@Table(name = "$tableName$")
@SequenceGenerator(name = "$tableName$_id_seq", sequenceName = "$tableName$_id_seq", allocationSize = 1)
public class $entity$ {$meta_columns :{ metaColumn |
    $if(metaColumn.flagPrimaryKey)$
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    $endif$
    @Column(name = "$metaColumn.nameColumn$")
    private $metaColumn.typeData$ $metaColumn.nameColumn$;

    public void set$metaColumn.nameColumnFirstUpper$($metaColumn.typeData$ $metaColumn.nameColumn$)
    {
        this.$metaColumn.nameColumn$ = $metaColumn.nameColumn$;
    }

    public $metaColumn.typeData$ get$metaColumn.nameColumnFirstUpper$()
    {
        return $metaColumn.nameColumn$;
    }
    }$

    //    ============= LOGGER ===============
        private static final Logger logger = Logger.getLogger($entity$.class);

        public $entity$()
        {
            logger.debug("New $entity$ is created. Type: " + this.getClass().getName());
        }
}
