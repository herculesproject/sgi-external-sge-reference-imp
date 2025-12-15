package org.crue.hercules.sgi.sge.util;

import java.lang.reflect.Field;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sge.config.SgiConfigProperties;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoCampoDto;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;
import org.crue.hercules.sgi.sge.model.DatoEconomico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public final class DatoEconomicoUtil {

  private static final Logger log = LoggerFactory.getLogger(DatoEconomicoUtil.class);

  private static SgiConfigProperties sgiConfigProperties;

  @Autowired
  public void setSgiConfigProperties(SgiConfigProperties sgiConfigProperties) {
    DatoEconomicoUtil.sgiConfigProperties = sgiConfigProperties;
  }

  public static List<DatoEconomicoCampoDto> convertColumnasToCampos(
      Map<String, Object> columnas,
      List<DatoEconomicoColumnaDto> definicionesColumnas) {

    return definicionesColumnas.stream()
        .map(definicionColumna -> DatoEconomicoCampoDto.builder()
            .nombre(definicionColumna.getNombre())
            .valor(toString(columnas.get(definicionColumna.getId())))
            .build())
        .collect(Collectors.toList());
  }

  public static Map<String, String> parseQuery(String q) {
    Map<String, String> params = new HashMap<>();
    if (q != null && !q.isEmpty()) {
      String[] pairs = q.split(";");
      for (String pair : pairs) {
        // divide por "==", "=in="
        String[] keyValue = pair.split("==|=in=|=ge=|=le=");
        if (keyValue.length == 2) {
          String key = keyValue[0].trim();
          String value = keyValue[1].replace("\"", "").replace("(", "").replace(")", "").trim();
          params.put(key, value);
        }
      }
    }
    return params;
  }

  @SuppressWarnings("all")
  public static Comparator<DatoEconomico> getComparator(Sort sort) {
    return (o1, o2) -> {
      for (Sort.Order order : sort) {
        try {
          Field field = DatoEconomico.class.getDeclaredField(order.getProperty());
          field.setAccessible(true);

          Comparable fieldValue1 = (Comparable) field.get(o1);
          Comparable fieldValue2 = (Comparable) field.get(o2);
          int comparison = fieldValue1.compareTo(fieldValue2);
          if (comparison != 0) {
            return order.isAscending() ? comparison : -comparison;
          }
        } catch (NoSuchFieldException | IllegalAccessException | SecurityException e) {
          log.error("Error en getComparator()", e);
        }
      }
      return 0;
    };
  }

  private static String toString(Object object) {
    return object == null ? null : object.toString();
  }

  public static String formatInstantToDateString(Instant instant) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        .withZone(sgiConfigProperties.getTimeZone().toZoneId());
    return formatter.format(instant);
  }
}
