package entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRanks is a Querydsl query type for Ranks
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRanks extends EntityPathBase<Ranks> {

    private static final long serialVersionUID = -356621348L;

    public static final QRanks ranks = new QRanks("ranks");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath rankName = createString("rankName");

    public final NumberPath<Long> salary = createNumber("salary", Long.class);

    public QRanks(String variable) {
        super(Ranks.class, forVariable(variable));
    }

    public QRanks(Path<? extends Ranks> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRanks(PathMetadata metadata) {
        super(Ranks.class, metadata);
    }

}

