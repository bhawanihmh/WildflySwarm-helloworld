package com.swarm.servlet;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.swarm.util.HelloUtil;
/**
 * 
 * @author bhawani.singh
 *
 */
public class HelloWorldCommand extends HystrixCommand<String> {

	
	private final String name;
	long startTime = 0;
	 
    public HelloWorldCommand(String name) {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("SystemX"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("HelloWorldCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("HelloWorldCommand"))
                .andCommandPropertiesDefaults(
                        // we default to a 500ms timeout for primary
                        HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(500)));
        
        
        this.name = name;
    }
 
    @Override
    protected String run() throws Exception {
        System.out.println("HelloWorldCommand Invoked");

        startTime = System.currentTimeMillis();
		
		System.out.println("\n\n@@@@@@@@@@@@ run()  Before m2 currentTimeMillis = " + startTime);
		
        m2();
        
        System.out.println("\n\n@@@@@@@@@@@@ run()  After m2 currentTimeMillis = " + (System.currentTimeMillis()- startTime));
        
        return name;
    }
    
    @Override
    protected String getFallback() {
        System.out.println("About to fallback");
        System.out.println("\n\n@@@@@@@@@@@@ getFallback()  After m2 currentTimeMillis = " + (System.currentTimeMillis()- startTime));
        return "Falling back";
    }
    
    
    public void m1() throws Exception {
		System.out.println("HelloWorldCommand.m1()");
		throw new Exception("Failure");
	}
	public void m2() throws Exception {
		System.out.println("HelloWorldCommand.m2()");
		new HelloUtil().generateTimeout();
	}
	
}
