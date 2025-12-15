package org.crue.hercules.sgi.sge.repository.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLPredicateResolver;
import org.crue.hercules.sgi.sge.model.Proyecto;
import org.crue.hercules.sgi.sge.model.Proyecto_;
import org.crue.hercules.sgi.sge.util.PredicateResolverUtil;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.perplexhub.rsql.RSQLOperators;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProyectoPredicateResolver implements SgiRSQLPredicateResolver<Proyecto> {

  private static final String LIKE_WILDCARD_PERCENT = "%";

  private enum Property {
    ID("id");

    private String code;

    private Property(String code) {
      this.code = code;
    }

    public static Property fromCode(String code) {
      for (Property property : Property.values()) {
        if (property.code.equals(code)) {
          return property;
        }
      }
      return null;
    }
  }

  private static ProyectoPredicateResolver instance;

  public static ProyectoPredicateResolver getInstance() {
    if (instance == null) {
      instance = new ProyectoPredicateResolver();
    }
    return instance;
  }

  @Override
  public boolean isManaged(ComparisonNode node) {
    Property property = Property.fromCode(node.getSelector());
    return property != null;
  }

  @Override
  public Predicate toPredicate(ComparisonNode node, Root<Proyecto> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {

    if (Property.ID.equals(Property.fromCode(node.getSelector()))) {
      return buildById(node, root, criteriaBuilder);
    }

    return null;
  }

  private static Predicate buildById(ComparisonNode node, Root<Proyecto> root,
      CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.IGNORE_CASE_LIKE);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String id = LIKE_WILDCARD_PERCENT + node.getArguments().get(0) + LIKE_WILDCARD_PERCENT;

    return cb.like(root.get(Proyecto_.id).as(String.class), id);
  }

}