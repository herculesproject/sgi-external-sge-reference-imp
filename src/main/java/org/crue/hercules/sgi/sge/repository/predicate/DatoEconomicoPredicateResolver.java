package org.crue.hercules.sgi.sge.repository.predicate;

import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.crue.hercules.sgi.framework.rsql.SgiRSQLPredicateResolver;
import org.crue.hercules.sgi.sge.config.SgiConfigProperties;
import org.crue.hercules.sgi.sge.enums.EjecucionEconomicaTipoOperacionEnum;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.crue.hercules.sgi.sge.model.DatoEconomico_;
import org.crue.hercules.sgi.sge.util.PredicateResolverUtil;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import io.github.perplexhub.rsql.RSQLOperators;

public class DatoEconomicoPredicateResolver implements SgiRSQLPredicateResolver<DatoEconomico> {

  private enum Property {
    PROYECTO_ID("proyectoId"),
    TIPO_OPERACION("tipoOperacion"),
    ANUALIDAD("anualidad"),
    REDUCIDA("reducida"),
    FECHA_DEVENGO("fechaDevengo"),
    FECHA_CONTABILIZACION("fechaContabilizacion"),
    FECHA_PAGO("fechaPago"),
    FECHA_FACTURA("fechaFactura");

    private final String code;

    Property(String code) {
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

  private final SgiConfigProperties sgiConfigProperties;

  private static DatoEconomicoPredicateResolver instance;

  private DatoEconomicoPredicateResolver(SgiConfigProperties sgiConfigProperties) {
    this.sgiConfigProperties = sgiConfigProperties;
  }

  public static DatoEconomicoPredicateResolver getInstance(SgiConfigProperties sgiConfigProperties) {
    if (instance == null) {
      instance = new DatoEconomicoPredicateResolver(sgiConfigProperties);
    }
    return instance;
  }

  @Override
  public boolean isManaged(ComparisonNode node) {
    return Property.fromCode(node.getSelector()) != null;
  }

  @Override
  public Predicate toPredicate(ComparisonNode node, Root<DatoEconomico> root, CriteriaQuery<?> query,
      CriteriaBuilder cb) {

    Property property = Property.fromCode(node.getSelector());

    if (Property.PROYECTO_ID.equals(property)) {
      return buildByProyectoId(node, root, cb);
    } else if (Property.TIPO_OPERACION.equals(property)) {
      return buildByTipoOperacion(node, root, cb);
    } else if (Property.ANUALIDAD.equals(property)) {
      return buildByAnualidad(node, root, cb);
    } else if (Property.REDUCIDA.equals(property)) {
      return buildByReducida(node, root, cb);
    } else if (Property.FECHA_DEVENGO.equals(property)) {
      return buildByFechaDevengo(node, root, cb);
    } else if (Property.FECHA_CONTABILIZACION.equals(property)) {
      return buildByFechaContabilizacion(node, root, cb);
    } else if (Property.FECHA_PAGO.equals(property) || Property.FECHA_FACTURA.equals(property)) {
      return buildByFecha(node, root, cb);
    }

    return null;
  }

  private Predicate buildByProyectoId(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.EQUAL);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String proyectoId = node.getArguments().get(0);
    return cb.equal(root.get(DatoEconomico_.proyectoId), proyectoId);
  }

  private Predicate buildByTipoOperacion(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.EQUAL);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    EjecucionEconomicaTipoOperacionEnum tipoOperacionEC = EjecucionEconomicaTipoOperacionEnum
        .valueOf(node.getArguments().get(0));
    return cb.equal(root.get(DatoEconomico_.tipoOperacion), tipoOperacionEC);
  }

  private Predicate buildByAnualidad(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.IN);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, node.getArguments().size());

    return root.get(DatoEconomico_.anualidad).in(node.getArguments());
  }

  private Predicate buildByReducida(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    return cb.conjunction();
  }

  private Predicate buildByFechaDevengo(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.GREATER_THAN_OR_EQUAL,
        RSQLOperators.LESS_THAN_OR_EQUAL);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String fechaDevengoArgument = node.getArguments().get(0);
    Instant fechaDevengo = LocalDate.parse(fechaDevengoArgument).atStartOfDay(
        sgiConfigProperties.getTimeZone().toZoneId()).toInstant();

    if (node.getOperator().equals(RSQLOperators.GREATER_THAN_OR_EQUAL)) {
      return cb.greaterThanOrEqualTo(root.get(DatoEconomico_.fechaDevengo), fechaDevengo);
    } else {
      return cb.lessThanOrEqualTo(root.get(DatoEconomico_.fechaDevengo), fechaDevengo);
    }
  }

  private Predicate buildByFecha(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    PredicateResolverUtil.validateOperatorIsSupported(node, RSQLOperators.GREATER_THAN_OR_EQUAL,
        RSQLOperators.LESS_THAN_OR_EQUAL);
    PredicateResolverUtil.validateOperatorArgumentNumber(node, 1);

    String fechaArgument = node.getArguments().get(0);
    Instant fecha = LocalDate.parse(fechaArgument).atStartOfDay(
        sgiConfigProperties.getTimeZone().toZoneId()).toInstant();

    if (node.getOperator().equals(RSQLOperators.GREATER_THAN_OR_EQUAL)) {
      return cb.greaterThanOrEqualTo(root.get(DatoEconomico_.fecha), fecha);
    } else {
      return cb.lessThanOrEqualTo(root.get(DatoEconomico_.fecha), fecha);
    }
  }

  private Predicate buildByFechaContabilizacion(ComparisonNode node, Root<DatoEconomico> root, CriteriaBuilder cb) {
    return cb.conjunction();
  }
}
