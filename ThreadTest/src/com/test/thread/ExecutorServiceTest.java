package com.test.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

//modified by jack gao
public class ExecutorServiceTest {
	
	public static final Random rd=new Random();
	public static final BlockingQueue<String> queue=new LinkedBlockingQueue<String>();
	
    public static void main(String[] args) throws InterruptedException {  
    	rd.setSeed(System.currentTimeMillis());
        ExecutorService executorService = Executors.newFixedThreadPool(10);  
        List<Future<String>> resultList = new ArrayList<Future<String>>();  
  
        // 创建10个任务并执行  
        for (int i = 0; i < 30; i++) {  
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中  
            Future<String> future = executorService.submit(new TaskWithResult(i));  
            // 将任务执行结果存储到List中  
            resultList.add(future);  
        }  
        
         
        // 遍历任务的结果
        Thread monitor=new RcvThread();
        monitor.start();
        /*
        System.out.println("shutdown---");
        executorService.shutdown();
        Future<String> future = executorService.submit(new TaskWithResult(1000));  
        // 将任务执行结果存储到List中  
        resultList.add(future);  
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        queue.put("quit");
        System.out.println("all thread shutdown");
              */
    }  
    
}  


class RcvThread extends Thread{

	@Override
	public void run() {
		 while(true){
	        	String msg=null;
				try {
					msg = ExecutorServiceTest.queue.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	System.out.println(msg+System.currentTimeMillis());
	        	if("quit".equals(msg))
	        		return;
	        } 
		
	}
	
}



class TaskWithResult implements Callable<String> {  
	private int id;  
	  
    public TaskWithResult(int id) {  
        this.id = id;  
    }  
  
    /** 
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。 
     *  
     * @return 
     * @throws Exception 
     */  
    public String call() throws Exception {  
        System.out.println(id+":干活！！！ " + Thread.currentThread().getName()+","+System.currentTimeMillis());  
        Thread.sleep(ExecutorServiceTest.rd.nextInt(10000));     
        String msg=id + ":" + Thread.currentThread().getName();
        ExecutorServiceTest.queue.put(msg);
        return msg;
    }  
} 

