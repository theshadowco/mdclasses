package com.github._1c_syntax.mdclasses.mdoreader;

import com.github._1c_syntax.mdclasses.mdo.MDOConfiguration;
import com.github._1c_syntax.mdclasses.mdo.MDOSynonym;
import com.github._1c_syntax.mdclasses.mdo.MDObjectBase;
import com.github._1c_syntax.mdclasses.metadata.additional.ConfigurationSource;
import com.github._1c_syntax.mdclasses.metadata.additional.MDOType;
import com.github._1c_syntax.mdclasses.utils.MDOPathUtils;
import lombok.experimental.UtilityClass;

import java.nio.file.Path;
import java.util.Optional;

@UtilityClass
public class MDFactory {
  public Optional<MDObjectBase> readMDOConfiguration(ConfigurationSource configurationSource, Path rootPath) {
    return MDOPathUtils.getMDOPath(configurationSource, rootPath,
      MDOType.CONFIGURATION,
      MDOType.CONFIGURATION.getName())
      .flatMap((Path mdoPath) -> readMDObject(configurationSource, MDOType.CONFIGURATION, mdoPath));
  }

  /**
   * Читает объект по его файлу описания, а также его дочерние при наличии
   *
   * @param configurationSource - формат исходных файлов
   * @param type                - тип читаемого объекта
   * @param mdoPath             - путь к файлу описания объекта
   * @return - прочитанный объект
   */
  public Optional<MDObjectBase> readMDObject(ConfigurationSource configurationSource,
                                             MDOType type,
                                             Path mdoPath) {
    Optional<MDObjectBase> mdo = readMDObjectFromFile(configurationSource, type, mdoPath);
//    mdo.ifPresent((MDObjectBase mdoValue) -> {
//      // проставляем mdo ссылку для объекта
//      if (mdoValue.getMdoReference() == null) {
//        mdoValue.setMdoReference(new MDOReference(mdoValue));
//      }
//      // проставляем mdo ссылку для дочерних объектов
//      if (mdoValue instanceof MDObjectComplex) {
//        computeMdoReference((MDObjectComplex) mdoValue);
//      }
//      // проставляем mdo ссылку для операций
//      if (mdoValue instanceof WebService) {
//        ((WebService) mdoValue).getOperations().parallelStream().forEach(
//          (WEBServiceOperation child) -> computeMdoReferenceForChild(mdoValue, child));
//      }
//      // проставляем mdo ссылку дочерних объектов http сервиса
//      if (mdoValue instanceof HTTPService) {
//        ((HTTPService) mdoValue).getUrlTemplates().parallelStream().forEach(
//          (HTTPServiceURLTemplate child) -> {
//            computeMdoReferenceForChild(mdoValue, child);
//            child.getHttpServiceMethods().parallelStream().forEach(method ->
//              computeMdoReferenceForChild(child, method));
//          });
//      }
//      // загружаем дочерние подсистемы для каждой подсистемы
//      if (mdoValue instanceof Subsystem) {
//        computeSubsystemChildren(configurationSource, (Subsystem) mdoValue, mdoPath);
//      }
//      // заполняем модули объекта
//      if (mdoValue instanceof MDObjectBSL) {
//        computeMdoModules(configurationSource, (MDObjectBSL) mdoValue, mdoPath);
//      }
//      // загрузка всех объектов конфигурации
//      if (mdoValue instanceof MDOConfiguration) {
//        MDOPathUtils.getRootPathByConfigurationMDO(configurationSource, mdoPath)
//          .ifPresent((Path rootPath) -> {
//            computeAllMDObject((MDOConfiguration) mdoValue, configurationSource, rootPath);
//            setDefaultConfigurationLanguage((MDOConfiguration) mdoValue);
//            setIncludedSubsystems((MDOConfiguration) mdoValue);
//          });
//      }
//
//      if (mdoValue instanceof MDObjectComplex) {
//        var mdObjectComplex = (MDObjectComplex) mdoValue;
//        var parentPath = mdoPath.getParent().toString();
//        mdObjectComplex.getForms().parallelStream().forEach(form -> {
//          var formDataPath = MDOPathUtils.getFormDataPath(configurationSource, mdoValue, parentPath,
//            form.getName());
//          readFormData(configurationSource, formDataPath).ifPresent(form::setData);
//
//          var pathToForm = MDOPathUtils.getPathToForm(configurationSource, parentPath,
//            mdoValue.getName(), form.getName());
//          form.setPath(pathToForm);
//        });
//
//        // template
//        mdObjectComplex.getTemplates().forEach(template -> {
//          var pathToTemplate = MDOPathUtils.getPathToTemplate(configurationSource, mdoValue,
//            parentPath, mdoValue.getName(), template.getName());
//          template.setPath(pathToTemplate);
//          var templateData = readTemplateData(configurationSource, template.getTemplateType(),
//            pathToTemplate);
//          template.setTemplateData(templateData);
//        });
//      }
//
//      if (mdoValue.getType() == MDOType.COMMON_FORM) {
//        var formDataPath = MDOPathUtils.getFormDataPath(configurationSource, mdoValue,
//          mdoPath.getParent().toString(), mdoValue.getName());
//        readFormData(configurationSource, formDataPath).ifPresent(((MDOForm) mdoValue)::setData);
//      }
//
//      if (mdoValue.getType() == MDOType.COMMON_TEMPLATE) {
//        var template = (CommonTemplate) mdoValue;
//        var pathToTemplate = MDOPathUtils.getPathToTemplate(configurationSource, template,
//          mdoPath.getParent().toString(), mdoValue.getName(), template.getName());
//        template.setPath(pathToTemplate);
//        var templateData = readTemplateData(configurationSource, template.getTemplateType(),
//          pathToTemplate);
//        template.setTemplateData(templateData);
//      }
//
//      // загрузка данных роли
//      if (mdoValue.getType() == MDOType.ROLE) {
//        var roleDataPath = MDOPathUtils.getRoleDataPath(configurationSource,
//          mdoPath.getParent().toString(), mdoValue.getName());
//        var roleDataOptional = readRoleData(roleDataPath);
//        roleDataOptional.ifPresent(((Role) mdoValue)::setRoleData);
//      }
//    });
//
    return mdo;
  }

  public Optional<MDObjectBase> readMDObjectFromFile(ConfigurationSource configurationSource,
                                                     MDOType type, Path mdoPath) {
    var mdoFile = mdoPath.toFile();
    if (!mdoFile.exists()) {
      return Optional.empty();
    }

    var reader = new MDOReader();
    reader.registerClass(MDOConfiguration.class);
    reader.registerClass(MDOSynonym.class);
    Optional<MDObjectBase> mdo;
    mdo = Optional.ofNullable((MDObjectBase) reader.read(mdoFile));

    mdo.ifPresent(mdObjectBase -> mdObjectBase.setPath(mdoPath));

    return mdo;
  }
}
