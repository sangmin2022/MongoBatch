package com.example.mongobatch.Job.insideStepClass;

import com.example.mongobatch.object.SaveObject;
import com.example.mongobatch.object.TestObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class TestProcessor implements ItemProcessor<List<TestObject>, SaveObject> {

    @Override
    public SaveObject process(List<TestObject> items) throws Exception {
        SaveObject saveObject = new SaveObject();
        saveObject.setInfo(items.toString());
        saveObject.setCount(items.size());
        return saveObject;
    }
}
