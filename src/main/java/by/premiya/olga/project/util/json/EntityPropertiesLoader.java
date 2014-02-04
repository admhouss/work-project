package by.premiya.olga.project.util.json;

import by.premiya.olga.project.constants.BasicConstant;
import by.premiya.olga.project.constants.accumulator.Polarity;
import by.premiya.olga.project.constants.producers.AccumulatorsProducer;
import by.premiya.olga.project.constants.producers.WheelsProducer;
import by.premiya.olga.project.constants.wheel.*;
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
    PropertiesJSON accumulatorsProperties = new PropertiesJSON();

    public EntityPropertiesLoader() {
        initWheels();
        initAccumulators();
    }

    private void initWheels() {
        wheelsProperties.setLabels(Arrays.<PairJSON<String, String>>asList(new PairJSON<>("model","Модель"),
                new PairJSON<>("price","Цена"),
                new PairJSON<>("loadIndexes","Индекс загрузки"),
                new PairJSON<>("plyRating","Слойность"),
                new PairJSON<>("outerDiameter","Внешний диаметр"),
                new PairJSON<>("sectionWidth","Ширина профиля"),
                new PairJSON<>("weight","Масса"),
                new PairJSON<>("maxLoad","Макс. нагрузка"),
                new PairJSON<>("maxPressure","Макс. давление"),
                new PairJSON<>("gateType","Тип вентиля")));
        Map<String, PairJSON<String,List<PairJSON<String,String>>>> enums = new HashMap<>();

        enums.put("producer",new PairJSON<>("Производитель", getEnumValuesToString(WheelsProducer.values())));
        enums.put("treadPattern",new PairJSON<>("Рисунок протектора", getEnumValuesToString(TreadPattern.values())));
        enums.put("speedIndex",new PairJSON<>("Макс. скорость", getEnumValuesToString(SpeedIndex.values())));
        enums.put("typeOfConstruction",new PairJSON<>("Тип конструкции", getEnumValuesToString(TypeOfConstruction.values())));
        enums.put("carcassAndBeltConstruction",new PairJSON<>("Констр. каркаса и брекера", getEnumValuesToString(CarcassAndBeltConstruction.values())));
        enums.put("version",new PairJSON<>("Исполнение", getEnumValuesToString(Version.values())));
        wheelsProperties.setEnums(enums);
    }

    private void initAccumulators() {
        accumulatorsProperties.setLabels(Arrays.<PairJSON<String, String>>asList(new PairJSON<>("model","Модель"),
                new PairJSON<>("price","Цена"),
                new PairJSON<>("voltage","Напряжение"),
                new PairJSON<>("capacity","Ёмкость"),
                new PairJSON<>("coldСranking","coldСranking"),
                new PairJSON<>("length","Длина"),
                new PairJSON<>("width","Ширина"),
                new PairJSON<>("height","Высота")));
        Map<String, PairJSON<String,List<PairJSON<String,String>>>> enums = new HashMap<>();

        enums.put("producer",new PairJSON<>("Производитель", getEnumValuesToString(AccumulatorsProducer.values())));
        enums.put("polarity",new PairJSON<>("Полярность", getEnumValuesToString(Polarity.values())));
        accumulatorsProperties.setEnums(enums);
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
            case "wheel":
            case "wheels": return wheelsProperties;
            case "accumulator":
            case "accumulators": return accumulatorsProperties;
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
