package entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSuppliers is a Querydsl query type for Suppliers
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSuppliers extends EntityPathBase<Suppliers> {

    private static final long serialVersionUID = -17803332L;

    public static final QSuppliers suppliers = new QSuppliers("suppliers");

    public final StringPath address = createString("address");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public QSuppliers(String variable) {
        super(Suppliers.class, forVariable(variable));
    }

    public QSuppliers(Path<? extends Suppliers> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSuppliers(PathMetadata metadata) {
        super(Suppliers.class, metadata);
    }

}

