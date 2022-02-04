//package com.example.mongobatch.Job.insideStepClass;
//
//import com.example.mongobatch.object.TestObject;
//import com.example.mongobatch.repository.TestObjectRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.NonTransientResourceException;
//import org.springframework.batch.item.ParseException;
//import org.springframework.batch.item.UnexpectedInputException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.List;
//
//@Slf4j
//@Configuration
//public class ScrapperReader implements ItemReader<inputObject> {
//
////    @Autowired
////    TestObjectRepository testObjectRepository;
////
////    private final boolean ExecuteStepOk;
////    private boolean readerOperate;
////    private boolean isExecuteStep;
////
////    public ScrapperReader(boolean ExecuteStepOk) {
////        this.ExecuteStepOk = ExecuteStepOk;
////        this.isExecuteStep = ExecuteStepOk;
////    }
//
//    @Override
//    public inputObject read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
//        return null;
//    }
//}


// reader : worker에서 id를 읽어옴
// processor : 읽은 id를 이용하여 각 id당 스크랩 정보를 가져와서 저장할 DB object 형태에 맞게 가공
// writer : 가공한 정보를 해당 DB에 저장

//--------------------------------------------------------------------------------------------------------------------//

// 스프링 배치가 JobRepository에 리더의 상태를 저장해서 이전에 종료된 지점부터 리더를 다시 시작할 수 있게 하려면 reader에 추가로 ItemStreaqm 인터페이스를 구현해야 함
// https://jooy-p.tistory.com/29

// ItemStream 인터페이스는 open, update, close 같은 세 개의 메서드로 구성
//public interface ItemStream{
//
//    void open(ExecutionContext executionContext) throws ItemStreamException;
//    void update(ExecutionContext executionContext) throws ItemStreamException;
//    void close() thorws ItemStreamException;
//
//}


// ItemStream 인터페이스 예시
//    public void close() throws ItemStreamException{}


//    public void open(ExecutionContext executionContext) throws ItemStreamException{
//        if(executionContext.containsKey(getExecutionContextKey(INDEX_KEY))){
//            int index = executionContext.getInt(getExecutionContextKey(INDEX_KEY));
//
//            if(index == 50) curIndex = 51;
//            else curIndex = index;
//        } else {
//            curIndex = 0;
//        }
//    }

//    public void update(ExecutionContext executionContext) throws ItemStreamException{
//        executionContext.putInt(getExecutionContextKey(INDEX_KEY), curIndex);
//    }