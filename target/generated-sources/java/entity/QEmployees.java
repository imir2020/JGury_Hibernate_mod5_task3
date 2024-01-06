package entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmployees is a Querydsl query type for Employees
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployees extends EntityPathBase<Employees> {

    private static final long serialVersionUID = -1652095206L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmployees employees = new QEmployees("employees");

    public final StringPath address = createString("address");

    public final DatePath<java.time.LocalDate> dateBirth = createDate("dateBirth", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastName = createString("lastName");

    public final StringPath middleName = createString("middleName");

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    protected QRanks rank;

    public QEmployees(String variable) {
        this(Employees.class, forVariable(variable), INITS);
    }

    public QEmployees(Path<? extends Employees> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmployees(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmployees(PathMetadata metadata, PathInits inits) {
        this(Employees.class, metadata, inits);
    }

    public QEmployees(Class<? extends Employees> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.rank = inits.isInitialized("rank") ? new QRanks(forProperty("rank")) : null;
    }

    public QRanks rank() {
        if (rank == null) {
            rank = new QRanks(forProperty("rank"));
        }
        return rank;
    }

}

