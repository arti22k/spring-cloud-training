package pl.training.cloud.organization.types;

import org.hibernate.annotations.TypeDef;
import pl.training.cloud.organization.entity.StatusType;

import javax.persistence.MappedSuperclass;

/**
 * Created by Artur.Kawalec on 2017-08-03.
 */
@MappedSuperclass
@TypeDef(defaultForType = StatusType.class, typeClass = SeverityLevelType.class)
public class SeverityLevelType extends EnumWithNumericValueType<StatusType> {

    @Override
    public Class<StatusType> returnedClass() {
        return StatusType.class;
    }
}
