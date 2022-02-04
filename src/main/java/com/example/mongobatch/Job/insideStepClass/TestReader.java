package com.example.mongobatch.Job.insideStepClass;

import com.example.mongobatch.object.TestObject;
import com.example.mongobatch.repository.TestObjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Slf4j
@Configuration
public class TestReader implements ItemReader<List<TestObject>> {

    @Autowired
    TestObjectRepository testObjectRepository;

    private final boolean ExecuteStepOk;
    private boolean readerOperate;
    private boolean isExecuteStep;

    public TestReader(boolean ExecuteStepOk) {
        this.ExecuteStepOk = ExecuteStepOk;
        this.isExecuteStep = ExecuteStepOk;
    }

    @Override
    public List<TestObject> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(isExecuteStep == ExecuteStepOk) readerOperate = true;
        log.info("========================================================");
        log.info("read() 시작점. 아래는 readerOperate 값");
        log.info(String.valueOf(ExecuteStepOk));
        log.info(String.valueOf(isExecuteStep));
        log.info(String.valueOf(readerOperate));
        log.info("========================================================");
        if (readerOperate) {
            log.info("----------------------------------------------------");
            log.info("'readerOperate = true' 가 되어 값을 읽어옴");
            log.info("----------------------------------------------------");
            isExecuteStep = false;
            readerOperate = false;
            return testObjectRepository.findAll();
        } else {
            log.info("----------------------------------------------------");
            log.info("'readerOperate = false' 가 되어 더이상 값을 읽지 않음 ");
            log.info("----------------------------------------------------");
            return null;
        }
    }
}
