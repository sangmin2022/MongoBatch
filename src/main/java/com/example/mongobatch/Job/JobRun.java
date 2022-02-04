package com.example.mongobatch.Job;

import com.example.mongobatch.Job.insideStepClass.TestProcessor;
import com.example.mongobatch.Job.insideStepClass.TestReader;
import com.example.mongobatch.Job.insideStepClass.TestWriter;
import com.example.mongobatch.utils.UniqueRunIdIncrementer;
import com.example.mongobatch.object.SaveObject;
import com.example.mongobatch.object.TestObject;
import com.example.mongobatch.repository.SaveObjectRepository;
import com.example.mongobatch.repository.TestObjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // Constructor DI를 위한 lombok 어노테이션
@EnableBatchProcessing // spring batch 기능 활성화
//@EnableMongoRepositories(basePackages = "com.mongo.repository")
@Configuration
public class JobRun {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public TestObjectRepository testObjectRepository;

    @Autowired
    public SaveObjectRepository saveObjectRepository;

    @Bean
    public boolean ExecuteStepOk() {
        return true;
    }

    @Bean("rpwTestJob")
    public Job rpwTestJob() {
        return jobBuilderFactory.get("rpwTestJob")
                .start(rpwTestStep(ExecuteStepOk()))
                .incrementer(new UniqueRunIdIncrementer())
                .build();
    }

    // Reader에서 데이터를 하나 읽어옴
    // 읽어온 데이터를 Processor에서 가공
    // 가공된 데이터들을 별도의 공간에 모은 뒤, Chunk 단위만큼 쌓이게 되면 Writer에 전달하고 Writer는 일괄 저장
    // :: Reader와 Processor에서는 1건씩 다뤄지고, Writer에선 Chunk 단위로 처리
    // JobScope : Bean의 생성 시점이 스프링 애플리케이션이 실행되는 시점이 아닌 @JobScope, @StepScope가 명시된 메서드가 실행될 때까지 지연
    @Bean
    @JobScope
    public Step rpwTestStep(boolean ExecuteStepOk) {
        log.info("read/process/write step start :)");
        return stepBuilderFactory.get("rpwTestStep")
                .<List<TestObject>, SaveObject>chunk(1)
                .reader(testReader(ExecuteStepOk))
                .processor(testProcessor())
                .writer(testWriter())
                .build();
    }

//    @Bean
//    @StepScope
//    public ItemReader<List<TestObject>> testReader(boolean ExecuteStepOk) {
//        return new ItemReader<>() {
//            private boolean isExecuteStep = ExecuteStepOk;
//            private boolean readerOperate;
//
//            @Override
//            public List<TestObject> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//                if(isExecuteStep == ExecuteStepOk) readerOperate = true;
//                log.info("========================================================");
//                log.info("read() 시작점. 아래는 readerOperate 값");
//                log.info(String.valueOf(readerOperate));
//                log.info("========================================================");
//                if (readerOperate) {
//                    log.info("----------------------------------------------------");
//                    log.info("'readerOperate = true' 가 되어 값을 읽어옴");
//                    log.info("----------------------------------------------------");
//                    isExecuteStep = false;
//                    readerOperate = false;
//                    return testObjectRepository.findAll();
//                } else {
//                    log.info("----------------------------------------------------");
//                    log.info("'readerOperate = false' 가 되어 더이상 값을 읽지 않음 ");
//                    log.info("----------------------------------------------------");
//                    return null;
//                }
//            }
//        };
//    }
//
//    @Bean
//    public ItemProcessor<List<TestObject>, SaveObject> testProcessor() {
//        return items -> {
//                SaveObject saveObject = new SaveObject();
//                saveObject.setInfo(items.toString());
//                saveObject.setCount(items.size());
//                return saveObject;
//        };
//    }
//
//    @Bean
//    public ItemWriter<SaveObject> testWriter() {
//        return items -> {
//            log.info(String.valueOf(items));
//            for(SaveObject item : items) {
//                saveObjectRepository.save(item);
//            }
//        };
//    }

    @Bean
    @StepScope
    public TestReader testReader(boolean ExecuteStepOk) {
        return new TestReader(ExecuteStepOk);
    }

    @Bean
    public TestProcessor testProcessor() {
        return new TestProcessor();
    }

    @Bean
    public TestWriter testWriter() {
        return new TestWriter();
    }


}