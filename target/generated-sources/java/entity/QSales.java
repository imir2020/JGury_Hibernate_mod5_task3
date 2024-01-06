package entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSales is a Querydsl query type for Sales
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSales extends EntityPathBase<Sales> {

    private static final long serialVersionUID = -355699935L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSales sales = new QSales("sales");

    public final NumberPath<Long> count = createNumber("count", Long.class);

    public final DatePath<java.time.LocalDate> dateSales = createDate("dateSales", java.time.LocalDate.class);

    protected QEmployees employee;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    protected QProducts product;

    public QSales(String variable) {
        this(Sales.class, forVariable(variable), INITS);
    }

    public QSales(Path<? extends Sales> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSales(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSales(PathMetadata metadata, PathInits inits) {
        this(Sales.class, metadata, inits);
    }

    public QSales(Class<? extends Sales> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.employee = inits.isInitialized("employee") ? new QEmployees(forProperty("employee"), inits.get("employee")) : null;
        this.product = inits.isInitialized("product") ? new QProducts(forProperty("product"), inits.get("product")) : null;
    }

    public QEmployees employee() {
        if (employee == null) {
            employee = new QEmployees(forProperty("employee"));
        }
        return employee;
    }

    public QProducts product() {
        if (product == null) {
            product = new QProducts(forProperty("product"));
        }
        return product;
    }

}

