package ptr.tick.util;

import ptr.tick.model.Activity;
import ptr.tick.model.DataRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Parser of file located at server_input.txt tick data
 * Does parsing the file , reads line by line , collect the server data records into
 * DataRecord objects. Groups data records into Activity objects.
 * Activity list of objects can be used for data reporting
 *
 * */
public class RecordProcessor {

    public void process(String sourceFilePath, String destFilePath){
        List<Activity> activityList = new ArrayList<>();
        // TODO remove or use
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();

        if(sourceFilePath == null || "".equals(sourceFilePath)){
            throw new RuntimeException(" input source file path is empty ");
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
            String str;
            String [] record, timeValue;
            DataRecord currentRecord;
            int code;
            LocalTime fromTime;
            final Stack<DataRecord> stack = new Stack<>();
            while((str = reader.readLine()) != null ){
                record = str.split(" ");
                code = Integer.valueOf(record[0]);
                timeValue = record[1].split(":");
                fromTime = LocalTime.of(Integer.valueOf(timeValue[0]), Integer.valueOf(timeValue[1]), Integer.valueOf(timeValue[2]));
                boolean isServerDown = (code == 400 || code == 500);
                currentRecord = new DataRecord(code, fromTime, isServerDown);
                if(stack.empty()){
                    stack.push(currentRecord);
                } else {
                    DataRecord prevRecord = stack.peek();
                    if(prevRecord.isDown() == currentRecord.isDown()){
                        System.out.println(" server is in SAME state running : " + currentRecord.isDown());
                        if(!prevRecord.equals(currentRecord)){
                            stack.push(currentRecord);
                        }
                    } else {
                        DataRecord firstRecord = stack.firstElement();
                        DataRecord lastRecord = stack.lastElement();
                        System.out.println(" server state CHANGED to running : " + currentRecord.isDown());
                        long durationOfMinutes = firstRecord.getStartTime().until(currentRecord.getStartTime(), ChronoUnit.MINUTES);
                        Activity activity = new Activity(lastRecord.isDown(), durationOfMinutes);
                        activityList.add(activity);
                        stack.clear();
                        stack.push(currentRecord);
                        // TODO impl processing last element stored in stack
                    }
                }
            }
        } catch (IOException iex) {
            iex.printStackTrace();
        }
        System.out.println(" activities : " + activityList);
    }

}
