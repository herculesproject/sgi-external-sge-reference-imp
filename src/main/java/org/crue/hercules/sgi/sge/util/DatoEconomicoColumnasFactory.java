package org.crue.hercules.sgi.sge.util;

import java.util.Arrays;
import java.util.List;

import org.crue.hercules.sgi.framework.spring.context.support.ApplicationContextSupport;
import org.crue.hercules.sgi.sge.dto.DatoEconomicoColumnaDto;

import liquibase.pro.packaged.de;

public class DatoEconomicoColumnasFactory {

  private static final String COLUMNAS_ID_1 = "1";
  private static final String COLUMNAS_ID_2 = "2";
  private static final String COLUMNAS_ID_3 = "3";
  private static final String COLUMNAS_ID_4 = "4";
  private static final String COLUMNAS_ID_5 = "5";
  private static final String COLUMNAS_ID_6 = "6";
  private static final String COLUMNAS_ID_7 = "7";
  private static final String COLUMNAS_ID_8 = "8";
  private static final String COLUMNAS_ID_9 = "9";
  private static final String COLUMNAS_ID_10 = "10";
  private static final String COLUMNAS_ID_11 = "11";
  private static final String COLUMNAS_ID_12 = "12";
  private static final String COLUMNAS_ID_13 = "13";
  private static final String COLUMNAS_ID_14 = "14";
  private static final String COLUMNAS_ID_15 = "15";
  private static final String COLUMNAS_ID_16 = "16";
  private static final String COLUMNAS_ID_17 = "17";
  private static final String COLUMNAS_ID_18 = "18";
  private static final String COLUMNAS_ID_19 = "19";
  private static final String COLUMNAS_ID_20 = "20";

  private DatoEconomicoColumnasFactory() {
  }

  public static List<DatoEconomicoColumnaDto> getColumnasEPA(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.epa.1"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.epa.2"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.epa.3"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4, ApplicationContextSupport.getMessage("datoEconomico.columnas.epa.4"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_5, ApplicationContextSupport.getMessage("datoEconomico.columnas.epa.5"),
            true));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasEPG(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.1"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.2"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.3"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.4"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_5, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.5"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_6, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.6"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_7, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.7"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_8, ApplicationContextSupport.getMessage("datoEconomico.columnas.epg.8"),
            true));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasEPI(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.epi.1"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.epi.2"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.epi.3"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4, ApplicationContextSupport.getMessage("datoEconomico.columnas.epi.4"),
            true));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasDOG(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.dog.1"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.dog.2"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.dog.3"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4, ApplicationContextSupport.getMessage("datoEconomico.columnas.dog.4"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_5, ApplicationContextSupport.getMessage("datoEconomico.columnas.dog.5"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_6, ApplicationContextSupport.getMessage("datoEconomico.columnas.dog.6"),
            true));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasDOI(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.doi.1"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.doi.2"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.doi.3"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4, ApplicationContextSupport.getMessage("datoEconomico.columnas.doi.4"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_5, ApplicationContextSupport.getMessage("datoEconomico.columnas.doi.5"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_6, ApplicationContextSupport.getMessage("datoEconomico.columnas.doi.6"),
            true));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasDOM(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.dom.1"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.dom.2"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.dom.3"),
            false));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasFJF(boolean reducido) {
    if (reducido) {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.4"),
              true));
    } else {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.5"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.4"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.6"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_7,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjf.7"),
              true));
    }
  }

  public static List<DatoEconomicoColumnaDto> getColumnasFJV(boolean reducido) {
    if (reducido) {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.4"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.5"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.6"),
              true));
    } else {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.4"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.7"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.8"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_7,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.9"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_8,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.10"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_9,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.11"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_10,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.12"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_11,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.13"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_12,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fjv.5"),
              false));
    }
  }

  public static List<DatoEconomicoColumnaDto> getColumnasFJP(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1, ApplicationContextSupport.getMessage("datoEconomico.columnas.fjp.1"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2, ApplicationContextSupport.getMessage("datoEconomico.columnas.fjp.2"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3, ApplicationContextSupport.getMessage("datoEconomico.columnas.fjp.3"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4, ApplicationContextSupport.getMessage("datoEconomico.columnas.fjp.4"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_5, ApplicationContextSupport.getMessage("datoEconomico.columnas.fjp.5"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_6, ApplicationContextSupport.getMessage("datoEconomico.columnas.fjp.6"),
            true));
  }

  public static List<DatoEconomicoColumnaDto> getColumnasFEM(boolean reducido) {
    if (reducido) {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.4"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.5"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.6"),
              true));
    } else {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.7"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.4"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.5"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_7,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.8"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_8,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.9"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_9,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.6"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_10,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.10"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_11,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.11"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_12,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.fem.12"),
              true));
    }
  }

  public static List<DatoEconomicoColumnaDto> getColumnasGAS(boolean reducido) {
    if (reducido) {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.2"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.4"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.5"),
              true));
    } else {
      return Arrays.asList(
          new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.1"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.4"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.2"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.3"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.6"),
              false),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.5"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_7,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.7"),
              true),
          new DatoEconomicoColumnaDto(COLUMNAS_ID_8,
              ApplicationContextSupport.getMessage("datoEconomico.columnas.gas.8"),
              true));
    }
  }

  public static List<DatoEconomicoColumnaDto> getColumnasING(boolean reducido) {
    return Arrays.asList(
        new DatoEconomicoColumnaDto(COLUMNAS_ID_1,
            ApplicationContextSupport.getMessage("datoEconomico.columnas.ing.1"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_2,
            ApplicationContextSupport.getMessage("datoEconomico.columnas.ing.2"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_3,
            ApplicationContextSupport.getMessage("datoEconomico.columnas.ing.3"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_4,
            ApplicationContextSupport.getMessage("datoEconomico.columnas.ing.4"),
            false),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_5,
            ApplicationContextSupport.getMessage("datoEconomico.columnas.ing.5"),
            true),
        new DatoEconomicoColumnaDto(COLUMNAS_ID_6,
            ApplicationContextSupport.getMessage("datoEconomico.columnas.ing.6"),
            true));
  }
}