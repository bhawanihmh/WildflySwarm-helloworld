package com.swarm.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
/**
 * 
 * @author bhawani.singh
 *
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("In HelloServlet doGet()");

		HystrixRequestContext context = HystrixRequestContext.initializeContext();
		try {
			ConfigurationManager.getConfigInstance().setProperty("primarySecondary.usePrimary", true);
			String successMessage = new HelloWorldCommand("Hello World").execute();
			resp.getWriter().write(successMessage);
			System.out.println("\n\n@@@@@@@@@@@@@@  HelloServlet.doGet()  successMessage = " + successMessage + "\n\n");
		} finally {
			context.shutdown();
			ConfigurationManager.getConfigInstance().clear();
		}

		// Please ignore below commented code
		
		
		// resp.getWriter().write("Hello World !!!");

		/*
		 * HystrixRequestContext context =
		 * HystrixRequestContext.initializeContext(); HystrixCommandMetrics
		 * creditCardMetrics = HystrixCommandMetrics
		 * .getInstance(HystrixCommandKey.Factory.asKey(HelloWorldCommand.class.
		 * getSimpleName()));
		 * ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.default.circuitBreaker.forceOpen", false);
		 * ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.threadpool.default.coreSize", 8);
		 * ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.default.metrics.rollingPercentile.numBuckets", 60);
		 * ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.threadpool.default.queueSizeRejectionThreshold", 5);
		 * ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.circuitBreaker.sleepWindowInMilliseconds",
		 * 5000); ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.execution.isolation.strategy",
		 * "THREAD"); ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.execution.isolation.thread.timeoutInMilliseconds",
		 * 20000); ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.execution.isolation.semaphore.maxConcurrentRequests",
		 * 10); ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.circuitBreaker.errorThresholdPercentage",
		 * 50); ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.circuitBreaker.requestVolumeThreshold",
		 * 20); ConfigurationManager.getConfigInstance().setProperty(
		 * "hystrix.command.HelloWorldCommand.metrics.rollingStats.timeInMilliseconds",
		 * 10000);
		 */
		// ConfigurationManager.getConfigInstance().setProperty("hystrix.command.CreditCardCommand.execution.isolation.thread.timeoutInMilliseconds",
		// 3000);
		// ConfigurationManager.getConfigInstance().setProperty("hystrix.command.GetUserAccountCommand.execution.isolation.thread.timeoutInMilliseconds",
		// 20000);
		// set the rolling percentile more granular so we see data change every
		// second rather than every 10 seconds as is the default

		// long startTime = System.currentTimeMillis();

		// System.out.println("\n\n@@@@@@@@@@@@ Before HystrixCommand
		// currentTimeMillis = " + startTime);
		// String successMessage = new HelloWorldCommand("Hello
		// World").execute();
		// System.out.println("\n\n@@@@@@@@@@@@ After HystrixCommand
		// currentTimeMillis = " + (System.currentTimeMillis()- startTime));
		// assertThat(successMessage, is("Hello World"));

		// System.out.println("\n\n@@@@@@@@@@@@@@ HelloServlet.doGet()
		// successMessage = " + successMessage + "\n\n");

		/*
		 * HelloWorldObservableCommand helloWorldCommand = new
		 * HelloWorldObservableCommand("World");
		 * logger.info("Completed executing HelloWorld Command");
		 * Observable<String> obs = helloWorldCommand.observe();
		 */

		// context.shutdown();

	}
}
