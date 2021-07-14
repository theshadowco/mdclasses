/*
 * This file is a part of MDClasses.
 *
 * Copyright © 2019 - 2021
 * Tymko Oleg <olegtymko@yandex.ru>, Maximov Valery <maximovvalery@gmail.com> and contributors
 *
 * SPDX-License-Identifier: LGPL-3.0-or-later
 *
 * MDClasses is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 *
 * MDClasses is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with MDClasses.
 */
package com.github._1c_syntax.bsl.mdclasses;

import com.github._1c_syntax.bsl.types.MDOType;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class MDClassesTest {

  @Test
  void createConfiguration() {
    File srcPath = new File("src/test/resources/metadata/edt");
    var configuration = MDClasses.createConfiguration(srcPath.toPath());

    assertThat(configuration.getChildren()).hasSize(61);

//   assertThat(configuration).isNotInstanceOf(ConfigurationExtension.class);
//    assertThat(configuration.getName()).isEqualTo("Конфигурация");
//    assertThat(configuration.getUuid()).isEqualTo("46c7c1d0-b04d-4295-9b04-ae3207c18d29");
//    assertThat(configuration.getConfigurationSource()).isEqualTo(ConfigurationSource.EDT);
//    assertThat(CompatibilityMode.compareTo(configuration.getCompatibilityMode(),
//      new CompatibilityMode(3, 10))).isZero();
//    assertThat(CompatibilityMode.compareTo(configuration.getConfigurationExtensionCompatibilityMode(),
//      new CompatibilityMode(3, 10))).isZero();
//    assertThat(configuration.getScriptVariant()).isEqualTo(ScriptVariant.RUSSIAN);
//    assertThat(configuration.getDefaultRunMode()).isEqualTo(ApplicationRunMode.MANAGED_APPLICATION);
//    assertThat(configuration.getDefaultLanguage().getName()).isEqualTo("Русский");
//    assertThat(configuration.getDataLockControlMode()).isEqualTo(DataLockControlMode.AUTOMATIC);
//    assertThat(configuration.getObjectAutonumerationMode()).isEqualTo("NotAutoFree");
//    assertThat(configuration.getModalityUseMode()).isEqualTo(UseMode.USE);
//    assertThat(configuration.getSynchronousExtensionAndAddInCallUseMode()).isEqualTo(UseMode.USE_WITH_WARNINGS);
//    assertThat(configuration.getSynchronousPlatformExtensionAndAddInCallUseMode()).isEqualTo(UseMode.DONT_USE);
//
//    assertThat(configuration.isUseManagedFormInOrdinaryApplication()).isTrue();
//    assertThat(configuration.isUseOrdinaryFormInManagedApplication()).isTrue();
//
//    assertThat(configuration.getCopyrights()).hasSize(2)
//      .anyMatch(copyright -> copyright.getContent().equals("Моя Программа")
//        && copyright.getLanguage().equals("ru"))
//      .anyMatch(copyright -> copyright.getContent().equals("My program")
//        && copyright.getLanguage().equals("en"));
//
//    assertThat(configuration.getBriefInformation()).hasSize(2)
//      .anyMatch(briefInfo -> briefInfo.getLanguage().equals("ru")
//        && briefInfo.getContent().equals("Краткая информация"))
//      .anyMatch(briefInfo -> briefInfo.getLanguage().equals("en")
//        && briefInfo.getContent().equals("Short info"));
//
//    assertThat(configuration.getDetailedInformation()).hasSize(2)
//      .anyMatch(briefInfo -> briefInfo.getLanguage().equals("ru")
//        && briefInfo.getContent().equals("Подробная информация"))
//      .anyMatch(briefInfo -> briefInfo.getLanguage().equals("en")
//        && briefInfo.getContent().equals("Detailed info"));
//
//    assertThat(configuration.getModulesByType()).hasSize(39);
//    assertThat(configuration.getModulesBySupport()).isEmpty();
//    assertThat(configuration.getModulesByObject()).hasSize(39);
//    assertThat(configuration.getModules()).hasSize(39);
//    assertThat(configuration.getCommonModules()).hasSize(6);
//    assertThat(configuration.getLanguages()).hasSize(3);
//    assertThat(configuration.getRoles()).hasSize(1);
//
//    assertThat(configuration.getChildren()).hasSize(106);
//    checkChildCount(configuration, MDOType.CONFIGURATION, 1);
    //checkChildCount(configuration, MDOType.COMMAND, 3);
//    checkChildCount(configuration, MDOType.FORM, 7);
//    checkChildCount(configuration, MDOType.TEMPLATE, 2);
//    checkChildCount(configuration, MDOType.ATTRIBUTE, 27);
//    checkChildCount(configuration, MDOType.WS_OPERATION, 2);
//    checkChildCount(configuration, MDOType.HTTP_SERVICE_URL_TEMPLATE, 1);
//    checkChildCount(configuration, MDOType.HTTP_SERVICE_METHOD, 2);
//
    checkChildCount(configuration, MDOType.ACCOUNTING_REGISTER, 1);
    checkChildCount(configuration, MDOType.ACCUMULATION_REGISTER, 1);
    checkChildCount(configuration, MDOType.BUSINESS_PROCESS, 1);
    checkChildCount(configuration, MDOType.CALCULATION_REGISTER, 1);
    checkChildCount(configuration, MDOType.CATALOG, 1);
    checkChildCount(configuration, MDOType.CHART_OF_ACCOUNTS, 1);
    checkChildCount(configuration, MDOType.CHART_OF_CALCULATION_TYPES, 1);
    checkChildCount(configuration, MDOType.CHART_OF_CHARACTERISTIC_TYPES, 1);
    checkChildCount(configuration, MDOType.COMMAND_GROUP, 1);
    checkChildCount(configuration, MDOType.COMMON_ATTRIBUTE, 1);
    checkChildCount(configuration, MDOType.COMMON_COMMAND, 1);
    checkChildCount(configuration, MDOType.COMMON_FORM, 1);
    checkChildCount(configuration, MDOType.COMMON_MODULE, 6);
    checkChildCount(configuration, MDOType.COMMON_PICTURE, 1);
    checkChildCount(configuration, MDOType.COMMON_TEMPLATE, 10);
    checkChildCount(configuration, MDOType.CONSTANT, 1);
    checkChildCount(configuration, MDOType.DATA_PROCESSOR, 1);
    checkChildCount(configuration, MDOType.DEFINED_TYPE, 1);
    checkChildCount(configuration, MDOType.DOCUMENT_JOURNAL, 1);
    checkChildCount(configuration, MDOType.DOCUMENT_NUMERATOR, 1);
    checkChildCount(configuration, MDOType.DOCUMENT, 1);
    checkChildCount(configuration, MDOType.ENUM, 1);
    checkChildCount(configuration, MDOType.EVENT_SUBSCRIPTION, 1);
    checkChildCount(configuration, MDOType.EXCHANGE_PLAN, 1);
    checkChildCount(configuration, MDOType.FILTER_CRITERION, 1);
    checkChildCount(configuration, MDOType.FUNCTIONAL_OPTION, 1);
    checkChildCount(configuration, MDOType.FUNCTIONAL_OPTIONS_PARAMETER, 1);
    checkChildCount(configuration, MDOType.HTTP_SERVICE, 1);
    checkChildCount(configuration, MDOType.INFORMATION_REGISTER, 2);
    checkChildCount(configuration, MDOType.LANGUAGE, 3);
    checkChildCount(configuration, MDOType.REPORT, 1);
    checkChildCount(configuration, MDOType.ROLE, 1);
    checkChildCount(configuration, MDOType.SCHEDULED_JOB, 1);
    checkChildCount(configuration, MDOType.SEQUENCE, 1);
    checkChildCount(configuration, MDOType.SESSION_PARAMETER, 1);
    checkChildCount(configuration, MDOType.SETTINGS_STORAGE, 1);
    checkChildCount(configuration, MDOType.STYLE_ITEM, 1);
    checkChildCount(configuration, MDOType.STYLE, 1);
    checkChildCount(configuration, MDOType.SUBSYSTEM, 2);
    checkChildCount(configuration, MDOType.TASK, 1);
    checkChildCount(configuration, MDOType.WEB_SERVICE, 1);
    checkChildCount(configuration, MDOType.WS_REFERENCE, 1);
    checkChildCount(configuration, MDOType.XDTO_PACKAGE, 1);
//
//    assertThat(configuration.getChildrenByMdoRef()).hasSize(105);
//
//    assertThat(configuration.getCommonModule("ГлобальныйОбщийМодуль")).isPresent();
//    assertThat(configuration.getCommonModule("НесуществующийМодуль")).isNotPresent();
//
//    checkFillPath(configuration.getChildren());
//    checkFormData(configuration.getChildren());
//
//    var modulesByType = configuration.getModulesByMDORef("Document.ДОкумент1");
//    assertThat(modulesByType).hasSize(2)
//      .containsKey(ModuleType.ManagerModule)
//      .containsKey(ModuleType.ObjectModule);
//
//    modulesByType = configuration.getModulesByMDORef("WSReference.WSСсылка");
//    assertThat(modulesByType).isEmpty();
//
//    checkOrderedCommonModules(configuration);
  }

  private void checkChildCount(MDClass configuration, MDOType type, int count) {
    assertThat(configuration.getChildren())
      .filteredOn(mdObject -> mdObject.getType() == type).hasSize(count);
  }
}