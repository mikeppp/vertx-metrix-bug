package mikeppp.test.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;

/**
 * Created by oje on 2016-01-04.
 */
public class DummyVerticle extends AbstractVerticle {
  String url1 = "www.cnn.com";
  String url2 = "www.google.com";
  String url3 = "www.tv.com";
  HttpClient _client1, _client2, _client3;

  @Override
  public void start(final Future<Void> startFuture) {
    try {
      HttpClientOptions httpClientOptions = new HttpClientOptions()
          .setKeepAlive(false)
          .setDefaultHost(url1)
          .setTcpNoDelay(true)
          .setVerifyHost(false)
          .setTrustAll(true)
          .setDefaultPort(80).setSsl(false);
      _client1 = vertx.createHttpClient(httpClientOptions);

      httpClientOptions = new HttpClientOptions()
          .setKeepAlive(false)
          .setDefaultHost(url2)
          .setTcpNoDelay(true)
          .setVerifyHost(false)
          .setTrustAll(true)
          .setDefaultPort(80).setSsl(false);
      _client2 = vertx.createHttpClient(httpClientOptions);

      httpClientOptions = new HttpClientOptions()
          .setKeepAlive(false)
          .setDefaultHost(url3)
          .setTcpNoDelay(true)
          .setVerifyHost(false)
          .setTrustAll(true)
          .setDefaultPort(80).setSsl(false);
      _client3 = vertx.createHttpClient(httpClientOptions);

      startFuture.complete();
    } catch (Throwable t) {
      startFuture.fail(t);
    }

  }

}
