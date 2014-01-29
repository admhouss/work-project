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
        wheelsProperties.setLabels(Arrays.<PairJSON<String, String>>asList(new PairJSON<>("name","Название"),
                new PairJSON<>("price","Цена"),
                new PairJSON<>("loadIndexes","Индекс загрузки"),
                new PairJSON<>("plyRating","Слойность"),
                new PairJSON<>("outerDiameter","Внешний диаметр"),
                new PairJSON<>("sectionWidth","Ширина профиля"),
                new PairJSON<>("weight","Масса"),
                new PairJSON<>("maxLoad","Макс. нагрузка"),
                new PairJSON<>("maxPressure","Макс. давление"),
                new PairJSON<>("gateType","Gate type")));
        Map<String, PairJSON<String,List<PairJSON<String,String>>>> enums = new HashMap<>();

        enums.put("speedIndex",new PairJSON<>("Макс. скорость", getEnumValuesToString(SpeedIndex.values())));
        enums.put("typeOfConstruction",new PairJSON<>("Тип конструкции", getEnumValuesToString(TypeOfConstruction.values())));
        enums.put("carcassAndBeltConstruction",new PairJSON<>("CarcassAndBeltConstruction", getEnumValuesToString(CarcassAndBeltConstruction.values())));
        enums.put("version",new PairJSON<>("Исполнение", getEnumValuesToString(Version.values())));
        wheelsProperties.setEnums(enums);
    }

    private List<PairJSON<String,String>> getEnumValuesToString(Enum[] values) {
        List<PairJSON<String,String>> strings = new LinkedList<>();
        for (Enum val : values) {
            strings.add(new PairJSON<>(val.toString(),((BasicConstant)val).getString()));
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
