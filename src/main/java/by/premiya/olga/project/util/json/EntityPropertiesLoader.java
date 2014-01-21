package by.premiya.olga.project.util.json;

import by.premiya.olga.project.entity.constants.BasicConstant;
import by.premiya.olga.project.entity.constants.wheel.CarcassAndBeltConstruction;
import by.premiya.olga.project.entity.constants.wheel.SpeedIndex;
import by.premiya.olga.project.entity.constants.wheel.TypeOfConstruction;
import by.premiya.olga.project.entity.constants.wheel.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

/**
 * @author Vlad Abramov
 */
public class EntityPropertiesLoader implements InitializingBean {
    private Logger logger = LoggerFactory.getLogger(EntityPropertiesLoader.class);

    PropertiesJSON wheelsProperties = new PropertiesJSON();

    public EntityPropertiesLoader() {
        initWheels();
    }

    private void initWheels() {
        wheelsProperties.setLabels(Arrays.asList("Название",
                "Цена",
                "Индекс загрузки",
                "Слойность",
                "Внешний диаметр",
                "Ширина профиля",
                "Макс. нагрузка",
                "Макс. давление",
                "Gate type"));
        Map<String, List<String>> enums = new HashMap<>();

        enums.put("Макс. скорость", getEnumValuesToString(SpeedIndex.values()));
        enums.put("Тип конструкции", getEnumValuesToString(TypeOfConstruction.values()));
        enums.put("CarcassAndBeltConstruction", getEnumValuesToString(CarcassAndBeltConstruction.values()));
        enums.put("Исполнение", getEnumValuesToString(Version.values()));
        wheelsProperties.setEnums(enums);
    }

    private List<String> getEnumValuesToString(Enum[] values) {
        List<String> strings = new LinkedList<>();
        for (Enum val : values) {
            strings.add(((BasicConstant)val).getString());
        }
        return strings;
    }

    public PropertiesJSON getProperties(String entityName) {
        switch (entityName) {
            case "wheels": return wheelsProperties;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("No such entity \'" + entityName + "\'");
        }
        throw new NoSuchElementException("No such entity \'" + entityName + "\'");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
