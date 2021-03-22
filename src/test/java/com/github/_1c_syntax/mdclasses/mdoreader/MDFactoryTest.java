package com.github._1c_syntax.mdclasses.mdoreader;

import com.github._1c_syntax.mdclasses.mdo.CommonForm;
import com.github._1c_syntax.mdclasses.mdo.Form;
import com.github._1c_syntax.mdclasses.mdo.MDOForm;
import com.github._1c_syntax.mdclasses.mdo.MDObjectBase;
import com.github._1c_syntax.mdclasses.metadata.Configuration;
import com.github._1c_syntax.mdclasses.metadata.ConfigurationExtension;
import com.github._1c_syntax.mdclasses.metadata.additional.ApplicationRunMode;
import com.github._1c_syntax.mdclasses.metadata.additional.CompatibilityMode;
import com.github._1c_syntax.mdclasses.metadata.additional.ConfigurationSource;
import com.github._1c_syntax.mdclasses.metadata.additional.DataLockControlMode;
import com.github._1c_syntax.mdclasses.metadata.additional.MDOType;
import com.github._1c_syntax.mdclasses.metadata.additional.ModuleType;
import com.github._1c_syntax.mdclasses.metadata.additional.ScriptVariant;
import com.github._1c_syntax.mdclasses.metadata.additional.UseMode;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class MDFactoryTest {

  @Test
  void readConfiguration() {
    File srcPath = new File("src/test/resources/metadata/original");
    Configuration configuration = Configuration.create2(srcPath.toPath());

    assertThat(configuration).isNotInstanceOf(ConfigurationExtension.class);
    assertThat(CompatibilityMode.compareTo(
      configuration.getCompatibilityMode(), new CompatibilityMode(3, 10)))
      .isZero();
    assertThat(CompatibilityMode.compareTo(
      configuration.getConfigurationExtensionCompatibilityMode(), new CompatibilityMode(3, 10)))
      .isZero();
    assertThat(configuration.getConfigurationSource()).isEqualTo(ConfigurationSource.DESIGNER);
    assertThat(configuration.getDataLockControlMode()).isEqualTo(DataLockControlMode.AUTOMATIC);
    assertThat(configuration.getDefaultLanguage().getName()).isEqualTo("Русский");
    assertThat(configuration.getDefaultRunMode()).isEqualTo(ApplicationRunMode.MANAGED_APPLICATION);
    assertThat(configuration.getModalityUseMode()).isEqualTo(UseMode.DONT_USE);
    assertThat(configuration.getObjectAutonumerationMode()).isEqualTo("NotAutoFree");
    assertThat(configuration.getScriptVariant()).isEqualTo(ScriptVariant.RUSSIAN);
    assertThat(configuration.getSynchronousExtensionAndAddInCallUseMode()).isEqualTo(UseMode.USE);
    assertThat(configuration.getSynchronousPlatformExtensionAndAddInCallUseMode()).isEqualTo(UseMode.DONT_USE);

    assertThat(configuration.isUseManagedFormInOrdinaryApplication()).isTrue();
    assertThat(configuration.isUseOrdinaryFormInManagedApplication()).isFalse();

    assertThat(configuration.getModulesByType()).hasSize(17);
    assertThat(configuration.getModulesBySupport()).isEmpty();
    assertThat(configuration.getModulesByObject()).hasSize(17);
    assertThat(configuration.getModules()).hasSize(17);
    assertThat(configuration.getCommonModules()).hasSize(6);
    assertThat(configuration.getLanguages()).hasSize(3);

    assertThat(configuration.getChildren()).hasSize(110);
    checkChildCount(configuration, MDOType.CONFIGURATION, 1);
    checkChildCount(configuration, MDOType.COMMAND, 1);
    checkChildCount(configuration, MDOType.FORM, 7);
    checkChildCount(configuration, MDOType.TEMPLATE, 2);
    checkChildCount(configuration, MDOType.ATTRIBUTE, 33);
    checkChildCount(configuration, MDOType.WS_OPERATION, 2);
    checkChildCount(configuration, MDOType.HTTP_SERVICE_URL_TEMPLATE, 1);
    checkChildCount(configuration, MDOType.HTTP_SERVICE_METHOD, 2);

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
    checkChildCount(configuration, MDOType.INTERFACE, 1);
    checkChildCount(configuration, MDOType.LANGUAGE, 3);
    checkChildCount(configuration, MDOType.REPORT, 1);
    checkChildCount(configuration, MDOType.ROLE, 1);
    checkChildCount(configuration, MDOType.SCHEDULED_JOB, 1);
    checkChildCount(configuration, MDOType.SEQUENCE, 1);
    checkChildCount(configuration, MDOType.SESSION_PARAMETER, 1);
    checkChildCount(configuration, MDOType.SETTINGS_STORAGE, 1);
    checkChildCount(configuration, MDOType.STYLE_ITEM, 1);
    checkChildCount(configuration, MDOType.STYLE, 1);
    checkChildCount(configuration, MDOType.SUBSYSTEM, 1);
    checkChildCount(configuration, MDOType.TASK, 1);
    checkChildCount(configuration, MDOType.WEB_SERVICE, 1);
    checkChildCount(configuration, MDOType.WS_REFERENCE, 1);
    checkChildCount(configuration, MDOType.XDTO_PACKAGE, 1);

    assertThat(configuration.getChildrenByMdoRef()).hasSize(110);

    assertThat(configuration.getCommonModule("ГлобальныйОбщийМодуль")).isPresent();
    assertThat(configuration.getCommonModule("ГлобальныйОбщийМодуль3")).isNotPresent();

    checkFillPath(configuration.getChildren());
    checkFormData(configuration.getChildren());

    var modulesByType = configuration.getModulesByMDORef("CommonModule.ГлобальныйОбщийМодуль");
    assertThat(modulesByType).hasSize(1)
      .containsKey(ModuleType.CommonModule);

    modulesByType = configuration.getModulesByMDORef("WSReference.WSСсылка");
    assertThat(modulesByType).isEmpty();

    modulesByType = configuration.getModulesByMDORef(configuration.getCommonModule("ГлобальныйОбщийМодуль")
      .get().getMdoReference());
    assertThat(modulesByType).hasSize(1)
      .containsKey(ModuleType.CommonModule);
  }

  private void checkFillPath(Set<MDObjectBase> child) {
    var elements = child.parallelStream()
      .filter(mdObjectBase -> mdObjectBase instanceof Form || mdObjectBase instanceof CommonForm)
      .filter(mdObjectBase -> mdObjectBase.getPath() == null)
      .filter(mdObjectBase -> !mdObjectBase.getPath().toFile().exists())
      .collect(Collectors.toList());
    assertThat(elements).isEmpty();
  }

  private void checkFormData(Set<MDObjectBase> child) {
    var elements = child.parallelStream()
      .filter(mdObjectBase -> mdObjectBase instanceof Form || mdObjectBase instanceof CommonForm)
      .filter(mdObjectBase -> ((MDOForm) mdObjectBase).getData() == null)
      .collect(Collectors.toList());
    assertThat(elements).isEmpty();
  }

  private void checkChildCount(Configuration configuration, MDOType type, int count) {
    assertThat(configuration.getChildren())
      .filteredOn(mdObjectBase -> mdObjectBase.getType() == type).hasSize(count);
  }
}