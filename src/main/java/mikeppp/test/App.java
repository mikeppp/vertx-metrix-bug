package mikeppp.test;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.metrics.MetricsOptions;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;

/**
 * Hello world!
 *
 */
public class App {
  protected static String deploymentId = "";

  public static void main(String[] args) {
    try {
      VertxOptions vOpt = new VertxOptions();
      vOpt.setMetricsOptions(new DropwizardMetricsOptions().setEnabled(true).setRegistryName("m4-metrics"));
      Vertx maniV = Vertx.vertx(vOpt);

      DeploymentOptions option = new DeploymentOptions().setInstances(8);
      maniV.deployVerticle("mikeppp.test.vertx.DummyVerticle", option, asyncResult -> {
        if (asyncResult.succeeded()) {
          deploymentId = asyncResult.result();
          System.out.println("Deployment ID is " + deploymentId);
          // You can use this section to send test messages!

        } else {
          System.out.println("Deployment failed!!: " + asyncResult.cause().toString());
          asyncResult.cause().printStackTrace();
          maniV.close();
          System.exit(-1);
        }
      });

      Runnable shutdown = () -> {
        System.out.println("Vertx shutting down...");
        maniV.undeploy(deploymentId);
        maniV.close();
      };

      Runtime.getRuntime().addShutdownHook(new Thread(shutdown));


    } catch (Throwable t) {
      System.out.println("Error: " + t.getMessage());
      t.printStackTrace();
    }
  }
}
